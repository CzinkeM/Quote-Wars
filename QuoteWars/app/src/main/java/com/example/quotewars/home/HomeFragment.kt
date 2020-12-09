package com.example.quotewars.home

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quotewars.R
import com.example.quotewars.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        binding.gameViewModel = viewModel as HomeFragmentViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonGetQuote.setOnClickListener {
            findNavController().navigate(R.id.toQuoteFragment)
        }
        binding.buttonGoToFavs.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
        }
        binding.buttonGithub.setOnClickListener {
            viewModel.openGithub(context!!)
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.info_actionbar_layout,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_info)
        val closeButton = dialog.findViewById(R.id.buttonCloseDialog) as Button
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        return super.onOptionsItemSelected(item)
    }
}