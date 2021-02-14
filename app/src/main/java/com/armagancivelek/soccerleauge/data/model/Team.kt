package com.armagancivelek.soccerleauge.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "teams")
@Parcelize
data class Team(
    @PrimaryKey(autoGenerate = true)
    val team_id: Int,
    val team_name: String
) : Parcelable