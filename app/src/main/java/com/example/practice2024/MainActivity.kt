package com.example.Practice2024

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter

data class HeroClass(
    val name: String,
    val avatar: String,
    val logo: String,
    val info: String)

public var HeroesPool = listOf(
    HeroClass("DeadPool",
        "https://oboi-telefon.ru/wallpapers/145260/39558.jpg",
        "https://w0.peakpx.com/wallpaper/681/463/HD-wallpaper-deadpool-deadpool-logo.jpg",
        "— Веселого Рождества.\n" +
                "— И вам веселого… апрельского вторника, мистер Пул."),

    HeroClass("Black widow",
        "https://809620.selcdn.ru/wallpaperio-net/wallpapers/full/3ad0ed6111216467c773c0dcf673f096.jpg",
        "https://img.freepik.com/premium-photo/black-spider-black-background-3d-rendering-3d-illustration-generative-ai_804788-45417.jpg?w=740",
        "Что за неистребимая любовь к дешевым мелодрамам, я это ненавижу!"),

    HeroClass("Loki",
        "https://809620.selcdn.ru/wallpaperio-net/wallpapers/full/55d80ccd674af1bedf6a11e07d5b9ed6.jpg",
        "https://w0.peakpx.com/wallpaper/51/825/HD-wallpaper-loki-intro-logo.jpg",
        "Истории есть везде, куда ни глянь."))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }


    @SuppressLint("UnrememberedMutableState")
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun HeroesList() {
        val LazyState = rememberLazyListState()
        val snapBehaviour = rememberSnapFlingBehavior(lazyListState = LazyState)

        LazyRow(state = LazyState, flingBehavior = snapBehaviour) {
            items(HeroesPool) { hero ->
                Column(
                    Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    var isClicked by mutableStateOf(false)
                    Box(Modifier.size(650.dp),
                        contentAlignment = Alignment.BottomCenter) {
                        IconButton(onClick = {isClicked = true},
                            Modifier.fillMaxSize()) {
                            if (isClicked) {
                                setContent {
                                    HeroCard(hero = hero)
                                }

                                isClicked = false }
                        }

                        Image(
                            painter = rememberAsyncImagePainter(
                                model = hero.avatar, imageLoader = ImageLoader(
                                    LocalContext.current
                                )
                            ),

                            contentDescription = "ava",
                            modifier = Modifier.size(700.dp), alignment = Alignment.Center
                        )
                    }

                    Text(
                        text = hero.name,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 30.sp, color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    @Composable
    fun MainScreen() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.marvel_logotype),
                contentDescription = "MARVEL_logotype"
            )

            Text(
                text = "CHOOSE YOUR HERO",
                color = Color.White,
                fontSize = 45.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )

            HeroesList()
        }
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun HeroCard(hero: HeroClass) {
        var isClicked by mutableStateOf(false)
        Image(
            painter = rememberAsyncImagePainter(
                model = hero.logo, imageLoader = ImageLoader(
                    LocalContext.current
                )
            ),

            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(), alignment = Alignment.Center)

        IconButton(onClick = { isClicked = true }, modifier = Modifier.size(70.dp)) {
            Icon(Icons.Default.Close, contentDescription = "Backing", tint = Color.Gray)
            if (isClicked) {
                setContent {
                    MainScreen()
                }

                isClicked = false
            }
        }

        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
            Column {
                Text(
                    text = hero.name, fontFamily = FontFamily.Default,
                    fontSize = 35.sp, color = Color.White, fontWeight = FontWeight.Black
                )

                Text(
                    text = hero.info,
                    fontFamily = FontFamily.Default,
                    fontSize = 30.sp, color = Color.White,
                    fontWeight = FontWeight.Black
                )
            }

        }
    }
}
