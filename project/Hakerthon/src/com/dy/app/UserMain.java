package com.dy.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

class ClientProcessThread implements Runnable{
	private Socket soc;
	private BufferedReader bufferedReader;
	private MainFrame frame;
	
	public ClientProcessThread(MainFrame frame, Socket soc) {
		setSoc(soc);
		setFrame(frame);
	}
	
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void run() {
		try {
			
		} catch (Exception e) {
		}
	}
}


public class UserMain {
	private static final String APP_NAME = "jungo";
	private static final String IP = "127.0.0.1";
	private static final int PORT = 50001;
	
	private Socket soc	;
	private LogInFrame frame;
	
	private UserMain() throws UnknownHostException, IOException {
		setSoc();
		startFrame();
	}
	
	public void setSoc() throws UnknownHostException, IOException {
		soc = new Socket(IP, PORT);
		System.out.println("Connected !");
	}
	
	public void startFrame() {
		frame = new LogInFrame(APP_NAME, soc);
	}
	
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
