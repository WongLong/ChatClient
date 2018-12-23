package com.chatnet.GUI.node;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WarnLabel extends javafx.scene.control.Label{
	public WarnLabel() {
		super();
		
		this.setTextFill(Color.RED);
		this.setFont(new Font(10));
	}
	
	public WarnLabel(String warning) {
		super(warning);
		
		this.setTextFill(Color.RED);
		
	}
}
