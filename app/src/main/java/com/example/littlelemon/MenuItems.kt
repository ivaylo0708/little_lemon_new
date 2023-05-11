package com.example.littlelemon

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonColor


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(databaseMenuItems : List<MenuItemRoom>) {
    var searchPhrase by remember {
        mutableStateOf(TextFieldValue(""))
    }



    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (item in databaseMenuItems) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(80.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(end = 16.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.h4
                    )
                    Text(
                        text = "$" + item.price,
                        style = MaterialTheme.typography.body2
                    )
                }
                GlideImage(
                    model = item.image,
                    contentDescription = "Images",
                    modifier = Modifier.clip(
                        RoundedCornerShape(10.dp)
                    )
                )
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

            }
        }
    }
}