package com.dy.app;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogInFrame extends JFrame {
	private static final int APP_WIDTH = 500;
	private static final int APP_HEIGHT = 600;
	private JPanel titlePanel, loginPanel;
	private JTextField titleField, idField, pwField;
	private JLabel idLabel, pwLabel;
	private JButton loginBtn, regBtn;
	private Socket soc;
	private String name;
	private AppDao userDao;
	private UserVo userVo;
	
	public LogInFrame(String name, Socket soc) {
		super(name);
		setName(name);
		setSoc(soc);
		setUserVo();
		setUserDao();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		
		setPanels();
		add(titlePanel);
		add(loginPanel);
		
		start();
	}
	
	public void setUserDao() {
		try {
			userDao = AppDao.getInstance();
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void start() {
		setVisible(true);
	}
	
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	public void setPanels() {
		titlePanel = new JPanel();
		loginPanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		loginPanel.setLayout(new FlowLayout());
		
		setTextFields();
		setBtns();
		
		titlePanel.add(titleField);
		loginPanel.add(idLabel);
		loginPanel.add(idField);
		loginPanel.add(pwLabel);
		loginPanel.add(pwField);
		loginPanel.add(loginBtn);
		loginPanel.add(regBtn);
	}
	
	public void setUserVo() {
		userVo = new UserVo();
	}
	
	public void closeWindow() {
		dispose();
		System.out.println("끄기");
		revalidate();
		repaint();
	}
	
	public void setBtns() {
		loginBtn = new JButton("Sign In");
		regBtn = new JButton("Sign Up");
		
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = idField.getText();
				String userPw = pwField.getText();
				
				try {
					if(userDao.isUser(userId, userPw)) {
						JOptionPane.showMessageDialog(null, "로그인 성공!");
						userVo = userDao.getUserInfo(userId);
						closeWindow();
						new MainFrame(name, soc, userVo, userDao);
					}
					else {
						JOptionPane.showMessageDialog(null, "로그인 실패");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		regBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterUserFrame(userDao);
			}
		});
	}
	
	public void setTextFields() {
		titleField = new JTextField("JUNGO BUY");
		titleField.setFont(new Font("Arial", Font.BOLD, 30));
		titleField.setBorder(null);
		titleField.setEditable(false);
		
		idLabel = new JLabel("ID");
		idField = new JTextField(20);
		pwLabel = new JLabel("PW");
		pwField = new JTextField(20);
		pwField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_ENTER != e.getKeyCode()) {
					return;
				}
				
				String userId = idField.getText().trim();
				String userPw = pwField.getText().trim();
				
				try {
					if(userDao.isUser(userId, userPw)) {
						JOptionPane.showMessageDialog(null, "로그인 성공!");
						userVo = userDao.getUserInfo(userId);
						closeWindow();
						new MainFrame(name, soc, userVo, userDao);
					}
					else {
						JOptionPane.showMessageDialog(null, "로그인 실패");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
//	public static void main(String[] args) {
//		new LogInFrame();
//	}
}
