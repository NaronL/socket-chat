package ru.naron.chat.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.naron.chat.Connection;
import ru.naron.chat.operation.Operation;

@AllArgsConstructor
public class ServerThread implements Runnable {

    @Getter
    private final Connection connection;

    @Getter
    private final ChatServer chatServer;

    @SneakyThrows
    @Override
    public void run() {
        while(!connection.getSocket().isClosed()){
            Operation operation = connection.read();

            operation.handle(chatServer);
            System.out.println("operation: " + operation);
            connection.getOutput().flush();
        }
    }
}
