package com.royalit.referconcept.Logins

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.royalit.referconcept.R
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityForgotBinding

class ForgotActivity : AppCompatActivity() {

    val binding: ActivityForgotBinding by lazy {
        ActivityForgotBinding.inflate(layoutInflater)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)


        binding.registerLinear.setOnClickListener {
            startActivity(Intent(this@ForgotActivity, RegisterActivity::class.java))
        }

        binding.txtBacktologin.setOnClickListener {
            startActivity(Intent(this@ForgotActivity, LoginActivity::class.java))
        }

    }
}