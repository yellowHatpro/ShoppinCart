package org.yellowhatpro.shoppincart.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.yellowhatpro.shoppincart.data.network.Api
import org.yellowhatpro.shoppincart.data.repositories.FirebaseRepository
import org.yellowhatpro.shoppincart.data.repositories.FirebaseRepositoryImpl
import org.yellowhatpro.shoppincart.data.repositories.MainRepository
import org.yellowhatpro.shoppincart.data.repositories.MainRepositoryImpl
import org.yellowhatpro.shoppincart.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(api: Api):
            MainRepository =
        MainRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideFirebaseRepository():
            FirebaseRepository =
        FirebaseRepositoryImpl()

    @Provides
    @Singleton
    fun provideApiInstance(): Api =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

}