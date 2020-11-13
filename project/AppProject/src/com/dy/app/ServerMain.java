package com.dy.app;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

class ServerProcessThread implements Runnable{
	private Socket soc;
	private BufferedInputStream bis;
	private ObjectInputStream ois;
	private AppDao appDao;
	
	/**
	 * Constructor for ServerProcessThread
	 * @param soc socket
	 */
	public ServerProcessThread(Socket soc) {
		setSoc(soc);
		setAppDao();
	}
	
	/**
	 * Set instance of appDao
	 */
	public void setAppDao() {
		try {
			appDao = AppDao.getInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set socket
	 * @param soc socket
	 */
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	/**
	 * Server save product's information into database when it gets Object instance of ProductVo from Client
	 */
	@Override
	public void run() {
		while(true) {					
			try {
				bis = new BufferedInputStream(soc.getInputStream());
				ois = new ObjectInputStream(bis);
				Object obj = ois.readObject();
				if(obj instanceof ProductVo) {
					appDao.registerProduct((ProductVo)obj);
				}
			} catch (SQLException e) {
				break;
			} catch (IOException e) {
				System.out.println("연결 종료");
				break;
			} catch(ClassNotFoundException e) {
				break;
			}
		}
	}
}

public class ServerMain {
	private static final int PORT = 50001;
	private ServerSocket sSoc;
	private Socket soc;
	private int clientCount;
	
	/**
	 * Server starts to listen to client and start processing thread when it gets the client
	 * @throws IOException
	 */
	private ServerMain() throws IOException {
		setsSoc();
		
		while(true){
			System.out.println("Listening...");
			soc = sSoc.accept();
			System.out.println("user_" + ++clientCount + " connected");
			Thread th = new Thread(new ServerProcessThread(soc));
			th.start();
		}
	}
	/**
	 * Set serverSocket with port number
	 * @throws IOException
	 */
	public void setsSoc() throws IOException {
		sSoc = new ServerSocket(PORT);
	}
	
	/**
	 * Main method that starts server program
	 * @param args arguments 
	 */
	public static void main(String[] args) {
		try {
			new ServerMain();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
