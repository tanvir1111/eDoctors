package com.incubation_lab.edoctor

import android.os.Bundle
import android.os.Handler
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.incubation_lab.edoctor.databinding.FragmentUserLoginBinding


class UserLoginFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var binding = FragmentUserLoginBinding.inflate(inflater)
        binding.userLoginBottomContainer.visibility = View.GONE
        val root = binding.root
        Handler().postDelayed(Runnable { TransitionManager.beginDelayedTransition(root)
            binding.userLoginPlaceholder.setContentId(R.id.user_login_logo)
            binding.userLoginBottomContainer.visibility = View.VISIBLE
0                                       },100)





        return root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                UserLoginFragment()
    }
}