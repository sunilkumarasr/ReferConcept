package com.royalit.referconcept.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.royalit.referconcept.Activitys.DashBoardActivity
import com.royalit.referconcept.AdaptersAndModels.LoginResponse
import com.royalit.referconcept.AdaptersAndModels.ReferrelResponse
import com.royalit.referconcept.R
import com.royalit.referconcept.Retrofit.RetrofitClient
import com.royalit.referconcept.Utils.ViewController
import com.royalit.referconcept.databinding.FragmentAboutBinding
import com.royalit.referconcept.databinding.FragmentReferrelBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReferrelFragment : Fragment() {

    private lateinit var binding: FragmentReferrelBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReferrelBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        referrelList()
    }


    private fun referrelList() {


        if(!ViewController.noInterNetConnectivity(requireActivity())){
            ViewController.showToast(requireActivity(), "Please check your connection ")
            return
        }else{
            RetrofitClient.apiInterface.referrelList("19").enqueue(object :
                Callback<ReferrelResponse> {
                override fun onResponse(call: Call<ReferrelResponse>, response: Response<ReferrelResponse>) {

                    if (response.isSuccessful) {
                        response.body()?.let {
                            if (it.status == 200) {
                                //referralList.clear()
                               // referralList.addAll(it.data.referral)
                                //referralAdapter.notifyDataSetChanged()
                            } else {
                                ViewController.showToast(requireActivity(), "Try again")
                            }
                        }
                    }

                }
                override fun onFailure(call: Call<ReferrelResponse>, t: Throwable) {
                    ViewController.showToast(requireActivity(), "Try again")
                }
            }
            )
        }
    }

}