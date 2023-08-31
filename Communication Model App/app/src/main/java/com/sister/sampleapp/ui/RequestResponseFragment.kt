package com.sister.sampleapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import com.sister.sampleapp.data.Response
import com.sister.sampleapp.data.Result
import com.sister.sampleapp.databinding.FragmentRequestResponseBinding
import com.sister.sampleapp.model.AppViewModel
import com.sister.sampleapp.model.ViewModelFactory


class RequestResponseFragment : Fragment() {

    private var _binding: FragmentRequestResponseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppViewModel by activityViewModels {
        ViewModelFactory.getInstance()
    }
    private lateinit var quoteText: TextView
    private lateinit var quoteAuthor: TextView
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestResponseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quoteText = binding.quoteText
        quoteAuthor = binding.quoteAuthor
        progress = binding.progressCircular

        binding.quoteButton.setOnClickListener {
            viewModel.getQuote().observe(viewLifecycleOwner) { quote ->
                when (quote) {
                    is Result.Loading -> {
                        isLoading(true)
                        Log.e("Fetching Data", "Loading")
                    }

                    is Result.Success -> {
                        isLoading(false)
                        Log.e("Succes", "${quote.data.quote}")
                        setUi(quote.data)
                    }

                    is Result.Error -> {
                        isLoading(false)
                        Log.e("Failed Get Quote", quote.error)
                        Toast.makeText(requireContext(), quote.error, Toast.LENGTH_LONG).show()
                    }

                    else -> {}
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUi(quote: Response) {
        quoteText.text = quote.quote
        quoteAuthor.text = quote.author
    }

    private fun isLoading(state: Boolean) {
        if (state) {
            progress.visibility = View.VISIBLE
            setUi(Response("", ""))
        } else {
            progress.visibility = View.INVISIBLE
        }
    }
}