package me.abdelraoufsabri.learn.gadsleaderboard.network

import me.abdelraoufsabri.learn.gadsleaderboard.model.HoursTopLearner
import me.abdelraoufsabri.learn.gadsleaderboard.model.SkillIqTopLearner
import retrofit2.Call
import retrofit2.http.GET

interface LearnersWebService {
    @GET("skilliq")
    fun getSkillIqLeaders() : Call<List<SkillIqTopLearner>>

    @GET("hours")
    fun getLearningHoursLeaders() : Call<List<HoursTopLearner>>


}