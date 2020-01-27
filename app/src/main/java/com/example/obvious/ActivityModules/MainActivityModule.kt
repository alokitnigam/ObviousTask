package com.example.obvious.ActivityModules

import com.example.obvious.Views.Adapter.ImageAdapter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideImageAdapter(): ImageAdapter {
        return ImageAdapter()
    }

}