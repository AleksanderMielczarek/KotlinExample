package com.github.aleksandermielczarek.kotlinexample

import android.app.Application
import com.github.aleksandermielczarek.napkin.ComponentProvider
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
class KotlinExampleApplication : Application(), ComponentProvider<AppComponent> {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var realmConfiguration: RealmConfiguration

    override fun provideComponent(): AppComponent {
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}