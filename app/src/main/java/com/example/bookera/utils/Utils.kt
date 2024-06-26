package com.example.bookera.utils

import android.icu.text.DateFormat
import com.google.firebase.Timestamp

fun formatDate(timestamp: Timestamp): String {
    return DateFormat
        .getDateInstance()
        .format(timestamp.toDate()).toString()
        .split(",")[0] // June 26, 2024
}