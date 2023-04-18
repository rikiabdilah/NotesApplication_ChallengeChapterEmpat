package com.riki.notesapp.presentation.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.riki.notesapp.R
import com.riki.notesapp.UserViewModel
import com.riki.notesapp.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var userVM : UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    companion object{
        const val PREFS_NAME = "dataUser"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)
//        loading()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userVM = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        Handler(Looper.myLooper()!!).postDelayed({
            checkIsLogin()
        },2000L)
    }

//    private fun loading() {
//        Handler(Looper.getMainLooper()).postDelayed({
//            findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment2)
//        }, 2000L)
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
    fun checkIsLogin(){
        if(sharedPreferences.getBoolean("isLogin",false)){
            findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
        } else {
            findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment2)
        }
    }

}