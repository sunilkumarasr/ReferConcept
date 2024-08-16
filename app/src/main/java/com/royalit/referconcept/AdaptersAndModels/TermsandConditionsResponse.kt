package com.royalit.referconcept.AdaptersAndModels

import com.google.gson.annotations.SerializedName

data class TermsandConditionsResponse(
    @SerializedName("status") val status: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: Data?
) {
    data class Data(
        @SerializedName("terms") val terms: List<Privacy> = emptyList()
    ) {
        data class Privacy(
            @SerializedName("content") val content: String?
        )
    }
}
