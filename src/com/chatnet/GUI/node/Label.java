package com.chatnet.GUI.node;

import javafx.geometry.Pos;

public class Label extends javafx.scene.control.Label{
	
	public Label(String title) {
		super(title);
	
		this.setPrefWidth(100);
		this.setAlignment(Pos.CENTER);
	}
}
