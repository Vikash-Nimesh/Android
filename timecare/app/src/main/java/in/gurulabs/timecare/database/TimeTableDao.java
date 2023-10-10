/*
 * *
 *  * Created by guruLabs on 12/31/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/31/20 5:53 PM
 *
 */

package in.gurulabs.timecare.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TimeTableDao {

    @Insert
    void insertTimeTable(TimeTable timeTable);

    @Query("SELECT * FROM time_table")
    List<TimeTable> getAllTimeTables();


    @Query("SELECT * FROM time_table WHERE day LIKE :day")
    List<TimeTable> getDistinctTimeTable(int day);

    @Query("SELECT * FROM time_table WHERE uid LIKE :uid")
    TimeTable findTimeTableById(int uid);

    @Update
    void updateTimeTable(TimeTable timeTable);

    @Query("SELECT * FROM time_table WHERE day = 0 ORDER BY time_in_24 ASC")
    LiveData<List<TimeTable>> findSundayTimeTable();

    @Query("SELECT * FROM time_table WHERE day = 1 ORDER BY time_in_24 ASC")
    LiveData<List<TimeTable>> findMondayTimeTable();

    @Query("SELECT * FROM time_table WHERE day = 2 ORDER BY time_in_24 ASC")
    LiveData<List<TimeTable>> findTuesdayTimeTable();

    @Query("SELECT * FROM time_table WHERE day = 3 ORDER BY time_in_24 ASC")
    LiveData<List<TimeTable>> findWednesdayTimeTable();

    @Query("SELECT * FROM time_table WHERE day = 4 ORDER BY time_in_24 ASC")
    LiveData<List<TimeTable>> findThursdayTimeTable();

    @Query("SELECT * FROM time_table WHERE day = 5 ORDER BY time_in_24 ASC")
    LiveData<List<TimeTable>> findFridayTimeTable();

    @Query("SELECT * FROM time_table WHERE day = 6 ORDER BY time_in_24 ASC")
    LiveData<List<TimeTable>> findSaturdayTimeTable();

    @Delete
    void deleteTimeTable(TimeTable timeTable);

    @Query("SELECT * FROM time_table WHERE is_notification_checked = 1")
    List<TimeTable> findNotificationSent();

    @Query("SELECT * FROM time_table WHERE subject_name LIKE'%' || :text || '%'")
    LiveData<List<TimeTable>> getSearchedTimeTable(String text);

}
