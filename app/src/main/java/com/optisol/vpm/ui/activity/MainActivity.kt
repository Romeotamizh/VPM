package com.optisol.vpm.ui.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.optisol.vpm.R
import com.optisol.vpm.databinding.ActivityMainBinding
import com.optisol.vpm.ui.adapter.MainTabAdapter


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.off_black)))
        initUI()
    }

    private fun switchToTab(tab: Tab) = with(binding) {
        currentTab = tab
        viewpager.currentItem = tab.ordinal
    }


    private fun initUI() = with(binding) {
        viewpager.adapter = MainTabAdapter(this@MainActivity)
        currentTab = Tab.VIDEOS
        videosButton.setOnClickListener { switchToTab(Tab.VIDEOS) }
        feedsButton.setOnClickListener { switchToTab(Tab.FEEDS) }
        viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentTab = Tab.values()[position]
            }

        })


    }

    enum class Tab {
        VIDEOS, FEEDS
    }

}