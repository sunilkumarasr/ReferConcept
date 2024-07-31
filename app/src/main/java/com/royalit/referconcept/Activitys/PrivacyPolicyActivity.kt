package com.royalit.referconcept.Activitys

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.royalit.referconcept.AdaptersAndModels.PrivacyPolicyResponse
import com.royalit.referconcept.R
import com.royalit.referconcept.Retrofit.RetrofitClient
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityPrivacyPolicyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrivacyPolicyActivity : AppCompatActivity() {

    val binding: ActivityPrivacyPolicyBinding by lazy {
        ActivityPrivacyPolicyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)

        inits()

    }

    private fun inits() {
        binding.root.findViewById<TextView>(R.id.txtTitle).text = "Privacy Policy"
        binding.root.findViewById<ImageView>(R.id.imgBack).setOnClickListener { finish() }

        privacyPolicyApi()
    }

    private fun privacyPolicyApi() {

        if(!ViewController.noInterNetConnectivity(this@PrivacyPolicyActivity)){
            ViewController.showToast(this@PrivacyPolicyActivity, "Please check your connection ")
            return
        }else{
            ViewController.showLoading(this@PrivacyPolicyActivity)
            RetrofitClient.apiInterface.privacyPolicyApi("").enqueue(object : Callback<PrivacyPolicyResponse> {
                override fun onResponse(
                    call: Call<PrivacyPolicyResponse>,
                    response: Response<PrivacyPolicyResponse>
                ) {
                    ViewController.hideLoading()
                    if (response.isSuccessful) {
                        response.body()?.let { aboutResponse ->
                            if (aboutResponse.status == 200) {

                                val privacyContent = aboutResponse.data?.privacy?.firstOrNull()?.content
                                binding.txtNote.text = Html.fromHtml(privacyContent, Html.FROM_HTML_MODE_COMPACT)

                            } else {
                                ViewController.showToast(
                                    this@PrivacyPolicyActivity,
                                    aboutResponse.message ?: "Try again"
                                )
                            }
                        }
                    } else {
                        ViewController.showToast(this@PrivacyPolicyActivity, "Response not successful")
                    }

                }

                override fun onFailure(call: Call<PrivacyPolicyResponse>, t: Throwable) {
                    ViewController.hideLoading()
                    ViewController.showToast(this@PrivacyPolicyActivity, "Response not successful")
                }

            })

        }

    }

}