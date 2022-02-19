package com.alexsis.server.handlers;

import com.alexsis.server.dataBase.Database;

import java.io.*;
import java.net.Socket;


public class ClientHandler extends Thread{

    private UserHandlerRepositoryService usersHandlerRepository;
    private BookHandlerRepositoryService bookHandlerRepository;
    private ReviewHandlerRepositoryService reviewHandlerRepository;
    private BookingHandlerRepositoryService bookingHandlerRepository;
    private PersonHandlerRepositoryService personHandlerRepository;
    private Socket socket;
    private BufferedReader messageFromServer;
    private BufferedWriter writeMessage;
    public ClientHandler(Socket socket, Database database,int count) {


        this.socket = socket;
        try {
            messageFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writeMessage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            usersHandlerRepository = new UserHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);
            bookHandlerRepository = new BookHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);
            reviewHandlerRepository = new ReviewHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);
            bookingHandlerRepository = new BookingHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);
            personHandlerRepository = new PersonHandlerRepositoryService(count,socket,database,messageFromServer,writeMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        try {
            userHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void userHandler() throws IOException {
        while (true) {
            switch (messageFromServer.readLine()) {

                case "авторизация":
                    usersHandlerRepository.authorization();
                    break;

                case "добавить пользователя":
                    usersHandlerRepository.registration();
                    break;

                case "вывести всех пользователей":
                    usersHandlerRepository.showAllUsers();
                    break;

                case "удалить пользователя":
                    usersHandlerRepository.deleteUser();
                    break;

                case "редактировать пользователя":
                    usersHandlerRepository.redactionUser();
                    break;




                case "добавить книгу":
                    bookHandlerRepository.addBookInDataBase();
                    break;

                case "вывести все книги":
                    bookHandlerRepository.showAll();
                    break;

                case "удалить книгу":
                    bookHandlerRepository.delete();
                    break;

                case "редактировать книгу":
                    bookHandlerRepository.redaction();
                    break;



                case "добавить отзыв":
                    reviewHandlerRepository.addReviewInDataBase();
                    break;

                case "вывести все отзывы":
                    reviewHandlerRepository.showAllReviews();
                    break;

                case "удалить отзыв":
                    reviewHandlerRepository.delete();
                    break;


                case
                "добавить бронь":
                    bookingHandlerRepository.addBookingInDataBase();
                break;
                case "вывести все брони":
                    bookingHandlerRepository.showAllBookings();
                    break;
                case "удалить бронь":
                    bookingHandlerRepository.delete();
                    break;





                case "добавить посетителя":
                    personHandlerRepository.addPersonInDataBase();
                    break;
                case "вывести всех посетителей":
                    personHandlerRepository.showAllPeople();
                    break;
                case "удалить посетителя":
                    personHandlerRepository.delete();
                    break;

            }
        }
    }


    public void sendMessage(String msg) {
        try {
            writeMessage.write(msg + "\n");
            writeMessage.flush();
        } catch (IOException ignored) {}
    }
}