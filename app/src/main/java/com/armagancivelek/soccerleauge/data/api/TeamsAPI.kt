package com.armagancivelek.soccerleauge.data.api

import com.armagancivelek.soccerleauge.data.model.Team
import retrofit2.Response
import retrofit2.http.GET

interface TeamsAPI {


    @GET("armagancivelek/SoccerLeauge/main/teams.json")
    suspend fun getTeams(): Response<List<Team>>
}