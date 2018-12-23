package com.chatnet.GUI;

import com.chatnet.GUI.node.EmptyPane;
import com.chatnet.GUI.node.Label;
import com.chatnet.GUI.node.WarnLabel;
import com.chatnet.entity.User;
import com.chatnet.service.RegisterService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterGUI {
	public static void show() {
		Label userNameLabel = new Label("用户名：");
		TextField userNameText = new TextField();
		userNameText.setPromptText("Name");
		HBox h1 = new HBox();
		h1.getChildren().addAll(userNameLabel, userNameText);

		Label passwordLabel = new Label("密码(8~16)：");
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Password");
		HBox h2 = new HBox();
		h2.getChildren().addAll(passwordLabel, passwordField);

		WarnLabel passwordError = new WarnLabel();
		HBox h3 = new HBox();
		h3.getChildren().addAll(new EmptyPane(210, 0), passwordError);

		Label ensurePassword = new Label("确认密码：");
		PasswordField ensureField = new PasswordField();
		ensureField.setPromptText("Ensure Password");
		HBox h4 = new HBox();
		h4.getChildren().addAll(ensurePassword, ensureField);

		WarnLabel inconformity = new WarnLabel();
		HBox h5 = new HBox();
		h5.getChildren().addAll(new EmptyPane(182, 0), inconformity);

		Button cancal = new Button("取消");
		Button register = new Button("注册");
		HBox h6 = new HBox();
		h6.getChildren().addAll(new EmptyPane(30, 0), cancal, new EmptyPane(150, 0), register);

		VBox mainPane = new VBox();
		mainPane.setPrefWidth(300);
		mainPane.getChildren().addAll(new EmptyPane(0, 25), h1, new EmptyPane(0, 15), h2, h3, h4, h5,
				new EmptyPane(0, 10), h6);

		Scene scene = new Scene(mainPane);

		Stage primaryStage = new Stage();
		primaryStage.setTitle("聊天注册");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		passwordField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (observable.getValue().length() < 8) {
					passwordError.setText("密码太短！");
				} else if (observable.getValue().length() > 16) {
					passwordError.setText("密码太长！");
				} else {
					passwordError.setText("");
				}

				if (!ensureField.getText().equals("")) {
					if (!observable.getValue().equals(ensureField.getText())) {
						inconformity.setText("两次密码不一致！");
					} else {
						inconformity.setText("");
					}
				}

			}
		});

		ensureField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!observable.getValue().equals(passwordField.getText())) {
					inconformity.setText("两次密码不一致！");
				} else {
					inconformity.setText("");
				}
			}
		});
		
		cancal.setOnAction(e -> {
			primaryStage.close();
		});

		register.setOnAction(e -> {
			if(userNameText.getText().equals("") || passwordField.getText().equals("")) {
				new Alert(AlertType.WARNING, "请输入用户名和密码！", ButtonType.CLOSE).show();
				return ;
			}
			
			if(!passwordField.getText().equals(ensureField.getText())) {
				inconformity.setText("两次密码不一致！");
			}
			
			if(inconformity.getText().equals("") && passwordError.getText().equals("")) {
				RegisterService service = new RegisterService();
				User user = service.register(userNameText.getText(), passwordField.getText());	
				
				if(user != null) {
					primaryStage.close();
					new Alert(AlertType.INFORMATION, "注册成功！请记住你的ID号码：" + user.getUserID(), ButtonType.OK).show();
				}
			}
		});
	}
}
