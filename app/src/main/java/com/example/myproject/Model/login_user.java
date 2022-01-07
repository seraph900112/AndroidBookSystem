package com.example.myproject.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "login_user",
        indices = {@Index(value = {"userName"}), @Index(value = {"userId"}, unique = true)},
        primaryKeys = {"userId", "userName"},
        foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class, parentColumns = "userName", childColumns = "userName", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})
public class login_user {

    @ColumnInfo(name = "userId")
    private int userId;

    @NonNull
    @ColumnInfo(name = "userName")
    private String username;

    @NonNull
    @ColumnInfo(name = "passwd")
    private String passwd;

    public login_user(int userId, @NonNull String username, @NonNull String passwd) {
        this.userId = userId;
        this.username = username;
        this.passwd = passwd;
    }

    public int getUserId() {
        return userId;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPasswd() {
        return passwd;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setPasswd(@NonNull String passwd) {
        this.passwd = passwd;
    }
}
