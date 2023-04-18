package com.riki.notesapp.presentation.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.riki.notesapp.R
import com.riki.notesapp.data.NoteEntity
import com.riki.notesapp.databinding.FragmentAddBinding

class AddFragment : DialogFragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[AddViewModel::class.java]
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.btnAdd.setOnClickListener {
            if (validate()) {
                val title = binding.etEditTitle.text.toString().trim()
                val desc = binding.etEditDescription.text.toString().trim()
                viewModel.add(NoteEntity(title = title, description = desc))
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }

    private fun validate(): Boolean {
        var flag = true
        binding.apply {
            val title = etEditTitle.text.toString().trim()
            val desc = etEditDescription.text.toString().trim()
            if (title.isEmpty() && desc.isEmpty()) {
                tilEditTitle.error = "Title tidak boleh kosong!"
                tilEditDescription.error = "Description tidak boleh kosong!"
                flag = false
            } else if (desc.isEmpty()) {
                tilEditDescription.error = "Description tidak boleh kosong!"
                tilEditDescription.requestFocus()
                flag = false
            } else if (title.isEmpty()) {
                tilEditTitle.error = "Title tidak boleh kosong!"
                tilEditTitle.requestFocus()
                flag = false
            }
        }
        return flag
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}