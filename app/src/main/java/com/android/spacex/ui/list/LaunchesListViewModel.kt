package com.android.spacex.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.spacex.model.LaunchUiModel
import com.android.spacex.usecase.LaunchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesListViewModel @Inject constructor(private val useCase: LaunchUseCase) : ViewModel() {

    private val _launchesListLD: MutableLiveData<List<LaunchUiModel>> = MutableLiveData()
    val launchesListLD: LiveData<List<LaunchUiModel>> get() = _launchesListLD

    private val _launchesFilterList: MutableLiveData<List<LaunchUiModel>> = MutableLiveData()
    val launchesFilterList: LiveData<List<LaunchUiModel>> get() = _launchesFilterList

    private var _launchesYear: List<String> = ArrayList()
    val launchesYear: List<String> get() = _launchesYear

    fun getLaunchesList() = viewModelScope.launch {
        val data = useCase.getLaunchesList()
        _launchesListLD.value = data
        getLaunchesYear(data)
    }

    private fun getLaunchesYear(list: List<LaunchUiModel>) = viewModelScope.launch {
        _launchesYear = list.map {
            it.launchYear
        }.toSet().toList()
    }

    fun setSelectedYear(year: String) = viewModelScope.launch {
        _launchesFilterList.value = launchesListLD.value?.filter { data ->
            data.launchYear == year
        }
    }

}