package ru.naron.chat.operation;

import ru.naron.chat.server.ChatServer;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public interface Operation extends Serializable {

    String text();
    void handle(ChatServer chatServer);
}
