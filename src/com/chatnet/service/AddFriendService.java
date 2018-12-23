package com.chatnet.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.chatnet.entity.User;

public class AddFriendService {
	private int addFriendPort = 14000;
	private String hostIP = "192.168.43.157";
	private Socket addSocket;

	public AddFriendService() {
		try {
			this.addSocket = new Socket(hostIP, addFriendPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AddFriendService(String hostIP, int port) {
		this.hostIP = hostIP;
		this.addFriendPort = port;

		try {
			this.addSocket = new Socket(hostIP, addFriendPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User addFriend(User user, String friendID) {
		String data = user.getUserID() + "," + friendID;

		try {
			ObjectOutputStream oos = new ObjectOutputStream(addSocket.getOutputStream());
			oos.writeObject(data);
			
			ObjectInputStream ois = new ObjectInputStream(addSocket.getInputStream());
			Object obj = ois.readObject();
			
			return (User)obj;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
