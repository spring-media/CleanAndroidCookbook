package pro.averin.anton.clean.android.cookbook.di

import dagger.Component
import pro.averin.anton.clean.android.cookbook.ui.main.view.MainActivity

//make sure ActivityComponent is dependent on AppComponent
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    //point where we manually inject dependencies into classes that we do not own a constructor of
    fun injectTo(mainActivity: MainActivity)
}