package com.mantequilla.devplanner.domain.item

import com.mantequilla.devplanner.data.model.task.TaskModel

data class TaskItem (
    var date: String? = "",
    var desc: String? = "",
    var id: Int? = 0,
    var priority: String? = "",
    var tag: List<String?>? = listOf(),
    var time: String? = "",
    var title: String? = "",
    var user_id: String? = ""
)

fun TaskModel.toTaskItem() = TaskItem(
    date, desc, id, priority, tag, time, title, user_id
)