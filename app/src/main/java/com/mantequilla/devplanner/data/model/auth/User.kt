package com.mantequilla.devplanner.data.model.auth

data class User(
    var app_metadata: AppMetadata? = AppMetadata(),
    var aud: String? = "",
    var confirmed_at: String? = "",
    var created_at: String? = "",
    var email: String? = "",
    var email_confirmed_at: String? = "",
    var id: String? = "",
    var identities: List<Identity?>? = listOf(),
    var last_sign_in_at: String? = "",
    var phone: String? = "",
    var role: String? = "",
    var updated_at: String? = "",
    var user_metadata: UserMetadata? = UserMetadata()
)