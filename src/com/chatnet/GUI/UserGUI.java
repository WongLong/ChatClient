package com.chatnet.GUI;

import java.util.ArrayList;
import java.util.List;

import com.chatnet.GUI.node.EmptyPane;
import com.chatnet.entity.User;
import com.chatnet.service.ChatService;
import com.chatnet.service.DeleteFriendService;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class UserGUI {
	private User user;
	private ChatService chatService;
	private List<ChatGUI> chatList = new ArrayList<>();

	public UserGUI(User user) {
		this.user = user;
		this.chatService = new ChatService(this);
		
		Platform.runLater(() ->{
			new Thread(this.chatService).start();
		});
	}

	public void show() {
		VBox main = new VBox();

		HBox title = new HBox();
		title.getChildren().addAll(new EmptyPane(20, 0), new UserMassageGUI(user), new EmptyPane(12, 0));

		Button bt = new Button("好友列表");
		bt.setTextAlignment(TextAlignment.CENTER);
		bt.setFont(new Font(18));
		bt.setPrefSize(320, 30);
		bt.setBorder(null);
		bt.setStyle("-fx-background-insets: 0;");
		bt.setTextFill(Color.GRAY);
		VBox.setMargin(bt, new Insets(10, 0, 0, 10));

		ScrollPane scroll = new ScrollPane();
		scroll.setPrefSize(300, 700);
		scroll.setStyle("-fx-background-color: white;");

		VBox friendsPane = new VBox();
		for (int i = 0; i < user.getFriends().size(); i++) {
			createFriendList(user.getFriends().get(i), friendsPane);
		}
		friendsPane.setSpacing(10);
		scroll.setContent(friendsPane);

		HBox bottom = new HBox();
		Button addFriend = new Button("添加好友");
		bottom.getChildren().addAll(new EmptyPane(130, 0), addFriend);

		main.getChildren().addAll(title, bt, scroll, bottom);
		main.setStyle("-fx-background-color: white");
		main.setSpacing(5);

		Stage stage = new Stage();
		stage.setTitle("用户");
		Scene scene = new Scene(main);
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);

		addFriend.setOnAction(e -> {
			new AddFriendGUI(friendsPane, this).show();
		});
	}

	public void createFriendList(User friend, VBox friendsPane) {
		HBox friendMassage = new HBox();
		VBox friendMassageGUI = new UserMassageGUI(friend);

		Button delete = new Button("删除好友");
		BorderPane bp = new BorderPane();
		bp.setCenter(delete);
		friendMassageGUI.getChildren().addAll(bp, new EmptyPane(0, 10));

		friendMassage.getChildren().addAll(new EmptyPane(20, 0), friendMassageGUI, new EmptyPane(12, 0));
		friendsPane.getChildren().add(friendMassage);

		delete.setOnAction(e -> {
			DeleteFriendService service = new DeleteFriendService();

			if (service.deleteFriend(user, friend.getUserID())) {
				this.user.getFriends().remove(friend);
				friendsPane.getChildren().remove(friendMassage);
			}
		});

		friendMassage.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				ChatGUI chat = new ChatGUI(this.user, friend);
				chatList.add(chat);
				chat.show(this.chatService);
			}
		});
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ChatGUI> getChatList() {
		return this.chatList;
	}

}
