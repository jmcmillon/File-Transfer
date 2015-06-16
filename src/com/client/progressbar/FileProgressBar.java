package com.client.progressbar;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class FileProgressBar extends JFrame {
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;

	public FileProgressBar() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Downloading files ...");
		progressBar = new JProgressBar();

		//construct the JFrame here
		setTitle("File Transfer");
		setSize( 310, 130 );
		setBounds(100, 100, 400, 200);
		
		panel.setPreferredSize( new Dimension( 310, 130 ) );
		getContentPane().add(panel);
		
		//set the parameters for the progress bar	
		progressBar.setBounds(102, 40, 150, 16);
		panel.add(progressBar);
		
		label.setPreferredSize( new Dimension( 280, 24 ) );
		label.setHorizontalTextPosition(JLabel.CENTER);
		panel.add(label);
		
		progressBar.setStringPainted(true);
		this.setVisible(true);
		
		//verify that the user wants to cancel downloading
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(FileProgressBar.this, 
		            "Are you sure to cancel the download?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});

	}
	
	/**
	 * Use this method to change the value of the progress bar
	 * @param n - the value to update the progress bar
	 */
	public void update(int n){
		progressBar.setValue(n);
	}

}
