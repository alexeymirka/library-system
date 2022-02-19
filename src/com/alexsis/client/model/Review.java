package com.alexsis.client.model;

public class Review {
    private int r_id;
    private String user_id;
    private String b_id;
    private String text;

    public Review(int r_id, String user_id, String b_id, String text) {
        this.r_id = r_id;
        this.user_id = user_id;
        this.b_id = b_id;
        this.text = text;
    }

    public Review(String user_id, String b_id, String text) {
        this.user_id = user_id;
        this.b_id = b_id;
        this.text = text;
    }

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getB_id() {
        return b_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Review{" +
                "r_id=" + r_id +
                ", user_id='" + user_id + '\'' +
                ", b_id='" + b_id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
