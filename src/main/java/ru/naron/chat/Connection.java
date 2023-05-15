package ru.naron.chat;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.naron.chat.operation.Operation;

import java.io.*;
import java.net.Socket;

@Getter
public class Connection implements Closeable, Serializable {
    private transient Socket socket;
    private transient ObjectOutputStream output;
    private transient ObjectInputStream input;

    public Connection(Socket socket){
        try {
            this.socket = socket;
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            output.close();
            input.close();
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @SneakyThrows
    public void write(Operation operation){
        output.writeObject(operation);
        output.flush();
    }

    @SneakyThrows
    public Operation read() {
        Operation object = (Operation) input.readObject();

        if(object == null){
            throw new IllegalArgumentException("object != operation");
        }

        return object;
    }
}
