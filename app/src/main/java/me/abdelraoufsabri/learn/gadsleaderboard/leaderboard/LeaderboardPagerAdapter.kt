package me.abdelraoufsabri.learn.gadsleaderboard.leaderboard

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.abdelraoufsabri.learn.gadsleaderboard.R

class LeaderboardPagerAdapter(val fragment: LeaderBoardFragment) : FragmentStateAdapter(fragment){
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> LearningHoursLeadersFragment()
            else -> LearningSkillIqLeadersFragment()
        }
    }

    fun getTitle(position: Int):String{
        return when(position){
            0 -> fragment.requireContext().resources.getString(R.string.learning_leaders)
            else -> fragment.requireContext().resources.getString(R.string.skill_iq_leaders)
        }
    }
}

