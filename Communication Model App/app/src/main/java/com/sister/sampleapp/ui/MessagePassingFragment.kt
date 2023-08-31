package com.sister.sampleapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sister.sampleapp.R
import com.sister.sampleapp.databinding.FragmentMessagePassingBinding


class MessagePassingFragment : Fragment() {

    private var _binding : FragmentMessagePassingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMessagePassingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moveButton.setOnClickListener {
            val message = binding.messageBox.text.toString()
            val action = MessagePassingFragmentDirections.actionNavigationMessageToMessageRecieverFragment(message)
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}