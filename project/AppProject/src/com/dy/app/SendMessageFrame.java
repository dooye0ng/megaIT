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
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SendMessageFrame extends JFrame{
	private static final int APP_WIDTH = 500;
	private static final int APP_HEIGHT = 700;
	private ProductVo productVo;
	private UserVo userVo;
	
	/**
	 * Constructor for SendMessageFrame with UserVo, ProductVo
	 * @param user instance of UserVo
	 * @param product instance of ProcuctVo
	 */
	public SendMessageFrame(UserVo user, ProductVo product) {
		super("Send Message");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setProductVo(product);
		setUserVo(user);
		setPanels();
		
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
	 * Set instance of ProductVo
	 * @param productVo instance of ProductVo
	 */
	public void setProductVo(ProductVo productVo) {
		this.productVo = productVo;
	}
	
	/**
	 * Set all the panels and add them to frame
	 */
	public void setPanels() {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		JTextField titleField = new JTextField("To. " + productVo.getUser_id());
		titleField.setEditable(false);
		titleField.setBorder(null);
		topPanel.add(titleField);
		add(topPanel, BorderLayout.NORTH);
		
		JTextArea contentArea = new JTextArea();
		add(contentArea, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		JButton sendBtn, closeBtn;
		
		sendBtn = new JButton("SEND");
		sendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = contentArea.getText().trim();
				if(msg.length() < 1) {
					return;
				}
				sendMessage(productVo.getUser_id(), msg);
				JOptionPane.showMessageDialog(null, "전송 완료!");
				contentArea.setText("");
			}
		});
		
		closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		bottomPanel.add(closeBtn);
		bottomPanel.add(sendBtn);
		
		add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Send Messages to one with user's id and message(content)
	 * @param id user's id
	 * @param msg content 
	 */
	public void sendMessage(String id, String msg) {
		String filePath = "msgs\\" + id + ".txt";
		FileReader reader = null;
		BufferedReader bfr = null;
		FileWriter writer = null;
		String orgMsg = "", tmp;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time;
		
		try {
			reader = new FileReader(filePath);
			bfr = new BufferedReader(reader);
			while(null !=(tmp = bfr.readLine())) {
				orgMsg += tmp + "\n";
			}
		} catch (FileNotFoundException e) {
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try {				
			writer = new FileWriter(filePath);
			time = sdf.format(System.currentTimeMillis());
			writer.write(orgMsg + "From. " + userVo.getId() +" [" +time+ "]" +  "\n" + msg + "\n");
		} catch(IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(null != writer) {
					writer.close();
				}
				if(null != reader) {
					reader.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Close the window
	 */
	public void closeWindow() {
		dispose();
	}
}
