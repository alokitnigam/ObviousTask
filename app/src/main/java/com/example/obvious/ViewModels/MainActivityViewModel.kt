package com.example.divumtask.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.obvious.DI.Local.JSONFileReader
import com.example.obvious.DI.Models.PodModel

import javax.inject.Inject

class MainActivityViewModel @Inject
constructor(private val jsonReader : JSONFileReader) : ViewModel() {
    val podList=MutableLiveData<ArrayList<PodModel>>()

    init {
        getPodsList()
    }

    fun getPodsList(){
        podList.postValue(jsonReader.getPodList())
    }


}