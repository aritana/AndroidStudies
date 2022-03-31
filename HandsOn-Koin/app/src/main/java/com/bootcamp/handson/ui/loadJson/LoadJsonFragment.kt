package com.bootcamp.handson.ui.loadJson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bootcamp.handson.databinding.FragmentLoadJsonBinding
import org.koin.android.ext.android.inject

class LoadJsonFragment : Fragment() {

    private var _binding: FragmentLoadJsonBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: LoadJsonViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadJsonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getJson()

        viewModel.jsonText.observe(viewLifecycleOwner) {
            updateTextView(it)
        }
    }

    private fun updateTextView(srtText: String) {
        binding.tvJson.text = srtText
    }
}