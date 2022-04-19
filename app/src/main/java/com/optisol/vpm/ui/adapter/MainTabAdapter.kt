package com.optisol.vpm.ui.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.optisol.vpm.ui.fragment.FeedFragment
import com.optisol.vpm.ui.fragment.VideoFragment

class MainTabAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) = when (position) {
        1 -> FeedFragment.newInstance()
        else -> VideoFragment.newInstance()
    }

}