package ru.naron.chat.application.impl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.naron.chat.Main;
import ru.naron.chat.application.SimpleApplication;
import ru.naron.chat.client.ChatClient;
import ru.naron.chat.server.ChatServer;
import ru.naron.chat.server.ServerManager;

import java.io.IOException;

public class AuthorizeApplication extends SimpleApplication {

    @FXML
    private Button disableButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<ChatServer> serverBox;
    ChatClient chatClient;

    public AuthorizeApplication() {
        super("login", "Авторизация", 700, 400);
    }

    @FXML
    public void initialize(){
        disableButton.setOnAction(action -> {
            Platform.exit();
        });

        Main.getServerManager().getServers().forEach(servers -> {
            serverBox.getItems().add(servers);
        });


        loginButton.setOnAction(action -> {
            String name = nameField.getText();
            int port = serverBox.getSelectionModel().getSelectedItem().getPort();
            ChatServer selectServer = Main.getServerManager().getServer(port);

            if (name.length() >= 10 || name.length() <= 3) {
                return;
            }

            if(serverBox.getSelectionModel().isEmpty()){
                return;
            }

            for(ChatClient clients : selectServer.getClients()){
                if(clients.getName().equalsIgnoreCase(name)){
                    return;
                }
            }

            try {
                chatClient = new ChatClient(name);
                chatClient.connect("localhost", port);

                new ChatApplication().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
