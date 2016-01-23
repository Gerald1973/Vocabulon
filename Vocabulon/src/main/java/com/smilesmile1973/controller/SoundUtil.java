package com.smilesmile1973.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundUtil {
	private static SoundUtil instance = null;
	private HashMap<String, Clip> sounds = new HashMap<String, Clip>();
	public static String FAILSOUND = "fails";
	public static String SUCCEEDSOUND = "succeed";
	
	private SoundUtil() {
		Clip fail = loadSound("/sounds/oh-no.wav");
		sounds.put(FAILSOUND, fail);
		Clip succeed = loadSound("/sounds/yeah.wav");
		sounds.put(SUCCEEDSOUND, succeed);
	}
	

	public static SoundUtil getInstance() {
		if (instance == null) {
			instance = new SoundUtil();
		}
		return instance;
	}

	private Clip loadSound(String soundFileName) {
		Clip result = null;
		try {
			URL url = SoundUtil.class.getResource(soundFileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			result = AudioSystem.getClip();
			result.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void playSound(String soundName){
		Clip clip = sounds.get(soundName);
		if (clip.isRunning()){
			clip.stop();
		}
		clip.setFramePosition(0);
		clip.start();
	}
	
	public static void main(String[] args){
		SoundUtil.getInstance().playSound(FAILSOUND);
	}
}
