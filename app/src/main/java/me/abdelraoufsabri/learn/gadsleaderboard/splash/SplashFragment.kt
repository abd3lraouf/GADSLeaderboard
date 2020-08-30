package me.abdelraoufsabri.learn.gadsleaderboard.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import me.abdelraoufsabri.learn.gadsleaderboard.R
import me.abdelraoufsabri.learn.gadsleaderboard.data.Learners
import me.abdelraoufsabri.learn.gadsleaderboard.model.HoursTopLearner
import me.abdelraoufsabri.learn.gadsleaderboard.model.SkillIqTopLearner
import me.abdelraoufsabri.learn.gadsleaderboard.network.GateWays
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.CountDownLatch

class SplashFragment : Fragment() {
    val startSignal = CountDownLatch(2)

    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GateWays.LearnersGateWay.webService.getSkillIqLeaders().enqueue(object : retrofit2.Callback<List<SkillIqTopLearner>> {
            override fun onResponse(call: Call<List<SkillIqTopLearner>>, response: Response<List<SkillIqTopLearner>>) {
                startSignal.countDown()
                Learners.skillIq = response.body()!!
                done()
            }

            override fun onFailure(call: Call<List<SkillIqTopLearner>>, t: Throwable) {
                startSignal.countDown()
                Learners.skillIq = listOf()
                done()
            }
        })

        GateWays.LearnersGateWay.webService.getLearningHoursLeaders().enqueue(object : retrofit2.Callback<List<HoursTopLearner>> {
            override fun onResponse(call: Call<List<HoursTopLearner>>, response: Response<List<HoursTopLearner>>) {
                startSignal.countDown()
                Learners.learningHours = response.body()!!
                done()
            }

            override fun onFailure(call: Call<List<HoursTopLearner>>, t: Throwable) {
                startSignal.countDown()
                Learners.learningHours = listOf()
                done()
            }
        })
    }

    private fun done() {
        if (startSignal.count == 0L)
            navController.navigate(SplashFragmentDirections.actionSplashFragmentToLeaderBoardFragment())
    }


}