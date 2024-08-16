package com.royalit.referconcept.AdaptersAndModels

import com.google.gson.annotations.SerializedName

data class ContactUsResponse(
    @SerializedName("status") val status: Int?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: Data?
) {
    data class Data(
        @SerializedName("contact") val contact: List<ContactUs> = emptyList()
    ) {
        data class ContactUs(
            @SerializedName("content") val content: String?,
            @SerializedName("address") val address: String?,
            @SerializedName("mobile") val mobile: String?,
            @SerializedName("email") val email: String?
        )
    }
}
