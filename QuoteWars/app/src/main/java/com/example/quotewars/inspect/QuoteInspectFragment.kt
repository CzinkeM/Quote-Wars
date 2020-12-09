package com.example.quotewars.inspect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quotewars.R
import com.example.quotewars.database.QuoteDatabase
import com.example.quotewars.databinding.FragmentQuoteInspectBinding

class QuoteInspectFragment : Fragment() {

    val args: QuoteInspectFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentQuoteInspectBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_quote_inspect,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = QuoteDatabase.getInstance(application).quoteDatabaseDao
        val viewModelFactory = QuoteInspectViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(QuoteInspectViewModel::class.java)
        viewModel.getQuote(args.quoteId)
        binding.viewModel = viewModel
        binding.textQuoteToInspect.text = args.quote
        binding.buttonDeleteQuote.setOnClickListener {
            viewModel.deleteQuote()
            findNavController().navigate(QuoteInspectFragmentDirections.navigateBackToRecyclerView())
        }
        return binding.root
    }
}