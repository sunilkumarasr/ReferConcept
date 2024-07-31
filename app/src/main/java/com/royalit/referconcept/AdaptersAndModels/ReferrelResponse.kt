package com.royalit.referconcept.AdaptersAndModels

import android.provider.ContactsContract.Data
import com.google.gson.annotations.SerializedName

data class ReferrelResponse(

    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: Data

) {
    data class Data(
        @SerializedName("referral") var holidays: ArrayList<Referral> = arrayListOf(),
    )
}

