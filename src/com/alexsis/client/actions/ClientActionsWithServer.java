package com.alexsis.client.actions;

import com.alexsis.client.model.*;
import com.alexsis.client.windowsAlert.AlertWindow;
import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.scene.control.Button;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ClientActionsWithServer {

    private BufferedReader acceptMessage;
    private BufferedWriter sendMessage;

    private String login;
    private String role;

    public ClientActionsWithServer(Socket clientSocket) {
        try {
            acceptMessage = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendMessage = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void send(String message) {
        try {
            sendMessage.write(message + "\n");
            sendMessage.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chekUserInDatabase(Button button) {
        try {
            String answer = acceptMessage.readLine();
            if (!answer.equals("false")) {

                String[] string = answer.split(" ");
                User.currentUser = new User(string[0], string[1], string[2]);

                if (User.currentUser.getRole().equals("Админ")) {
                    ChangeWindow.changeWindow.openWindow(button, "/com/alexsis/client/resources/admin_window.fxml", "Приветствую, АДМИН!", login, role);

                } else {
                    ChangeWindow.changeWindow.openWindow(button, "/com/alexsis/client/resources/user_window.fxml", "Приветствую, ПОЛЬЗОВАТЕЛЬ!", login, role);

                }
            } else {

                AlertWindow.alertWindow.alertWindow("Неверный логин или пароль!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUserInDatabase(Button button, String username, String email, String password, String role) {
            try {
                String answer = acceptMessage.readLine();

                if (!answer.equals("false")) {
                    String[] string = new String[]{username, email, password, role};
                    User.currentUser = new User(string[0], string[1], string[2], string [3]);
                    Client.interactionsWithServer.send(string[0] + " " + string[1] + " " + string[2] + " " + string[3]);
                    ChangeWindow.changeWindow.openWindow(button, "/com/alexsis/client/resources/log_in.fxml", "Приветствую, ПОЛЬЗОВАТЕЛЬ!", null, null);

                } else {

                    AlertWindow.alertWindow.alertWindow("Пользователь с таким логином уже зарегистрирован!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public LinkedList<Book> allBooks() throws IOException {

        send("вывести все книги");
        LinkedList<Book> books = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i = 0; i < sizeList; i++){
            addBookInList(acceptMessage.readLine(), books);
        }

        return books;
    }

    public LinkedList<User> allUsers() throws IOException {

        send("вывести всех пользователей");
        LinkedList<User> users = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i = 0; i < sizeList; i++){
            addUserInList(acceptMessage.readLine(), users);
        }

        return users;
    }

    public LinkedList<Review> allReviews() throws IOException {

        send("вывести все отзывы");
        LinkedList<Review> reviews = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i = 0; i < sizeList; i++){
            addReviewInList(acceptMessage.readLine(), reviews);
        }
        return reviews;
    }

    public LinkedList<Booking> allBookings() throws IOException {
        send("вывести все брони");
        LinkedList<Booking> bookings = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i = 0; i < sizeList; i++){
            addBookingInList(acceptMessage.readLine(), bookings);
        }
        return bookings;
    }

    public LinkedList<People> allPeople() throws IOException{
        send("вывести всех посетителей");
        LinkedList<People> people = new LinkedList<>();

        int sizeList = Integer.parseInt(acceptMessage.readLine());
        for(int i = 0; i < sizeList; i++){
            addPeopleInList(acceptMessage.readLine(), people);
        }
        return people;
    }

    private void addBookInList(String string, LinkedList<Book> books){
        String[] product;
        product = string.split(";");
        books.add(new Book(Integer.parseInt(product[0]),product[1],product[2],product[3], product[4]));

    }

    private void addUserInList(String string, LinkedList<User> users){
        String[] product;
        product = string.split(";");
        users.add(new User(Integer.parseInt(product[0]),product[1],product[2],product[3], product[4]));

    }

    private void addBookingInList(String string, LinkedList<Booking> bookings){
        String[] product;
        product = string.split(";");
        bookings.add(new Booking(Integer.parseInt(product[0]),product[1],product[2],product[3],product[4]));
    }

    private void addReviewInList(String string, LinkedList<Review> reviews){
        String[] product;
        product = string.split(";");
        reviews.add(new Review(Integer.parseInt(product[0]),product[1],product[2],product[3]));

    }

    private void addPeopleInList(String string, LinkedList<People> people){
        String[] product;
        product = string.split(";");
        people.add(new People(Integer.parseInt(product[0]),product[1],product[2]));

    }

    public void redactionBook(int b_id, String b_name, String b_author, String b_year, String b_quantity) {
        send("редактировать книгу");
        send(b_id + ";" + b_name + ";" + b_author + ";" + b_year + ";"+ b_quantity);
    }

    public void redactionUser(int user_id, String username, String email, String password, String role) {
        send("редактировать пользователя");
        send(user_id + ";" + username  + ";" + email + ";"+ password + ";"+ role);
    }

    public void redactionPeople(int p_id, String name, String status) {
        send("редактировать посетителя");
        send(p_id + ";" + name  + ";" + status);
    }

//    public void redactionReview(int r_id, String text, String text1, String text2) {
//        send("редактировать отзыв");
//        send(user_id + ";" + username  + ";" + email + ";"+ password);
//    }

    public void deleteBook(int b_id) {
        send("удалить книгу");
        send(String.valueOf(b_id));
    }

    public void deleteUser(int user_id) {
        send("удалить пользователя");
        send(String.valueOf(user_id));
    }

    public void deleteReview(int r_id) {
        send("удалить отзыв");
        send(String.valueOf(r_id));
    }

    public void deleteBooking(int bk_id) {
        send("удалить бронь");
        send(String.valueOf(bk_id));
    }

    public void deletePeople(int p_id) {
        send("удалить посетителя");
        send(String.valueOf(p_id));
    }

    public void addNewBookInDataBase(String b_name, String b_author, String b_year, String b_quantity) {
        send("добавить книгу");
        send(b_name + ";" + b_author + ";" + b_year + ";" + b_quantity);
    }

    public void addNewUserInDatabase(Button button, String username, String email, String password, String role) {
        try {
            String answer = acceptMessage.readLine();

            if (!answer.equals("false")) {
                String[] string = new String[]{username, email, password, role};
                User.currentUser = new User(string[0], string[1], string[2], string [3]);
                Client.interactionsWithServer.send(string[0] + ";" + string[1] + ";" + string[2] + ";" + string[3]);
                ChangeWindow.changeWindow.openWindow(button, "/com/alexsis/client/resources/admin_window.fxml", "ПОЛЬЗОВАТЕЛЬ добавлен!!", null, null);

            } else {

                AlertWindow.alertWindow.alertWindow("Пользователь с таким логином уже зарегистрирован!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewReviewInDataBase(String book_name, String username, String review_text) {
        send("добавить отзыв");
        send(book_name + ";" + username + ";" + review_text);
    }

    public void addNewBookingInDataBase(String username, String book_name, String person_name, String date) {
        send("добавить бронь");
        send(username + ";" + book_name + ";" + person_name + ";" + date);
    }

    public void addNewPersonInDatabase(Button button, String name, String status) {
        try {
            String answer = acceptMessage.readLine();

            if (!answer.equals("false")) {
                String[] string = new String[]{name, status};
                People.currentPeople = new People(string[0], string[1]);
                Client.interactionsWithServer.send(string[0] + ";" + string[1]);
            } else {
                AlertWindow.alertWindow.alertWindow("Посетитель с таким логином уже есть!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
