package com.example.littlelemon

import android.content.Context
import android.provider.Settings.Global.putString
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.LittleLemonColor


@Composable
fun ProfileScreen(navController: NavController,
                  readPreferences : () -> Map<String, String>,
                  savePreferences : (firstName : String, lastName : String, email : String) -> Unit) {

    val userDetails = readPreferences()


    Column {
        Image(
            painterResource(id = R.drawable.logo), contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.3f)
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Personal information", fontSize = 20.sp,
            color = Color.Black, modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Profile Information",
                    style = MaterialTheme.typography.h3
                )

                Column() {
                    Text(
                        text = "First Name:",
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "${userDetails["firstName"]}"
                    )
                }

                Column() {
                    Text(
                        text = "Last Name:",
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "${userDetails["lastName"]}"
                    )
                }

                Column() {
                    Text(
                        text = "Email:",
                        style = MaterialTheme.typography.body2
                    )
                    Text(
                        text = "${userDetails["email"]}"
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                        savePreferences("", "", "")
                        navController.navigate(Onboarding.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), colors = ButtonDefaults.buttonColors(
                        backgroundColor = LittleLemonColor.yellow
                    )
                ) {
                    Text(text = "Log out")
                }
            }

        }
    }
}