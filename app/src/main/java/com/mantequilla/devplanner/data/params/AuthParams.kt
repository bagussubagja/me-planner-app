package com.mantequilla.devplanner.data.params

import kotlinx.serialization.Serializable

@Serializable
data class AuthParams (
    val email: String,
    val password: String
)