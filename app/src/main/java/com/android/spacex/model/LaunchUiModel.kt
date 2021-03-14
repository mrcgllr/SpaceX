package com.android.spacex.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaunchUiModel(
    val flightNumber: Int,
    val missionName: String,
    val rocketName: String,
    val launchYear: String,
    val launchDate: String,
    val siteName: String,
    val description: String,
    val missionPatchSmall: String,
    val missionPatch: String
) : Parcelable