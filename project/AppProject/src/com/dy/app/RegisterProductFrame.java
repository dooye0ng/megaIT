package com.dy.app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterProductFrame extends JFrame {
	private static final int APP_WIDTH = 800;
	private static final int APP_HEIGHT = 600;
	private Socket soc;
	private UserVo userVo;
	private ProductVo productVo;
	private AppDao appDao;
	private JPanel titlePanel, contentPanel, btnPanel;
	private JTextField titleField, priceField, imgNameField;
	private JTextArea contentArea;
	private JButton regBtn, backBtn;
	private ObjectOutputStream oos;
	private BufferedOutputStream bos;
	
	/**
	 * Constructor for RegisterProductFrame with socket, userVo, userDao
	 * @param soc socket
	 * @param userVo instance of UserVo
	 * @param userDao instance of UserDao
	 */
	public RegisterProductFrame(Socket soc, UserVo userVo, AppDao userDao) {
		super("Register Product");
		setSoc(soc);
		setAppDao(userDao);
		setUserVo(userVo);
		setProductVo();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setPanels();
		
		setVisible(true);
	}
	/**
	 * Set instance of ProductVo
	 */
	public void setProductVo() {
		this.productVo = new ProductVo();
	}
	/**
	 * Set instance of UserVo
	 * @param userVo instance of UserVo
	 */
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	/**
	 * Set instance of AppDao
	 * @param appDao instance of AppDao
	 */
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}
	/**
	 * Set socket
	 * @param soc socket
	 */
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	/**
	 * Set btnPanel containing register, cancel button
	 */
	public void setBtnPanel() {
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		regBtn = new JButton("Register");
		backBtn = new JButton("Close");
		
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		regBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = titleField.getText().trim();
				String content = contentArea.getText().trim();
				String imgName = imgNameField.getText().trim();
				int price = 0;
				
				try {
					price = Integer.parseInt(priceField.getText());
					if(name.length() < 1 || content.length() < 1) {
						JOptionPane.showMessageDialog(null, "Fill in all fields");
						return;
					}
					
					productVo.setName(name);
					productVo.setPrice(price);
					productVo.setContent(content);
					productVo.setUser_id(userVo.getId());
					productVo.setImg_name(imgName);
					
					bos = new BufferedOutputStream(soc.getOutputStream());
					oos = new ObjectOutputStream(bos);
					oos.writeObject(productVo);
					oos.flush();
					
				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Price must be number");
					return;
				}  catch(IOException ioe) {
					ioe.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Register Success!");
				closeWindow();
			}
		});
		
		btnPanel.add(backBtn);
		btnPanel.add(regBtn);
		add(btnPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Close current frame
	 */
	public void closeWindow() {
		dispose();
	}
	
	/**
	 * Set contentPanel containing contentArea
	 */
	public void setContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		contentArea = new JTextArea();
		contentPanel.add(contentArea);
		
		contentPanel.add(new JLabel("Content"), BorderLayout.NORTH);
		contentPanel.add(contentArea, BorderLayout.CENTER);
		add(contentPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Set titlePanel containing title and price field
	 */
	public void setTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titleField = new JTextField(15);
		priceField = new JTextField(15);
		titlePanel.add(new JLabel("Title : "));
		titlePanel.add(titleField);
		titlePanel.add(new JLabel("Price : "));
		titlePanel.add(priceField);
		titlePanel.add(new JLabel("Image Name : "));
		imgNameField = new JTextField(15);
		titlePanel.add(imgNameField);
		
		add(titlePanel, BorderLayout.NORTH);
	}
	
	/**
	 * Set titlePanel, content Panel, btnPanel
	 */
	public void setPanels() {
		setTitlePanel();
		setContentPanel();
		setBtnPanel();
	}
}
