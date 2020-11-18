package com.dy.app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReceiveMessageFrame extends JFrame{
	private static final int APP_WIDTH = 500;
	private static final int APP_HEIGHT = 700;
	private UserVo userVo;
	private JTextArea contentArea;
	
	/**
	 * Constructor for ReceiveMessageFrame with UserVo
	 * @param userVo instance of UserVo
	 */
	public ReceiveMessageFrame(UserVo userVo) {
		super("Message Box");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setUserVo(userVo);
		setPanels();
		readMessage();
		setVisible(true);
	}
	
	/**
	 * Set instance of UserVo
	 * @param userVo instance of UserVo
	 */
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	/**
	 * Set all the panels and add them to frame
	 */
	public void setPanels() {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		JTextField titleField = new JTextField(15);
		titleField.setText("Message History");
		titleField.setBorder(null);
		titleField.setEditable(false);
		topPanel.add(titleField);
		add(topPanel, BorderLayout.NORTH);
		
		contentArea = new JTextArea();
		contentArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(contentArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();
		JButton closeBtn = new JButton("Close");
		JButton deleteBtn = new JButton("Clean History");
		
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(0 != JOptionPane.showConfirmDialog(null, "정말 내용을 모두 삭제하시겠습니까?")) {
					return;
				}
				
				cleanMessage();
				refreshWindow();
			}
		});
		
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(closeBtn);
		bottomPanel.add(deleteBtn);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Refresh window
	 */
	public void refreshWindow() {
		dispose();
		new ReceiveMessageFrame(userVo);
	}
	
	/**
	 * Close window
	 */
	public void closeWindow() {
		dispose();
	}
	
	/**
	 * Get messages and show them to user
	 */
	public void readMessage() {
		FileReader reader = null;
		BufferedReader bfr = null;
		String filePath = "msgs\\" + userVo.getId() + ".txt";
		String msg = "", tmp;
		try {
			reader = new FileReader(filePath);
			bfr = new BufferedReader(reader);
			while(null != (tmp = bfr.readLine())) {
				msg += tmp + "\n";
			}
		} catch(FileNotFoundException e) {
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != bfr) {
					bfr.close();
				}
				if(null != reader) {
					reader.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		contentArea.setText(msg);
	}
	
	/**
	 * Delete all the history for good
	 */
	public void cleanMessage() {
		FileWriter writer = null;
		String filePath = "msgs\\" + userVo.getId() + ".txt";
		try {
			writer = new FileWriter(filePath);
			writer.write("");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != writer) {
					writer.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
