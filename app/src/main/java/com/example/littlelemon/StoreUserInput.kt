package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreFirstName(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("FirstName")
        val FIRST_NAME_KEY = stringPreferencesKey("first_name")

    }

    val getFirstName: Flow<String?> = context.dataStore.data
        .map {
                preferences ->
            preferences[FIRST_NAME_KEY] ?: ""
        }

    suspend fun saveFirstName(name: MutableState<TextFieldValue>) {
        context.dataStore.edit { preferences ->
            preferences[FIRST_NAME_KEY] = name.toString()
        }
    }

}
class StoreLastName(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("LastName")
        val LAST_NAME_KEY = stringPreferencesKey("last_name")

    }

    val getLastName: Flow<String?> = context.dataStore.data
        .map {
                preferences ->
            preferences[LAST_NAME_KEY] ?: ""
        }

    suspend fun saveLastName(name: MutableState<TextFieldValue>) {
        context.dataStore.edit { preferences ->
            preferences[LAST_NAME_KEY] = name.toString()
        }
    }

}

class StoreEmail(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("Email")
        val EMAIL_KEY = stringPreferencesKey("email")

    }

    val getEmail: Flow<String?> = context.dataStore.data
        .map {
                preferences ->
            preferences[EMAIL_KEY] ?: ""
        }

    suspend fun saveEmail(name: MutableState<TextFieldValue>) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = name.toString()
        }
    }

}













