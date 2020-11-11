package com.dy.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

public class ProfileFrame extends JFrame{
	private static final int APP_WIDTH = 600;
	private static final int APP_HEIGHT = 700;
	private UserVo userVo;
	private AppDao appDao;
	private JPanel userInfoPanel, contentPanel, btnsPanel;
	private JTextField nameField, ratingField, productCountField;
	private	JScrollPane productsScrollPane;
	private JButton cancelBtn;
	private Socket soc;
	private ProfileFrame window;
	
	public ProfileFrame(UserVo userVo, AppDao appDao, Socket soc) {
		this.window = this;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setSocket(soc);
		setUserDao(appDao);
		setUserVo(userVo);
		setLayout(new BorderLayout());
		setPanels();
		
		start();
	}
	
	public void setSocket(Socket soc) {
		this.soc = soc;
	}
	
	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		try {
			this.userVo = appDao.getUserInfo(userVo.getId());
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public AppDao getUserDao() {
		return appDao;
	}

	public void setUserDao(AppDao userDao) {
		this.appDao = userDao;
	}
	
	public void setBtnsPanel() {
		btnsPanel = new JPanel();
		btnsPanel.setLayout(new FlowLayout());
		
		// set buttons
		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// add buttons
		btnsPanel.add(cancelBtn);
		
		// add panel to frame
		add(btnsPanel, BorderLayout.SOUTH);
	}
	
	public void signOut() {
		window.dispose();
	}
	
	public JPanel setItemPanel(ProductVo product) {
		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(new BorderLayout());
		
		// NORTH
		JPanel itemTopPanel = new JPanel();
		itemTopPanel.setLayout(new FlowLayout());
		JTextField itemUserField = new JTextField(10);
		itemUserField.setText(product.getUser_id());
		itemUserField.setEditable(false);
		itemUserField.setBorder(null);
		JTextField itemTitleField = new JTextField(15);
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
		JTextArea itemContentArea = new JTextArea(product.getContent());
		itemContentArea.setEditable(false);
		JScrollPane contentScroll = new JScrollPane(itemContentArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		itemPanel.add(contentScroll, BorderLayout.CENTER);
		
		// SOUTH
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		JButton soldBtn, delBtn;
		soldBtn = new JButton("SOLD");
		soldBtn.setName(product.getReg_date());
		System.out.println(product.getNo());
		soldBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				try {
					String id = appDao.getIdByRegdate(btn.getName());
					appDao.deleteProductByRegdate(btn.getName());
					appDao.updateUserProducts(id);
					appDao.countUpSoldById(id);
					appDao.updateRatingById(id);
					JOptionPane.showMessageDialog(null, "판매완료!");
					refreshWindow();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		delBtn = new JButton("DELETE ITEM");
		delBtn.setName(product.getReg_date());
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				try {
					String id = appDao.getIdByRegdate(btn.getName());
					appDao.deleteProductByRegdate(btn.getName());
					appDao.updateUserProducts(id);
					JOptionPane.showMessageDialog(null, "삭제완료!");
					refreshWindow();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		southPanel.add(soldBtn);
		southPanel.add(delBtn);
		itemPanel.add(southPanel, BorderLayout.SOUTH);
		
		
		itemPanel.setPreferredSize(new Dimension(300, 200));
		return itemPanel;
	}
	
	public void refreshWindow() {
		dispose();
		new ProfileFrame(userVo, appDao, soc);
	}
	
	public void setProductsPanel() throws SQLException {
		int row = appDao.getUserProducts(userVo.getId());
		contentPanel = new JPanel();
		if(row < 5) {
			row = 5;
		}
		contentPanel.setLayout(new GridLayout(row, 0));
		
		ArrayList<ProductVo> productList = appDao.getProductsById(userVo.getId());
		for(ProductVo p : productList) {
			contentPanel.add(setItemPanel(p));
		}
		productsScrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(productsScrollPane, BorderLayout.CENTER);
	}
	
	public void setUserInfoPanel() {
		userInfoPanel = new JPanel();
		userInfoPanel.setLayout(new FlowLayout());
		// set fields
		nameField = new JTextField( userVo.getId());
		nameField.setEditable(false);
		nameField.setBorder(null);
		nameField.setFont(new Font("Arial", Font.BOLD, 15));
		ratingField = new JTextField(userVo.getRating());
		ratingField.setEditable(false);
		ratingField.setBorder(null);
		ratingField.setFont(new Font("Arial", Font.BOLD, 15));
		productCountField = new JTextField( String.valueOf(userVo.getProducts()));
		productCountField.setEditable(false);
		productCountField.setBorder(null);
		productCountField.setFont(new Font("Arial", Font.BOLD, 15));
		
		// add fields
		JLabel idLabel = new JLabel("아이디 : ");
//		idLabel.setFont(new Font("Arial", Font.BOLD, 15));
		JLabel ratingLabel = new JLabel("등급 : ");
//		ratingLabel.setFont(new Font("Arial", Font.BOLD, 15));
		JLabel countLabel = new JLabel("내 상품 : ");
//		countLabel.setFont(new Font("Arial", Font.BOLD, 15));
		userInfoPanel.add(idLabel);
		userInfoPanel.add(nameField);
		userInfoPanel.add(ratingLabel);
		userInfoPanel.add(ratingField);
		userInfoPanel.add(countLabel);
		userInfoPanel.add(productCountField);
		
		// add panel to frame
		add(userInfoPanel, BorderLayout.NORTH);
	}
	
	public void setPanels() {
		try {
			setUserInfoPanel();
			setProductsPanel();
			setBtnsPanel();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		setVisible(true);
	}
	
}
