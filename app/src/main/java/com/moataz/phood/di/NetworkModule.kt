package com.moataz.phood.di

import com.moataz.phood.BuildConfig
import com.phood.identity.data.remote.IdentityService
import com.phood.identity.data.remote.IdentityServiceImpl
import com.phood.recipes.data.remote.RecipesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideIdentityService(): IdentityService {
        return IdentityServiceImpl()
    }

    @Singleton
    @Provides
    fun provideRecipesNetworkService(
        retrofit: Retrofit,
    ): RecipesService {
        return retrofit.create(RecipesService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient()
            .newBuilder()
            .callTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}
