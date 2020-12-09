package com.example.quotewars.favorites

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quotewars.R
import com.example.quotewars.database.QuoteDatabase
import com.example.quotewars.databinding.FragmentFavoritesBinding
import kotlinx.coroutines.coroutineScope

class FavoritesFragment : Fragment() {
    lateinit var favoriteViewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFavoritesBinding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_favorites,container,false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = QuoteDatabase.getInstance(application).quoteDatabaseDao
        val viewModelFactory = FavoritesViewModelFactory(dataSource, application)
        favoriteViewModel = ViewModelProvider(this,viewModelFactory).get(FavoritesViewModel::class.java)
        binding.favoriteViewModel = favoriteViewModel
        binding.lifecycleOwner = this
        val adapter = FavoriteAdapter(QuoteListener { quoteId ->
            favoriteViewModel.onClickListItem(quoteId,view!!)
        })
        binding.listFavorites.adapter = adapter
        favoriteViewModel.allFavoriteQuote.observe(viewLifecycleOwner, Observer{
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })


        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.custom_actionbar_layout,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id==R.id.buttonClearDatabase)
        {
            favoriteViewModel.clearDatabase()
        }else Log.i("clear","Wrong id")
        return super.onOptionsItemSelected(item)
    }
}