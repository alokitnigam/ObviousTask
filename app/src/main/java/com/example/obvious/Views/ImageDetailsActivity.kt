package com.example.obvious.Views

import android.animation.Animator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE
import com.example.obvious.ViewModels.ImageDetailsViewModel
import com.example.obvious.Base.BaseActivity
import com.example.obvious.DI.Models.PodModel
import com.example.obvious.R
import com.example.obvious.Views.Adapter.ImageDetailsAdapter
import com.example.obvious.Views.Decorators.BackgroundToForegroundTransformer
import kotlinx.android.synthetic.main.activity_image_details.*
import javax.inject.Inject


class ImageDetailsActivity : BaseActivity<ImageDetailsViewModel>(),
    ImageDetailsAdapter.OnPagerItemClickListner {
    private var isViewsVisible: Boolean=true
    private var imageList: ArrayList<PodModel>?=null
    @Inject
    lateinit var imageDetailsAdapter: ImageDetailsAdapter

    @Inject
    lateinit var imageDetailsViewModel: ImageDetailsViewModel

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.white)
        }

        setLightStatusBar(window.decorView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContentView(R.layout.activity_image_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getViewModel().currentPosition = intent.getIntExtra("position",0)

        setSystemUiListener()
        setUpObservers()
        setUpPager()
        setUpWindowAttributes()


    }

    private fun setUpWindowAttributes() {
        val attributes = window.attributes
        attributes.flags =  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.attributes = attributes
        window.navigationBarColor = Color.TRANSPARENT
    }

    private fun setUpPager() {
        viewPager.setPageTransformer(BackgroundToForegroundTransformer())
        imageDetailsAdapter.setItemClickListner(this)
        viewPager.adapter = imageDetailsAdapter
        viewPager.offscreenPageLimit =1
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if(state == SCROLL_STATE_IDLE)
                    setDetails()

            }
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                imageDetailsViewModel.currentPosition = position

            }
        })
    }

    private fun setDetails() {
        if (imageList.isNullOrEmpty())
            return
        val pod=imageList!!.get(viewPager.currentItem)
        image_title.text=pod.title
        date.text=getString(R.string.date ,pod.date)
        explaination.text=getString(R.string.explaination,pod.explanation)
        setVisibility(true)
    }

    private fun setUpObservers() {
        getViewModel().podList.observe(this, Observer {
            imageList=it
            imageDetailsAdapter.setList(imageList!!)
            viewPager.setCurrentItem(getViewModel().currentPosition,false)
            setDetails()
        })
    }

    private fun setSystemUiListener() {
        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility == View.VISIBLE) {
                setVisibility(true)
                isViewsVisible = true

            }
        }
    }

    private fun setVisibility(pVisibility: Boolean) {
        if (!pVisibility) {
            slideUp(top_view)
            slideDown(bottom_view)
            window.decorView.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
        } else {
            window.decorView.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

            }
            slideToNormalPosition(bottom_view)
            slideToNormalPosition(top_view)
        }
    }

    private fun slideUp(pView: View) {
        pView.animate()
            .translationY(-pView.height.toFloat())
            .setDuration(300)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {

                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {

                }

            })
            .start()

    }

    private fun slideDown(pView: View) {
        pView.animate()
            .translationY(pView.height.toFloat())
            .setDuration(300)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {

                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {

                }

            }).start()
    }

    private fun slideToNormalPosition(pView: View) {  // 2 for top and 1 for bottom
        pView.animate()
            .translationY(0f)
            .setDuration(100)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {

                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {

                }

            }).start()
    }

    private fun setLightStatusBar(view: View) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            var flags = view.getSystemUiVisibility()
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.setSystemUiVisibility(flags)
            window.statusBarColor = Color.WHITE
        }
    }

    override fun layoutRes(): Int {
        return R.layout.activity_image_details
    }

    override fun getViewModel(): ImageDetailsViewModel {
        return imageDetailsViewModel
    }

    override fun onPagerItemClicked() {
        if (!isViewsVisible) {
            setVisibility(true)

        } else
            setVisibility(false)

        isViewsVisible = !isViewsVisible
    }


}
