package me.abdelraoufsabri.learn.gadsleaderboard.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_leader_board.view.*
import me.abdelraoufsabri.learn.gadsleaderboard.R


class LeaderBoardFragment : Fragment() {
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()

        return inflater.inflate(R.layout.fragment_leader_board, container, false).apply {


            btnOpenSubmission.setOnClickListener {
                navController.navigate(LeaderBoardFragmentDirections.actionLeaderBoardFragmentToProjectSubmissionFragment())
            }
            val adapter = LeaderboardPagerAdapter(this@LeaderBoardFragment)

            this.leaderboard_pager.adapter = adapter
            TabLayoutMediator(this.tab_layout, this.leaderboard_pager) { tab, position ->
                tab.text = adapter.getTitle(position)

            }.attach()
        }
    }
}