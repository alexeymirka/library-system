package com.alexsis.client.model;

public class People {
    private int p_id;
    private String p_name;
    private String p_status;

    public static  People  currentPeople;

    public People(int p_id, String p_name, String p_status) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_status = p_status;
    }

    public People(String p_name, String p_status) {
        this.p_name = p_name;
        this.p_status = p_status;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_status() {
        return p_status;
    }

    public void setP_status(String p_status) {
        this.p_status = p_status;
    }

    public static People getCurrentPeople() {
        return currentPeople;
    }

    public static void setCurrentPeople(People currentPeople) {
        People.currentPeople = currentPeople;
    }

    @Override
    public String toString() {
        return "People{" +
                "p_id=" + p_id +
                ", p_name='" + p_name + '\'' +
                ", p_status='" + p_status + '\'' +
                '}';
    }
}
