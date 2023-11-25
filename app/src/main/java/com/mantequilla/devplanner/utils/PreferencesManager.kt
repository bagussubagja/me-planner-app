package com.mantequilla.devplanner.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val userLoginInfo: SharedPreferences =
        context.getSharedPreferences(StorageKey.userLoginInfo, Context.MODE_PRIVATE)

    fun saveUserLoginInfo(key: String, value: Boolean) {
        val editor = userLoginInfo.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getUserLoginInfo(key: String, value: Boolean): Boolean {
        return userLoginInfo.getBoolean(key, value) ?: value
    }

    private val emailUserInfo: SharedPreferences =
        context.getSharedPreferences(StorageKey.userEmail, Context.MODE_PRIVATE)

    fun saveEmailUserInfo (key: String, value: String) {
        val editor = emailUserInfo.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getEmailUserInfo (key: String, value: String) : String {
        return emailUserInfo.getString(key, value) ?: value
    }

    private val idUserInfo: SharedPreferences =
        context.getSharedPreferences(StorageKey.userId, Context.MODE_PRIVATE)

    fun saveIdUserInfo (key: String, value: String) {
        val editor = idUserInfo.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getIdUserInfo(key: String, value: String) : String {
        return idUserInfo.getString(key, value) ?: value
    }

    fun clearAllPreferences() {
        clearSharedPreferences(userLoginInfo)
        clearSharedPreferences(emailUserInfo)
        clearSharedPreferences(idUserInfo)
    }

    private fun clearSharedPreferences(sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}