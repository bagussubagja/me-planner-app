package com.mantequilla.devplanner.data.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskParams(
    @SerialName("user_id") val user_id : String,
    @SerialName("title") val title: String,
    @SerialName("desc") val desc: String,
    @SerialName("tag") val tag: List<String>,
    @SerialName("time") val time: String,
    @SerialName("date") val date: String,
    @SerialName("priority") val priority: String
)
