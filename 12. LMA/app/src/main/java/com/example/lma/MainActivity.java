package com.example.lma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.lma.databinding.ActivityMainBinding;
import com.example.lma.model.Category;
import com.example.lma.model.Course;
import com.example.lma.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandlers handlers;

    private Category selectedCategory;

    private ArrayList<Category> categoryArrayList;
    private ArrayList<Course> courseArrayList;


    private RecyclerView courseRecyclerView;
    private CourseAdapter courseAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        handlers = new MainActivityClickHandlers();
        activityMainBinding.setClickHandler(handlers);



        mainActivityViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {

            @Override
            public void onChanged(List<Category> categories) {
                categoryArrayList = (ArrayList<Category>) categories;

                for (Category category : categories){
                    Log.i("TAG",category.getCategoryName());
                }

                showOnSpinner();

            }
        });


        mainActivityViewModel.getCoursedOfSelectedCategory(1).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                for (Course course : courses){
                    Log.i("TAG",course.getCourseName());
                }
            }
        });


    }

    private void showOnSpinner() {
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this,R.layout.spinner_item,categoryArrayList);

        categoryArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.setSpinnerAdapter(categoryArrayAdapter);
    }

    public void loadCoursesArrayList(int categoryId){

        mainActivityViewModel.getCoursedOfSelectedCategory(categoryId).observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                courseArrayList = (ArrayList<Course>) courses;
                loadRecyclerView();
            }
        });



    }

    private void loadRecyclerView() {
        courseRecyclerView = activityMainBinding.secondaryLayout.recyclerView;
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseRecyclerView.setHasFixedSize(true);
        courseAdapter = new CourseAdapter();
        courseRecyclerView.setAdapter(courseAdapter);
        courseAdapter.setCourses(courseArrayList);



    }


    public class MainActivityClickHandlers{
        public void onFABClicked(View view){

            Toast.makeText(MainActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();

        }

        public void onSelectItem(AdapterView<?> parent,View view, int pos, long id){
            selectedCategory = (Category) parent.getItemAtPosition(pos);

            String msg = "id is :" + selectedCategory.getId() + "\n name is " + selectedCategory.getCategoryName();

            Toast.makeText(parent.getContext()," "+ msg, Toast.LENGTH_SHORT).show();

            loadCoursesArrayList(selectedCategory.getId());

        }
    }
}