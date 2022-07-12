package com.example.hepubliccontent.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.hepubliccontent.R
import com.example.hepubliccontent.databinding.FragmentPublicContentBinding
import com.example.hepubliccontent.presentation.adapter.TabLayoutAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class PublicContentFragment : Fragment() {
    private lateinit var binding: FragmentPublicContentBinding
    private lateinit var pager: ViewPager2
    private lateinit var tabLayout:TabLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPublicContentBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        pager=binding.pager
        tabLayout=binding.tabLayout
        pager.isUserInputEnabled = false
        binding.loginText.text="Eligible users of  %1s get access to additional features after sign in."
        val fManager = childFragmentManager
        val tabLayoutAdapter: FragmentStateAdapter = TabLayoutAdapter(this)
        pager.adapter = tabLayoutAdapter
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.news)))
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        pager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

}