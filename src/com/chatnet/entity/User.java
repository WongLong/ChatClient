package com.chatnet.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID;
	private String userName;
	private String password;
	private List<User> friends;

	public User() {
		
	}
	
	public User(String userID) {
		this.userID = userID;
	}
	
	public User(String userID, String userName) {
		this.userID = userID;
		this.userName = userName;
	}

	public User(String userID, String userName, String password) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
}
