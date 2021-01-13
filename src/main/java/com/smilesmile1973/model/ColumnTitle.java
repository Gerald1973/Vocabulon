package com.smilesmile1973.model;

import java.util.Vector;

public class ColumnTitle {
	private Vector<String> titles;

	public ColumnTitle() {
		titles = new Vector<String>();
	}

	public void addTitle(String title) {
		titles.add(title);
	}

	public String getTitle(int columnIndex) {
		return titles.get(columnIndex);
	}

	public int getNumberOfColumns() {
		return titles.size();
	}
}