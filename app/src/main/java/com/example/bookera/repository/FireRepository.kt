package com.example.bookera.repository

import com.example.bookera.data.DataOrException
import com.example.bookera.model.MBook
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(
    private val queryBook: Query
) {
    suspend fun getAllBooksFromDatabase(): DataOrException<List<MBook>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MBook>, Boolean, Exception>()

        try {
            dataOrException.loading = true//IS true, then we show the loading widget
      /*This comes from firebase*/ dataOrException.data = queryBook.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MBook::class.java)!!
            }

            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false


        } catch (exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }
        return dataOrException

    }


}