package com.dmt.wordlearner.ui.add

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dmt.wordlearner.R
import com.dmt.wordlearner.adapters.LanguageArrayAdapter
import com.dmt.wordlearner.databinding.AddFragmentBinding
import com.dmt.wordlearner.db.WordsDatabase
import com.dmt.wordlearner.repository.TranslationRepository

class AddFragment : Fragment() {

    private lateinit var viewModel: AddViewModel
    private val args : AddFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding : AddFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.add_fragment, container, false)

        setHasOptionsMenu(true)

        val db = WordsDatabase.getInstance(requireContext())
        val repo = TranslationRepository(db)
        val factory = AddViewModelFactory(repo, args.wordId)
        viewModel = ViewModelProvider(this, factory).get(AddViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToMainFragment.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.doneNavigatingMainFragment()
                val action = AddFragmentDirections.actionAddFragmentToMainFragment()
                this.findNavController().navigate(action)
            }
        })

        val adapter = LanguageArrayAdapter(requireContext(), mutableListOf())

        viewModel.languages.observe(viewLifecycleOwner, Observer {
            adapter.updatedData(it)
        })

        binding.languageAutoComplete.setAdapter(adapter)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.done_adding -> {
                viewModel.addWord()
                true
            }
            else -> false
        }
    }

}