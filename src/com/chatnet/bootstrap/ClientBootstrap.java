package com.chatnet.bootstrap;

import com.chatnet.GUI.LoginGUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class ClientBootstrap extends Application {
	public static void main(String[] args) {
		System.out.println("holle");
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginGUI.show();
	}
}
