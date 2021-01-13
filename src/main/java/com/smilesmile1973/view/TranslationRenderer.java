package com.smilesmile1973.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.smilesmile1973.model.Translation;

public class TranslationRenderer implements TableCellRenderer {
	int height = 30;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		CellPanel cellPanel = new CellPanel();
		Translation tr = (Translation) value;
		table.setRowHeight(row, height);
		cellPanel.setOpaque(true);
		switch (column) {
		case 0:
			cellPanel.setText(tr.getSource());
			cellPanel.setBackground(Color.WHITE);
			break;
		case 1:
			cellPanel.processAnswer(tr);
			break;
		case 2:
			cellPanel.setText(tr.getAnswer());
			cellPanel.setBackground(Color.WHITE);
			break;
		}
		return cellPanel;
	}
}
