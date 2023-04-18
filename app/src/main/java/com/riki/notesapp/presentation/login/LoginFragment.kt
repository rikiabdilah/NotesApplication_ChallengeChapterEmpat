package com.riki.notesapp.presentation.login

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.riki.notesapp.R
import com.riki.notesapp.UserViewModel
import com.riki.notesapp.databinding.FragmentLoginBinding
import java.util.prefs.Preferences


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var userVM : UserViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: Editor

    companion object{
        const val PREFS_NAME = "dataUser"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        setListener()
    }
    private fun setListener(){
        binding.btnLogin.setOnClickListener {
            val name = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            verificationUser(name,password)
        }
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
        }
    }

    private fun verificationUser(name: String, password: String) {
        userVM.verifyUser(name, password).observe(viewLifecycleOwner){
            if(it == null){
                Toast.makeText(requireContext(),"Username Tidak Terdaftar", Toast.LENGTH_SHORT).show()
            }else {
                editor.putString("username", it.username)
                editor.putBoolean("isLogin", true)
                editor.apply()
                navigateToHome()
            }
        }
    }
    private fun navigateToHome() {
        findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)
    }
}