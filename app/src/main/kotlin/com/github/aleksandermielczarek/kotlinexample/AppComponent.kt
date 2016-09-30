package com.github.aleksandermielczarek.kotlinexample

import com.github.aleksandermielczarek.napkin.scope.AppScope
import dagger.Component

/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
@AppScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(application: KotlinExampleApplication)

    fun inject(activity: UsersActivity)

    fun inject(activity: UserActivity)
}