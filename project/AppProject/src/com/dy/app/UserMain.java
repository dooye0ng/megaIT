package com.dy.app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserMain {
	private static final String APP_NAME = "jungo";
	private static final String IP = "127.0.0.1";
	private static final int PORT = 50001;
	
	private Socket soc;
	/**
	 * Constructor for UserMain 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private UserMain() throws UnknownHostException, IOException {
		setSoc();
		new LogInFrame(APP_NAME, soc);
	}
	
	/**
	 * Set socket with ip, port
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void setSoc() throws UnknownHostException, IOException {
		soc = new Socket(IP, PORT);
	}
	/**
	 * Main method for client program
	 * @param args main arguments
	 */
	public static void main(String[] args) {
		try {
			new UserMain();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
