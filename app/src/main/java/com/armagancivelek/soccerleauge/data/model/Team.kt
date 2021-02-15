package com.armagancivelek.soccerleauge.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "table_teams")
data class Team(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val team_id: Int,
    @SerializedName("team_name")
    val team_name: String
)
