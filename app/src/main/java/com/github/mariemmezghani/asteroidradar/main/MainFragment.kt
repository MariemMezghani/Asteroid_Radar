package com.github.mariemmezghani.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.mariemmezghani.asteroidradar.Asteroid
import com.github.mariemmezghani.asteroidradar.R
import com.github.mariemmezghani.asteroidradar.databinding.FragmentMainBinding
import com.github.mariemmezghani.asteroidradar.repository.AsteroidsRepository

class MainFragment : Fragment() {
    val adapter =
        AsteroidAdapter(AsteroidAdapter.OnClickListener { viewModel.displayAsteroidDetails(it) })
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.asteroidRecycler.adapter = adapter
        displayAsteroids(viewModel.asteroids)

        setHasOptionsMenu(true)
        viewModel.navigateToSelectedAsteroid.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.displayAsteroidDetailsComplete()
            }
        })

        return binding.root
    }

    fun displayAsteroids(list: LiveData<List<Asteroid>>) {
        list.observe(viewLifecycleOwner, Observer {
            it?.apply {
                adapter.submitList(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        displayAsteroids(
            when (item.itemId) {
                R.id.show_today_menu -> viewModel.asteroidsOfToday
                R.id.show_saved_menu -> viewModel.savedAsteroids
                else -> viewModel.asteroids
            }
        )
        return true
    }
}