package com.alexsis.client.controllers;

import com.alexsis.client.actions.Client;
import com.alexsis.client.model.Book;
import com.alexsis.client.model.People;
import com.alexsis.client.windowsAlert.ChangeWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.LinkedList;

public class PeopleController {

    @FXML
    private TableView<People> person_table;

    @FXML
    private Button backButton;

    @FXML
    private Button updateButton;
    @FXML
    private Button addPersonButton;

    @FXML
    private TableColumn<People, String> p_status;

    @FXML
    private TableColumn<People, Integer> p_id;

    @FXML
    private TableColumn<People, String> p_name;

    @FXML
    private Button searchPersonButton;

    @FXML
    private TextField searchPersonText;

    private final ObservableList<People> listPeople = FXCollections.observableArrayList();
    private ObservableList<People> listPeopleToShow = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initPeople(Client.interactionsWithServer.allPeople());
        } catch (IOException e) {
            e.printStackTrace();
        }

        searchPersonButton.setOnAction(event -> {
            initPeople(searchPeople());
        });

        updateButton.setOnAction(event -> {
            try {
                initPeople(Client.interactionsWithServer.allPeople());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addPersonButton.setOnAction(event -> {
        try {
        ChangeWindow.changeWindow.openWindow(addPersonButton, "/com/alexsis/client/resources/addNewPerson.fxml", "Добавь посетителя!", null, null);
        } catch (IOException e) {
        e.printStackTrace();
        }
        });

        backButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(backButton, "/com/alexsis/client/resources/user_window.fxml", "Посмотрели и хватит?", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initPeople(LinkedList<People> people){

        listPeople.clear();
        listPeople.addAll(people);

        listPeopleToShow = FXCollections.observableArrayList();
        for (int i = 0; i < listPeople.size(); i++) {
            int id = listPeople.get(i).getP_id();
            String name = listPeople.get(i).getP_name();
            String status = listPeople.get(i).getP_status();

            People person = new People(id, name, status);
            listPeopleToShow.add(person);
        }

        p_id.setCellValueFactory(new PropertyValueFactory<People, Integer>("p_id"));
        p_name.setCellValueFactory(new PropertyValueFactory<People, String>("p_name"));
        p_status.setCellValueFactory(new PropertyValueFactory<People, String>("p_status"));

        person_table.setItems(listPeople);
    }

    private LinkedList<People> searchPeople(){
        String search = searchPersonText.getText();
        LinkedList<People> personSearches = new LinkedList<>();
        for(People person : listPeople){
            if(search.equals(person.getP_name())){
                personSearches.add(person);
            }
        }
        return personSearches;
    }

}
