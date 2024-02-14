package com.example.lma.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lma.model.Category;
import com.example.lma.model.Course;
import com.example.lma.model.CourseShopRepository;

import java.util.List;


/*
Finally I got something a simpler explanation, a bit...... ...The AndroidViewModel class is a subclass of ViewModel
and similar to them, they are designed to store and manage UI-related data are responsible to prepare & provide data
for UI and automatically allow data to survive configuration change.

The only difference with AndroidViewModel is it comes with the application context, which is helpful if you require
context to get a system service or have a similar requirement. the bold text makes it clearer to sense it.
*/

public class MainActivityViewModel extends AndroidViewModel {

    //Repository
    private CourseShopRepository repository;


    //LiveData
    private LiveData<List<Category>> allCategories;
    private LiveData<List<Course>> coursedOfSelectedCategory;



    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new CourseShopRepository(application);
    }


    public LiveData<List<Category>> getAllCategories(){

        allCategories = repository.getCategories();
        return allCategories;
    }

    public LiveData<List<Course>> getCoursedOfSelectedCategory(int categoryId){

        coursedOfSelectedCategory = repository.getCourses(categoryId);
        return coursedOfSelectedCategory;
    }

    public void addNewCourse(Course course){
        repository.insertCourse(course);
    }
    public void updateCourse(Course course){
        repository.updateCourse(course);
    }
    public void deleteCourse(Course course){
        repository.deleteCourse(course);
    }


}
