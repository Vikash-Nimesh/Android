/*
 * *
 *  * Created by guruLabs on 12/31/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/31/20 5:53 PM
 *
 */

package in.gurulabs.timecare.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {TimeTable.class},version = 1,exportSchema = false)
public abstract class TimeTableRoomDatabase extends RoomDatabase {


    public abstract TimeTableDao timeTableDao();

    private static volatile TimeTableRoomDatabase INSTANCE;


    public static TimeTableRoomDatabase getInstance(Context context){


        if(INSTANCE==null){
            synchronized (TimeTableRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), TimeTableRoomDatabase.class,"time_table_db")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
