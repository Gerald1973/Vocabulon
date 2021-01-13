package com.smilesmile1973.model;

import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class TranslationList extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2475410571751345763L;

	private ColumnTitle columnTitle;

	private Vector<TableModelListener> tableModelListeners;

	private String title;

	private Vector<Translation> translations;

	private Translation toTranslate;

	int score;

	int pointer;

	public Vector<Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(Vector<Translation> translations) {
		this.translations = translations;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public Translation getToTranslate() {
		return toTranslate;
	}

	public void setToTranslate(Translation toTranslate) {
		this.toTranslate = toTranslate;
	}

	public TranslationList() {
		tableModelListeners = new Vector<TableModelListener>();
		translations = new Vector<Translation>();
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		if (tableModelListeners.indexOf(l) == -1) {
			tableModelListeners.add(l);
		}
	}

	public void addTranslation(Translation translation) {
		translations.add(translation);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> clazz = Translation.class;
		return clazz;
	}

	@Override
	public int getColumnCount() {
		return columnTitle.getNumberOfColumns();
	}

	@Override
	public String getColumnName(int columnIndex) {
		String result = null;
		result = columnTitle.getTitle(columnIndex);
		return result;
	}

	public ColumnTitle getColumnTitle() {
		return columnTitle;
	}

	@Override
	public int getRowCount() {
		return translations.size();
	}

	public String getTitle() {
		return title;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Translation result = translations.get(rowIndex);
		return result;

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public void setColumnTitle(ColumnTitle columnTitle) {
		this.columnTitle = columnTitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
