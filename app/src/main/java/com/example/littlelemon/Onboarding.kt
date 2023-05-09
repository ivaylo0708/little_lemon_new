package com.example.littlelemon

import android.widget.Toast
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch




@Composable
fun Onboarding(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(id = R.drawable.logo), contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.2f)
                .background(Color.White)
        )
        Text(
            "Let's get to know you",
            Modifier
                .background(LittleLemonColor.green)
                .fillMaxWidth(1f)
                .fillMaxHeight(0.2f)
            ,
            fontSize = 30.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

          Text(
                "Personal information",
                Modifier.background(Color.White),
                fontSize = 25.sp,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        Spacer(modifier = Modifier.height(20.dp))

        var firstName = remember { mutableStateOf(TextFieldValue()) }
        var lastName = remember { mutableStateOf(TextFieldValue()) }
        var email = remember { mutableStateOf(TextFieldValue()) }
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val dataStore = StoreFirstName(context)
        val savedFirstName = dataStore.getFirstName.collectAsState(initial = "")

        val context1 = LocalContext.current
        val scope1 = rememberCoroutineScope()
        val dataStore1 = StoreLastName(context)
        val savedLastName = dataStore1.getLastName.collectAsState(initial = "")

        val context2 = LocalContext.current
        val scope2 = rememberCoroutineScope()
        val dataStore2 = StoreEmail(context)
        val savedEmail = dataStore2.getEmail.collectAsState(initial = "")

        TextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            label = { Text(text = "First name") })
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            label = { Text(text = "Last name") })
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = "Email") })
        Spacer(modifier = Modifier.height(40.dp))
        Button (
            onClick = {

                if(firstName.value == null && lastName.value == null && email.value ==null){
                    Toast.makeText(context,"Registration unsuccessful.Please enter all data" , Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                    scope.launch { dataStore.saveFirstName(firstName) }
                    scope1.launch { dataStore1.saveLastName(lastName) }
                    scope2.launch { dataStore2.saveEmail(email)}
                    navController.navigate(Home.route)

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),colors = ButtonDefaults.buttonColors(
                backgroundColor = LittleLemonColor.yellow),

            )

        {
            Text(text = "Register")
        }
    }

}


