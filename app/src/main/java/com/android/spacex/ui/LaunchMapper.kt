package com.android.spacex.ui

import com.android.spacex.model.LaunchUiModel
import com.android.spacex.model.response.LaunchResponse
import javax.inject.Inject

class LaunchMapper @Inject constructor() {
    fun mapFrom(model: LaunchResponse): LaunchUiModel {
        return LaunchUiModel(
            missionName = model.mission_name,
            rocketName = model.rocket.rocket_name,
            launchYear = model.launch_year,
            launchDate = model.launch_date_utc,
            siteName = model.launch_site.site_name,
            description = if (model.details.isNullOrEmpty()) "Description is empty" else model.details,
            missionPatch = if (model.links.mission_patch.isNullOrEmpty()) "Mission patch is empty" else model.links.mission_patch,
            missionPatchSmall = if (model.links.mission_patch_small.isNullOrEmpty()) "Mission patch small is empty" else model.links.mission_patch_small,
            flightNumber = model.flight_number
        )
    }
}