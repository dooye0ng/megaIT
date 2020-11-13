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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInFrame extends JFrame {	
	private static final String APP_TITLE = "Jungo Buy";
	private static final int APP_WIDTH = 700;
	private static final int APP_HEIGHT = 350;
	private JPanel titlePanel, loginPanel;
	private JTextField titleField, idField;
	private JPasswordField pwField;
	private JLabel idLabel, pwLabel;
	private JButton loginBtn, regBtn;
	private Socket soc;
	private String name;
	private AppDao userDao;
	private UserVo userVo;
	/**
	 * Constructor for LogInFrame
	 * <p>
	 * @param name frame's name
	 * @param soc client's socket
	 */
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
		setVisible(true);
	}
	
	/**
	 * Set instance of UserDao by getInstance()
	 */
	public void setUserDao() {
		try {
			userDao = AppDao.getInstance();
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set socket
	 * @param soc Socket
	 */
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	/**
	 * Set instance of UserVo
	 */
	public void setUserVo() {
		userVo = new UserVo();
	}
	
	/**
	 * Set all Panels
	 * 
	 */
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
		
		add(titlePanel);
		add(loginPanel);
	}
	
	
	/**
	 * Close current frame
	 */
	public void closeWindow() {
		dispose();
	}
	
	
	/**
	 * Try to log into application with id and password
	 * <p>
	 * @param userId login id
	 * @param userPw login password
	 * @throws SQLException
	 */
	public void tryLogIn(String userId, String userPw) throws SQLException {
		if(!userDao.isUser(userId, userPw)) {
			JOptionPane.showMessageDialog(null, "로그인 실패");
			return;
		}
		JOptionPane.showMessageDialog(null, "로그인 성공!");
		userVo = userDao.getUserInfo(userId);
		closeWindow();
		new MainFrame(name, soc, userVo, userDao);
	}
	
	/**
	 * Set Sign in button and Sign up button
	 */
	public void setBtns() {
		loginBtn = new JButton("Sign In");
		regBtn = new JButton("Sign Up");
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId = idField.getText();
				@SuppressWarnings("deprecation")
				String userPw = pwField.getText();
				try {
					tryLogIn(userId, userPw);
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
	
	/**
	 * Set id and password field and title field
	 */
	public void setTextFields() {
		titleField = new JTextField(APP_TITLE);
		titleField.setFont(new Font("Arial", Font.BOLD, 30));
		titleField.setBorder(null);
		titleField.setEditable(false);
		
		idLabel = new JLabel("ID");
		idField = new JTextField(15);
		pwLabel = new JLabel("PW");
		pwField = new JPasswordField(15);
		pwField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_ENTER != e.getKeyCode()) {
					return;
				}

				String userId = idField.getText().trim();
				@SuppressWarnings("deprecation")
				String userPw = pwField.getText().trim();
				try {
					tryLogIn(userId, userPw);
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}
		});
	}
}
