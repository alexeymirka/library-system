package com.alexsis.server.handlers;

import com.alexsis.server.dataBase.Database;
import com.alexsis.server.repository.BookService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class BookHandlerRepositoryService implements BookService {

    private int countUser;
    private Socket socket;

    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;

    private Database database;

    public BookHandlerRepositoryService(int countUser, Socket socket, Database database, BufferedReader messageFromServer, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.messageFromServer = messageFromServer;
        this.writeMessage = writeMessage;
        this.database = database;
    }

    @Override
    public void showAll() {
        LinkedList<String> listBooks = database.showAllBooks();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listBooks.size()));
        for (String user : listBooks) {
            ServerConnection.usersConnected.get(countUser).sendMessage(user);
        }
    }

    @Override
    public void delete() {

        try {
            database.deleteBookInDataBase(Integer.parseInt(messageFromServer.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addBookInDataBase() {
        try {
            String[] line = messageFromServer.readLine().split(";");
            database.addBookInDataBase(line[0], (line[1]), line[2], line[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void redaction() {
        try {
            String[] line = messageFromServer.readLine().split(";");
            database.redactionBookInDataBase(Integer.parseInt(line[0]), (line[1]), (line[2]), (line[3]), line[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
