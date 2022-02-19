package com.alexsis.client.model;

public class Booking {
    private int bk_id;
    private String user_id;
    private String b_id;
    private String p_id;
    private String date;

    public Booking (int bk_id, String user_id, String b_id, String p_id, String date) {
        this.bk_id = bk_id;
        this.user_id = user_id;
        this.b_id = b_id;
        this.p_id = p_id;
        this.date = date;
    }

    public int getBk_id() {
        return bk_id;
    }

    public void setBk_id(int bk_id) {
        this.bk_id = bk_id;
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

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bk_id=" + bk_id +
                ", user_id='" + user_id + '\'' +
                ", b_id='" + b_id + '\'' +
                ", p_id='" + p_id + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
