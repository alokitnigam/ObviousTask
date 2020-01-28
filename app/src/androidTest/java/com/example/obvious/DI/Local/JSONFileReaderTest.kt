package com.example.obvious.DI.Local

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class JSONFileReaderTest {

    @Test
    fun loadJSONFromAsset(){//Testing is json fetched from assets
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val jsonFileReader = JSONFileReader(context)
        val jsonFromAssets = jsonFileReader.loadJSONFromAsset()
        assertNotNull(jsonFromAssets)

    }



    @Test
    fun parseJSON() {
        //Testing if fetched JSON parsed correctly
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val jsonFileReader = JSONFileReader(context)
        val podModels = jsonFileReader.parseJSON()
        assertNotNull(podModels)
        assertEquals(26,podModels.size)


    }


}