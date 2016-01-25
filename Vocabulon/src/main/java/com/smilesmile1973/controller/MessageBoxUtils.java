package com.smilesmile1973.controller;

import javax.swing.JOptionPane;

public enum MessageBoxUtils {
	INSTANCE;

	public void showInformationalMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void showErrorMessage(String message){
		JOptionPane.showMessageDialog(null, message,"Error",JOptionPane.ERROR_MESSAGE);
	}
}
