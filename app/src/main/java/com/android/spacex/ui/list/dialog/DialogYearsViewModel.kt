package com.android.spacex.ui.list.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DialogYearsViewModel : ViewModel() {

    private val _yearsListLD: MutableLiveData<List<String>> = MutableLiveData()
    val yearsList: LiveData<List<String>> get() = _yearsListLD

    fun setYears(list: List<String>) = viewModelScope.launch {
        _yearsListLD.value = list
    }
}