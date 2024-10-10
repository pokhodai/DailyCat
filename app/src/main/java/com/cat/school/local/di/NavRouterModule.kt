package com.cat.school.local.di

import com.cat.school.local.core.nav.router.EventNavRouter
import com.cat.school.local.core.nav.router.NavRouter
import com.cat.school.local.nav.EventNavRouterImpl
import com.cat.school.local.nav.NavRouterImpl
import com.cat.school.local.nav.holders.ContainerNavRouterHolder
import com.cat.school.local.nav.holders.RootNavRouterHolder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavRouterModule {

    companion object {
        @Provides
        @Singleton
        fun provideRootNavRouterHolder(): RootNavRouterHolder {
            return RootNavRouterHolder()
        }

        @Provides
        @Singleton
        fun provideContainerNavRouterHolder(): ContainerNavRouterHolder {
            return ContainerNavRouterHolder()
        }
    }

    @Binds
    @Singleton
    abstract fun bindNavRouterImpl(impl: NavRouterImpl): NavRouter

    @Binds
    abstract fun bindEventNavRouterImpl(impl: EventNavRouterImpl): EventNavRouter
}