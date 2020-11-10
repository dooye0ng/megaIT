package com.dy.app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegisterProductFrame extends JFrame {
	private static final int APP_WIDTH = 500;
	private static final int APP_HEIGHT = 600;
	private RegisterProductFrame window;
	private Socket soc;
	private UserVo userVo;
	private ProductVo productVo;
	private AppDao appDao;
	private JPanel titlePanel, contentPanel, btnPanel;
	private JTextField titleField, priceField;
	private JTextArea contentArea;
	private JButton regBtn, backBtn;
	private ObjectOutputStream oos;
	private BufferedOutputStream bos;
	
	public RegisterProductFrame(Socket soc, UserVo userVo, AppDao userDao) {
		setSoc(soc);
		setUserDao(userDao);
		setUserVo(userVo);
		setProductVo();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setPanels();
		this.window = this;
		
		start();
	}
	
	

	public ProductVo getProductVo() {
		return productVo;
	}



	public void setProductVo() {
		this.productVo = new ProductVo();
	}



	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public AppDao getUserDao() {
		return appDao;
	}

	public void setUserDao(AppDao userDao) {
		this.appDao = userDao;
	}

	public Socket getSoc() {
		return soc;
	}

	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	public void setBtnPanel() {
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		regBtn = new JButton("Register");
		backBtn = new JButton("Close");
		
//		back button
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
		});
		
//		register button
		regBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = titleField.getText().trim();
				String content = contentArea.getText().trim();
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
					
					bos = new BufferedOutputStream(soc.getOutputStream());
					oos = new ObjectOutputStream(bos);
					oos.writeObject(productVo);
					oos.flush();
					System.out.println("객체 전송 완료");
					
				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Price must be number");
					return;
				}  catch(IOException ioe) {
					ioe.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Register Success!");
				window.dispose();
			}
		});
		
		btnPanel.add(backBtn);
		btnPanel.add(regBtn);
		
		add(btnPanel, BorderLayout.SOUTH);
		
	}
	
	public void setContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
		contentArea = new JTextArea();
		contentPanel.add(contentArea);
		
		contentPanel.add(new JLabel("Content"), BorderLayout.NORTH);
		contentPanel.add(contentArea, BorderLayout.CENTER);
		add(contentPanel, BorderLayout.CENTER);
	}
	
	public void setTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titleField = new JTextField(15);
		priceField = new JTextField(15);
		titlePanel.add(new JLabel("Title : "));
		titlePanel.add(titleField);
		titlePanel.add(new JLabel("Price : "));
		titlePanel.add(priceField);
		
		add(titlePanel, BorderLayout.NORTH);
	}
	
	public void setPanels() {
		setTitlePanel();
		setContentPanel();
		setBtnPanel();
	}
	
	public void start() {
		setVisible(true);
	}
	
}
