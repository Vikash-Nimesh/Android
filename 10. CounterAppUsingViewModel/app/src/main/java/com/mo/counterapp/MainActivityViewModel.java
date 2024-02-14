package com.mo.counterapp;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private int counter = 0;


    public int getInitialCounter(){
        return  counter;


    }public int increaseCounter(){

        counter++;
        return  counter;
    }

}
