package com.cat.school.local.di

import com.cat.school.local.nav.activity.LocalNavActivityHolder
import com.cat.school.local.nav.container.LocalNavContainerHolder
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
    fun provideLocalNavHolder(): LocalNavActivityHolder {
        return LocalNavActivityHolder()
    }

    @Provides
    @Singleton
    fun provideLocalNavContainerHolder(): LocalNavContainerHolder {
        return LocalNavContainerHolder()
    }
}