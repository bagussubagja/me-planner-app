package com.mantequilla.devplanner.navigation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskModelNav(
    val id: Int? = 0,
    val user_id: String? = "",
    val title: String? = "",
    val desc: String? = "",
    val priority: String? = "",
    val tag: List<String?>? = listOf(),
) : Parcelable
