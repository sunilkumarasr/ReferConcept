package com.royalit.referconcept.AdaptersAndModels

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null
)
