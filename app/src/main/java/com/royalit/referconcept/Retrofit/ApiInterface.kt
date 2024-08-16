package com.royalit.referconcept.Retrofit

import com.royalit.referconcept.AdaptersAndModels.AboutResponse
import com.royalit.referconcept.AdaptersAndModels.ContactUsResponse
import com.royalit.referconcept.AdaptersAndModels.LoginResponse
import com.royalit.referconcept.AdaptersAndModels.PrivacyPolicyResponse
import com.royalit.referconcept.AdaptersAndModels.ReferrelResponse
import com.royalit.referconcept.AdaptersAndModels.RegisterResponse
import com.royalit.referconcept.AdaptersAndModels.TermsandConditionsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("mobile") mobile: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun registerApi(
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("image") image: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>


    @FormUrlEncoded
    @POST("referralData")
    fun referrelList(
        @Field("customer_id") customer_id: String
    ): Call<ReferrelResponse>


    @FormUrlEncoded
    @POST("about")
    fun aboutApi(
        @Field("customer_id") customer_id: String
    ): Call<AboutResponse>

    @FormUrlEncoded
    @POST("terms")
    fun termsAndConditionsApi(
        @Field("customer_id") customer_id: String
    ): Call<TermsandConditionsResponse>

    @FormUrlEncoded
    @POST("privacy")
    fun privacyPolicyApi(
        @Field("customer_id") customer_id: String
    ): Call<PrivacyPolicyResponse>

    @FormUrlEncoded
    @POST("contact")
    fun contactUsApi(
        @Field("customer_id") customer_id: String
    ): Call<ContactUsResponse>



}