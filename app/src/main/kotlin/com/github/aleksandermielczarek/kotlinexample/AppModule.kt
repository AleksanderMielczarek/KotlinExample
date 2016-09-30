package com.github.aleksandermielczarek.kotlinexample

import android.content.Context
import com.github.aleksandermielczarek.napkin.scope.AppScope
import com.github.aleksandermielczarek.realmrepository.configuration.RealmRepositoryConfiguration
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration


/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
@Module
@AppScope
class AppModule(val context: Context) {

    @Provides
    @AppScope
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder(context).deleteRealmIfMigrationNeeded().build()
    }

    @Provides
    @AppScope
    fun provideRepositoryConfiguration(): RealmRepositoryConfiguration {
        return RealmRepositoryConfiguration.getDefault()
    }

    @Provides
    @AppScope
    fun provideUserRepository(repositoryConfiguration: RealmRepositoryConfiguration): UserRepository {
        return UserRepositoryImpl(repositoryConfiguration)
    }

}