package com.filetransfer.gui;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FileTransferDialog {

	private JFrame frmFileTransfer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileTransferDialog window = new FileTransferDialog();
					window.frmFileTransfer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileTransferDialog() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFileTransfer = new JFrame();
		frmFileTransfer.setTitle("File Transfer");
		
		JTabbedPane menu = new JTabbedPane();
		frmFileTransfer.add(menu);
		
		JPanel clientTab = new JPanel();
		Container clientContainer = new Container();
		
		
		
		
		
		JPanel serverTab = new JPanel();
		
		menu.addTab("Client", clientTab);
		menu.addTab("Server", serverTab);
		
		
		
	}

}
