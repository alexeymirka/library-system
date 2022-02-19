package com.alexsis.server.handlers;

import com.alexsis.server.dataBase.Database;
import com.alexsis.server.repository.BookingService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;


public class BookingHandlerRepositoryService implements BookingService {
    private int countUser;
    private Socket socket;

    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;

    private Database database;

    public BookingHandlerRepositoryService(int countUser, Socket socket, Database database, BufferedReader messageFromServer, BufferedWriter writeMessage) {
        this.countUser = countUser;
        this.socket = socket;
        this.messageFromServer = messageFromServer;
        this.writeMessage = writeMessage;
        this.database = database;
    }

    @Override
    public void addBookingInDataBase() {
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
            int person_id = 0;
            LinkedList<String> people = database.showAllPeople();
            for (int i = 0; i < people.size(); i++) {
                String[] array = people.get(i).split(";");
                if (array[1].equals(line[2])) {
                    person_id = Integer.parseInt(array[0]);
                }
            }
            database.addBookingInDataBase(user_id, book_id, person_id, line[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllBookings() {
        LinkedList<String> listBookings = database.showAllBooking();
        ServerConnection.usersConnected.get(countUser).sendMessage(String.valueOf(listBookings.size()));
        for (String user : listBookings) {
            ServerConnection.usersConnected.get(countUser).sendMessage(user);
        }
    }

    @Override
    public void delete() {
        try {
            database.deleteBookingInDataBase(Integer.parseInt(messageFromServer.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
