package com.android.spacex.model.response

data class LaunchResponse(
    val details: String?,
    val flight_number: Int,
    val launch_date_utc: String,
    val launch_site: LaunchSite,
    val launch_year: String,
    val links: Links,
    val mission_name: String,
    val rocket: Rocket
)