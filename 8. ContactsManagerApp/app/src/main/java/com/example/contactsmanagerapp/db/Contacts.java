package com.example.contactsmanagerapp.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contacts {


    @ColumnInfo(name = "contact_name")
    private String contact_name;
    @ColumnInfo(name = "contact_email")
    private String contact_email;
    @ColumnInfo(name = "contact_id")
    @PrimaryKey(autoGenerate = true)
    private int contact_id;

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
