package com.armagancivelek.soccerleauge.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = "table_teams")
@Parcelize
data class Team(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val team_id: Int,
    @SerializedName("team_name")
    val team_name: String
) : Parcelable
