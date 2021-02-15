package com.armagancivelek.soccerleauge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_fixture")
data class Fixture(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "round_count")
    val roundCount: Int,
    @ColumnInfo(name = "home_team")
    val homeTeam: Int,
    @ColumnInfo(name = "away_team")
    val awayTeam: Int
)

