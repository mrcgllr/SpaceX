package com.android.spacex.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.spacex.model.LaunchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailViewModel @Inject constructor() : ViewModel() {

    private val _launchDetailLD: MutableLiveData<LaunchUiModel> = MutableLiveData()
    val launchDetailLD: LiveData<LaunchUiModel> get() = _launchDetailLD

    fun setLaunchItem(item: LaunchUiModel) = viewModelScope.launch {
        _launchDetailLD.value = item
    }
}