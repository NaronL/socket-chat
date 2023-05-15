package ru.naron.chat.server;

import lombok.Getter;
import ru.naron.chat.Connection;
import ru.naron.chat.Main;
import ru.naron.chat.client.ChatClient;
import ru.naron.chat.operation.Operation;
import ru.naron.chat.utils.Executor;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ChatServer extends Thread implements Serializable {
    private final List<ChatClient> clients = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();
    private transient ServerSocket serverSocket;
    private final int port;
    private final String serverName;

    public ChatServer(String serverName, int port){
        this.serverName = serverName;
        this.port = port;
    }

    public void enable() {
        try {
            serverSocket = new ServerSocket(port);
            Main.getServerManager().addServer(this);
            start();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                Executor.executorService.execute(new ServerThread( new Connection(socket), this));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void close() {
        try {
            serverSocket.close();
            clients.clear();
            messages.clear();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        Main.getServerManager().removeServer(this);
    }

    @Override
    public String toString() {
        return serverName;
    }
}
