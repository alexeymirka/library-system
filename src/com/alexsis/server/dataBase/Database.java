package com.alexsis.server.dataBase;

import com.alexsis.server.constants.Constants;
import java.util.LinkedList;
import java.sql.*;

public class Database {

    private  Connection connection;
    private  Statement statement;

    public Database() {
        connectionToDB();
        //Tables tables = new Tables(connection,statement);
       // tables.createTablesInDataBase();
    }

    public void connectionToDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(Constants.HOST_DATABASE, Constants.USER_DATABASE, Constants.PASSWORD_DATABASE);
            statement= connection.createStatement();

            System.out.println("Database connection is done");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> showAllBooks(){

        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT "+Constants.B_ID +" , " + Constants.B_BOOK +" , " + Constants.B_AUTHOR +" , " +Constants.B_YEAR +" , "
                +Constants.B_QUANTITY + " FROM " + Constants.BOOKS;
        ResultSet rs = null;
        String contribution="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {
                contribution+=rs.getString(Constants.B_ID)+";";
                contribution+=rs.getString(Constants.B_BOOK)+";";
                contribution+=rs.getString(Constants.B_AUTHOR)+";";
                contribution+=rs.getString(Constants.B_YEAR)+";";
                contribution+=rs.getString(Constants.B_QUANTITY)+";";
                list.add(contribution);
                contribution="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void addBookInDataBase(String b_name, String b_author, String b_year, String b_quantity){

        try {
            String query = " insert into "+Constants.BOOKS + " (b_name, b_author, b_year, b_quantity)"
                    + " values (?,?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, b_name);
            preparedStmt.setString (2, b_author);
            preparedStmt.setString (3, b_year);
            preparedStmt.setString (4, b_quantity);


            preparedStmt.executeUpdate();
            System.out.println("Новая книга была добавлена ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteBookInDataBase(int b_id)  {
        System.out.println(b_id);
        String selectSQL = "DELETE FROM "+Constants.BOOKS +  " WHERE b_id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, b_id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public  void redactionBookInDataBase(int b_id, String b_name, String b_author, String b_year, String b_quantity)  {

        String query = "UPDATE "+Constants.BOOKS +" SET b_name  = ?, b_author = ?, b_year = ? ,b_quantity = ?   WHERE b_id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString   (1, b_name);
            preparedStmt.setString   (2, b_author);
            preparedStmt.setString(3, b_year);
            preparedStmt.setString(4, b_quantity);
            preparedStmt.setInt(5, b_id);

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void appendNewUserInDatabase(String username, String email,String password,String role){

        try {
            String query = " insert into "+Constants.USERS+ " (username,password, role, email)"
                    + " values (?,?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, username);
            preparedStmt.setString (2, password);
            preparedStmt.setString (3, role);
            preparedStmt.setString (4, email);


            preparedStmt.executeUpdate();
            System.out.println("Новый пользователь был добавлен !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String checkLoginInDataBase(String username) {
        String user="";
        try {
            String query = "SELECT " + Constants.USERNAME + " FROM " + Constants.USERS +
                    " WHERE " + Constants.USERNAME + " = " + "'" + username + "'" ;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {

                while (rs.next()) {
                    user += rs.getString(Constants.USERNAME) + " ";
                }
            } else {
                return username;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Пользователь c таким логином уже зарегистрирован!");
        return "false";
        //}
    }


    public String verificationUserInDataBase(String username, String password){
        String user="";
        try {
            String query = "SELECT " + Constants.USERNAME + "," + Constants.PASSWORD+","+Constants.USER_ID+","+Constants.ROLE + " FROM " + Constants.USERS +
                    " WHERE " + Constants.USERNAME + " = " + "'" + username + "'" + " AND " + Constants.PASSWORD + " = " + "'" + password + "'";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                user+=rs.getString(Constants.USERNAME)+" ";
                user+=rs.getString(Constants.PASSWORD)+" ";
                user+=rs.getString(Constants.ROLE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user.equals("")) {
            System.out.println("Пользователь не найден !");
            return "false";
        }
        else {
            return user;
        }
    }

    public LinkedList<String> showAllUsers() {

        LinkedList<String> users = new LinkedList<>();
        String query = "SELECT "+Constants.USER_ID+" , " + Constants.USERNAME+" , "+Constants.EMAIL+" , "
                + Constants.PASSWORD+" , " + Constants.ROLE+ " FROM " + Constants.USERS ;
        ResultSet rs = null;
        String user="";

        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {

                user+=rs.getString(Constants.USER_ID)+";";
                user+=rs.getString(Constants.USERNAME)+";";
                user+=rs.getString(Constants.EMAIL)+";";
                user+=rs.getString(Constants.PASSWORD)+";";
                user+=rs.getString(Constants.ROLE)+";";
                users.add(user);
                user="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


    public void deleteUserInDataBase(int user_id)  {
        System.out.println(user_id);
        String selectSQL = "DELETE FROM "+Constants.USERS +  " WHERE user_id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, user_id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void redactionUserDatabase(int user_id,String username,String email ,String password,String role )  {

        String query = "UPDATE users SET username  = ?,email = ?,password = ? , role = ?   WHERE user_id = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString   (1, username);
            preparedStmt.setString(4, role);
            preparedStmt.setString(3, password);
            preparedStmt.setString(2, email);
            preparedStmt.setInt(5, user_id);

            preparedStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addReviewInDataBase (int user_id, int b_id, String review_text) {
        try {
            String query = " insert into "+Constants.REVIEWS+ " (user_id,b_id,review_text)"
                    + " values (?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, user_id);
            preparedStmt.setInt (2, b_id);
            preparedStmt.setString (3, review_text);


            preparedStmt.executeUpdate();
            System.out.println("Новый отзыв был добавлен !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public LinkedList<String> showAllReviews(){

        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT r_id, users.username,books.b_name,reviews.review_text FROM reviews " +
                "INNER JOIN users ON users.user_id = reviews.user_id " +
                "INNER JOIN books ON books.b_id = reviews.b_id " +
                "WHERE users.user_id = reviews.user_id AND books.b_id = reviews.b_id";
        ResultSet rs = null;
        String contribution="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {
                contribution+=rs.getString(Constants.R_ID)+";";
                contribution+=rs.getString(Constants.USERNAME)+";";
                contribution+=rs.getString(Constants.B_BOOK)+";";
                contribution+=rs.getString(Constants.R_TEXT)+";";
                list.add(contribution);
                contribution="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void deleteReviewInDataBase(int r_id)  {
        System.out.println(r_id);
        String selectSQL = "DELETE FROM "+Constants.REVIEWS +  " WHERE r_id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, r_id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addBookingInDataBase (int user_id, int b_id, int p_id, String date) {
        try {
            String query = " insert into "+Constants.BOOKING+ " (user_id,b_id,p_id,date)"
                    + " values (?,?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, user_id);
            preparedStmt.setInt (2, b_id);
            preparedStmt.setInt (3, p_id);
            preparedStmt.setString (4, date);


            preparedStmt.executeUpdate();
            System.out.println("Новая бронь была добавлена !");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public LinkedList<String> showAllBooking () {

        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT booking.bk_id, users.username,books.b_name,people.person,booking.date FROM booking " +
                "INNER JOIN users ON users.user_id = booking.user_id " +
                "INNER JOIN books ON books.b_id = booking.b_id " +
                "INNER JOIN people ON people.p_id = booking.p_id " +
                "WHERE users.user_id = booking.user_id AND books.b_id = booking.b_id AND people.p_id = booking.p_id";
        ResultSet rs = null;
        String contribution="";
        try {
            rs = statement.executeQuery(query);

            while (rs.next()) {
                contribution+=rs.getInt(Constants.BK_ID)+";";
                contribution+=rs.getString(Constants.USERNAME)+";";
                contribution+=rs.getString(Constants.B_BOOK)+";";
                contribution+=rs.getString(Constants.P_NAME)+";";
                contribution+=rs.getString(Constants.DATE)+";";
                list.add(contribution);
                contribution="";
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void deleteBookingInDataBase(int bk_id)  {
        System.out.println(bk_id);
        String selectSQL = "DELETE FROM "+Constants.BOOKING +  " WHERE bk_id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, bk_id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public LinkedList<String> showAllPeople () {

        LinkedList<String> list = new LinkedList<>();
        String query = "SELECT " + Constants.P_ID + " , " + Constants.P_NAME + " , " + Constants.P_STATUS + " FROM " + Constants.PEOPLE ;
        ResultSet rs = null;
        String person="";
        try {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                person+=rs.getInt(Constants.P_ID)+";";
                person+=rs.getString(Constants.P_NAME)+";";
                person+=rs.getString(Constants.P_STATUS)+";";
                list.add(person);
                person="";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void deletePeopleInDataBase(int p_id)  {
        System.out.println(p_id);
        String selectSQL = "DELETE FROM "+Constants.BOOKING +  " WHERE p_id = ?";
        try {
            connection.prepareStatement(selectSQL);
            PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
            preparedStmt.setInt(1, p_id);
            preparedStmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void addPersonInDataBase(String person, String status) {
        try {
            String query = " insert into " + Constants.PEOPLE + " (person,status)"
                    + " values (?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, person);
            preparedStmt.setString(2, status);

            preparedStmt.executeUpdate();
            System.out.println("Новый посетитель был добавлен !");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    public String checkPersonInDataBase(String person_name) {
        String user="";
        try {
            String query = "SELECT " + Constants.P_NAME + " FROM " + Constants.PEOPLE +
                    " WHERE " + Constants.P_NAME + " = " + "'" + person_name + "'" ;

            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {

                while (rs.next()) {
                    user += rs.getString(Constants.P_NAME) + " ";
                }
            } else {
                return person_name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Пользователь c таким логином уже зарегистрирован!");
        return "false";
    }
}
