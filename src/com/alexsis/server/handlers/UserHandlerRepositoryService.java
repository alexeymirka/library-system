package com.alexsis.server.handlers;

import com.alexsis.server.dataBase.Database;
import com.alexsis.server.repository.UserService;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class UserHandlerRepositoryService implements UserService {

    private int countUser;
    private Socket socket;

    private  BufferedReader messageFromServer;
    private BufferedWriter writeMessage;

    private Database database;

    public UserHandlerRepositoryService(int countUser, Socket socket, Database database, BufferedReader messageFromServer, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.database = database;
        this.messageFromServer = messageFromServer;
        this.writeMessage = writeMessage;
    }

    @Override
    public void authorization() {
        try {
            String[] line = messageFromServer.readLine().split(" ");
            String check = database.verificationUserInDataBase(line[0], line[1]);
            if (!check.equals("false")) {

                ServerConnection.usersConnected.get(countUser).sendMessage(check);
            } else {
                ServerConnection.usersConnected.get(countUser).sendMessage("false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registration() {
        try {
            String[] line = messageFromServer.readLine().split(" ");
            String check= database.checkLoginInDataBase(line[0]);
            if (check.equals("false")) {
                ServerConnection.usersConnected.get(countUser).sendMessage("false");
            } else {
                ServerConnection.usersConnected.get(countUser).sendMessage("true");
                String[] lineAdd = messageFromServer.readLine().split(" ");
                database.appendNewUserInDatabase(lineAdd[0], lineAdd[1], lineAdd[2], lineAdd[3]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllUsers() {
        LinkedList<String> listUsers = database.showAllUsers();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listUsers.size()));
        for(String user:listUsers){
            ServerConnection.usersConnected.get(countUser).sendMessage(user);
        }
    }

    @Override
    public void deleteUser() {
        try {
            String line = messageFromServer.readLine();
            database.deleteUserInDataBase(Integer.parseInt(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void redactionUser() {
        try {
            String[] user = messageFromServer.readLine().split(";");
            database.redactionUserDatabase(Integer.parseInt(user[0]),user[1],user[2],user[3],user[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
