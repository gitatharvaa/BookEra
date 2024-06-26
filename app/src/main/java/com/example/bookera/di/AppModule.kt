package com.example.bookera.di

import com.example.bookera.Network.BooksApi
import com.example.bookera.repository.FireRepository
import com.example.bookera.utils.Constants.BASE_URL
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule { //will be used for creating instances, eg:repository, database, etc.

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {
        @Singleton
        @Provides
        fun provideFireBookRepository() = FireRepository(
            queryBook = FirebaseFirestore.getInstance()
                .collection("books")
        )


        @Singleton
        @Provides
        fun provideBookApi(): BooksApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BooksApi::class.java)
        }
    }
}