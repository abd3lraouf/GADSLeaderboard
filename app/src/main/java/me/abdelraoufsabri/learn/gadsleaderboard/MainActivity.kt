package me.abdelraoufsabri.learn.gadsleaderboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import me.abdelraoufsabri.learn.gadsleaderboard.splash.SplashFragmentDirections
import me.abdelraoufsabri.learn.gadsleaderboard.utils.Extensions.setupAppBackground

class MainActivity : AppCompatActivity() {
    private val host by lazy { supportFragmentManager.findFragmentById(R.id.main_nav_host_container) as NavHostFragment }
    private val navController by lazy { host.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAppBackground()
    }
}