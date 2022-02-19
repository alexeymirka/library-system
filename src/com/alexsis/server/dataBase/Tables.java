/*package com.alexsis.server.dataBase;

import com.alexsis.server.constants.Constants;

import java.sql.*;

public class Tables {

    private final Connection connection;
    private final Statement statement;

    public Tables(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;

    }

    public void createTablesInDataBase(){
        addTableUsers();
        addTableBooks();
        addTableAuthors();
        addTableReviews();
        addTableBooking();
    }

    private void addTableUsers(){
        if(tableExists(Constants.USERS)) {
            try {
                String SQL = "CREATE TABLE "+ Constants.USERS +
                        "( " +
                        " user_id SERIAL PRIMARY KEY," +
                        " username VARCHAR (45), " +
                        " password VARCHAR (45), " +
                        " role VARCHAR (45)"+
                        " email VARCHAR (45), " +
                        ")";
                System.out.println("Table #1 created!");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTableBooks(){
        if(tableExists(Constants.BOOKS)) {
            try {
                String SQL = "CREATE TABLE "+ Constants.BOOKS +
                        "( " +
                        " b_id  SERIAL PRIMARY KEY," +
                        " name VARCHAR (45), " +
                        " author VARCHAR (45), " +
                        " year INT, " +
                        " quantity INT, " +
                        ")";
                System.out.println("Table #2 created !");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTableAuthors(){
        if(tableExists(Constants.AUTHORS)) {
            try {
                String SQL = "CREATE TABLE "+ Constants.AUTHORS +
                        "( " +
                        " a_id  SERIAL PRIMARY KEY," +
                        " author VARCHAR (45), " +
                        " genre VARCHAR (45), " +
                        " country VARCHAR (45), " +
                        ")";
                System.out.println("Table #3 created !");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTableReviews(){
        if(tableExists(Constants.REVIEWS)) {
            try {
                String SQL = "CREATE TABLE "+ Constants.REVIEWS +
                        "( " +
                        " r_id  SERIAL PRIMARY KEY," +
                        " user_id INT, " +
                        " b_id INT, " +
                        " review_text VARCHAR (45), " +
                        ")";
                System.out.println("Table #4 created !");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addTableBooking(){
        if(tableExists(Constants.BOOKING)) {
            try {
                String SQL = "CREATE TABLE "+ Constants.BOOKING +
                        "( " +
                        " bk_id  SERIAL PRIMARY KEY," +
                        " user_id INT, " +
                        " b_id INT, " +
                        " date INT, " +
                        ")";
                System.out.println("Table #5 created !");
                statement.executeUpdate(SQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean tableExists(String nameTable){

        try{
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, nameTable, null);
            rs.last();
            return rs.getRow() <= 0;
        }catch(SQLException ignored){

        }
        return true;
    }
}
*/