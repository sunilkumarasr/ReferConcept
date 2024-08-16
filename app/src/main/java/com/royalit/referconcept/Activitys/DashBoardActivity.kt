package com.royalit.referconcept.Activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.royalit.referconcept.Fragments.AboutFragment
import com.royalit.referconcept.Fragments.HomeFragment
import com.royalit.referconcept.Fragments.ProfileFragment
import com.royalit.referconcept.Fragments.ReferrelFragment
import com.royalit.referconcept.R
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity(), View.OnClickListener  {

    val binding: ActivityDashBoardBinding by lazy {
        ActivityDashBoardBinding.inflate(layoutInflater)
    }

    private lateinit var bottomNavigationView: BottomNavigationView

    //fragments
    private val homeFragment = HomeFragment()
    private val referrelFragment = ReferrelFragment()
    private val aboutFragment = AboutFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(
            this,
            ContextCompat.getColor(this, R.color.colorPrimary),
            false
        )

        bottomMenu()

        binding.imgNotification.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgNotification -> {
                startActivity(Intent(this@DashBoardActivity, NotificationActivity::class.java))
            }
        }
    }

    private fun bottomMenu() {
        replaceFragment(homeFragment)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        // Set listener for item selection
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->

            menuItem.isChecked = true

            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment)
                    true
                }

                R.id.referrals -> {
                    replaceFragment(referrelFragment)
                    true
                }

                R.id.about -> {
                    replaceFragment(aboutFragment)
                    true
                }

                R.id.profile -> {
                    replaceFragment(profileFragment)
                    true
                }

                else -> false
            }
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }


    override fun onBackPressed() {
        super.onBackPressed()

        finishAffinity()

    }


}