package com.dmt.wordlearner.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.dmt.wordlearner.GlobalViewModel
import com.dmt.wordlearner.R
import com.dmt.wordlearner.adapters.TranslationAdapter
import com.dmt.wordlearner.databinding.MainFragmentBinding
import com.dmt.wordlearner.db.WordsDatabase
import com.dmt.wordlearner.repository.TranslationRepository
import com.dmt.wordlearner.storage.IStorage
import com.dmt.wordlearner.storage.SharedPreferencesStorage
import com.dmt.wordlearner.utils.ALL
import com.dmt.wordlearner.utils.ItemClickListener

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var globalViewModel: GlobalViewModel
    private lateinit var sharedPref: IStorage
    private lateinit var adapter: TranslationAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding : MainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)

        setHasOptionsMenu(true)

        sharedPref = SharedPreferencesStorage(requireContext())
        globalViewModel = ViewModelProvider(requireActivity()).get(GlobalViewModel::class.java)

        val db = WordsDatabase.getInstance(requireContext())
        val repo = TranslationRepository(db)
        val filter = sharedPref.getString("listPref").toIntOrNull() ?: ALL
        val factory = MainViewModelFactory(repo, filter)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)


        globalViewModel.filter.observe(viewLifecycleOwner, Observer {
            viewModel.applyFilter(it)
        })

        val itemClickListener = ItemClickListener<Long> {
            val action = MainFragmentDirections.actionMainFragmentToAddFragment(it)
            this.findNavController().navigate(action)
        }

        adapter = TranslationAdapter(itemClickListener)
        binding.historyRecyclerView.adapter = adapter

        viewModel.translations.observe(viewLifecycleOwner, Observer {
            adapter.submit(it)
        })

        binding.addButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAddFragment()
            this.findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}