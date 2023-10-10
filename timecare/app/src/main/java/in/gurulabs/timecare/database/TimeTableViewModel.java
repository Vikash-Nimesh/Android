/*
 * *
 *  * Created by guruLabs on 12/31/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/31/20 5:53 PM
 *
 */

package in.gurulabs.timecare.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TimeTableViewModel extends AndroidViewModel {

    TimeTableRoomDatabase timeTableRoomDatabase;
    public TimeTableViewModel(@NonNull Application application) {
        super(application);
        timeTableRoomDatabase = TimeTableRoomDatabase.getInstance(application.getApplicationContext());
    }

    public LiveData<List<TimeTable>> getSundayTimeTable(){
        return timeTableRoomDatabase.timeTableDao().findSundayTimeTable();
    }

    public LiveData<List<TimeTable>> getMondayTimeTable(){
        return timeTableRoomDatabase.timeTableDao().findMondayTimeTable();
    }

    public LiveData<List<TimeTable>> getTuesdayTimeTable(){
        return timeTableRoomDatabase.timeTableDao().findTuesdayTimeTable();
    }

    public LiveData<List<TimeTable>> getWednesdayTimeTable(){
        return timeTableRoomDatabase.timeTableDao().findWednesdayTimeTable();
    }

    public LiveData<List<TimeTable>> getThursdayTimeTable(){
        return timeTableRoomDatabase.timeTableDao().findThursdayTimeTable();
    }

    public LiveData<List<TimeTable>> getFridayTimeTable(){
        return timeTableRoomDatabase.timeTableDao().findFridayTimeTable();
    }

    public LiveData<List<TimeTable>> getSaturdayTimeTable(){
        return timeTableRoomDatabase.timeTableDao().findSaturdayTimeTable();
    }

    public LiveData<List<TimeTable>> getSearchedTimeTable(String text){
        return timeTableRoomDatabase.timeTableDao().getSearchedTimeTable(text);
    }

}
