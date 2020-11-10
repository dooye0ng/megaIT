package com.dy.app;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

class ServerProcessThread implements Runnable{
	
	private ArrayList<Socket> socketList;
	private Socket soc;
	private BufferedInputStream bis;
	private ObjectInputStream ois;
	private AppDao appDao;
	
	public ServerProcessThread(ArrayList<Socket> socketList, Socket soc) {
		setSocketList(socketList);
		setSoc(soc);
		setAppDao();
	}
	
	public void setAppDao() {
		try {
			appDao = AppDao.getInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setSoc(Socket soc) {
		this.soc = soc;
	}
	
	public void setSocketList(ArrayList<Socket> socketList) {
		this.socketList = socketList;
	}

	@Override
	public void run() {
		while(true) {					
			try {
				bis = new BufferedInputStream(soc.getInputStream());
				ois = new ObjectInputStream(bis);
				Object obj = ois.readObject();
				if(obj instanceof ProductVo) {
					appDao.registerProduct((ProductVo)obj);
				} else if(obj instanceof UserVo) {
				}
			} catch (SQLException e) {
				e.printStackTrace();
				break;
			} catch (IOException e) {
				System.out.println("연결 종료");
				break;
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}

public class ServerMain {
	private static final int PORT = 50001;
	
	private ServerSocket sSoc;
	private Socket soc;
	private ArrayList<Socket> socketList;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private int clientCount;
	
	private ServerMain() throws IOException {
		init();
		start();
	}
	
	private void init() throws IOException {
		setsSoc();
		setSocketList();
	}
	
	private void start() throws IOException {
		
		while(true){
			System.out.println("기다리는 중 ...");
			soc = sSoc.accept();
			socketList.add(soc);
			System.out.println("user_" + ++clientCount + " 연결!");
			Thread th = new Thread(new ServerProcessThread(socketList, soc));
			th.start();
			System.out.println("user_" + clientCount + "스레드 실행!");
		}
	}
	
	public void setServerTh() {
		
	}
	public ArrayList<Socket> getSocketList() {
		return socketList;
	}
	
	public void setSocketList() {
		socketList = new ArrayList<Socket>();
	}
	
	public ServerSocket getsSoc() {
		return sSoc;
	}


	public void setsSoc() throws IOException {
		sSoc = new ServerSocket(PORT);
	}


	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setBufferedReader( ) {
	}

	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
		
	
	public static void main(String[] args) {
		try {
			new ServerMain();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
