package com.example.librarymanagment.model;

public class User {
    private int user_id;
    private String name;
    private String username;
    private String password;
    private String email;

    private Double outstanding_fines;

    //private String address;
    //private ArrayList<Book> borrowbooks = new ArrayList<Book>();

    public User(int user_id, String name, String username, String password, String email) {
        setUser_id(user_id);
        setName(name);
        setUsername(username);
        setPassword(password);
        setEmail(email);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
