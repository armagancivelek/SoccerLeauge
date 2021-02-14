package com.armagancivelek.soccerleauge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.armagancivelek.soccerleauge.data.model.Team

@Dao
interface TeamDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(team: Team): Long


    @Query("Select *FROM table_teams")
    fun getAllTeams(): LiveData<List<Team>>

}