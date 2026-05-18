package com.example.kavyakanaja.di

import android.app.Application
import androidx.room.Room
import com.example.kavyakanaja.data.local.AppDatabase
import com.example.kavyakanaja.data.remote.api.PoetryApi
import com.example.kavyakanaja.data.repository.PoetryRepositoryImpl
import com.example.kavyakanaja.domain.repository.PoetryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePoetryApi(): PoetryApi {
        // 10.0.2.2 is the standard alias to your host loopback interface (localhost) on Android emulators
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create()) // Since we already have Gson configured in libs we can leave it for now or swap it
            .build()
            .create(PoetryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePoetryRepository(api: PoetryApi, db: AppDatabase, app: Application): PoetryRepository {
        return PoetryRepositoryImpl(api, db.poemDao, app)
    }
}
