package com.armagancivelek.soccerleauge.repository

import com.armagancivelek.soccerleauge.data.api.RetrofitInstance
import com.armagancivelek.soccerleauge.data.db.TeamDatabase
import com.armagancivelek.soccerleauge.data.model.Fixture
import com.armagancivelek.soccerleauge.data.model.Team

class SoccerRepository(
    val db: TeamDatabase
) {

    suspend fun getTeams() = RetrofitInstance.api.getTeams()


    suspend fun upsert(vararg teams: Team) = db.getTeamDao().upsert(*teams)

    fun getSavedTeams() = db.getTeamDao().getAllTeams()

    suspend fun saveFixture(vararg fixtures: Fixture) = db.getTeamDao().saveFixture(*fixtures)


    fun getSavedFixture() = db.getTeamDao().getAllFixture()

    suspend fun deleteAllFixture() = db.getTeamDao().deleteAllFixture()

    fun getRoundList(roundCount: Int) = db.getTeamDao().getRoundList(roundCount)


}