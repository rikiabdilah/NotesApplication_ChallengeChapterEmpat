package com.riki.notesapp.presentation.delete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.riki.notesapp.R
import com.riki.notesapp.databinding.FragmentDeleteBinding

class DeleteFragment : DialogFragment() {

    private var _binding: FragmentDeleteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DeleteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DeleteViewModel::class.java]
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.btnDelete.setOnClickListener {
            viewModel.delete(arguments?.getParcelable("NOTE_ENTITY")!!)
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}