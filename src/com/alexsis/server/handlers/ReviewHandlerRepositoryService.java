package com.alexsis.server.handlers;

import com.alexsis.server.dataBase.Database;
import com.alexsis.server.repository.ReviewService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class ReviewHandlerRepositoryService implements ReviewService {

    private int countUser;
    private Socket socket;

    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;

    private Database database;

    public ReviewHandlerRepositoryService(int countUser, Socket socket, Database database, BufferedReader messageFromServer, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.messageFromServer = messageFromServer;
        this.writeMessage = writeMessage;
        this.database = database;
    }

    @Override
    public void addReviewInDataBase() {
        try {
            String[] line = messageFromServer.readLine().split(";");
            int user_id = 0;
            LinkedList<String> users = database.showAllUsers();
            for (int i = 0; i < users.size(); i++) {
                String[] array = users.get(i).split(";");
                if (array[1].equals(line[0])) {
                    user_id = Integer.parseInt(array[0]);
                }
            }
            int book_id = 0;
            LinkedList<String> books = database.showAllBooks();
            for (int i = 0; i < books.size(); i++) {
                String[] array = books.get(i).split(";");
                if (array[1].equals(line[1])) {
                    book_id = Integer.parseInt(array[0]);
                }
            }
            database.addReviewInDataBase(user_id, book_id, line[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllReviews() {
        LinkedList<String> listReviews = database.showAllReviews();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listReviews.size()));
        for (String user : listReviews) {
            ServerConnection.usersConnected.get(countUser).sendMessage(user);
        }
    }

    @Override
    public void delete() {
        try {
            database.deleteReviewInDataBase(Integer.parseInt(messageFromServer.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
