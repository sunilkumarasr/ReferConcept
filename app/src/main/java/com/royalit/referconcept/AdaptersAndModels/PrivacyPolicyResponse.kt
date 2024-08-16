package com.royalit.referconcept.AdaptersAndModels

import com.google.gson.annotations.SerializedName

data class PrivacyPolicyResponse(
    @SerializedName("status") val status: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: Data?
) {
    data class Data(
        @SerializedName("privacy") val privacy: List<Privacy> = emptyList()
    ) {
        data class Privacy(
            @SerializedName("content") val content: String?
        )
    }
}
