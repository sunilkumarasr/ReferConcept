package com.royalit.referconcept.AdaptersAndModels

import com.google.gson.annotations.SerializedName

data class Referral(
    @SerializedName("eid") var eid: String? = null,
    @SerializedName("product_ids") var product_ids: String? = null,
    @SerializedName("userid") var userid: String? = null,
    @SerializedName("detailsname") var detailsname: String? = null,
    @SerializedName("detailsphone") var detailsphone: String? = null,
    @SerializedName("detailsemail") var detailsemail: String? = null,
    @SerializedName("detailsmessage") var detailsmessage: String? = null,
    @SerializedName("registered_on") var registered_on: String? = null,
    @SerializedName("enquiry_status") var enquiry_status: String? = null,
    @SerializedName("uid") var uid: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("mobile") var mobile: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("product_description") var product_description: String? = null,
    @SerializedName("category_id") var category_id: String? = null,
    @SerializedName("product_status") var product_status: String? = null
)
