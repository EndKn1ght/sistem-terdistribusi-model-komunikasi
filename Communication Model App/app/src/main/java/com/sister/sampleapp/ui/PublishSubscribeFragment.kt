package com.sister.sampleapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.sister.sampleapp.R
import com.sister.sampleapp.databinding.FragmentPublishSubscribeBinding

class PublishSubscribeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentPublishSubscribeBinding? = null
    private val binding get() = _binding!!
    private lateinit var downloadReceiver: BroadcastReceiver
    private var requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Sms receiver permission diterima", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "Sms receiver permission ditolak", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPublishSubscribeBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.smsButton.setOnClickListener(this)
        binding.downloadButton.setOnClickListener(this)

        downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show()
            }
        }
        val downloadIntentFilter = IntentFilter(ACTION_DOWNLOAD_STATUS)
        requireContext().registerReceiver(downloadReceiver, downloadIntentFilter)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        requireContext().unregisterReceiver(downloadReceiver)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.sms_button -> requestPermissionLauncher.launch(Manifest.permission.RECEIVE_SMS)
            R.id.download_button -> {
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        val notifyFinishIntent = Intent().setAction(ACTION_DOWNLOAD_STATUS)
                        requireContext().sendBroadcast(notifyFinishIntent)
                    },
                    3000
                )
            }
        }
    }

    companion object {
        const val ACTION_DOWNLOAD_STATUS = "download_status"
    }
}