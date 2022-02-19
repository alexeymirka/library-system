package com.alexsis.client.controllers;

import java.io.IOException;
import java.util.LinkedList;

import com.alexsis.client.actions.Client;
import com.alexsis.client.model.Book;
import com.alexsis.client.model.Booking;
import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserWindowController {

    @FXML
    private Button addBookButton;

    @FXML
    private Button addBookingButton;

    @FXML
    private Button addReviewButton;

    @FXML
    private TableColumn<Book, String> b_author;

    @FXML
    private TableColumn<Book, Integer> b_id;

    @FXML
    private TableColumn<Book, String> b_name;

    @FXML
    private TableColumn<Book, Integer> b_quantity;

    @FXML
    private TableColumn<Book, Integer> b_year;

    @FXML
    private TableColumn<Booking, Integer> bk_b_name;

    @FXML
    private TableColumn<Booking, Integer> bk_id;

    @FXML
    private TableColumn<Booking, String> bk_date;

    @FXML
    private TableColumn<Booking, Integer> bk_p_id;

    @FXML
    private TableColumn<Booking, Integer> bk_user_id;

    @FXML
    private Button button_log_out;

    @FXML
    private Button button_log_out_2;

    @FXML
    private Button checkPersonButton;

    @FXML
    private Button deleteBookingButton;

    @FXML
    private Button searchBookBookingButton;

    @FXML
    private TextField searchBookBookingText;

    @FXML
    private Button searchBookButton;

    @FXML
    private TextField searchBookText;

    @FXML
    private Button updateBookButton;

    @FXML
    private Button updateBookingButton;

    @FXML
    private TableView<Book> user_book_table;

    @FXML
    private TableView<Booking> user_booking_table;

    private final ObservableList<Book> listBooks = FXCollections.observableArrayList();
    private ObservableList<Book> listBooksToShow = FXCollections.observableArrayList();
    private final ObservableList<Booking> listBookings = FXCollections.observableArrayList();
    private ObservableList<Booking> listBookingsToShow = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        button_log_out.setOnAction(event -> {
            log_out();
        });

        button_log_out_2.setOnAction(event -> {
            log_out();
        });


        try {
            initBooks(Client.interactionsWithServer.allBooks());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            initBookings(Client.interactionsWithServer.allBookings());
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateBookButton.setOnAction(event -> {
            try {
                initBooks(Client.interactionsWithServer.allBooks());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateBookingButton.setOnAction(event -> {
            try {
                initBookings(Client.interactionsWithServer.allBookings());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        searchBookButton.setOnAction(event -> {
            initBooks(searchBook());
        });

        searchBookBookingButton.setOnAction(event -> {
            initBookings(searchBooking());
        });

        addBookButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(addBookButton, "/com/alexsis/client/resources/addNewBook.fxml", "Добавь книгу!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addReviewButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(addReviewButton, "/com/alexsis/client/resources/addNewReview.fxml", "Добавь отзыв!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addBookingButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(addBookingButton, "/com/alexsis/client/resources/addNewBooking.fxml", "Добавь бронь!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        checkPersonButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(checkPersonButton, "/com/alexsis/client/resources/people.fxml", "Кто тут в бане?!", null, null);
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
    }

    private void log_out(){
        try {
            ChangeWindow.changeWindow.openWindow(button_log_out,"/com/alexsis/client/resources/log_in.fxml", "Войди!", null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initBooks(LinkedList<Book> books){

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

        user_book_table.setItems(listBooks);
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

    private LinkedList<Book> searchBook(){
        String search = searchBookText.getText();
        LinkedList<Book> bookSearches = new LinkedList<>();
        for(Book book : listBooks){
            if(search.equals(book.getB_name())){
                bookSearches.add(book);
            }
        }
        return bookSearches;
    }

    private LinkedList<Booking> searchBooking(){
        String search = searchBookBookingText.getText();
        LinkedList<Booking> bookingSearches = new LinkedList<>();
        for(Booking booking : listBookings){
            if(search.equals(booking.getB_id())){
                bookingSearches.add(booking);
            }
        }
        return bookingSearches;
    }
}
