package dev.fomenko.latinhelper.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.fomenko.latinhelper.data.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        context.deleteDatabase("LatinHelper")
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "LatinHelper"
        ).createFromAsset("LatinHelper.db").build()
    }

    @Singleton
    @Provides
    fun providePhraseDao(database: AppDatabase) = database.phraseDao()
}