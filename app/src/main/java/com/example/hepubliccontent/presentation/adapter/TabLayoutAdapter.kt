package com.example.hepubliccontent.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hepubliccontent.presentation.PublicContentActivity
import com.example.hepubliccontent.presentation.fragments.NewsFragment
import com.example.hepubliccontent.presentation.fragments.PublicContentFragment

class TabLayoutAdapter(publicContentFragment: PublicContentFragment) : FragmentStateAdapter(publicContentFragment) {

    override fun getItemCount(): Int {
        return 1
    }

    override fun createFragment(position: Int): Fragment {
        return NewsFragment()
    }
}