package com.example.contactsmanager2.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.contactsmanager2.Contact;

import java.util.List;

@Dao
public interface ContactsDAO {

    @Insert
    void insertContact(Contact contact);

    @Query("SELECT * FROM contact")
    List<Contact> getAllContacts();


    @Delete
    void deleteContact(Contact contact);


}
