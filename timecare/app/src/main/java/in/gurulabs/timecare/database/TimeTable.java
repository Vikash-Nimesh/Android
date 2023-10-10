/*
 * *
 *  * Created by guruLabs on 12/31/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/31/20 5:53 PM
 *
 */

package in.gurulabs.timecare.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "time_table")
public class TimeTable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "subject_name")
    private String title;

    @ColumnInfo(name = "subject_desc")
    private String description;

    @ColumnInfo(name = "day")
    private int day;



    @ColumnInfo(name = "from_time")
    private String fromTime;

    @ColumnInfo(name = "to_time")
    private String toTime;


    @ColumnInfo(name = "is_notification_checked")
    private Boolean isNotificationChecked;


    @ColumnInfo(name = "notificaiton_priority")
    private int notificationPriority;

    @ColumnInfo(name = "alarm_notify_time")
    private long alarm_notify_time;


    @ColumnInfo(name = "time_in_24")
    private long time_in_24;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public Boolean getNotificationChecked() {
        return isNotificationChecked;
    }

    public void setNotificationChecked(Boolean notificationChecked) {
        isNotificationChecked = notificationChecked;
    }

    public int getNotificationPriority() {
        return notificationPriority;
    }

    public void setNotificationPriority(int notificationPriority) {
        this.notificationPriority = notificationPriority;
    }

    public long getAlarm_notify_time() {
        return alarm_notify_time;
    }

    public void setAlarm_notify_time(long alarm_notify_time) {
        this.alarm_notify_time = alarm_notify_time;
    }

    public long getTime_in_24() {
        return time_in_24;
    }

    public void setTime_in_24(long time_in_24) {
        this.time_in_24 = time_in_24;
    }
}
