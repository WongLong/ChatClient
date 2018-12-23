package com.chatnet.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.chatnet.GUI.ChatGUI;
import com.chatnet.GUI.UserGUI;
import com.chatnet.entity.Message;
import com.chatnet.entity.User;

public class ChatService implements Runnable {
	private int chatPort = 16000;
	private String hostIP = "192.168.43.157";
	private Socket chatSocket;
	private UserGUI userGUI;

	public ChatService(UserGUI userGUI) {
		this.userGUI = userGUI;
		try {
			this.chatSocket = new Socket(hostIP, chatPort);
			User loginedUser = userGUI.getUser();
			ObjectOutputStream oos = new ObjectOutputStream(this.chatSocket.getOutputStream());
			oos.writeObject(loginedUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ChatService(String hostIP, int chatPost) {

		this.hostIP = hostIP;
		this.chatPort = chatPost;

		try {
			this.chatSocket = new Socket(hostIP, chatPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void chat(Message message) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(chatSocket.getOutputStream());
			oos.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			Message message = null;
			while ((message = (Message) (new ObjectInputStream(chatSocket.getInputStream()).readObject())) != null) {
				List<ChatGUI> chatList = this.userGUI.getChatList();
				boolean flag = false;

				for (ChatGUI chat : chatList) {
					if (chat.getFriend().getUserID().equals(message.getInitiator().getUserID())) {
						chat.showMessage(message.getInitiator().getUserName() + "หตฃบ " + message.getMessage(), true);
						flag = true;
						break;
					}
				}

				if (!flag) {
					ChatGUI newChat = new ChatGUI(this.userGUI.getUser(), message.getInitiator());
					newChat.showMessage(message.getInitiator().getUserName() + "หตฃบ " + message.getMessage(), true);
					chatList.add(newChat);
					newChat.show(this);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
