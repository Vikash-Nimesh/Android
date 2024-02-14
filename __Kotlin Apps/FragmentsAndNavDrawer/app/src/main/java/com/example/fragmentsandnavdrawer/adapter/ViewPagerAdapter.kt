package com.example.fragmentsandnavdrawer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentsandnavdrawer.fragments.MonFragment
import com.example.fragmentsandnavdrawer.fragments.TueFragment
import com.example.fragmentsandnavdrawer.fragments.WedFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0-> return MonFragment()
            1-> return TueFragment()

        }
        return WedFragment()
    }
}