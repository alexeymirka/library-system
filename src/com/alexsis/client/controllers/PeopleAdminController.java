package com.alexsis.client.controllers;

import com.alexsis.client.actions.Client;
import com.alexsis.client.model.People;
import com.alexsis.client.windowsAlert.AlertWindow;
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
import java.util.HashMap;
import java.util.LinkedList;

public class PeopleAdminController {

    @FXML
    private Button backButton;

    @FXML
    private Button choosePersonButton;

    @FXML
    private Button deletePersonButton;

    @FXML
    private Button addPersonButton;

    @FXML
    private TableColumn<People, Integer> p_id;

    @FXML
    private TableColumn<People, String> p_name;

    @FXML
    private TableColumn<People, String> p_status;

    @FXML
    private TableView<People> person_table;

    @FXML
    private Button redactionPersonButton;

    @FXML
    private Button searchPersonButton;

    @FXML
    private TextField searchPersonText;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_status;

    @FXML
    private Button updateButton;

    private String person;
    private String status;

    private final ObservableList<People> listPeople = FXCollections.observableArrayList();
    private ObservableList<People> listPeopleToShow = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        initPeopleTextField();

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

        choosePersonButton.setOnAction(event -> {
            int count = person_table.getSelectionModel().getSelectedCells().get(0).getRow();
            System.out.println(count);
            tf_name.setText(listPeople.get(count).getP_name());
            tf_status.setText(listPeople.get(count).getP_status());
        });

        redactionPersonButton.setOnAction(event -> {
            person = tf_name.getText();
            status = tf_status.getText();

            int count = person_table.getSelectionModel().getSelectedCells().get(0).getRow();
            if (!person.equals("") && !status.equals("")) {
                if (status.equals("Зеленый список") || status.equals("Красный список") || status.equals("Черный список")) {
                    Client.interactionsWithServer.redactionPeople(listPeople.get(count).getP_id(), person, status);
                    try {
                        initPeople(Client.interactionsWithServer.allPeople());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    initPeopleTextField();
                } else {
                    AlertWindow.alertWindow.alertWindow("Статус может быть только: Зеленый список/Красный список/Черный список!");
                }
            } else {
                AlertWindow.alertWindow.alertWindow("Заполните все ячейки!");
            }
        });

        addPersonButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(addPersonButton, "/com/alexsis/client/resources/addNewPersonAdmin.fxml", "Добавь посетителя!", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        deletePersonButton.setOnAction(event -> {
            int count = person_table.getSelectionModel().getSelectedCells().get(0).getRow();
            Client.interactionsWithServer.deletePeople(listPeople.get(count).getP_id());
            try {
                initPeople(Client.interactionsWithServer.allPeople());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButton.setOnAction(event -> {
            try {
                ChangeWindow.changeWindow.openWindow(backButton, "/com/alexsis/client/resources/admin_window.fxml", "Посмотрели и хватит?", null, null);
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
            String p_status = listPeople.get(i).getP_status();

            People person = new People(id, name, p_status);
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

    private void initPeopleTextField() {
        tf_name.setText("");
        tf_status.setText("");
    }
}
