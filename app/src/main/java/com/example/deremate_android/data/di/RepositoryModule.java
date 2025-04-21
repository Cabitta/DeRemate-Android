package com.example.deremate_android.data.di;

import com.example.deremate_android.data.api.ApiClient;
import com.example.deremate_android.data.repository.RouteRepository;
import com.example.deremate_android.data.repository.RouteRepositoryInMemory;
import com.example.deremate_android.data.service.DeliveryDetailService;
import com.example.deremate_android.data.service.DeliveryHistoryService;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {
    @Binds
    @Singleton
    public abstract RouteRepository provideRouteRepository(RouteRepositoryInMemory implementation);

    @Provides
    @Singleton
    static Retrofit provideRetrofit() {
        return ApiClient.getClient();
    }

    @Provides
    @Singleton
    static DeliveryDetailService provideDeliveryDetailService(Retrofit retrofit) {
        return retrofit.create(DeliveryDetailService.class);
    }

    @Provides
    @Singleton
    static DeliveryHistoryService provideDeliveryHistoryService(Retrofit retrofit) {
        return retrofit.create(DeliveryHistoryService.class);
    }
}