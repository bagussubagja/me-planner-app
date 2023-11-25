package com.mantequilla.devplanner.data.model.user

data class UserModel(
    var email: String? = "",
    var id: Int? = 0,
    var job_title: String? = "",
    var name: String? = "",
    var specialist: List<String?>? = listOf()
)