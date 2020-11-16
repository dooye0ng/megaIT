package com.dy.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

	/**
	 * Constructor for MainFrame. Start the main application frame.
	 * <p>
	 * @param name application's name
	 * @param soc socket
	 * @param userVo user information instance of UserVo
	 * @param appDao instance of AppDao
	 */
	public MainFrame(String name, Socket soc, UserVo userVo, AppDao appDao) {
		super(name);
		setName(name);
		setSoc(soc);
		setUserVo(userVo);
		setAppDao(appDao);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setPanels();
		
		setVisible(true);
	}
	
	/**
	 * Set name for frame
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set socket
	 * @param soc socket
	 */
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	/**
	 * Set top, main, bottom panel
	 */
	public void setPanels() {
		try {
			setTopBarPanel();
			setMainPanel();
			setBottomBarPanel();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set instance of AppDao
	 * @param appDao instance of AppDao
	 */
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}
	
	/**
	 * Set instance of UserVo
	 * @param userVo instance of UserVo
	 */
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
	/**
	 * Set time field with SimpleDateFormat
	 */
	public void setTimeField() {
		timeField = new JTextField(15);
		timeField.setEditable(false);
		timeField.setBorder(null);
		timeField.setFont(new Font("Arial", Font.BOLD, 15));
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("[yyyy-MM-dd] HH:mm:ss");
					while(true) {
						String time = sdf.format(System.currentTimeMillis());
						timeField.setText(time);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	/**
	 * Set user name's field
	 */
	public void setUserNameField() {
		userNameField = new JTextField(15);
		userNameField.setText("ID : " + userVo.getId());
		userNameField.setEditable(false);
		userNameField.setBorder(null);
		userNameField.setFont(new Font("Arial", Font.BOLD, 15));
	}
	
	/**
	 * Set item panels that attaches to scroll pane
	 * <p>
	 * Set user id, rating, and current time with all the products registered in database.
	 * buttons like profile, sell, message box, sign out, delete account attached.
	 * @param product instance of ProductVo
	 * @return instance of JPanel containing all product panel
	 */
	public JPanel setItemPanel(ProductVo product) {
		itemPanel = new JPanel();
		itemPanel.setLayout(new BorderLayout());
		
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
		JButton imgBtn = new JButton("View Image");
		imgBtn.setName(product.getReg_date());
		imgBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				new ImageFrame(appDao, btn.getName());
			}
		});
		itemTopPanel.add(imgBtn);
		JButton msgBtn = new JButton("Send Message");
		msgBtn.setName(product.getUser_id());
		msgBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userVo.getId().equals(((JButton)e.getSource()).getName())) {
					JOptionPane.showMessageDialog(null, "자기 자신에게는 메시지를 보낼 수 없습니다.");
					return;
				}
				new SendMessageFrame(userVo, product);
			}
		});
		itemTopPanel.add(msgBtn);
		itemPanel.add(itemTopPanel, BorderLayout.NORTH);
		
		itemContentArea = new JTextArea(product.getContent());
		itemContentArea.setEditable(false);
		JScrollPane contentScroll = new JScrollPane(itemContentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		itemPanel.add(contentScroll, BorderLayout.CENTER);
		itemPanel.setPreferredSize(new Dimension(900, 200));
		
		return itemPanel;
	}
	
	/**
	 * Set main scroll pane to display all the products in database
	 * @throws SQLException
	 */
	public void setMainPanel() throws SQLException {
		int row = appDao.getAllProductsCount();
		mainPanel = new JPanel();
		
		if(row < 5) {
			row = 5;
		}
		mainPanel.setLayout(new GridLayout(row, 0));
		
		ArrayList<ProductVo> productList = appDao.getAllProducts();
		for(ProductVo p : productList) {
			mainPanel.add(setItemPanel(p));			
		}
		
		productsScrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(productsScrollPane, BorderLayout.CENTER);
	}
	/**
	 * Refreshes current frame
	 * @throws SQLException
	 */
	public void refreshWindow() throws SQLException {
		setUserVo(appDao.updateUserVoById(userVo.getId()));
		dispose();
		new MainFrame(name, soc, userVo, appDao);
	}
	
	/**
	 * Set top panel containing user id, user rating, and refresh button
	 */
	public void setTopBarPanel() {
		topBarPanel = new JPanel();
		topBarPanel.setLayout(new FlowLayout());
		setUserNameField();
		setTimeField();
		refreshBtn = new JButton("Refresh");
		
		refreshBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		userRatingField = new JTextField(15);
		userRatingField.setText("RATING : " + userVo.getRating());
		userRatingField.setEditable(false);
		userRatingField.setBorder(null);
		userRatingField.setFont(new Font("Arial", Font.BOLD, 15));
		
		topBarPanel.add(userNameField);
		topBarPanel.add(userRatingField);
		topBarPanel.add(timeField);
		topBarPanel.add(refreshBtn);
		add(topBarPanel, BorderLayout.NORTH);
	}
	
	/**
	 * Close current frame and go to Login frame
	 */
	public void signOut() {
		dispose();
		new LogInFrame(name, soc);
	}

	/**
	 * Set bottom Panel containing profile button, register button, and delete account button
	 */
	public void setBottomBarPanel() {
		JButton signOutBtn = new JButton("Sign Out");
		JButton deleteBtn = new JButton("DELETE ACCOUNT");
		JButton msgBtn = new JButton("Message");
		
		bottomBarPanel = new JPanel();
		profileBtn = new JButton("Profile");
		profileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ProfileFrame(name, userVo, appDao);
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
				if(JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?") != 0) {
					return;
				}
				signOut();
			}
		});
		
		deleteBtn.setBackground(Color.RED);
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까?") != 0) {
						return;
					}
					appDao.deleteUserAccount(userVo);
					signOut();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton msgBoxBtn = new JButton("Message Box");
		msgBoxBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReceiveMessageFrame(userVo);
			}
		});
		
		bottomBarPanel.add(profileBtn);
		bottomBarPanel.add(regProductBtn);
		bottomBarPanel.add(msgBoxBtn);
		bottomBarPanel.add(signOutBtn);
		bottomBarPanel.add(deleteBtn);
		add(bottomBarPanel, BorderLayout.SOUTH);
	}
}
