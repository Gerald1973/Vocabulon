package com.smilesmile1973.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
	private static Utils instance = null;
	private String propertiesFileName = "vocabulon.properties";
	private String espeakLocation = null;

	private Utils() throws IOException {
		init();
	}

	private void init() throws IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(propertiesFileName);
		prop.load(in);
		espeakLocation = prop.getProperty("pathespeak");
	}

	public String getEspeakLocation() {
		return espeakLocation;
	}

	public static Utils getInstance() {
		if (instance == null) {
			try {
				instance = new Utils();
			} catch (IOException e) {
				System.err.println("The properties file has to be in : " + Utils.class.getClassLoader().getResource("."));
				e.printStackTrace();
			} catch (Exception e){
				System.err.println("The properties file has to be in : " + Utils.class.getClassLoader().getResource("."));
			}
		}
		return instance;
	}
}
