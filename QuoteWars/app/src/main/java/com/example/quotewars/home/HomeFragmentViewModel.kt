package com.example.quotewars.home

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.Window
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.quotewars.R
import com.example.quotewars.database.QuoteDatabase

class HomeFragmentViewModel: ViewModel() {

    fun openGithub(context: Context){
        val pageURL="https://github.com/CzinkeM/Quote-Wars"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageURL))
        context.startActivity(browserIntent)
    }
}