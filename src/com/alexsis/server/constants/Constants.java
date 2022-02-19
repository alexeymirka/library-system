package com.alexsis.server.constants;

public class Constants {
    public static   final int PORT = 8090;

    public static final String  HOST_DATABASE ="jdbc:mysql://localhost:3306/desktop?serverTimezone=Europe/Moscow";
    public static final String  USER_DATABASE ="root";
    public static final String  PASSWORD_DATABASE ="root";

    public static final String USERS = "users";
    public static final String USER_ID="user_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String EMAIL = "email";

    public static final String BOOKS = "books";
    public static final String B_ID = "b_id";
    public static final String B_BOOK = "b_name";
    public static final String B_AUTHOR = "b_author";
    public static final String B_YEAR = "b_year";
    public static final String B_QUANTITY = "b_quantity";

    public static final String PEOPLE = "people";
    public static final String P_ID = "p_id";
    public static final String P_NAME = "person";
    public static final String P_STATUS = "status";

    public static final String REVIEWS = "reviews";
    public static final String R_ID = "r_id";
    public static final String R_USER_ID = "user_id";
    public static final String R_B_ID = "b_id";
    public static final String R_TEXT = "review_text";

    public static final String BOOKING = "booking";
    public static final String BK_ID = "bk_id";
    public static final String BK_USER_ID = "user_id";
    public static final String BK_B_ID = "b_id";
    public static final String BK_P_ID = "p_id";
    public static final String DATE = "date";
}
