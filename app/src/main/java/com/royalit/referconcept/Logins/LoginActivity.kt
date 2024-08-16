package com.royalit.referconcept.Logins

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.royalit.referconcept.Activitys.DashBoardActivity
import com.royalit.referconcept.AdaptersAndModels.LoginResponse
import com.royalit.referconcept.R
import com.royalit.referconcept.Retrofit.RetrofitClient.apiInterface
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityLoginBinding
import com.royalit.referconcept.databinding.ActivitySplashBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)


        binding.txtForgot.setOnClickListener {

        }

        binding.registerRelative.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.loginRelative.setOnClickListener {
            loginApi()
        }
        
    }

    private fun loginApi() {
        val mobile=binding.mobileEdit.text?.trim().toString()
        val password_=binding.passwordEdit.text?.trim().toString()

        if(mobile.isEmpty()){
            ViewController.showToast(applicationContext, "Enter mobile number")
            return
        }
        if(password_.isEmpty()){
            ViewController.showToast(applicationContext, "Enter password")
            return
        }

        if(!ViewController.noInterNetConnectivity(applicationContext)){
            ViewController.showToast(applicationContext, "Please check your connection ")
            return
        }else if (!validateMobileNumber(mobile)) {
            ViewController.showToast(applicationContext, "Enter Valid mobile number")
        }else{
            ViewController.showLoading(this@LoginActivity)
            apiInterface.login(mobile,password_).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    ViewController.hideLoading()
                    var strRes= response.body().toString();
                    val gson = Gson()
                    val successResponse = gson.toJson(response.body())
                    val jsobObj= JSONObject(successResponse)

                    if(jsobObj.getInt("status")==200){

//                        val user_id=jsobObj.getString("employee_id")
//                        val otp=jsobObj.getInt("otp")
//                        Log.e("otp_",otp.toString())

                        startActivity(Intent(this@LoginActivity, DashBoardActivity::class.java))
                        finish()
                        return
                    }else{
                        ViewController.showToast(applicationContext, "Login Failed")
                    }

                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    ViewController.hideLoading()
                    ViewController.showToast(applicationContext, "Try again")
                }
            }
            )
        }
    }


    private fun validateMobileNumber(mobile: String): Boolean {
        val mobilePattern = "^[6-9][0-9]{9}\$"
        return Patterns.PHONE.matcher(mobile).matches() && mobile.matches(Regex(mobilePattern))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}