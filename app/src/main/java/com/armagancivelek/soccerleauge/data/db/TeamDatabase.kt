package com.armagancivelek.soccerleauge.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.armagancivelek.soccerleauge.data.model.Team


@Database(
    entities = [Team::class],
    version = 1,
    exportSchema = false
)

abstract class TeamDatabase : RoomDatabase() {
    abstract fun getTeamDao(): TeamDAO

    companion object {
        @Volatile
        private var instance: TeamDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {

            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context,
            TeamDatabase::class.java,
            "team_db.db"
        ).build()

    }
}