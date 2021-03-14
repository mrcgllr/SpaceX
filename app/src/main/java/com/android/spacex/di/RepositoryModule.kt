package com.android.spacex.di


import com.android.spacex.api.ApiService
import com.android.spacex.repository.LaunchesListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideRocketListRepository(apiService: ApiService): LaunchesListRepository {
        return LaunchesListRepository(apiService)
    }
}