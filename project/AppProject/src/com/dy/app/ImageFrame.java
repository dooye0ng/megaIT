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
import java.io.FileNotFoundException;
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
    
    /**
     * Constructor for ImagePanel
     * <p>
     * @param filePath path for image file
     * @throws IOException, FileNotFoundException 
     */
    public ImagePanel(String filePath) throws IOException, FileNotFoundException {
    	try {
    		fis = new FileInputStream(new File(filePath + ".jpg"));
    		image = ImageIO.read(fis);
    	} catch (Exception e) {
			fis = new FileInputStream(new File(filePath + ".png"));
			image = ImageIO.read(fis);
    	} finally {
    		try {
    			if(null != fis) {
    				fis.close();
    			}
    		} catch(IOException e) {
    			e.printStackTrace();
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
	
	/**
	 * Constructor for ImageFrame
	 * <p>
	 * @param appDao instance of appDao
	 * @param regdate regdate of product
	 */
	public ImageFrame(AppDao appDao, String regdate) {
		super("Image");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(APP_WIDTH, APP_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setAppDao(appDao);
		setRegdate(regdate);
		setFrame();
		setVisible(true);
	}
	/**
	 * Set frame
	 * <p>
	 * Set image name and set call setPanels()
	 */
	public void setFrame() {
		try {
			setImgName();
			setPanels();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Set image's name
	 * @throws SQLException
	 */
	public void setImgName() throws SQLException {
		this.imgName = appDao.getImgNameByRegdate(regdate);
	}
	
	/**
	 * Set instance of appDao
	 * <p>
	 * @param appDao instance of AppDao
	 */
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}
	
	/**
	 * Set register date
	 * @param regdate regdate of product
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	/**
	 * Set title panel, main panel, buttons panel and add them to frame
	 * <p>
	 * @throws SQLException
	 */
	void setPanels() throws SQLException {
		
		// set titlePanel 
		titlePanel = new JPanel();
		titleField = new JTextField(imgName);
		titlePanel.setLayout(new FlowLayout());
		titleField.setEditable(false);
		titleField.setBorder(null);
		titlePanel.add(titleField);
		add(titlePanel, BorderLayout.NORTH);
		
		// set mainPanel
		String filePath = "imgs\\" + imgName;
		try {
			imgPanel = new ImagePanel(filePath);
			add(imgPanel, BorderLayout.CENTER);
		} catch(Exception e) {
			titleField.setText("No Image");
		}
		
		
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
	
	/**
	 * Close current frame
	 */
	void closeWindow() {
		dispose();
	}
}
