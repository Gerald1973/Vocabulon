package com.smilesmile1973.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import com.smilesmile1973.event.AskEvent;
import com.smilesmile1973.event.AskEventListener;
import com.smilesmile1973.event.ScoreEvent;
import com.smilesmile1973.event.ScoreEventListener;
import com.smilesmile1973.model.ColumnTitle;
import com.smilesmile1973.model.Translation;
import com.smilesmile1973.model.TranslationList;

public class TranslationUtil {

	private static TranslationUtil instance = null;
	public static String DELIMITER = "|";

	private TranslationUtil() {
	}

	public static TranslationUtil getInstance() {
		if (instance == null) {
			instance = new TranslationUtil();
		}
		return instance;
	}

	public TranslationList fetchFromFile(String fileName) throws Exception {
		TranslationList result = new TranslationList();
		InputStream inputStream = new FileInputStream(fileName);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
		BufferedReader in = new BufferedReader(inputStreamReader);
		String sourceLanguage = null;
		String targetLanguage = null;
		//1st Line the title
		result.setTitle(in.readLine().trim());
		//2ndLine the languages
		StringTokenizer st = new StringTokenizer(in.readLine().trim(), DELIMITER);
		sourceLanguage = st.nextToken();
		targetLanguage = st.nextToken();
		//next the translations
		result.setColumnTitle(convertLineColumnToColumnTitle(in.readLine()));
		String line = in.readLine();
		while (line != null) {
			Translation translation = convertLineToTranslation(line);
			translation.setSourceLanguge(sourceLanguage);
			translation.setTargetLanguage(targetLanguage);
			if (swapOrNotSwap()) {
				translation.swap();
			}
			result.addTranslation(translation);
			line = in.readLine();
		}
		in.close();
		mixTranslations(result.getTranslations());
		return result;
	}

	public ColumnTitle convertLineColumnToColumnTitle(String line) {
		ColumnTitle result = new ColumnTitle();
		line = line.trim();
		StringTokenizer st = new StringTokenizer(line, DELIMITER);
		while (st.hasMoreTokens()) {
			result.addTitle(st.nextToken());
		}
		return result;
	}

	public Translation convertLineToTranslation(String line) {
		Translation result = new Translation();
		line = line.trim();
		StringTokenizer st = new StringTokenizer(line, DELIMITER);
		result.setSource(st.nextToken());
		result.setTarget(st.nextToken());
		return result;
	}

	/**
	 * If this method returns <code>true</code> then we will swap the target and
	 * the source.
	 * 
	 * @return <code>true</code> or <code>false</code>
	 */
	public boolean swapOrNotSwap() {
		boolean result = false;
		int i = (int) (2.0 * Math.random()) + 1;
		if (i == 2) {
			result = true;
		}
		return result;
	}

	public void ask(TranslationList model, int number) {
		model.setPointer(number);
		notifyAskEventListeners(new AskEvent(number));
	}

	private Vector<AskEventListener> askEventListeners = new Vector<AskEventListener>();

	private void notifyAskEventListeners(AskEvent event) {
		for (AskEventListener askEventListener : askEventListeners) {
			askEventListener.askEventPerformed(event);
		}
	}

	public void removeAskListener(AskEventListener listener) {
		askEventListeners.remove(listener);
	}

	public void addAskEventListener(AskEventListener listener) {
		if (!askEventListeners.contains(listener)) {
			askEventListeners.add(listener);
		}
	}

	public void calCulScore(TranslationList model) {
		Vector<Translation> translations = model.getTranslations();
		int maximum = translations.size();
		int score = 0;
		for (Translation translation : translations) {
			if (translation.isGoodAnswer() && translation.isGoodAnswer()) {
				score++;
			}
		}
		ScoreEvent event = new ScoreEvent(score, maximum);
		notifyScoreEventListeners(event);
	}
	
	public void playSound(Translation translation){
		if (translation.isGoodAnswer()){
			SoundUtil.getInstance().playSound(SoundUtil.SUCCEEDSOUND);
		}else{
			SoundUtil.getInstance().playSound(SoundUtil.FAILSOUND);
		}
	}

	private Vector<ScoreEventListener> scoreEventListeners = new Vector<ScoreEventListener>();

	public void addScoreEventListener(ScoreEventListener listener) {
		if (!scoreEventListeners.contains(listener)) {
			scoreEventListeners.add(listener);
		}
	}

	public void removeScoreEventListener(ScoreEventListener listener) {
		scoreEventListeners.remove(listener);
	}

	public void notifyScoreEventListeners(ScoreEvent event) {
		for (ScoreEventListener listener : scoreEventListeners) {
			listener.scoreEventPerformed(event);

		}
	}
	
	public void mixTranslations(Vector<Translation> translations){
		int numberOfTranlation = translations.size();
		int c = 0;
		Translation tmp = null;
		for (int i = 0;i < numberOfTranlation; i ++) {
			c = (int) (numberOfTranlation * Math.random());
			tmp = translations.get(c);
			translations.set(c, translations.get(i));
			translations.set(i, tmp);
		}
	}
}
