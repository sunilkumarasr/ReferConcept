package com.royalit.referconcept.Activitys

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.royalit.referconcept.AdaptersAndModels.AboutResponse
import com.royalit.referconcept.AdaptersAndModels.ContactUsResponse
import com.royalit.referconcept.R
import com.royalit.referconcept.Retrofit.RetrofitClient
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityContactUsBinding
import com.royalit.referconcept.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactUsActivity : AppCompatActivity() {

    val binding: ActivityContactUsBinding by lazy {
        ActivityContactUsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)

        inits()
    }

    private fun inits() {
        binding.root.findViewById<TextView>(R.id.txtTitle).text = "Contact us"
        binding.root.findViewById<ImageView>(R.id.imgBack).setOnClickListener { finish() }

        contactUsApi()
    }

    private fun contactUsApi() {

        if(!ViewController.noInterNetConnectivity(this@ContactUsActivity)){
            ViewController.showToast(this@ContactUsActivity, "Please check your connection ")
            return
        }else{
            ViewController.showLoading(this@ContactUsActivity)
            RetrofitClient.apiInterface.contactUsApi("").enqueue(object : Callback<ContactUsResponse> {
                override fun onResponse(
                    call: Call<ContactUsResponse>,
                    response: Response<ContactUsResponse>
                ) {
                    ViewController.hideLoading()
                    if (response.isSuccessful) {
                        response.body()?.let { aboutResponse ->
                            if (aboutResponse.status == 200) {

                                val privacyContent = aboutResponse.data?.contact?.firstOrNull()?.content
                                binding.txtNote.text = privacyContent
                                binding.txtPhone.text = aboutResponse.data?.contact?.firstOrNull()?.mobile
                                binding.txtEmail.text = aboutResponse.data?.contact?.firstOrNull()?.email
                                binding.txtAddress.text = aboutResponse.data?.contact?.firstOrNull()?.address

                            } else {
                                ViewController.showToast(
                                    this@ContactUsActivity,
                                    aboutResponse.message ?: "Try again"
                                )
                            }
                        }
                    } else {
                        ViewController.showToast(this@ContactUsActivity, "Response not successful")
                    }

                }

                override fun onFailure(call: Call<ContactUsResponse>, t: Throwable) {
                    ViewController.hideLoading()
                    ViewController.showToast(this@ContactUsActivity, "Response not successful")
                }

            })

        }

    }

}