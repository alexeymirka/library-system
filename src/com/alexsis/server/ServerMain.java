package com.alexsis.server;

import com.alexsis.server.handlers.ServerConnection;

public class ServerMain {
    public static void main(String[] args) {
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.startServer();
        serverConnection.connectNewClientInToServer();
        serverConnection.closeAll();
    }
}
