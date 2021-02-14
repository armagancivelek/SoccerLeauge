package com.armagancivelek.soccerleauge.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.armagancivelek.soccerleauge.repository.SoccerRepository

class SoccerViewModelFactoryProvider(
    val repo: SoccerRepository,
    val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SoccerViewModel(repo, app) as T
    }

}