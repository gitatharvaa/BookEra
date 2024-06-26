package com.example.bookera

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookera.navigation.ReaderNavigation
import com.example.bookera.ui.theme.BookEraTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookEraTheme {
                ReaderApp()
                FirebaseApp.initializeApp(this)
            }
        }
    }
}


@Composable
fun ReaderApp() {

    Surface(color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 46.dp)) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            ReaderNavigation()

        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookEraTheme {

    }
}
//Found the issue. Check your data classes and make sure they are returning the actual fields
// & values that is set up in the API. For me the issue was in the VolumeInfo data class, with the field averageInfo.
// It is actually supposed to be a double, but it was an int. So change the Int to a Double and
// it should pass through and work as expected.