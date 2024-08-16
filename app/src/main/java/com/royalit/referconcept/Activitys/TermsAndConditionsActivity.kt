package com.royalit.referconcept.Activitys

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.royalit.referconcept.AdaptersAndModels.TermsandConditionsResponse
import com.royalit.referconcept.R
import com.royalit.referconcept.Retrofit.RetrofitClient
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityTermsAndConditionsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TermsAndConditionsActivity : AppCompatActivity() {

    val binding: ActivityTermsAndConditionsBinding by lazy {
        ActivityTermsAndConditionsBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)

        inits()

    }

    private fun inits() {
        binding.root.findViewById<TextView>(R.id.txtTitle).text = "Terms & Conditions"
        binding.root.findViewById<ImageView>(R.id.imgBack).setOnClickListener { finish() }

        termsAndConditionsApi()
    }

    private fun termsAndConditionsApi() {

        if(!ViewController.noInterNetConnectivity(this@TermsAndConditionsActivity)){
            ViewController.showToast(this@TermsAndConditionsActivity, "Please check your connection ")
            return
        }else{
            ViewController.showLoading(this@TermsAndConditionsActivity)
            RetrofitClient.apiInterface.termsAndConditionsApi("").enqueue(object : Callback<TermsandConditionsResponse> {
                override fun onResponse(
                    call: Call<TermsandConditionsResponse>,
                    response: Response<TermsandConditionsResponse>
                ) {
                    ViewController.hideLoading()
                    if (response.isSuccessful) {
                        response.body()?.let { aboutResponse ->
                            if (aboutResponse.status == 200) {

                                val privacyContent = aboutResponse.data?.terms?.firstOrNull()?.content
                                binding.txtNote.text = Html.fromHtml(privacyContent, Html.FROM_HTML_MODE_COMPACT)

                            } else {
                                ViewController.showToast(
                                    this@TermsAndConditionsActivity,
                                    aboutResponse.message ?: "Try again"
                                )
                            }
                        }
                    } else {
                        ViewController.showToast(this@TermsAndConditionsActivity, "Response not successful")
                    }

                }

                override fun onFailure(call: Call<TermsandConditionsResponse>, t: Throwable) {
                    ViewController.hideLoading()
                    ViewController.showToast(this@TermsAndConditionsActivity, "Response not successful")
                }

            })

        }

    }
}