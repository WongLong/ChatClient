package com.chatnet.service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.chatnet.entity.User;

public class DeleteFriendService {
	private int deleteFriendPort = 15000;
	private String hostIP = "192.168.43.157";
	private Socket deleteSocket;
	
	public DeleteFriendService() {
		try {
			this.deleteSocket = new Socket(hostIP, deleteFriendPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public DeleteFriendService(String hostIP, int port) {
		this.hostIP = hostIP;
		this.deleteFriendPort = port;
		
		try {
			this.deleteSocket = new Socket(hostIP, deleteFriendPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteFriend(User user, String friendID) {
		String data = user.getUserID() + "," + friendID;
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(deleteSocket.getOutputStream());
			oos.writeObject(data);
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
}
