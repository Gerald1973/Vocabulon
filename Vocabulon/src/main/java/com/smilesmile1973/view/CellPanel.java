package com.smilesmile1973.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.smilesmile1973.controller.Utils;
import com.smilesmile1973.model.Translation;

public class CellPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4750791676895445514L;
	public JButton listenButton;
	public JLabel label;
	private String text;
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getText() {
		return text;
	}

	public CellPanel() {
		init();
		bind();
	}
	
	public void processAnswer(Translation translation) {
		if (translation.isAnswered()) {
			setText(translation.getTarget());
			if (translation.isGoodAnswer()) {
				setBackground(Color.WHITE);
			} else {
				setBackground(Color.RED);
			}
			listenButton.setVisible(true);
		} else {
			setBackground(Color.WHITE);
			setText("???");
			listenButton.setVisible(false);
		}

	}

	private void bind() {
		listenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ECOUTE : " + getText() + "Language :" + getLanguage());
				String[] cmds = new String[5];
				cmds[0] = Utils.getInstance().getEspeakLocation();
				cmds[1] = "-a200";
				cmds[2] = "-s100";
				cmds[3] = "-v"+getLanguage();
				cmds[4] = "\"" + getText() + "\"";
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec(cmds);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	private void init() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		listenButton = new JButton();
		listenButton.setIcon(new ImageIcon(CellPanel.class.getResource("/speaker.png")));
		label = new JLabel();
		label.setOpaque(true);
		//label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		setLayout(layout);
		listenButton.setPreferredSize(new Dimension(20,20));
		c.gridx=0;
		c.gridy=0;
		c.weightx=0;
		add(listenButton,c);
		c.gridx=1;
		c.gridy=0;
		c.anchor=GridBagConstraints.WEST;
		c.weightx=1;
		add(label,c);
		setEnabled(true);
	}

	public void setText(String text) {
		this.text = text;
		label.setText(text);
	}

	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		if (label != null) {
			label.setBackground(bg);
		}
	}
	
	public static void main(String args[]){
		JFrame frame = new JFrame();
		CellPanel tmp = new CellPanel();
		tmp.setText("ABCDEFGHIJKLM");
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.add(tmp);
		frame.setVisible(true);
		frame.pack();
	}
}
