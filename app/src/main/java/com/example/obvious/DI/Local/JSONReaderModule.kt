package com.example.obvious.DI.Local

import android.content.Context
import com.example.obvious.DI.Models.PodModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class JSONReaderModule  {

    @Singleton
    @Provides
    fun provideParsedData(context: Context): JSONFileReader {
        return JSONFileReader(context)

    }
}