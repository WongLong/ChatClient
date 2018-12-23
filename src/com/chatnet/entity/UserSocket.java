package com.chatnet.entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class UserSocket {
	private User user;
	private Socket socket;
	
	public UserSocket() {
		
	}
	
	public UserSocket(User user) {
		this.user = user;
	}
	
	public UserSocket(User user, Socket socket) {
		this.user = user;
		this.socket = socket;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void speek(String massage) {
		try {
			PrintWriter write = new PrintWriter(this.getSocket().getOutputStream());
			write.write(this.getUser().getUserName() + " Speek: " + massage);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
