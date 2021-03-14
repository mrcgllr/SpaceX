package com.android.spacex.usecase

import com.android.spacex.model.LaunchUiModel
import com.android.spacex.repository.LaunchesListRepository
import com.android.spacex.ui.LaunchMapper
import javax.inject.Inject

class LaunchUseCase @Inject constructor(
    private val repository: LaunchesListRepository,
    private val mapper: LaunchMapper
) {
    suspend fun getLaunchesList(): List<LaunchUiModel> {
        val response = repository.getLaunchesList()
        return response.map {
            mapper.mapFrom(it)
        }
    }
}