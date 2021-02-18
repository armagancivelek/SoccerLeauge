package com.armagancivelek.soccerleauge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.armagancivelek.soccerleauge.data.model.Fixture
import com.armagancivelek.soccerleauge.data.model.Team

@Dao
interface TeamDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg teams: Team)


    @Query("Select *FROM table_teams")
    fun getAllTeams(): LiveData<List<Team>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFixture(vararg fixture: Fixture)

    @Query("Select *FROM table_fixture")
    fun getAllFixture(): LiveData<List<Fixture>>

    @Query("DELETE FROM  table_fixture")
    suspend fun deleteAllFixture()

    @Query("SELECT *  FROM table_fixture WHERE round_count LIKE:roundCount")
    fun getRoundList(roundCount: Int): LiveData<List<Fixture>>


}