package com.riki.notesapp.presentation.registration

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
import com.riki.notesapp.data.UserEntity
import com.riki.notesapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding : FragmentRegisterBinding
    private lateinit var userVM : UserViewModel
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    companion object{
        const val PREFS_NAME = "dataUser"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        binding.btnRegister.setOnClickListener {
            val email = binding.etInputEmail.text.toString()
            val username = binding.etInputUsername.text.toString()
            val password = binding.etInputPassword.text.toString()
            val repeatPassword = binding.etRepeatPassword.text.toString()

            if (verifyPassword(password,repeatPassword)){
                saveUser(email, username, password)
                Toast.makeText(context,"Register Berhasil", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment2)
            } else {
                Toast.makeText(context,"Password Tidak Sesuai",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun verifyPassword(password: String, repeatPassword: String): Boolean {
        return password == repeatPassword
    }

    private fun saveUser(email: String, username: String, password: String) {
        userVM.insertUser(UserEntity(0,username,email,password))
    }
}