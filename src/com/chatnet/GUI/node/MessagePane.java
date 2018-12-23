package com.chatnet.GUI.node;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessagePane extends BorderPane {
	public MessagePane(String message, boolean isLeft) {
		Label label = new Label(message);
		label.setFont(new Font(18));
		label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
		this.setPadding(new Insets(5, 5, 5, 0));
		this.setPrefWidth(600);
		
		if(isLeft) {
			this.setLeft(label);
		}else {
			this.setRight(label);
		}
	}
}
