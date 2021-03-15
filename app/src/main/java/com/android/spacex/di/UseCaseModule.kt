package com.android.spacex.di

import com.android.spacex.repository.LaunchesListRepository
import com.android.spacex.ui.LaunchMapper
import com.android.spacex.usecase.LaunchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideUseCase(repository:LaunchesListRepository,mapper:LaunchMapper): LaunchUseCase {
        return LaunchUseCase(repository,mapper)
    }
}