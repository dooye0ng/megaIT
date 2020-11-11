package com.dy.app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ImagePanel extends JPanel{

    private BufferedImage image;
    private FileInputStream fis;

    public ImagePanel(String filePath) {
    	System.out.println(System.getProperty("user.dir"));
    	try {
    		System.out.println(filePath + ".jpg");
    		fis = new FileInputStream(new File(filePath + ".jpg"));
    		image = ImageIO.read(fis);
    	} catch (Exception e) {
    		try {
    			System.out.println(filePath + ".png");
    			fis = new FileInputStream(new File(filePath + ".png"));
    			image = ImageIO.read(fis);
    		} catch(Exception e1) {
    			e1.printStackTrace();
    		}
    	}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        int x = (this.getWidth() - image.getWidth(null)) / 2;
        int y = (this.getHeight() - image.getHeight(null)) / 2;
        g2d.drawImage(image, x, y, null);     
    }
}

public class ImageFrame extends JFrame{
	private static final int APP_WIDTH = 600;
	private static final int APP_HEIGHT = 600;
	private JPanel titlePanel;
	private ImagePanel imgPanel;
	private JTextField titleField;
	private AppDao appDao;
	private String regdate, imgName;
	
	public ImageFrame(AppDao appDao, String regdate) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setAppDao(appDao);
		setRegdate(regdate);
		try {
			setImgName();
			setPanels();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		start();
	}
	public void setImgName() throws SQLException {
		this.imgName = appDao.getImgNameByRegdate(regdate);
	}
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}
	
	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	void setPanels() throws SQLException {
		
		// set titlePanel 
		titleField = new JTextField(15);
		titleField.setText(imgName);
		titleField.setEditable(false);
		titleField.setBorder(null);
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleField);
		add(titlePanel, BorderLayout.NORTH);
		
		// set mainPanel
		String filePath = "imgs\\" + imgName;
		imgPanel = new ImagePanel(filePath);
		add(imgPanel, BorderLayout.CENTER);
		
		// set btnsPanel
		JPanel btnsPanel = new JPanel();
		btnsPanel.setLayout(new FlowLayout());
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		btnsPanel.add(closeBtn);
		add(btnsPanel, BorderLayout.SOUTH);
	}
	
	void closeWindow() {
		dispose();
	}
	void start() {
		setVisible(true);
	}
}
