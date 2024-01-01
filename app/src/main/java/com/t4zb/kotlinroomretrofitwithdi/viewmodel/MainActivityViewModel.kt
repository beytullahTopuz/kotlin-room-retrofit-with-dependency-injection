package com.t4zb.kotlinroomretrofitwithdi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.t4zb.kotlinroomretrofitwithdi.data.local.repo.BaconipsumLocalRepository
import com.t4zb.kotlinroomretrofitwithdi.data.model.LocalLorem
import com.t4zb.kotlinroomretrofitwithdi.data.remote.repo.BaconIpsumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val remoteBaconIpsumRepository: BaconIpsumRepository,
    private val baconipsumLocalRepository: BaconipsumLocalRepository
) : ViewModel() {

    var loremLiveData = MutableLiveData<List<LocalLorem>>()

    fun getLoremFromRemoteApi() {
        viewModelScope.launch {
            val result = remoteBaconIpsumRepository.getLoremApi(
                type = "meat",
                sentences = 5
            )

            val mLoremList: ArrayList<LocalLorem> = ArrayList()
            result.forEach { data ->
                val currentLorem = LocalLorem(id = null, lorem = data)
                mLoremList.add(currentLorem)
            }
            loremLiveData.value = mLoremList
        }
    }

    fun saveDB() {
        viewModelScope.launch {
            if (loremLiveData.value != null) {
                loremLiveData.value!![0].let {
                    val localLorem: LocalLorem = it
                    baconipsumLocalRepository.insertLorem(localLorem)
                }
            }
        }
    }

    fun getLocalLorem() {
        viewModelScope.launch {
            val result = baconipsumLocalRepository.getAllLorem()
            loremLiveData.value = result
        }
    }
}