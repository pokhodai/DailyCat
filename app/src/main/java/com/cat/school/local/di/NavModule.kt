package com.cat.school.local.di

import com.cat.school.local.core.nav.EventNav
import com.cat.school.local.core.nav.Nav
import com.cat.school.local.nav.EventNavImpl
import com.cat.school.local.nav.NavImpl
import com.cat.school.local.nav.holders.ContainerNavHolder
import com.cat.school.local.nav.holders.RootNavHolder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavModule {

    companion object {
        @Provides
        @Singleton
        fun provideRootNavHolder(): RootNavHolder {
            return RootNavHolder()
        }

        @Provides
        @Singleton
        fun provideContainerHolder(): ContainerNavHolder {
            return ContainerNavHolder()
        }
    }

    @Binds
    @Singleton
    abstract fun bindNavImpl(impl: NavImpl): Nav

    @Binds
    abstract fun bindEventNavImpl(impl: EventNavImpl): EventNav
}