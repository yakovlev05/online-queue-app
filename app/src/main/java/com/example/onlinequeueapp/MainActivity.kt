package com.example.onlinequeueapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainMenu()
        }
    }
}

@Composable
fun MainMenu() {
    val nameState = remember { mutableStateOf("") }
    val questionState = remember { mutableStateOf("") }
    val isShowNameFieldError = remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)

    ) {
        RequiredTextField(
            state = nameState,
            isShowErrorState = isShowNameFieldError
        )

        OutlinedTextField(
            value = questionState.value,
            onValueChange = { questionState.value = it },
            placeholder = { Text(stringResource(R.string.text_field_question_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )

        Button(
            onClick = {
                if (nameState.value.isEmpty()) {
                    isShowNameFieldError.value = true
                    return@Button;
                }

                val contactIntent = Intent(context, ContactActivity::class.java)
                contactIntent.putExtra("name", nameState.value)
                context.startActivity(contactIntent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Отправить")
        }
    }
}

@Composable
fun RequiredTextField(state: MutableState<String>, isShowErrorState: MutableState<Boolean>) {
    OutlinedTextField(
        value = state.value,
        onValueChange = {
            state.value = it
            isShowErrorState.value = false
        },
        placeholder = { Text(stringResource(R.string.text_field_name_placeholder)) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        isError = isShowErrorState.value
    )

    if (isShowErrorState.value) {
        Text(
            stringResource(R.string.button_text),
            color = Color.Red,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MainMenu()
}