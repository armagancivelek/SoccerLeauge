package com.armagancivelek.soccerleauge.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armagancivelek.soccerleauge.data.model.Fixture
import com.armagancivelek.soccerleauge.data.model.Team
import com.armagancivelek.soccerleauge.repository.SoccerRepository
import com.armagancivelek.soccerleauge.utils.NetworkResult
import com.armagancivelek.soccerleauge.utils.NetworkResult.*
import com.armagancivelek.soccerleauge.utils.generateFixtureForDual
import com.armagancivelek.soccerleauge.utils.generateFixtureForSingle
import kotlinx.coroutines.launch
import retrofit2.Response

class SoccerViewModel(
    val repo: SoccerRepository,
    val app: Application
) : ViewModel() {


    var fixtureList = ArrayList<Fixture>()
    val teams: MutableLiveData<NetworkResult<List<Team>>> = MutableLiveData()

    init {

        getTeams()

    }


    fun getTeams() = viewModelScope.launch {
        teams.postValue(Loading())
        val response: Response<List<Team>>

        try {

            response = repo.getTeams()
            teams.postValue(handleTeamsResponse(response))


        } catch (e: Exception) {


        }


    }

    fun getSavedTeams() = repo.getSavedTeams()

    private fun handleTeamsResponse(response: Response<List<Team>>): NetworkResult<List<Team>> {

        when {
            response.isSuccessful && !response.body().isNullOrEmpty() -> {
                viewModelScope.launch {
                    repo.upsert(*(response.body()!!.toTypedArray()))

                }
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

    fun createFixture(count: Int): Boolean {

        if (count % 2 == 0) {
            fixtureList = generateFixtureForDual(count)
        } else {
            fixtureList = generateFixtureForSingle(count)

        }

        fixtureList.forEach {
            println("A-> home:${it.homeTeam} - away:${it.awayTeam} - hafta:${it.roundCount} - pas :${it.passTeam}\n")

        }


        saveFixture(fixtureList)



        return true


    }

    fun saveFixture(fixtureList: ArrayList<Fixture>) = viewModelScope.launch {


        repo.saveFixture(*fixtureList.toTypedArray())


    }


}