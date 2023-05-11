package com.example.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor


@Composable
fun Image(navController : NavHostController, readPreferences : () -> Map<String, String>, databaseMenuItems : List<MenuItemRoom>) {
    var filter by remember {
        mutableStateOf("all")
    }
    var searchPhrase by remember{
        mutableStateOf(TextFieldValue(""))
    }

    Column {
        androidx.compose.foundation.Image(
            painterResource(id = R.drawable.logo), contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.2f)
                .background(Color.White)
        )
    }

    Column(horizontalAlignment = Alignment.End) {
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier.size(60.dp).clickable { navController.navigate(Profile.route) }
        )


        Column(
            modifier = Modifier
                .background(LittleLemonColor.green)

        ) {

            Text(
                text = "Little Lemon",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = LittleLemonColor.yellow
            )
            Text(
                text = "Chicago",
                fontSize = 24.sp,
                color = LittleLemonColor.cloud
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    style = MaterialTheme.typography.body1,
                    color = LittleLemonColor.cloud,
                    modifier = Modifier
                        .padding(bottom = 28.dp, end = 20.dp)
                        .fillMaxWidth(0.6f)
                )
                androidx.compose.foundation.Image(
                    painter = painterResource(id = R.drawable.hero),
                    contentDescription = "Hero", modifier = Modifier.clip(RoundedCornerShape(16.dp))
                )

            }

        }
        Row (
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                onClick = { filter = "all" }
            ) {
                Text(
                    text = "All",
                    style = MaterialTheme.typography.h3
                )
            }
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                onClick = { filter = "starters" }
            ) {
                Text(
                    text = "Starters",
                    style = MaterialTheme.typography.h3
                )
            }
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                onClick = { filter = "mains" }
            ) {
                Text(
                    text = "Mains",
                    style = MaterialTheme.typography.h3
                )
            }
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow),
                onClick = { filter = "desserts" }
            ) {
                Text(
                    text = "Desserts",
                    style = MaterialTheme.typography.h3
                )
            }
        }
        if (filter == "all") {
            MenuItems(databaseMenuItems.filter { it.title.lowercase().contains(searchPhrase.text.lowercase()) })
        } else {
            MenuItems(databaseMenuItems
                .filter { it.title.lowercase().contains(searchPhrase.text.lowercase()) }
                .filter { it.category == filter }
            )
        }

    }
}







