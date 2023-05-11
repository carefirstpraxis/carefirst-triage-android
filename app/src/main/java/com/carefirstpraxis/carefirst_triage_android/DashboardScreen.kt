package com.carefirstpraxis.carefirst_triage_android

import android.Manifest
import android.app.Application
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.carefirstpraxis.carefirst_triage_android.VoiceToTextParser

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun DashBoardScreen(navController: NavController, app: Application) {

    val voiceToText by lazy {
        VoiceToTextParser(app)
    }

    var canRecord by remember {
        mutableStateOf(false)
    }

    // Creates an permission request
    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            canRecord = isGranted
        }
    )

    LaunchedEffect(key1 = recordAudioLauncher) {
        // Launches the permission request
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    val state by voiceToText.state.collectAsState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (canRecord) {
                        if (!state.isSpeaking) {
                            voiceToText.startListening("en")
                        } else {
                            voiceToText.stopListening()
                        }
                    }
                }
            ) {
                AnimatedContent(targetState = state.isSpeaking) { isSpeaking ->
                    if (isSpeaking) {
                        Icon(
                            imageVector = Icons.Rounded.Create,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedContent(targetState = state.isSpeaking) { isSpeaking ->
                if (isSpeaking) {
                    Text(
                        text = "Speak...",
                        style = MaterialTheme.typography.titleMedium)
                } else {
                    Text(
                        text = state.spokenText.ifEmpty { "Click on record" },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashBoardPreview() {
}