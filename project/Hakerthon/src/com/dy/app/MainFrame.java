package com.dy.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame{
	private static final int APP_WIDTH = 1000;
	private static final int APP_HEIGHT = 800;
	private String name;
	private JPanel mainPanel, topBarPanel, bottomBarPanel, itemPanel;
	private JTextField userNameField, timeField, userRatingField, itemTitleField, itemUserField;
	private JTextArea itemContentArea;
	private JButton profileBtn, regProductBtn, refreshBtn;
	private Socket soc;
	private UserVo userVo;
	private AppDao appDao;
	private JScrollPane productsScrollPane;

	
	public MainFrame(String name, Socket soc, UserVo userVo, AppDao appDao) {
		super(name);
		this.name = name;
		setSoc(soc);
		setUserVo(userVo);
		setUserDao(appDao);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setPanels();
		start();
	}
	
	
	public void setUserDao(AppDao userDao) {
		this.appDao = userDao;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public JPanel getTopBarPanel() {
		return topBarPanel;
	}

	public JPanel getBottomBarPanel() {
		return bottomBarPanel;
	}

	public JTextField getuserNameField() {
		return userNameField;
	}
	
	public void setTimeField() {
		timeField = new JTextField(15);
		timeField.setText("current time");
		timeField.setEditable(false);
	}

	public void setuserNameField() {
		userNameField = new JTextField(15);
		userNameField.setText("ID : " + userVo.getId());
		userNameField.setEditable(false);
	}
	
	public JPanel setItemPanel(ProductVo product) {
		itemPanel = new JPanel();
		itemPanel.setLayout(new BorderLayout());
		
		// NORTH
		JPanel itemTopPanel = new JPanel();
		itemTopPanel.setLayout(new FlowLayout());
		itemUserField = new JTextField(10);
		itemUserField.setText(product.getUser_id());
		itemUserField.setEditable(false);
		itemUserField.setBorder(null);
		itemTitleField = new JTextField(15);
		itemTitleField.setText(product.getName());
		itemTitleField.setEditable(false);
		itemTitleField.setBorder(null);
		JTextField registerDateField = new JTextField(10);
		registerDateField.setText(product.getReg_date().substring(0, 16));
		registerDateField.setEditable(false);
		registerDateField.setBorder(null);
		itemTopPanel.add(new JLabel("작성자 : "));
		itemTopPanel.add(itemUserField);
		itemTopPanel.add(new JLabel("제목 : "));
		itemTopPanel.add(itemTitleField);
		itemTopPanel.add(new JLabel("작성일자 : "));
		itemTopPanel.add(registerDateField);
		itemPanel.add(itemTopPanel, BorderLayout.NORTH);
		
		// CENTER
		itemContentArea = new JTextArea(product.getContent());
		itemContentArea.setEditable(false);
		JScrollPane contentScroll = new JScrollPane(itemContentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		itemPanel.add(contentScroll, BorderLayout.CENTER);
		
		itemPanel.setPreferredSize(new Dimension(900, 300));
		return itemPanel;
	}

	public void setMainPanel() throws SQLException {
		int row = appDao.getAllProductsCount();
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(row, 0));
		
		ArrayList<ProductVo> productList = appDao.getAllProducts();
		for(ProductVo p : productList) {
			mainPanel.add(setItemPanel(p));			
		}

		productsScrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		productsScrollPane.setPreferredSize(new Dimension(500, 300));
		
		add(productsScrollPane, BorderLayout.CENTER);

//		add(mainPanel, BorderLayout.CENTER);
	}
	
	public void refreshWindow() {
		dispose();
		new MainFrame(name, soc, userVo, appDao);
	}

	public void setTopBarPanel() {
		topBarPanel = new JPanel();
		topBarPanel.setLayout(new GridLayout(1, 4));
		setuserNameField();
		setTimeField();
		refreshBtn = new JButton("Refresh");
		
		refreshBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshWindow();
			}
		});
		userRatingField = new JTextField(15);
		userRatingField.setText("RATING : " + userVo.getRating());
		userRatingField.setEditable(false);
		
		topBarPanel.add(userNameField);
		topBarPanel.add(userRatingField);
		topBarPanel.add(timeField);
		topBarPanel.add(refreshBtn);
		add(topBarPanel, BorderLayout.NORTH);
	}
	
	public void signOut() {
		dispose();
		new LogInFrame(name, soc);
	}

	public void setBottomBarPanel() {
		JButton signOutBtn = new JButton("Sign Out");
		JButton deleteBtn = new JButton("DELETE ACCOUNT");
		
		bottomBarPanel = new JPanel();
		profileBtn = new JButton("Profile");
		profileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProfileFrame(userVo, appDao, soc);
			}
		});
		regProductBtn = new JButton("Sell");
		regProductBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterProductFrame(soc, userVo, appDao);
			}
		});
		signOutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signOut();
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(JOptionPane.showConfirmDialog(null, "진짜 탈퇴하시겠습니까?") != 0) {
						return;
					}
					appDao.deleteUserAccount(userVo);
					signOut();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		bottomBarPanel.add(profileBtn);
		bottomBarPanel.add(regProductBtn);
		bottomBarPanel.add(signOutBtn);
		bottomBarPanel.add(deleteBtn);
		add(bottomBarPanel, BorderLayout.SOUTH);
	}

	
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	public void setPanels() {
		try {
			setTopBarPanel();
			setMainPanel();
			setBottomBarPanel();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProduct(String str) {
		JTextArea tmpArea = new JTextArea(str);
		mainPanel.add(tmpArea);
	}
	
	public void start() {
		setVisible(true);
		Thread th = new Thread(new ClientProcessThread(this, soc));
	}
}
