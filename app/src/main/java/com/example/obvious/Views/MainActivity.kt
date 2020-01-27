package com.example.obvious.Views

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.divumtask.ViewModels.MainActivityViewModel
import com.example.obvious.Views.Adapter.ImageAdapter
import com.example.obvious.Base.BaseActivity
import com.example.obvious.DI.Models.PodModel
import com.example.obvious.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.math.roundToInt

class MainActivity : BaseActivity<MainActivityViewModel>() {
    lateinit var podsList: List<PodModel>
    @Inject
    lateinit var imageAdapter: ImageAdapter
    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
        imagesrv.layoutManager = GridLayoutManager(this,2)
        imagesrv.adapter = imageAdapter
        imageAdapter.setBoardItemSizeInAdapter(getBoardItemSize())
    }

    private fun setObservers() {
        mainActivityViewModel.podList.observe(this, Observer{

            imageAdapter.setList(it)


        })
    }

    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainActivityViewModel {
        return mainActivityViewModel
    }

    private fun getBoardItemSize(): Int {
        val dm = resources.displayMetrics
        val paddingFrame = convertDpToPixel(4f, this).roundToInt()
        val frameWidth = dm.widthPixels - paddingFrame
        val itemWidth = (frameWidth / 2)
        return itemWidth
    }

    private fun convertDpToPixel(dp: Float, context: Context): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}
