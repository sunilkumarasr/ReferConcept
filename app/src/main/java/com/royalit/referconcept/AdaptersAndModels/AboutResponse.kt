package com.royalit.referconcept.AdaptersAndModels

import com.google.gson.annotations.SerializedName
import com.royalit.referconcept.Activitys.PrivacyPolicyActivity

data class AboutResponse(
    @SerializedName("status") val status: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: Data?
) {
    data class Data(
        @SerializedName("about") val about: List<Privacy> = emptyList()
    ) {
        data class Privacy(
            @SerializedName("paragraph1") val paragraph1: String?,
            @SerializedName("paragraph2") val paragraph2: String?
        )
    }
}