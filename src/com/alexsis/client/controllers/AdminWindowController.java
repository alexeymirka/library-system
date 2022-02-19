package com.alexsis.client.controllers;

import java.io.IOException;
import java.util.LinkedList;

import com.alexsis.client.actions.Client;


import com.alexsis.client.model.*;
import com.alexsis.client.windowsAlert.AlertWindow;
import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminWindowController {

    @FXML
    private Button addBookButton;

    @FXML
    private Button addBookingButton;

    @FXML
    private Button addPersonButton;

    @FXML
    private Button addReviewButtonAdmin;

    @FXML
    private Button addUserButton;

    @FXML
    private TableView<Book> admin_book_table;

    @FXML
    private TableView<User> admin_user_table;

    @FXML
    private TableColumn<Book, String> b_author;

    @FXML
    private TextField b_authorField;

    @FXML
    private TableColumn<Book, Integer> b_id;

    @FXML
    private TableColumn<Book, String> b_name;

    @FXML
    private TextField b_nameField;

    @FXML
    private TableColumn<Book, Integer> b_quantity;

    @FXML
    private TextField b_quantityField;

    @FXML
    private TableColumn<Book, Integer> b_year;

    @FXML
    private TextField b_yearField;

    @FXML
    private TableColumn<Booking, Integer> bk_b_name;

    @FXML
    private TableColumn<Booking, String> bk_date;

    @FXML
    private TableColumn<Booking, Integer> bk_id;

    @FXML
    private TableColumn<Booking, Integer> bk_p_id;

    @FXML
    private TableColumn<Booking, Integer> bk_user_id;

    @FXML
    private TableColumn<Review, String> book_review;

    @FXML
    private Button button_log_out_1;

    @FXML
    private Button button_log_out_2;

    @FXML
    private Button button_log_out_3;

    @FXML
    private Button button_log_out_4;

    @FXML
    private Button button_log_out_5;

    @FXML
    private Button checkPersonAdminButton;

    @FXML
    private Button chooseBookButton;

    @FXML
    private Button choosePersonButton;

    @FXML
    private Button chooseUserButton;

    @FXML
    private Button deleteBookButton;

    @FXML
    private Button deleteBookingButton;

    @FXML
    private Button deletePersonButton;

    @FXML
    private Button deleteReviewButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TextField emailField;

    @FXML
    private Label label_welcome;

    @FXML
    private Label label_welcome1;

    @FXML
    private Label label_welcome21;

    @FXML
    private Label label_welcome3;

    @FXML
    private TableColumn<?, ?> p_id;

    @FXML
    private TableColumn<?, ?> p_name;

    @FXML
    private TableColumn<?, ?> p_status;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TextField passwordField;

    @FXML
    private TableView<?> person_table;

    @FXML
    private TableColumn<Review, Integer> r_id;

    @FXML
    private Button redactionBookFieldButton;

    @FXML
    private Button redactionPersonButton;

    @FXML
    private Button redactionUserFieldButton;

    @FXML
    private TableView<Review> review_book_table;

    @FXML
    private TableColumn<Review, String> review_text;

    @FXML
    private TableColumn<User, String> role;

    @FXML
    private TextField roleField;

    @FXML
    private Button searchBookBookingButton;

    @FXML
    private TextField searchBookBookingText;

    @FXML
    private Button searchBookButton;

    @FXML
    private Button searchBookReviewButton;

    @FXML
    private TextField searchBookReviewText;

    @FXML
    private TextField searchBookText;

    @FXML
    private Button searchPersonButton;

    @FXML
    private TextField searchPersonText;

    @FXML
    private Button searchUserButton;

    @FXML
    private TextField searchUserText;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_status;

    @FXML
    private Button updateBookButton;

    @FXML
    private Button updateBookingButton;

    @FXML
    private Button updatePersonButton;

    @FXML
    private Button updateReviewButton;

    @FXML
    private Button updateUserButton;

    @FXML
    private TableView<Booking> user_booking_table;

    @FXML
    private TableColumn<User, Integer> user_id;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TextField usernameField;

    @FXML
    private TableColumn<Review, String> username_review;

    private String pochta;
    private String parol;
    private String roll;
    private String login;

    private String person;
    private String status;

//    private final ObservableList<People> listPeople = FXCollections.observableArrayList();
//    private ObservableList<People> listPeopleToShow = FXCollections.observableArrayList();
    private final ObservableList<Book> listBooks = FXCollections.observableArrayList();
    private ObservableList<Book> listBooksToShow = FXCollections.observableArrayList();
    private final ObservableList<User> listUsers = FXCollections.observableArrayList();
    private ObservableList<User> listUsersToShow = FXCollections.observableArrayList();
    private final ObservableList<Review> listReviews = FXCollections.observableArrayList();
    private ObservableList<Review> listReviewsToShow = FXCollections.observableArrayList();
    private final ObservableList<Booking> listBookings = FXCollections.observableArrayList();
    private ObservableList<Booking> listBookingsToShow = FXCollections.observableArrayList();

    public void initialize() {

        initBooksTextField();
        initUsersTextField();

        checkPersonAdminButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(checkPersonAdminButton, "/com/alexsis/client/resources/personAdmin.fxml", "Кто тут в бане?!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        try {
//            initPeople(Client.interactionsWithServer.allPeople());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        searchPersonButton.setOnAction(event -> {
//            initPeople(searchPeople());
//        });
//
//        updatePersonButton.setOnAction(event -> {
//            try {
//                initPeople(Client.interactionsWithServer.allPeople());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

//        addPersonButton.setOnAction(event -> {
//            try {
//                ChangeWindow.changeWindow.openWindow(addPersonButton, "/com/alexsis/client/resources/addNewPersonAdmin.fxml", "Добавь посетителя!", null, null);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });

        button_log_out_1.setOnAction(event -> {
            log_out();
        });

        button_log_out_2.setOnAction(event -> {
            log_out();
        });

        button_log_out_3.setOnAction(event -> {
            log_out();
        });

        button_log_out_4.setOnAction(event -> {
            log_out();
        });

//        button_log_out_5.setOnAction(event -> {
//            log_out();
//        });

        addBookingButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(addBookingButton, "/com/alexsis/client/resources/addNewBookingAdmin.fxml", "Добавь бронь!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateBookButton.setOnAction(event -> {
            try {
                initBooks(Client.interactionsWithServer.allBooks());
                searchBookText.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateBookingButton.setOnAction(event -> {
            try {
                initBookings(Client.interactionsWithServer.allBookings());
                searchBookBookingText.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateUserButton.setOnAction(event -> {
            try {
                initUsers(Client.interactionsWithServer.allUsers());
                searchUserText.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateReviewButton.setOnAction(event -> {
            try {
                initReviews(Client.interactionsWithServer.allReviews());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            initUsers(Client.interactionsWithServer.allUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            initBooks(Client.interactionsWithServer.allBooks());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            initReviews(Client.interactionsWithServer.allReviews());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            initBookings(Client.interactionsWithServer.allBookings());
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchBookButton.setOnAction(event -> {
            initBooks(searchBook());
        });

        searchUserButton.setOnAction(event -> {
            initUsers(searchUser());
        });

        searchBookReviewButton.setOnAction(event -> {
            initReviews(searchReview());
        });

        searchBookBookingButton.setOnAction(event -> {
            initBookings(searchBooking());
        });

        deleteBookButton.setOnAction(event -> {
            int count = admin_book_table.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.deleteBook(listBooks.get(count).getB_id());
            try {
                initBooks(Client.interactionsWithServer.allBooks());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteUserButton.setOnAction(event -> {
            int count = admin_user_table.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.deleteUser(listUsers.get(count).getUser_id());
            try {
                initUsers(Client.interactionsWithServer.allUsers());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteBookingButton.setOnAction(event -> {
            int count = user_booking_table.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.deleteBooking(listBookings.get(count).getBk_id());
            try {
                initBookings(Client.interactionsWithServer.allBookings());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deleteReviewButton.setOnAction(event -> {
            int count = review_book_table.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.deleteReview(listReviews.get(count).getR_id());
            try {
                initReviews(Client.interactionsWithServer.allReviews());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        redactionBookFieldButton.setOnAction(event -> {
            int count = admin_book_table.getSelectionModel().getSelectedCells().get(0).getRow();

            Client.interactionsWithServer.redactionBook(listBooks.get(count).getB_id(), b_nameField.getText(), b_authorField.getText(), b_yearField.getText(), b_quantityField.getText());
            try {
                initBooks(Client.interactionsWithServer.allBooks());
            } catch (IOException e) {
                e.printStackTrace();
            }
            initBooksTextField();
        });

        redactionUserFieldButton.setOnAction(event -> {
            login = usernameField.getText();
            pochta = emailField.getText();
            parol = passwordField.getText();
            roll = roleField.getText();

            int count = admin_user_table.getSelectionModel().getSelectedCells().get(0).getRow();
            if (!login.equals("") && !pochta.equals("") && !parol.equals("") && !roll.equals("")) {
                if (roll.equals("Админ") || roll.equals("Гость") || roll.equals("Работник")) {
                    Client.interactionsWithServer.redactionUser(listUsers.get(count).getUser_id(), login, pochta, parol, roll);
                    try {
                        initUsers(Client.interactionsWithServer.allUsers());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    initUsersTextField();
                } else {
                    AlertWindow.alertWindow.alertWindow("Роль может быть только: Админ/Работник/Гость!");
                }
            } else {
                AlertWindow.alertWindow.alertWindow("Заполните все ячейки!");
            }
        });

        chooseBookButton.setOnAction(event -> {
            int count = admin_book_table.getSelectionModel().getSelectedCells().get(0).getRow();
            System.out.println(count);
            b_nameField.setText(listBooks.get(count).getB_name());
            b_authorField.setText(listBooks.get(count).getB_author());
            b_yearField.setText(listBooks.get(count).getB_year());
            b_quantityField.setText(listBooks.get(count).getB_quantity());
        });

        chooseUserButton.setOnAction(event -> {
            int count = admin_user_table.getSelectionModel().getSelectedCells().get(0).getRow();
            System.out.println(count);
            usernameField.setText(listUsers.get(count).getUsername());
            emailField.setText(listUsers.get(count).getEmail());
            passwordField.setText(listUsers.get(count).getPassword());
            roleField.setText(listUsers.get(count).getRole());
        });

        addBookButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(addBookButton, "/com/alexsis/client/resources/addNewBookAdmin.fxml", "Добавь книгу!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addUserButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(addUserButton, "/com/alexsis/client/resources/addNewUser.fxml", "Добавь пользователя!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addReviewButtonAdmin.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(addUserButton, "/com/alexsis/client/resources/addNewReviewAdmin.fxml", "Добавь отзыв!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void log_out() {
        try {
            ChangeWindow.changeWindow.openWindow(button_log_out_1, "/com/alexsis/client/resources/log_in.fxml", "Войди!", null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initBookings(LinkedList<Booking> bookings){

        listBookings.clear();
        listBookings.addAll(bookings);

        listBookingsToShow = FXCollections.observableArrayList();
        for (int i = 0; i < listBookings.size(); i++) {
            int id = listBookings.get(i).getBk_id();
            String username = listBookings.get(i).getUser_id();
            String book_name = listBookings.get(i).getB_id();
            String person_name = listBookings.get(i).getP_id();
            String date = listBookings.get(i).getDate();

            Booking booking = new Booking (id, username, book_name, person_name, date);
            listBookingsToShow.add(booking);
        }

        bk_id.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("bk_id"));
        bk_user_id.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("user_id"));
        bk_b_name.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("b_id"));
        bk_p_id.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("p_id"));
        bk_date.setCellValueFactory(new PropertyValueFactory<Booking, String>("date"));

        user_booking_table.setItems(listBookings);
    }

    private void initReviews(LinkedList<Review> reviews) {

        listReviews.clear();
        listReviews.addAll(reviews);

        listReviewsToShow = FXCollections.observableArrayList();
        for (int i = 0; i < listReviews.size(); i++) {
            int id = listReviews.get(i).getR_id();
            String username = listReviews.get(i).getUser_id();
            String book_name = listReviews.get(i).getB_id();
            String review_text = listReviews.get(i).getText();

            Review review = new Review(username, book_name, review_text);
            listReviewsToShow.add(review);
        }

        r_id.setCellValueFactory(new PropertyValueFactory<Review, Integer>("r_id"));
        username_review.setCellValueFactory(new PropertyValueFactory<Review, String>("user_id"));
        book_review.setCellValueFactory(new PropertyValueFactory<Review, String>("b_id"));
        review_text.setCellValueFactory(new PropertyValueFactory<Review, String>("text"));

        review_book_table.setItems(listReviews);
    }

    private void initBooks(LinkedList<Book> books) {

        listBooks.clear();
        listBooks.addAll(books);

        listBooksToShow = FXCollections.observableArrayList();
        for (int i = 0; i < listBooks.size(); i++) {
            int id = listBooks.get(i).getB_id();
            String name = listBooks.get(i).getB_name();
            String author = listBooks.get(i).getB_author();
            String year = listBooks.get(i).getB_year();
            String quantity = listBooks.get(i).getB_quantity();

            Book book = new Book(id, name, author, year, quantity);
            listBooksToShow.add(book);
        }

        b_id.setCellValueFactory(new PropertyValueFactory<Book, Integer>("b_id"));
        b_name.setCellValueFactory(new PropertyValueFactory<Book, String>("b_name"));
        b_author.setCellValueFactory(new PropertyValueFactory<Book, String>("b_author"));
        b_year.setCellValueFactory(new PropertyValueFactory<Book, Integer>("b_year"));
        b_quantity.setCellValueFactory(new PropertyValueFactory<Book, Integer>("b_quantity"));

        admin_book_table.setItems(listBooks);
    }

    private void initUsers(LinkedList<User> users) {

        listUsers.clear();
        listUsers.addAll(users);

        listUsersToShow = FXCollections.observableArrayList();
        for (int i = 0; i < listUsers.size(); i++) {
            int user_id = listUsers.get(i).getUser_id();
            String username = listUsers.get(i).getUsername();
            String email = listUsers.get(i).getEmail();
            String password = listUsers.get(i).getPassword();
            String role = listUsers.get(i).getRole();

            User user = new User(user_id, username, email, password, role);
            listUsersToShow.add(user);
        }

        user_id.setCellValueFactory(new PropertyValueFactory<User, Integer>("user_id"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));

        admin_user_table.setItems(listUsers);
    }

    private LinkedList<Book> searchBook() {
        String search = searchBookText.getText();
        LinkedList<Book> bookSearches = new LinkedList<>();
        for (Book book : listBooks) {
            if (search.equals(book.getB_name())) {
                bookSearches.add(book);
            }

        }
        return bookSearches;
    }

    private LinkedList<User> searchUser() {
        String search = searchUserText.getText();
        LinkedList<User> userSearches = new LinkedList<>();
        for (User user : listUsers) {
            if (search.equals(user.getUsername())) {
                userSearches.add(user);
            }
        }
        return userSearches;
    }

    private LinkedList<Review> searchReview() {
        String search = searchBookReviewText.getText();
        LinkedList<Review> reviewSearches = new LinkedList<>();
        for (Review review : listReviews) {
            if (search.equals(review.getB_id())) {
                reviewSearches.add(review);
            }
        }
        return reviewSearches;
    }

    private LinkedList<Booking> searchBooking() {
        String search = searchBookBookingText.getText();
        LinkedList<Booking> bookingSearches = new LinkedList<>();
        for (Booking booking : listBookings) {
            if (search.equals(booking.getBk_id())) {
                bookingSearches.add(booking);
            }
        }
        return bookingSearches;
    }

    private void initBooksTextField() {
        b_nameField.setText("");
        b_authorField.setText("");
        b_yearField.setText("");
        b_quantityField.setText("");
    }

    private void initUsersTextField() {
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        roleField.setText("");
    }

//    private void initPeople(LinkedList<People> people){
//
//        listPeople.clear();
//        listPeople.addAll(people);
//
//        listPeopleToShow = FXCollections.observableArrayList();
//        for (int i = 0; i < listPeople.size(); i++) {
//            int id = listPeople.get(i).getP_id();
//            String name = listPeople.get(i).getP_name();
//            String status = listPeople.get(i).getP_status();
//
//            People person = new People(id, name, status);
//            listPeopleToShow.add(person);
//        }
//
//        p_id.setCellValueFactory(new PropertyValueFactory<People, Integer>("p_id"));
//        p_name.setCellValueFactory(new PropertyValueFactory<People, String>("p_name"));
//        p_status.setCellValueFactory(new PropertyValueFactory<People, String>("p_status"));
//
//        person_table.setItems(listPeople);
//    }
//
//    private LinkedList<People> searchPeople(){
//        String search = searchPersonText.getText();
//        LinkedList<People> personSearches = new LinkedList<>();
//        for(People person : listPeople){
//            if(search.equals(person.getP_name())){
//                personSearches.add(person);
//            }
//        }
//        return personSearches;
//    }
//
//    private void initPeopleTextField() {
//        tf_name.setText("");
//        tf_status.setText("");
//    }
}