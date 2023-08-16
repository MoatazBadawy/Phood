package com.moataz.phood.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.moataz.phood.di.utils.Constant.DATABASE_NAME
import com.moataz.phood.di.utils.Constant.PREFERENCE_NAME
import com.phood.recipes.data.local.RecipesDao
import com.phood.recipes.data.local.RecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): RecipesDatabase {
        return Room.databaseBuilder(
            appContext,
            RecipesDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }

    @Singleton
    @Provides
    fun provideRecipesDao(database: RecipesDatabase): RecipesDao {
        return database.recipesDao()
    }

    @Provides
    @Singleton
    fun provideIdentityDataStorePreferences(
        @ApplicationContext applicationContext: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            applicationContext.preferencesDataStoreFile(PREFERENCE_NAME)
        }
    }
}
