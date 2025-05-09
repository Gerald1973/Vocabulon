package com.smilesmile1973.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
/**
 * This utilities class is responsible to execute the ESPeak Command Line.
 * @author marechal
 *
 */
public enum ESpeakUtils {
	INSTANCE;

	/**
	 * The name of the global properties file
	 */
	private String propertiesFileName = "vocabulon.properties";
	/**
	 * Contains the path for the espeak.exe command
	 */
	private String espeakLocation = null;

	private void init() {
		Properties prop = new Properties();
		String path = null;
		try {
			path = ESpeakUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			InputStream in = ESpeakUtils.class.getResourceAsStream("/" + propertiesFileName);
			prop.load(in);
			espeakLocation = prop.getProperty("pathespeak");
		} catch (Exception e) {
			String message = "The file \"" + propertiesFileName + "\" has to be in : \n" + path;
			message = message + "Vocal synhesizer will not be available.";
			URL url = ESpeakUtils.class.getProtectionDomain().getCodeSource().getLocation();
			MessageBoxUtils.INSTANCE.showInformationalMessage(message);
		}
	}

	public String getEspeakLocation() {
		return espeakLocation;
	}

	public void executeESpeak(String language, String message) {
		String[] cmds = new String[5];
		cmds[0] = getEspeakLocation();
		if (StringUtils.isNotEmpty(cmds[0])) {
			cmds[1] = "-a200";
			cmds[2] = "-s100";
			cmds[3] = "-v" + language;
			cmds[4] = "\"" + message + "\"";
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec(cmds);
			} catch (IOException e) {
				MessageBoxUtils.INSTANCE.showErrorMessage(e.getMessage());
			}
		}
	}

	private ESpeakUtils() {
		init();
	}
}
