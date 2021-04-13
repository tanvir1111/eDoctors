package com.incubation_lab.edoctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DoctorLoginFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_doctor_login, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                DoctorLoginFragment()
    }
}