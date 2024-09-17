package com.cat.school.local.di

import com.cat.school.local.nav.LocalNavHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalNavHolder(): LocalNavHolder {
        return LocalNavHolder()
    }
}