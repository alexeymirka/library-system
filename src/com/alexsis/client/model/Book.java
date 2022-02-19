package com.alexsis.client.model;

public class Book {
    private int b_id;
    private String b_name;
    private String b_author;
    private String b_year;
    private String b_quantity;

    public Book(int b_id, String b_name, String b_author, String b_year, String b_quantity) {
        this.b_id = b_id;
        this.b_name = b_name;
        this.b_author = b_author;
        this.b_year = b_year;
        this.b_quantity = b_quantity;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_author() {
        return b_author;
    }

    public void setB_author(String b_author) {
        this.b_author = b_author;
    }

    public String getB_year() {
        return b_year;
    }

    public void setB_year(String b_year) {
        this.b_year = b_year;
    }

    public String getB_quantity() {
        return b_quantity;
    }

    public void setB_quantity(String b_quantity) {
        this.b_quantity = b_quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "b_id=" + b_id +
                ", b_name='" + b_name + '\'' +
                ", b_author='" + b_author + '\'' +
                ", b_year='" + b_year + '\'' +
                ", b_quantity='" + b_quantity + '\'' +
                '}';
    }

}