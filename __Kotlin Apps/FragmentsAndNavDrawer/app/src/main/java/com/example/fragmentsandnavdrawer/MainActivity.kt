package com.example.fragmentsandnavdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.fragmentsandnavdrawer.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    val arrayTabs = arrayOf("Monday","Tuesday","Wednesday")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewpager:ViewPager2 = findViewById(R.id.viewpager2)
        val tabLayout:TabLayout = findViewById(R.id.tabLayout)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewpager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout,viewpager){
            tab, position -> tab.text = arrayTabs[position]
        }.attach()


    }
}