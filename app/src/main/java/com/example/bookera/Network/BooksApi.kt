package com.example.bookera.Network

import com.example.bookera.model.Book
import com.example.bookera.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

//its basically DAO(Data access object)
//this will be responsible for creating the infrastructure that will connect to our
//retrofit instance and get information.
@Singleton
interface BooksApi {

    @GET("volumes")//we wre going to append the volumes in our path
    suspend fun getAllBooks(@Query("q") query: String): Book
                                //"q" for actual query
   @GET("volumes/{bookId}")
   suspend fun getBookInfo(@Path("bookId") bookId: String): Item
    //This is connected to our retrofit
}