package com.chatnet.entity;
 
public class UserFriendTable {
	private String userID;
	private String friendID;
	
	public UserFriendTable() {
		
	}
	
	public UserFriendTable(String userID, String friendID) {
		this.userID = userID;
		this.friendID = friendID;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFriendID() {
		return friendID;
	}
	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}
}
