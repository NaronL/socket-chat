package ru.naron.chat.operation.impl;

import lombok.AllArgsConstructor;
import ru.naron.chat.client.ChatClient;
import ru.naron.chat.operation.Operation;
import ru.naron.chat.server.ChatServer;

import java.io.Serializable;

@AllArgsConstructor
public class ClientConnected implements Operation {
    ChatClient client;

    @Override
    public String text() {
        return "Client " + client.getName() + " connected!";
    }

    public void handle(ChatServer chatServer){
        chatServer.getClients().add(client);
    }
}
