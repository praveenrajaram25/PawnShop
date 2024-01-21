package com.source.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="USER")
@Entity
public class User {

    @Id
    @Column(name="ID")
    private int id;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="PASSWORD")
    private  String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
