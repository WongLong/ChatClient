package com.chatnet.GUI;

import com.chatnet.GUI.node.EmptyPane;
import com.chatnet.GUI.node.MessagePane;
import com.chatnet.entity.Message;
import com.chatnet.entity.User;
import com.chatnet.service.ChatService;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChatGUI {
	private User initiator;
	private User friend;
	private VBox chatPane = new VBox();

	public ChatGUI(User initiator, User friend) {
		this.initiator = initiator;
		this.friend = friend;
	}

	public void show(ChatService service) {
		ScrollPane chatContainer = new ScrollPane();
		chatContainer.setStyle("-fx-background-insets: 0;");
		chatContainer.setPrefSize(600, 600);

		chatContainer.setContent(chatPane);
		chatContainer.setPadding(new Insets(5, 0, 5, 10));

		TextArea input = new TextArea();
		input.setStyle("-fx-background-insets: 0;");
		input.setPrefSize(600, 200);
		input.setFont(new Font(16));

		Button send = new Button("发送");
		send.setFont(new Font(15));
		HBox bottom = new HBox();
		bottom.getChildren().addAll(new EmptyPane(545, 0), send);

		VBox main = new VBox();
		main.getChildren().addAll(chatContainer, input, new EmptyPane(0, 5), bottom);

		Scene scene = new Scene(main);
		
		Platform.runLater(() -> {
			Stage stage = new Stage();
			stage.setResizable(false);
			stage.setTitle("与" + friend.getUserName() + "聊天中");
			stage.setScene(scene);
			stage.show();
		});

		send.setOnAction(e -> {
			if (input.getText().equals("")) {
				new Alert(AlertType.ERROR, "没有消息可以发送", ButtonType.OK).show();
				;
				return;
			}

			Message message = new Message(initiator, input.getText(), friend);
			showMessage(input.getText() + "：我", false);
			input.setText("");
			service.chat(message);
		});
	}

	public void showMessage(String message, boolean isLeft) {
		MessagePane label = new MessagePane(message, isLeft);

		Platform.runLater(() -> {
			this.chatPane.getChildren().add(label);
		});

	}

	public User getFriend() {
		return this.friend;
	}

}
