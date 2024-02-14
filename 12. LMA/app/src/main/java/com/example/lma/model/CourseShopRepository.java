package com.example.lma.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseShopRepository {


    private CategoryDAO categoryDAO;
    private CourseDAO courseDAO;

    private LiveData<List<Category>> categories;
    private LiveData<List<Course>> courses;

    public CourseShopRepository(Application application){

        CourseDatabase courseDatabase = CourseDatabase.getInstance(application);

        categoryDAO = courseDatabase.categoryDAO();
        courseDAO = courseDatabase.courseDAO();


    }

    public LiveData<List<Category>> getCategories(){
        return categoryDAO.getAllCategories();

    }

    public LiveData<List<Course>> getCourses(int categoryId){
        return courseDAO.getCourse(categoryId);

    }

    public void insertCategory(Category category){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.insert(category);
            }
        });


    }


    public void deleteCategory(Category category){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.delete(category);
            }
        });


    }

    public void updateCategory(Category category){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.update(category);
            }
        });


    }

    public void insertCourse(Course course){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.insert(course);
            }
        });


    }

    public void deleteCourse(Course course){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.delete(course);
            }
        });


    }

    public void updateCourse(Course course){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                courseDAO.update(course);
            }
        });


    }


}
