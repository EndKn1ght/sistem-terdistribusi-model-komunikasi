package com.sister.sampleapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sister.sampleapp.R
import com.sister.sampleapp.databinding.FragmentMessageRecieverBinding


class MessageRecieverFragment : Fragment() {

    private var _binding: FragmentMessageRecieverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageRecieverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recievedMessage = MessageRecieverFragmentArgs.fromBundle(requireArguments()).message
        binding.messagePassed.text = recievedMessage
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}