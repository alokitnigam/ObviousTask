package com.example.obvious.DI

import android.app.Application
import com.example.obvious.DI.Modules.ActivityBindingModule
import com.example.obvious.DI.Modules.ContextModule
import com.example.obvious.DI.VMFactory.MyViewModelModule
import com.example.obvious.DI.VMFactory.ViewModelFactoryModule
import com.example.obvious.DI.Local.JSONReaderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class,AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,JSONReaderModule::class,
    ViewModelFactoryModule::class, MyViewModelModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

}