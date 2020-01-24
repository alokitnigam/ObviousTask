package com.example.obvious

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.divumtask.ViewModels.MainActivityViewModel
import com.example.obvious.Base.BaseActivity
import com.example.obvious.DI.Models.PodModel
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel>() {
    lateinit var podsList: List<PodModel>
    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        mainActivityViewModel.podList.observe(this, Observer{
           Log.i("","")

        })
    }

    override fun layoutRes(): Int {
        return  R.layout.activity_main
    }

    override fun getViewModel(): MainActivityViewModel {
        return mainActivityViewModel
    }
}
