package com.mantequilla.devplanner.data.model.auth

data class AuthModel(
    var access_token: String? = "",
    var expires_at: Int? = 0,
    var expires_in: Int? = 0,
    var refresh_token: String? = "",
    var token_type: String? = "",
    var user: User? = User()
)