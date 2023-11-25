package com.mantequilla.devplanner.domain.item

import com.mantequilla.devplanner.data.model.auth.AuthModel
import com.mantequilla.devplanner.data.model.auth.User

data class AuthItem(
    var access_token: String? = "",
    var expires_at: Int? = 0,
    var expires_in: Int? = 0,
    var refresh_token: String? = "",
    var token_type: String? = "",
    var user: User? = User()
)

fun AuthModel.toAuthItem() =
    AuthItem(access_token, expires_at, expires_in, refresh_token, token_type, user)