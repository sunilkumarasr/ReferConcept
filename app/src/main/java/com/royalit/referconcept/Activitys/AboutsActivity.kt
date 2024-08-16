package com.royalit.referconcept.Activitys

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.royalit.referconcept.AdaptersAndModels.AboutResponse
import com.royalit.referconcept.R
import com.royalit.referconcept.Retrofit.RetrofitClient
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityAboutsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AboutsActivity : AppCompatActivity() {

    val binding: ActivityAboutsBinding by lazy {
        ActivityAboutsBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)

        inits()

    }

    private fun inits() {
        binding.root.findViewById<TextView>(R.id.txtTitle).text = "About us"
        binding.root.findViewById<ImageView>(R.id.imgBack).setOnClickListener { finish() }

        aboutApi()
    }


    private fun aboutApi() {

        if(!ViewController.noInterNetConnectivity(this@AboutsActivity)){
            ViewController.showToast(this@AboutsActivity, "Please check your connection ")
            return
        }else{
            ViewController.showLoading(this@AboutsActivity)
            RetrofitClient.apiInterface.aboutApi("").enqueue(object : Callback<AboutResponse> {
                override fun onResponse(
                    call: Call<AboutResponse>,
                    response: Response<AboutResponse>
                ) {
                    ViewController.hideLoading()
                    if (response.isSuccessful) {
                        response.body()?.let { aboutResponse ->
                            if (aboutResponse.status == 200) {

                                val privacyContent = aboutResponse.data?.about?.firstOrNull()?.paragraph1
                                binding.txtNote.text = Html.fromHtml(privacyContent, Html.FROM_HTML_MODE_COMPACT)

                                val privacyContent2 = aboutResponse.data?.about?.firstOrNull()?.paragraph2
                                binding.txtNote2.text = Html.fromHtml(privacyContent2, Html.FROM_HTML_MODE_COMPACT)

                            } else {
                                ViewController.showToast(
                                    this@AboutsActivity,
                                    aboutResponse.message ?: "Try again"
                                )
                            }
                        }
                    } else {
                        ViewController.showToast(this@AboutsActivity, "Response not successful")
                    }

                }

                override fun onFailure(call: Call<AboutResponse>, t: Throwable) {
                    ViewController.hideLoading()
                    ViewController.showToast(this@AboutsActivity, "Response not successful")
                }

            })

    }

}

}


