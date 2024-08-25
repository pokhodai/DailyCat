package com.cat.school.local.di

import com.cat.school.core.router.Router
import com.cat.school.local.router.RouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RouterModule {

    @Binds
    @Singleton
    abstract fun bindRouterService(impl: RouterImpl): Router
}