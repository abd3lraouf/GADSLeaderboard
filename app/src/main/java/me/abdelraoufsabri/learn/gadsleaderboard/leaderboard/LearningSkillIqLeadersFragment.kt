package me.abdelraoufsabri.learn.gadsleaderboard.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_leaders_skill_iq.view.*
import me.abdelraoufsabri.learn.gadsleaderboard.R
import me.abdelraoufsabri.learn.gadsleaderboard.data.Learners
import me.abdelraoufsabri.learn.gadsleaderboard.leaderboard.adapter.SkillIqAdapter


class LearningSkillIqLeadersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leaders_skill_iq, container, false).apply {
            this.rvLearningSkillIqLeaders.apply {
                val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
                }

                addItemDecoration(divider)
                adapter = SkillIqAdapter(Learners.skillIq.sortedByDescending { it.score })
                layoutManager =
                    LinearLayoutManager(this@LearningSkillIqLeadersFragment.requireContext())
            }
        }
    }

}