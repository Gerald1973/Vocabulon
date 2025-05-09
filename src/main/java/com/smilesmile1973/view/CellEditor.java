package com.smilesmile1973.view;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.smilesmile1973.model.Translation;

public class CellEditor extends AbstractCellEditor implements TableCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3893030560998000957L;
	private Translation translation;

	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation(Translation translation) {
		this.translation = translation;
	}

	@Override
	public Object getCellEditorValue() {
		return getTranslation();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		setTranslation((Translation) value);
		CellPanel cellPanel = new CellPanel();
		switch (column) {
		case 0:
			cellPanel.setText(getTranslation().getSource());
			cellPanel.setLanguage(getTranslation().getSourceLanguge());
			break;
		case 1:
			cellPanel.setText(getTranslation().getTarget());
			cellPanel.setLanguage(getTranslation().getTargetLanguage());
			cellPanel.processAnswer(getTranslation());
			break;
		}
		return cellPanel;
	}

}
