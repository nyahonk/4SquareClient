package com.example.main.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.BaseFragment
import com.example.main.R
import com.example.main.data.entities.venues.Venue
import com.example.main.di.MainComponent
import com.example.main.ui.list.adapter.MainListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main_list.*
import javax.inject.Inject
import javax.inject.Provider

class MainListFragment: BaseFragment() {

    private val rvAdapter = MainListAdapter(arrayListOf())

    override val layoutRes = R.layout.fragment_main_list

    override fun injectDependencies() {
        MainComponent.Builder
            .build(coreComponent)
            .inject(this)
    }

    @Inject
    lateinit var viewModelFactory: Provider<MainListViewModel>

    private val viewModel: MainListViewModel by lazy {
        viewModelFactory.get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }

        subscribeViews()
        subscribeActions()
    }

    private fun subscribeViews() {
        val venuesObserver = Observer<List<Venue>> {
            rvAdapter.venues = it
            rvAdapter.notifyDataSetChanged()
        }
        val errorObserver = Observer<String> {
            showError(it)
        }

        viewModel.apply {
            venues.observe(viewLifecycleOwner, venuesObserver)
            error.observe(viewLifecycleOwner, errorObserver)
        }
    }

    private fun subscribeActions() {
        buttom_to_map.setOnClickListener { findNavController().navigate(R.id.MainMapFragment) }
    }
}