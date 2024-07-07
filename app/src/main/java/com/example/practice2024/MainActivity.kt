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
    val logo: String,
    val avatar: String,
    val info: String)

public var HeroesPool = listOf(
    HeroClass("DeadPool",
        "https://instalook.ru/uploads/dakimakura/dedpul-2080.jpg",
        "https://get.wallhere.com/photo/illustration-text-logo-superhero-circle-Deadpool-brand-shape-line-symbol-font-48268.png",
        "Я ДЭДПУЛ!!!"),

    HeroClass("Black widow",
        "https://b1.filmpro.ru/c/807359.700xp.jpg",
        "https://browsecat.art/sites/default/files/black-widow-background-logo-126594-239702-8977137.png",
        "Я черная вдова!!!"),

    HeroClass("Loki",
        "https://i.pinimg.com/736x/e1/0e/70/e10e70e66db9b8107a35a1b6de5411f7.jpg",
        "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/7307f351052193.58e14e6617611.png",
        "Я Локи!!!"))

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
                                model = hero.logo, imageLoader = ImageLoader(
                                    LocalContext.current
                                )
                            ),

                            contentDescription = "logotype",
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
                model = hero.avatar, imageLoader = ImageLoader(
                    LocalContext.current
                )
            ),

            contentDescription = "avatar",
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
