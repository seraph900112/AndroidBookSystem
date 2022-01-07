package com.example.myproject;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myproject.DAO.login_dao;
import com.example.myproject.DAO.main_dao;
import com.example.myproject.Model.Book;
import com.example.myproject.Model.User;
import com.example.myproject.Model.login_user;


@Database(entities = {login_user.class, User.class, Book.class}, version = 2, exportSchema = false)
public abstract class Project_DataBase extends RoomDatabase {


    public abstract login_dao loginDao();

    public abstract main_dao main_dao();

    private static Project_DataBase INSTANCE;

    public static Project_DataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (Project_DataBase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Project_DataBase.class, "Project")
                        .fallbackToDestructiveMigration()
                        .addCallback(initDB)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback initDB = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new initDB(INSTANCE).execute();
        }
    };


    private static class initDB extends AsyncTask<Void, Void, Void> {
        private final login_dao login_dao;
        private final main_dao main_dao;

        initDB(Project_DataBase db) {
            login_dao = db.loginDao();
            main_dao = db.main_dao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            login_dao.deleteAll();
            main_dao.deleteAllUser();
            main_dao.deleteAllBook();
            login_user[] init = new login_user[3];
            User[] initUser = new User[3];

            init[0] = new login_user(1, "Andy", "123456");
            init[1] = new login_user(2, "Betty", "123456");
            init[2] = new login_user(3, "Cindy", "123456");
            initUser[0] = new User(1, "Andy", '男', "2014-3-17");
            initUser[1] = new User(2, "Betty", '女', "2001-01-12");
            initUser[2] = new User(3, "Cindy", '女', "1998-08-18");

            for (int i = 0; i < 3; i++) {
                main_dao.insert(initUser[i]);
                login_dao.insert(init[i]);
            }
            for (int i = 0; i < 30; i++) {
                Book book = new Book(i, "Book" + i, "SCI-FI", "Writer" + i);
                main_dao.insert(book);
            }
            return null;
        }
    }

}
