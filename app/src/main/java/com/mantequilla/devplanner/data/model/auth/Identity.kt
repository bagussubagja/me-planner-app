package com.mantequilla.devplanner.data.model.auth

data class Identity(
    var created_at: String? = "",
    var id: String? = "",
    var identity_data: IdentityData? = IdentityData(),
    var last_sign_in_at: String? = "",
    var provider: String? = "",
    var updated_at: String? = "",
    var user_id: String? = ""
)