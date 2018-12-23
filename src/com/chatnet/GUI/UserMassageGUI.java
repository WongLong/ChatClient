package com.chatnet.GUI;

import com.chatnet.entity.User;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UserMassageGUI extends VBox{
	private User user;
	
	public UserMassageGUI(User user) {
		this.user = user;
		
		Label title = new Label(user.getUserName());
		title.setPrefWidth(300);
		title.setFont(new Font(50));
		title.setPadding(new Insets(10, 0, 0, 5));
		
		Label ID = new Label(user.getUserID());
		ID.setFont(new Font(20));
		ID.setPadding(new Insets(0, 0, 10, 15));
		
		this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));
		this.getChildren().addAll(title, ID);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
