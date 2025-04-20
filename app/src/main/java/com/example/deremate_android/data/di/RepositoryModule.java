package com.example.deremate_android.data.di;

import com.example.deremate_android.data.repository.RouteRepository;
import com.example.deremate_android.data.repository.RouteRepositoryInMemory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {
    @Binds
    @Singleton
    public abstract RouteRepository provideRouteRepository(RouteRepositoryInMemory implementation);
}
