package ru.naron.chat;

import javafx.application.Application;
import lombok.Getter;
import ru.naron.chat.application.impl.AuthorizeApplication;
import ru.naron.chat.server.ChatServer;
import ru.naron.chat.server.ServerManager;

public class Main {
    @Getter
    private static ServerManager serverManager;
    @Getter
    private static ChatServer chatServer;

    public static void main(String[] args) {
        serverManager = new ServerManager();

        chatServer = new ChatServer( "Сервер #1", 1001);
        chatServer.enable();

        Application.launch(AuthorizeApplication.class);
    }
}
