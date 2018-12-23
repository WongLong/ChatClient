package com.chatnet.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.chatnet.entity.User;

public class LoginService {
	private int loginPort = 13000;
	private String hostIP = "192.168.43.157";
	private Socket loginSocket;

	public LoginService() {
		try {
			this.loginSocket = new Socket(hostIP, loginPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LoginService(String hostIP, int loginPort) {
		this.loginPort = loginPort;
		this.hostIP = hostIP;

		try {
			this.loginSocket = new Socket(hostIP, loginPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User login(String userID, String password) {
		User user = new User(userID);
		user.setPassword(password);

		try {
			ObjectOutputStream oos = new ObjectOutputStream(loginSocket.getOutputStream());
			oos.writeObject(user);
			
			ObjectInputStream ois = new ObjectInputStream(loginSocket.getInputStream());
			
			Object obj = ois.readObject();
			if(obj == null) {
				return null;
			}
			
			return (User)obj;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				this.loginSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
