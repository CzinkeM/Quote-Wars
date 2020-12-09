package com.example.quotewars.favorites

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.quotewars.R
import com.example.quotewars.model.QuoteModel

@BindingAdapter("quoteFactionColor")
fun ImageView.setFactionImage(item: QuoteModel) {
    setImageResource(when(item.faction){
        0 -> R.mipmap.ic_jedi_order
        1 -> R.mipmap.ic_rabels
        2 -> R.mipmap.ic_galactic_republic
        3 -> R.mipmap.ic_imperialseal
        4 -> R.mipmap.ic_first_order
        else -> R.mipmap.ic_first_order
    })
}