package com.example.marghclone;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_layout, container, false);

        final ViewPager2 videosViewPager = view.findViewById(R.id.MainViewPager);

        // creating array list
        List<VideoAct> videoActList = new ArrayList<>();

        VideoAct videoItems1 = new VideoAct();
        videoItems1.videoURL = "https://images.pexels.com/photos/433989/pexels-photo-433989.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
        videoActList.add(videoItems1);

        VideoAct videoItems2 = new VideoAct();
        videoItems2.videoURL = "https://i0.wp.com/digital-photography-school.com/wp-content/uploads/2013/08/1.-Moraine_final.jpg?w=600&h=1260&ssl=1";
        videoActList.add(videoItems2);

        VideoAct videoItems3 = new VideoAct();
        videoItems3.videoURL = "https://cdn.pixabay.com/photo/2016/08/03/06/22/space-1565986_640.jpg";
        videoActList.add(videoItems3);

        VideoAct videoItems5 = new VideoAct();
        videoItems5.videoURL = "https://img.freepik.com/free-photo/beautiful-vertical-shot-river-flowing-trees-stones_181624-2699.jpg";
        videoActList.add(videoItems5);

        VideoAct videoItems4 = new VideoAct();
        videoItems4.videoURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzgoiBy-29j2bmz_0OLJVR4CxWpShdut61-A&usqp=CAU";
        videoActList.add(videoItems4);

        // here you can add more videos of your choice

        // adapter
        videosViewPager.setAdapter(new VideoAdapter(getContext(),videoActList));



        return view;
    }


}