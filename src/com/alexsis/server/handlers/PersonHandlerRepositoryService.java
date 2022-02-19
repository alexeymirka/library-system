package com.alexsis.server.handlers;

import com.alexsis.server.dataBase.Database;
import com.alexsis.server.repository.PersonService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;


public class PersonHandlerRepositoryService implements PersonService {
    private int countUser;
    private Socket socket;

    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;

    private Database database;

    public PersonHandlerRepositoryService(int countUser, Socket socket, Database database, BufferedReader messageFromServer, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.messageFromServer = messageFromServer;
        this.writeMessage = writeMessage;
        this.database = database;
    }

    @Override
    public void showAllPeople() {
        LinkedList<String> listPeople = database.showAllPeople();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listPeople.size()));
        for (String user : listPeople) {
            ServerConnection.usersConnected.get(countUser).sendMessage(user);
        }
    }

    @Override
    public void delete() {
        try {
            database.deletePeopleInDataBase(Integer.parseInt(messageFromServer.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void addPersonInDataBase() {
        try {
            String[] line = messageFromServer.readLine().split(";");
            String check = database.checkPersonInDataBase(line[0]);
            if (check.equals("false")) {
                ServerConnection.usersConnected.get(countUser).sendMessage("false");
            } else {
                ServerConnection.usersConnected.get(countUser).sendMessage("true");
                String[] lineAdd = messageFromServer.readLine().split(";");
                database.addPersonInDataBase(lineAdd[0], lineAdd[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
