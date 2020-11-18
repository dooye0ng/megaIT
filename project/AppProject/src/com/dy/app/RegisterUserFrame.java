package com.dy.app;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterUserFrame extends JFrame{
	private static final String BLANK = "";
	private static final int APP_WIDTH = 400;
	private static final int APP_HEIGHT = 650;
	private static final int TITLE = 0;
	private static final int ID = 1;
	private static final int PW_1= 2;
	private static final int PW_2 = 3;
	private static final int BTN = 4;
	private static final int ROW = 5;
	private static final int COL = 1;
	private AppDao appDao;
	private JPanel[] panels = new JPanel[5];
	private JTextField[] fields = new JTextField[4];
	private JButton regBtn;
	
	/**
	 * Constructor for RegisterUserFrame with instance of appDao
	 * @param appDao instance of AppDao
	 */
	public RegisterUserFrame(AppDao appDao) {
		super("Sign in");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(ROW, COL));
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setAppDao(appDao);
		setPanels();
		
		setVisible(true);
	}
	
	/**
	 * Set instance of AppDao
	 * @param appDao instance of AppDao
	 */
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}
	
	/**
	 * Set title, id, password, password check panel and add them to frame
	 */
	public void setPanels() {
		for(int i=0; i<panels.length; ++i) {
			panels[i] = new JPanel();
			panels[i].setLayout(new FlowLayout());
		}
		
		setBtns();
		setFields();
		
		panels[TITLE].add(fields[TITLE]);
		panels[ID].add(new JLabel("ID"));
		panels[ID].add(fields[ID]);
		panels[PW_1].add(new JLabel("PW"));
		panels[PW_1].add(fields[PW_1]);
		panels[PW_2].add(new JLabel("PW CHECK"));
		panels[PW_2].add(fields[PW_2]);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panels[BTN].add(cancelBtn);
		panels[BTN].add(regBtn);
		
		for(JPanel p : panels) {
			add(p);
		}
	}
	/**
	 * Set title, id, password, password check field
	 */
	public void setFields() {
		for(int i=0; i<fields.length; ++i) {
			fields[i] = new JTextField(15);
		}
		
		fields[TITLE].setText("SIGN UP");
		fields[TITLE].setFont(new Font("Arial", Font.BOLD, 25));
		fields[TITLE].setBorder(null);
		fields[TITLE].setEditable(false);
		fields[PW_2].addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_ENTER != e.getKeyCode()) {
					return;
				}
				String id = fields[ID].getText().trim();
				String pw1 = fields[PW_1].getText().trim();
				String pw2 = fields[PW_2].getText().trim();
				registerUser(id, pw1, pw2);
			}
		});
	}
	
	/**
	 * Reset fields when get invalid input
	 */
	public void resetFields() {
		for(JTextField f : fields) {
			f.setText("");
		}
		fields[TITLE].setText("SIGN UP");
	}
	
	/**
	 * Refuse user to login with message
	 * <p>
	 * @param msg error message
	 */
	public void refuseRegister(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		resetFields();
	}
	
	/**
	 * Try to register user with id, password
	 * <p>
	 * @param id user's id
	 * @param pw1 user's password
	 * @param pw2 user's password check
	 */
	public void registerUser(String id, String pw1, String pw2) {
		if(id.equals(BLANK) || pw1.equals(BLANK) || pw2.equals(BLANK)) {
			refuseRegister("Fill in all the fields!");
			return;
		}
		
		if(!pw1.equals(pw2)) {
			refuseRegister("password does not match!");
			return;
		}
		
		try {
			appDao.registerUser(id, pw1);
			JOptionPane.showMessageDialog(null, id + " Registered!");
			dispose();
		} catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ie) {
			refuseRegister("ID already exists!");
			return;
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	/**
	 * Set register button that try to register user
	 */
	public void setBtns() {
		regBtn = new JButton("REGISTER");
		regBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = fields[ID].getText().trim();
				String pw1 = fields[PW_1].getText().trim();
				String pw2 = fields[PW_2].getText().trim();
				registerUser(id, pw1, pw2);
			}
		});
	}
}