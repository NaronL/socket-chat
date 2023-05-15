package ru.naron.chat.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.naron.chat.Connection;
import ru.naron.chat.Main;
import ru.naron.chat.operation.impl.ClientConnected;
import ru.naron.chat.operation.impl.ClientMessage;
import ru.naron.chat.server.ChatServer;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
@Getter
public class ChatClient implements Serializable{
    private Connection connection;
    private ChatServer chatServer;
    private String name;

    public ChatClient(String name){
        this.name = name;
    }

    public void connect(String address, int port) {
        try {
            connection = new Connection(new Socket(address, port));
            chatServer = Main.getServerManager().getServer(port);

            connection.write(new ClientConnected(this));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void sendMessage(String message){
        connection.write(new ClientMessage(message));
    }

    public boolean isConnection(){
        return connection != null;
    }

    public void setName(String name) {
        this.name = name;
    }

}
