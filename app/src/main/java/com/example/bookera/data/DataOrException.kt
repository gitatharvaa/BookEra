package com.example.bookera.data

//we have 'wrap' the elements
data class DataOrException<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)
