package com.royalit.referconcept.Logins

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.royalit.referconcept.Activitys.DashBoardActivity
import com.royalit.referconcept.AdaptersAndModels.LoginResponse
import com.royalit.referconcept.AdaptersAndModels.RegisterResponse
import com.royalit.referconcept.R
import com.royalit.referconcept.Retrofit.RetrofitClient
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityLoginBinding
import com.royalit.referconcept.databinding.ActivityRegisterBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)



        binding.relisterRelative.setOnClickListener{
            registerApi()
        }

        binding.loginLinear.setOnClickListener {
            finish()
        }

    }

    private fun registerApi() {
        val name_=binding.nameEdit.text.toString()
        val mobileNumber_=binding.mobileNumberEdit.text?.trim().toString()
        val email_=binding.emailEdit.text?.trim().toString()
        val password_=binding.passwordEdit.text?.trim().toString()
        val Cpassword_=binding.CpasswordEdit.text?.trim().toString()

        if(name_.isEmpty()){
            ViewController.showToast(applicationContext, "Enter name")
            return
        }
        if(mobileNumber_.isEmpty()){
            ViewController.showToast(applicationContext, "Enter mobile number")
            return
        }
        if(email_.isEmpty()){
            ViewController.showToast(applicationContext, "Enter mobile number")
            return
        }
        if(password_.isEmpty()){
            ViewController.showToast(applicationContext, "Enter password")
            return
        }
        if(Cpassword_.isEmpty()){
            ViewController.showToast(applicationContext, "Enter Conform password")
            return
        }
        if(password_!=Cpassword_){
            ViewController.showToast(applicationContext, "password and conform password not match")
            return
        }


        if(!ViewController.noInterNetConnectivity(applicationContext)){
            ViewController.showToast(applicationContext, "Please check your connection ")
            return
        }else if (!validateMobileNumber(mobileNumber_)) {
            ViewController.showToast(applicationContext, "Enter Valid mobile number")
        }else{
            ViewController.showLoading(this@RegisterActivity)

            RetrofitClient.apiInterface.registerApi(name_,mobileNumber_,"",email_,password_).enqueue(object :
                Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    ViewController.hideLoading()

                    var strRes= response.body().toString();
                    val gson = Gson()
                    val successResponse = gson.toJson(response.body())
                    val jsobObj= JSONObject(successResponse)

                    if(jsobObj.getInt("status")==200){

//                        val user_id=jsobObj.getString("employee_id")
//                        val otp=jsobObj.getInt("otp")
//                        Log.e("otp_",otp.toString())

                        if (jsobObj.getString("message").equals("REGISTRATION_SUCCESS")){
                            ViewController.showToast(applicationContext, "success please Login")
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        }

                        return
                    }else{
                        ViewController.showToast(applicationContext, "Register Failed")
                    }

                }
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
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

}