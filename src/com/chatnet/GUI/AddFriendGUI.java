package com.chatnet.GUI;

import com.chatnet.GUI.node.EmptyPane;
import com.chatnet.GUI.node.Label;
import com.chatnet.GUI.node.WarnLabel;
import com.chatnet.entity.User;
import com.chatnet.service.AddFriendService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddFriendGUI {
	private User user;
	private VBox friendsPane;
	private UserGUI userGUI;
	
	public AddFriendGUI(VBox friendsPane, UserGUI userGUI) {
		this.user = userGUI.getUser();
		this.friendsPane = friendsPane;
		this.userGUI = userGUI;
	}

	public void show() {
		Label friendIDLabel = new Label("请输入ID号：");
		TextField friendIDText = new TextField();
		friendIDText.setPromptText("ID");
		HBox h1 = new HBox();
		h1.getChildren().addAll(friendIDLabel, friendIDText);

		WarnLabel IDError = new WarnLabel();
		HBox h2 = new HBox();
		h2.getChildren().addAll(new EmptyPane(210, 0), IDError);

		Button cancal = new Button("取消");
		Button add = new Button("添加");
		HBox h3 = new HBox();
		h3.getChildren().addAll(new EmptyPane(30, 0), cancal, new EmptyPane(150, 0), add);

		VBox mainPane = new VBox();
		mainPane.setPrefWidth(300);
		mainPane.getChildren().addAll(new EmptyPane(0, 25), h1, h2, new EmptyPane(0, 15), h3, new EmptyPane(0, 10));

		Scene scene = new Scene(mainPane);

		Stage primaryStage = new Stage();
		primaryStage.setTitle("添加好友");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		friendIDText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				IDError.setText("");
			}
		});
		
		cancal.setOnAction(e -> {
			primaryStage.close();
		});

		add.setOnAction(e -> {
			if (friendIDText.getText().equals("")) {
				IDError.setText("请输入ID号");

				return;
			}else if(friendIDText.getText().equals(user.getUserID())) {
				new Alert(AlertType.ERROR, "无法添加自己为好友", ButtonType.CLOSE).show();
				
				return;
			}

			AddFriendService service = new AddFriendService();
			User friend = service.addFriend(user, friendIDText.getText());
			
			if(friend == null) {
				new Alert(AlertType.ERROR, "ID不存在或无法重复添加好友", ButtonType.CLOSE).show();
				
			}else {
				primaryStage.close();
				
				user.getFriends().add(friend);
				this.userGUI.createFriendList(friend, friendsPane);
			}
		});
	}
}
