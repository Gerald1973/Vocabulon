package com.smilesmile1973.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.smilesmile1973.controller.TranslationUtil;
import com.smilesmile1973.event.AskEvent;
import com.smilesmile1973.event.AskEventListener;
import com.smilesmile1973.event.ScoreEvent;
import com.smilesmile1973.event.ScoreEventListener;
import com.smilesmile1973.model.Translation;
import com.smilesmile1973.model.TranslationList;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5088880938977063155L;

	private static final String TITLE = "VOCABULON - ";

	private JTextField txtFieldAnswer;
	private JLabel labelToTranslate;
	private TranslationList model;
	private JButton okButton;
	private JLabel score;

	private JTable vocabularyTable;

	public MainFrame() {
		super();
		setVisible(true);
		setTitle(TITLE);
		URL urlIcon = MainFrame.class.getResource("/images/icon.png");
		ImageIcon icon = new ImageIcon(urlIcon);
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initMenu();
		initComponents();
		bind();
		pack();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("QUIT")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else if (command.equals("OPEN")) {
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					score.setText("");
					prepareTable(fileChooser.getSelectedFile().getAbsolutePath());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private void verifyAnswer() {
		getModel().getTranslations().get(getModel().getPointer()).setAnswer(txtFieldAnswer.getText().trim());
		txtFieldAnswer.setText("");
		getModel().getTranslations().get(getModel().getPointer()).setAnswered(true);
		getModel().fireTableDataChanged();
		TranslationUtil.getInstance().calCulScore(getModel());
		TranslationUtil.getInstance().playSound(getModel().getTranslations().get(getModel().getPointer()));
		TranslationUtil.getInstance().ask(getModel(), getModel().getPointer() + 1);
	}

	private void bind() {

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				verifyAnswer();
			}
		});

		txtFieldAnswer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				verifyAnswer();
			}
		});

		TranslationUtil.getInstance().addScoreEventListener(new ScoreEventListener() {

			@Override
			public void scoreEventPerformed(ScoreEvent event) {
				score.setText(event.getScore() + "/" + event.getMaximum());
			}
		});

		TranslationUtil.getInstance().addAskEventListener(new AskEventListener() {
			@Override
			public void askEventPerformed(AskEvent event) {
				if (event.getNumber() >= getModel().getTranslations().size()) {
					labelToTranslate.setText("");
					txtFieldAnswer.setText("");
					okButton.setEnabled(false);
					txtFieldAnswer.setEnabled(false);
				} else {
					okButton.setEnabled(true);
					txtFieldAnswer.setEnabled(true);
					labelToTranslate.setText(getModel().getTranslations().get(event.getNumber()).getSource());
					txtFieldAnswer.requestFocus();
				}
			}
		});

	}

	public TranslationList getModel() {
		return model;
	}

	private void initComponents() {
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);
		vocabularyTable = new JTable();
		vocabularyTable.setDefaultRenderer(Translation.class, new TranslationRenderer());
		vocabularyTable.setDefaultEditor(Translation.class, new CellEditor());
		JScrollPane scrollPane = new JScrollPane(vocabularyTable);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 3, 3, 3);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		add(scrollPane, c);

		c.weightx = 0;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		JLabel labelScore = new JLabel("Score");
		add(labelScore, c);

		c.gridwidth = 2;
		c.gridx = 3;
		c.gridy = 1;
		score = new JLabel();
		add(score, c);

		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 8;
		labelToTranslate = new JLabel();
		add(labelToTranslate, c);

		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 8;
		txtFieldAnswer = new JTextField();
		add(txtFieldAnswer, c);

		c.weightx = 0;
		c.gridx = 8;
		c.gridy = 3;
		c.gridwidth = 2;
		okButton = new JButton("Ok");

		add(okButton, c);
	}

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.setActionCommand("QUIT");
		openMenuItem.setActionCommand("OPEN");
		menuBar.add(fileMenu);
		fileMenu.add(openMenuItem);
		fileMenu.add(quitMenuItem);
		openMenuItem.addActionListener(this);
		quitMenuItem.addActionListener(this);
		setJMenuBar(menuBar);

	}

	private void prepareTable(String fileName) throws Exception {
		TranslationList list = TranslationUtil.getInstance().fetchFromFile(fileName);
		this.setTitle(TITLE + list.getTitle());
		setModel(list);
		// list.fireTableStructureChanged();
		TranslationUtil.getInstance().ask(list, 0);
	}

	private void setModel(TranslationList model) {
		this.model = model;
		vocabularyTable.setModel(model);
	}

}
