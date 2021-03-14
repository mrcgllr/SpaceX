package com.android.spacex.repository

import com.android.spacex.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LaunchesListRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getLaunchesList() =
        withContext(Dispatchers.IO) {
            apiService.getLaunchesList()
        }
}
