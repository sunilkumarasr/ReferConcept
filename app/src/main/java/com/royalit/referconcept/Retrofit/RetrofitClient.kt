package com.royalit.referconcept.Retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://referconcept.com/Api/"

    var intercepter= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(intercepter)
        .build()
    fun getClient(): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiInterface = getClient(). create(ApiInterface::class.java)
}

object RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestbuilder= request .newBuilder()

        requestbuilder.header("Content-Type","application/json");
        //requestbuilder.header("api_key",API_KEY);


        return chain.proceed(request)
    }
}