package ru.naron.chat.server;

import lombok.Getter;
import ru.naron.chat.server.ChatServer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ServerManager {
    private static final Map<Integer, ChatServer> cache = new HashMap<>();

    public void addServer(ChatServer chatServer){
        cache.put(chatServer.getPort(), chatServer);
    }

    public void removeServer(ChatServer chatServer){
        cache.remove(chatServer.getPort(), chatServer);
    }

    public ChatServer getServer(int port){
        return cache.get(port);
    }

    public Collection<ChatServer> getServers(){
        return cache.values();
    }
}
