@file:OptIn(ExperimentalGlideComposeApi::class)

package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.runtime.remember


class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Navigation()


            }

        }

    }


    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        return httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json ")
            .body<MenuNetwork>()
            .menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }


    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun MenuItemsList(items: List<MenuItemRoom>) {
        val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

        var orderMenuItems by remember {
            mutableStateOf(false)
        }


        var menuItems = if (orderMenuItems) {
            databaseMenuItems.sortedBy { it.title }
        } else {
            databaseMenuItems
        }
        LazyColumn(

            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 20.dp)
        ) {
            items(
                items = items,
                itemContent = { menuItem ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(menuItem.title, style = MaterialTheme.typography.h2)
                        Text(
                            text = menuItem.price,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp)
                                .fillMaxWidth(.75f)
                        )
                        Text(
                            text = menuItem.price,
                            style = MaterialTheme.typography.body2,
                        )
                        GlideImage(
                            model = menuItem.image,
                            contentDescription = "Images",
                            modifier = Modifier.clip(
                                RoundedCornerShape(10.dp)
                            )
                        )
                        var searchPhrase by remember {
                            mutableStateOf("")
                        }

                        OutlinedTextField(
                            value = searchPhrase,
                            onValueChange = {
                                searchPhrase = it
                            },
                            label = { Text("Search") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 50.dp, end = 50.dp)
                        )
                        if (searchPhrase.isNotEmpty()) {
                            menuItems = menuItems.filter {
                                it.title.contains(
                                    searchPhrase,
                                    ignoreCase = true
                                )
                            }
                        }

                        MenuItemsList(menuItems)

                    }
                }
            )
        }
    }

}









