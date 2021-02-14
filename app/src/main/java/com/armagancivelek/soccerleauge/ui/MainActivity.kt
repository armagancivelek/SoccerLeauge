package com.armagancivelek.soccerleauge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.armagancivelek.soccerleauge.R
import com.armagancivelek.soccerleauge.data.db.TeamDatabase
import com.armagancivelek.soccerleauge.repository.SoccerRepository
import com.armagancivelek.soccerleauge.utils.isConnected
import com.armagancivelek.soccerleauge.utils.showInternetDialog
import com.armagancivelek.soccerleauge.viewmodel.SoccerViewModel
import com.armagancivelek.soccerleauge.viewmodel.SoccerViewModelFactoryProvider

class MainActivity : AppCompatActivity() {
    lateinit var mViewModel: SoccerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SoccerLeauge)
        if (isConnected(this)) {
            setContentView(R.layout.activity_main)
        } else {
            showInternetDialog(this)
        }


        val repo = SoccerRepository(TeamDatabase.invoke(this))
        val viewModelProviderFactory = SoccerViewModelFactoryProvider(repo, application)
        mViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(SoccerViewModel::class.java)


    }
}