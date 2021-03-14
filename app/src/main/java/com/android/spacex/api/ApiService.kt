package com.android.spacex.api

import com.android.spacex.model.response.LaunchResponse
import com.android.spacex.util.Util.END_POINT_LAUNCHES_LIST
import retrofit2.http.GET

interface ApiService {
    @GET(END_POINT_LAUNCHES_LIST)
    suspend fun getLaunchesList(): List<LaunchResponse>
}