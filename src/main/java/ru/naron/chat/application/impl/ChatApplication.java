package ru.naron.chat.application.impl;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ru.naron.chat.Main;
import ru.naron.chat.application.SimpleApplication;
import ru.naron.chat.client.ChatClient;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

public class ChatApplication extends SimpleApplication {
    @FXML
    private Button disableButton;

    @FXML
    private Button sendButton;

    @FXML
    private Text memberText;

    @FXML
    private TextField messageField;

    @FXML
    private VBox receiveMessage;

    public ChatApplication() {
        super("chat", "Чат", 800, 600);
    }

    @FXML
    public void initialize(){
        disableButton.setOnAction(action -> {
            Platform.exit();
        });

        AtomicReference<String> message = new AtomicReference<>();
        sendButton.setOnAction(action -> {
            message.set(messageField.getText());
            for(ChatClient client : Main.getChatServer().getClients()){
                client.sendMessage(message.get());
                System.out.println(client.getName() + "/" + message.get());
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                StringBuilder stringBuilder = new StringBuilder();

                for(ChatClient client : Main.getChatServer().getClients()){
                    stringBuilder.append("\n");
                    stringBuilder.append(client.getName());
                }

                memberText.setText("Участники: " + stringBuilder);
            }
        }, 0, 5000L);
    }

}
