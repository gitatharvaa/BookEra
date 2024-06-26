package com.example.bookera.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class MBook(
    @Exclude var id: String? = null,//we don't need to pass the firebase ID when we
    //are saving this book.
    var title: String? = null,
    var authors: String? = null,
    var notes: String? = null,

    @get:PropertyName("book_photo_url")//getter- to read the value from firestore
    @set:PropertyName("book_photo_url")//setter - to write the value to firestore
    var photoUrl: String? = null,
    var categories: String? = null,

    @get:PropertyName("published_date")
    @set:PropertyName("published_date")
    var publishedDate: String? = null,

    var rating: Double? = null,
    var description: String? = null,

    @get:PropertyName("page_count")
    @set:PropertyName("page_count")
    var pageCount: String? = null,

    @get:PropertyName("started_reading_at")
    @set:PropertyName("started_reading_at")
    var startedReading: Timestamp? = null,
    //In the case of timestamp, we use it coz the person started reading it independent
    //of where they are reading it from.

    @get:PropertyName("finished_reading_at")
    @set:PropertyName("finished_reading_at")
    var finishedReading: Timestamp? = null,

    @get:PropertyName("user_id")
    @set:PropertyName("user_id")
    var userId: String? = null,

    @get:PropertyName("google_book_id")
    @set:PropertyName("google_book_id")
    var googleBookId: String? = null)
