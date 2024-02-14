package com.example.contactsmanagerapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Contacts.class},version = 1,exportSchema = false)
public abstract class ContractsRoomDatabase extends RoomDatabase {

    public abstract ContactsDAO contactsDAO();

    private static volatile ContractsRoomDatabase INSTANCE;


    public static ContractsRoomDatabase getInstance(Context context){


        if(INSTANCE==null){
            synchronized (ContractsRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), ContractsRoomDatabase.class,"contracts_db")
                            .build();

                }
            }
        }
        return INSTANCE;
    }


}
