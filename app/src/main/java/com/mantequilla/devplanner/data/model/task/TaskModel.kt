package com.mantequilla.devplanner.data.model.task

data class TaskModel(
    var date: String? = "",
    var desc: String? = "",
    var id: Int? = 0,
    var priority: String? = "",
    var tag: List<String?>? = listOf(),
    var time: String? = "",
    var title: String? = "",
    var user_id: String? = ""
)