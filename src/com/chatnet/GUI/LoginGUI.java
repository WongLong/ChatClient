package com.chatnet.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.chatnet.GUI.node.EmptyPane;
import com.chatnet.GUI.node.Label;
import com.chatnet.GUI.node.WarnLabel;
import com.chatnet.entity.User;
import com.chatnet.service.LoginService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginGUI {
	public static void show() {
		VBox mainPane = new VBox();
		mainPane.setPrefWidth(300);

		Label userIDLabel = new Label("”√ªßID£∫");
		TextField userIDText = new TextField();
		userIDText.setPromptText("ID");
		HBox h1 = new HBox();
		h1.getChildren().addAll(userIDLabel, userIDText);

		Label passwordLabel = new Label("√‹¬Î£∫");
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("password");
		HBox h2 = new HBox();
		h2.getChildren().addAll(passwordLabel, passwordField);

		WarnLabel error = new WarnLabel();
		HBox h3 = new HBox();
		h3.getChildren().addAll(new EmptyPane(190, 0), error);

		Button register = new Button("◊¢≤·");
		Button login = new Button("µ«¬º");
		HBox h4 = new HBox();
		h4.getChildren().addAll(new EmptyPane(30, 0), register, new EmptyPane(150, 0), login);

		mainPane.getChildren().addAll(new EmptyPane(0, 20), h1, new EmptyPane(0, 10), h2, h3, new EmptyPane(0, 10), h4,
				new EmptyPane(0, 10));

		Scene scene = new Scene(mainPane);
		
		Stage primaryStage = new Stage();
		primaryStage.setResizable(false);
		primaryStage.setTitle("¡ƒÃÏµ«¬º");
		primaryStage.setScene(scene);
		primaryStage.show();

		register.setOnAction(e -> {
			RegisterGUI.show();
		});

		login.setOnAction(e -> {
			if(userIDText.getText().equals("") || passwordField.getText().equals("")) {
				error.setText("IDªÚ√‹¬Î”–ŒÛ£°");
				return;
			}
			
			LoginService service = new LoginService();
			User user = service.login(userIDText.getText(), passwordField.getText());

			if (user == null) {
				error.setText("IDªÚ√‹¬Î”–ŒÛ£°");
			} else {
				primaryStage.close();
				
				new UserGUI(user).show();
			}
		});

		userIDText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				error.setText("");
			}
		});
		
		passwordField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				error.setText("");
			}
		});
	}

}
