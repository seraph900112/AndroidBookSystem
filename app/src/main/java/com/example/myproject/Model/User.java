package com.example.myproject.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "user",
        indices = {@Index(value = "userId", unique = true), @Index(value = "userName", unique = true)},
        primaryKeys = {"userId", "userName"})
public class User {

    @ColumnInfo(name = "userId")
    private int userid;


    @NonNull
    @ColumnInfo(name = "userName")
    private String username;

    @ColumnInfo(name = "gender")
    private char gender;

    @ColumnInfo(name = "birth")
    private String birth;

    public User(int userid, @NonNull String username, char gender, String birth) {
        this.userid = userid;
        this.username = username;
        this.gender = gender;
        this.birth = birth;
    }


    public int getUserid() {
        return userid;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public char getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
