package com.armagancivelek.soccerleauge.repository

import com.armagancivelek.soccerleauge.data.api.RetrofitInstance
import com.armagancivelek.soccerleauge.data.db.TeamDatabase

class SoccerRepository(
    val db: TeamDatabase
) {

    suspend fun getTeams() = RetrofitInstance.api.getTeams()


}