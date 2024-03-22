package com.jasmeet.vocabmaster.presentation

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer

@androidx.annotation.OptIn(UnstableApi::class) @OptIn(ExperimentalFoundationApi::class)
@Composable
fun WordList(words: List<String>) {
    val groupedWords = words.groupBy { it.first().uppercaseChar() }
    val url =
        "https://api.dictionaryapi.dev/media/pronunciations/en/blackberry-us.mp3"

    val context = LocalContext.current

    val mediaItem = MediaItem.Builder().setUri(url.toUri()).setMimeType(MimeTypes.BASE_TYPE_AUDIO).build()

    val exoPlayer = remember{
        ExoPlayer.Builder(context)
            .build().apply {
                playWhenReady = false
                setMediaItem(mediaItem)
                prepare()
                addListener(
                    object : Player.Listener {
                        override fun onPlayerError(error: PlaybackException) {
                            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onPlaybackStateChanged(playbackState: Int) {
                            super.onPlaybackStateChanged(playbackState)
                            when (playbackState) {
                                Player.STATE_READY -> {
                                }
                                Player.STATE_ENDED -> {
                                    seekTo(0)
                                    stop()
                                }

                                else ->{}

                            }
                        }
                    }
                )
            }
    }



    LazyColumn(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()) {
        groupedWords.forEach { (firstLetter, words) ->
            stickyHeader (
                key = firstLetter
            ){
                Text(text = firstLetter.toString(), style = MaterialTheme.typography.headlineMedium)
            }
            items(words) { word ->

                Row(Modifier.fillMaxWidth()){
                    Button(
                        onClick = {
                            exoPlayer.prepare()
                            exoPlayer.play()

                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Play Sound")
                    }
                }
            }
        }
    }
    DisposableEffect(
        key1 = Unit,
        effect = {
            onDispose {
                exoPlayer.release()
            }
        }
    )
}






