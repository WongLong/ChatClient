package com.chatnet.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.chatnet.entity.User;

public class RegisterService {
	private int registerPort = 12000;
	private String hostIP = "192.168.43.157";
	private Socket registerSocket;
	
	public RegisterService() {
		try {
			this.registerSocket = new Socket(hostIP, registerPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public RegisterService(String hostIP, int registerPort) {
		this.registerPort = registerPort;
		this.hostIP = hostIP;
		
		try {
			this.registerSocket = new Socket(hostIP, registerPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public User register(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(registerSocket.getOutputStream());
			oos.writeObject(user);
			
			ObjectInputStream ois = new ObjectInputStream(registerSocket.getInputStream());
			user = (User)ois.readObject();
			
			return user;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				registerSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
