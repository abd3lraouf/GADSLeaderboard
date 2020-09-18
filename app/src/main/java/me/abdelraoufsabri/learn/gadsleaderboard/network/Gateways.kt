package me.abdelraoufsabri.learn.gadsleaderboard.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GateWays{
    val getOkHttpClient: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

    object LearnersGateWay {
        val webService = Retrofit.Builder()
            .baseUrl("https://gadsapi.herokuapp.com/api/")
            .client(getOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LearnersWebService::class.java)
    }

    object GoogleForm{
        val webService = Retrofit.Builder()
            .baseUrl("https://docs.google.com/forms/d/e/")
            .client(getOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GoogleFormsWebService::class.java)
    }
}
