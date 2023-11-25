package com.mantequilla.devplanner.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.mantequilla.devplanner.navigation.models.TaskModelNav

class AssetParamType : NavType<TaskModelNav>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TaskModelNav? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): TaskModelNav {
        return Gson().fromJson(value, TaskModelNav::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: TaskModelNav) {
        bundle.putParcelable(key, value)
    }
}