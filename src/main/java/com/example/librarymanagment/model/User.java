package com.example.librarymanagment.model;

public class User {
    private int user_id;
    private String name;
    private String username;
    private String password;
    private String phone;

    private Double outstanding_fines;

    //private String address;
    //private ArrayList<Book> borrowbooks = new ArrayList<Book>();

    public User(int user_id, String name, String username, String password, String phone) {
        setUser_id(user_id);
        setName(name);
        setUsername(username);
        setPassword(password);
        setPhone(phone);
    }

    public User(int id, String user, String pass) {
    }




    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getOutstanding_fines() {
        return outstanding_fines;
    }

    public void setOutstanding_fines(Double outstanding_fines) {
        this.outstanding_fines = outstanding_fines;
    }
}
