package com.royalit.referconcept.Activitys

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.royalit.referconcept.R
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityAboutsBinding
import com.royalit.referconcept.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    val binding: ActivityNotificationBinding by lazy {
        ActivityNotificationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)

        inits()
    }

    private fun inits() {
        binding.root.findViewById<TextView>(R.id.txtTitle).text = "Notifications"
        binding.root.findViewById<ImageView>(R.id.imgBack).setOnClickListener { finish() }


    }

}