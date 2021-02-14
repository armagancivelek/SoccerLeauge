package com.armagancivelek.soccerleauge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armagancivelek.soccerleauge.data.model.Team
import com.armagancivelek.soccerleauge.repository.SoccerRepository
import com.armagancivelek.soccerleauge.utils.NetworkResult
import com.armagancivelek.soccerleauge.utils.NetworkResult.*
import kotlinx.coroutines.launch
import retrofit2.Response

class SoccerViewModel(
    val repo: SoccerRepository,
    val app: Application
) : ViewModel() {


    val teams: MutableLiveData<NetworkResult<List<Team>>> = MutableLiveData()

    init {

        getTeams()


    }


    private fun getTeams() = viewModelScope.launch {
        teams.postValue(Loading())
        val response: Response<List<Team>>

        try {

            response = repo.getTeams()
            teams.postValue(handleTeamsResponse(response))


        } catch (e: Exception) {
            Log.d("ABC", "hkjhhkj")


        }


    }

    private fun handleTeamsResponse(response: Response<List<Team>>): NetworkResult<List<Team>> {

        when {
            response.isSuccessful && !response.body().isNullOrEmpty() -> {
                return Success(response.body()!!)

            }
            response.code() == 402 -> {
                return Error("Api Key Limited", response.body())
            }
            response.body().isNullOrEmpty() -> {
                return Error("Veriler bulunamdı", response.body())

            }
            response.message().toString().contains("timeout") -> {
                return Error("Zaman aşımı", response.body())
            }
            else -> {
                return Error("hata", response.body())
            }


        }

    }


}