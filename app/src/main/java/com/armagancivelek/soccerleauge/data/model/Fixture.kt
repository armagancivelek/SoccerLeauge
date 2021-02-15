package com.armagancivelek.soccerleauge.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_fixture")
data class Fixture(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "round_count")
    var roundCount: Int? = null,
    @ColumnInfo(name = "home_team")
    var homeTeam: Int? = null,
    @ColumnInfo(name = "away_team")
    var awayTeam: Int? = null,
    @ColumnInfo(name = "pass_team")
    var passTeam: Int? = null
)
