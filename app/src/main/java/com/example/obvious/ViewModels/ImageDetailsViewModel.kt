package com.example.obvious.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.obvious.DI.Local.JSONFileReader
import com.example.obvious.DI.Models.PodModel

import javax.inject.Inject

class ImageDetailsViewModel @Inject
constructor(private val jsonReader : JSONFileReader) : ViewModel() {
    val podList=MutableLiveData<ArrayList<PodModel>>()
    var currentPosition : Int = 0

    init {
        getPodsList()
    }

    fun getPodsList(){
        podList.postValue(jsonReader.getPodList())
    }


}