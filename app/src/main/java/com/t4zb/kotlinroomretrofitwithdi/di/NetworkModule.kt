package com.t4zb.kotlinroomretrofitwithdi.di

import android.content.Context
import androidx.room.Room
import com.t4zb.kotlinroomretrofitwithdi.data.local.LoremDatabase
import com.t4zb.kotlinroomretrofitwithdi.data.local.dao.LoremDao
import com.t4zb.kotlinroomretrofitwithdi.data.local.repo.BaconipsumLocalRepository
import com.t4zb.kotlinroomretrofitwithdi.data.remote.api.BaconIpsumApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://baconipsum.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideBaconipsumApi(retrofit: Retrofit): BaconIpsumApi {
        return retrofit.create(BaconIpsumApi::class.java)
    }

    // local

    @Provides
    @Singleton
    fun provideLocalLoremDatabase(@ApplicationContext context: Context): LoremDatabase {
        return Room.databaseBuilder(
            context,
            LoremDatabase::class.java,
            "LoremDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalLoremDao(loremDatabase: LoremDatabase): LoremDao {
        return loremDatabase.loremDao()
    }

    @Provides
    @Singleton
    fun provideLocalLoremRepository(loremDao: LoremDao): BaconipsumLocalRepository {
        return BaconipsumLocalRepository(loremDao)
    }


}