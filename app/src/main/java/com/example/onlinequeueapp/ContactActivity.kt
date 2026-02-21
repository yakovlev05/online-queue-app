package com.example.onlinequeueapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

class ContactActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainContactMenu(intent.getStringExtra("name"))
        }
    }
}

@Composable
fun MainContactMenu(name: String?) {
    val context = LocalContext.current
    val phone = stringResource(R.string.text_phone)

    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.img_checked),
            contentDescription = "Successful operation",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = stringResource(R.string.text_result, name ?: "Who is you?")
        )

        Text(
            text = phone,
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                val phoneNumber = phone.replace(" ", "")
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:$phoneNumber".toUri()
                }
                context.startActivity(intent)
            }
        )
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContactPreview() {
    MainContactMenu("Алексей")
}
