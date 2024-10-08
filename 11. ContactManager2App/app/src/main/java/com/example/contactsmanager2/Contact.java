package com.example.contactsmanager2;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;

@Entity(tableName = "contact")
public class Contact extends BaseObservable {

    String name;
    String email;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
