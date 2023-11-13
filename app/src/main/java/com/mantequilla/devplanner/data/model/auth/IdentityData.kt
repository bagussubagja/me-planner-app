package com.mantequilla.devplanner.data.model.auth

data class IdentityData(
    var email: String? = "",
    var email_verified: Boolean? = false,
    var phone_verified: Boolean? = false,
    var sub: String? = ""
)