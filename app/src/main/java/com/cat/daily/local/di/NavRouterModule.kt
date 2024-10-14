package com.cat.daily.local.di

import com.cat.daily.local.core.router.EventRouter
import com.cat.daily.local.core.router.Router
import com.cat.daily.local.router.EventRouterImpl
import com.cat.daily.local.router.RouterImpl
import com.cat.daily.local.presentation.container.holder.ContainerRouterHolder
import com.cat.daily.local.presentation.root.holder.RootRouterHolder
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
        fun provideRootRouterHolder(): RootRouterHolder {
            return RootRouterHolder()
        }

        @Provides
        @Singleton
        fun provideContainerRouterHolder(): ContainerRouterHolder {
            return ContainerRouterHolder()
        }
    }

    @Binds
    @Singleton
    abstract fun bindNRouterImpl(impl: RouterImpl): Router

    @Binds
    abstract fun bindEventRouterImpl(impl: EventRouterImpl): EventRouter
}