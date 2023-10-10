package com.example.contactsmanagerapp.db;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface ContactsDAO {

    @Insert
    void insertContact(Contacts contacts);

    @Query("SELECT * FROM contacts")
    List<Contacts> getAllContacts();


}
