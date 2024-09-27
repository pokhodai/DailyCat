package com.cat.school.local.di

import com.cat.school.local.core.nav.api.LocalNav
import com.cat.school.local.nav.LocalNavImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalNavModule {

    @Binds
    abstract fun bindLocalNavImpl(impl: LocalNavImpl): LocalNav
}