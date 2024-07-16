package com.royalit.referconcept.Fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.royalit.referconcept.Activitys.AboutsActivity
import com.royalit.referconcept.Activitys.ContactUsActivity
import com.royalit.referconcept.Activitys.EditProfileActivity
import com.royalit.referconcept.Activitys.FaqActivity
import com.royalit.referconcept.Activitys.HelpAndSupportActivity
import com.royalit.referconcept.Activitys.PrivacyPolicyActivity
import com.royalit.referconcept.Activitys.TermsAndConditionsActivity
import com.royalit.referconcept.Logins.LoginActivity
import com.royalit.referconcept.R
import com.royalit.referconcept.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(), View.OnClickListener  {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        callEmiDetailsApi()
//        callPendingInvoiceApi()

        init()
    }

    private fun init() {
        binding.imgEdit.setOnClickListener(this)
        binding.linearAboutus.setOnClickListener(this)
        binding.linearContactUs.setOnClickListener(this)
        binding.linearTermsandConditons.setOnClickListener(this)
        binding.linearPrivacyPolicy.setOnClickListener(this)
        binding.linearFAQ.setOnClickListener(this)
        binding.linearHelpandDesk.setOnClickListener(this)
        binding.linearLogout.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgEdit -> {
                startActivity(Intent(activity, EditProfileActivity::class.java))
            }

            R.id.linearAboutus -> {
                startActivity(Intent(activity, AboutsActivity::class.java))
            }

            R.id.linearContactUs -> {
                startActivity(Intent(activity, ContactUsActivity::class.java))
            }

            R.id.linearTermsandConditons -> {
                startActivity(Intent(activity, TermsAndConditionsActivity::class.java))
            }

            R.id.linearPrivacyPolicy -> {
                startActivity(Intent(activity, PrivacyPolicyActivity::class.java))
            }

            R.id.linearFAQ -> {
                startActivity(Intent(activity, FaqActivity::class.java))
            }

            R.id.linearHelpandDesk -> {
                startActivity(Intent(activity, HelpAndSupportActivity::class.java))
            }

            R.id.linearLogout -> {
                logOut()
            }



        }
    }

    private fun logOut() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Are you sure you want to logout?")
        builder.setTitle("Alert!")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "Yes"
        ) { dialog: DialogInterface?, which: Int ->
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finishAffinity() // Close all activities in the app
        }
        builder.setNegativeButton(
            "No"
        ) { dialog: DialogInterface, which: Int ->
            dialog.cancel()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

}