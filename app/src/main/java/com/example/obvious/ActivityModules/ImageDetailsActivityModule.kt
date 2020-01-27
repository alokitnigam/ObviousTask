package com.example.obvious.ActivityModules

import com.example.obvious.Views.Adapter.ImageAdapter
import com.example.obvious.Views.Adapter.ImageDetailsAdapter
import dagger.Module
import dagger.Provides

@Module
class ImageDetailsActivityModule {

    @Provides
    internal fun provideViewPagerAdapter(): ImageDetailsAdapter {
        return ImageDetailsAdapter()
    }

}