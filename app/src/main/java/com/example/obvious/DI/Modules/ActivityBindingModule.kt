package  com.example.divumtask.DI.Modules


import com.example.obvious.ActivityModules.MainActivityModule
import com.example.obvious.Views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivity(): MainActivity




}