package com.mantequilla.devplanner.data.model.auth

data class AppMetadata(
    var provider: String? = "",
    var providers: List<String?>? = listOf()
)