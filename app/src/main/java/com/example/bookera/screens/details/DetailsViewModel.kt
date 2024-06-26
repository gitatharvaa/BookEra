package com.example.bookera.screens.details

import androidx.lifecycle.ViewModel
import com.example.bookera.data.Resource
import com.example.bookera.model.Item
import com.example.bookera.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: BookRepository)
    : ViewModel(){

    suspend fun getBookInfo(bookId: String): Resource<Item> {
        return repository.getBookInfo(bookId)

    }
}