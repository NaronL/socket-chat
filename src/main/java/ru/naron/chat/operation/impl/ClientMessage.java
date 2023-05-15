package ru.naron.chat.operation.impl;

import lombok.AllArgsConstructor;
import ru.naron.chat.operation.Operation;
import ru.naron.chat.server.ChatServer;

@AllArgsConstructor
public class ClientMessage implements Operation {

    private final String message;

    @Override
    public String text() {
        return "";
    }

    @Override
    public void handle(ChatServer chatServer) {
        chatServer.getMessages().add(message);
    }
}
