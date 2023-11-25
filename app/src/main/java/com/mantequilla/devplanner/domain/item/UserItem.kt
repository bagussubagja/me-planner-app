package com.mantequilla.devplanner.domain.item

import com.mantequilla.devplanner.data.model.user.UserModel

data class UserItem(
    var email: String? = "",
    var id: Int? = 0,
    var job_title: String? = "",
    var name: String? = "",
    var specialist: List<String?>? = listOf()
)

fun UserModel.toUserItem() = UserItem(
    email, id, job_title, name, specialist
)
