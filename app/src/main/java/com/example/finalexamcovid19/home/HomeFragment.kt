package com.example.finalexamcovid19.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalexamcovid19.databinding.FragmentHomeBinding
import com.example.finalexamcovid19.dbModel.Country
import java.util.*


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    var adapter =
        HomeAdapter() { findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.iD)) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        adapter.submitList(viewModel.response?.countries)
        binding.rvHome.adapter = adapter
        setItem()


        binding.btnName.setOnClickListener {
            viewModel.response?.countries?.sortedBy { it.country }?.let { it1 ->
                sortByCountries(
                    it1
                )
            }
            adapter.notifyDataSetChanged()
        }
        binding.btnDeath.setOnClickListener {
            viewModel.response?.countries?.sortedBy { it.totalDeaths }?.let { it1 ->
                sortByDeaths(
                    it1
                )
            }
            adapter.notifyDataSetChanged()
        }
        binding.btnCase.setOnClickListener {
            viewModel.response?.countries?.sortedBy { it.totalConfirmed }?.let { it1 ->
                sortByCase(
                    it1
                )
            }
            adapter.notifyDataSetChanged()
        }

        binding.etSearch.addTextChangedListener {
            val wordList = viewModel.searchWord(it.toString())
            setSearchAdapter(wordList)


//            binding.btnCase.setOnClickListener {
//                viewModel.sortByCase(wordList)
//
//            }
//            binding.btnDeath.setOnClickListener {
//                viewModel.sortByDeaths(wordList)
//
//            }
//            binding.btnName.setOnClickListener {
//                viewModel.sortByCountries(wordList)
//
//            }
            adapter.notifyDataSetChanged()
        }

        binding.icRefresh.setOnClickListener {
            viewModel.refresh()
            setItem()
        }

    }

    fun setItem() {
        binding.tvGlobalTotalDeaths.text = viewModel.response?.global?.totalDeaths.toString()
        binding.tvNumGlobalConfirmed.text = viewModel.response?.global?.totalConfirmed.toString()
        binding.tvDate.text = viewModel.response?.date.toString()
    }

    fun setSearchAdapter(wordList: MutableList<Country>) {
        adapter.submitList(wordList)
    }

    fun sortByDeaths(list: List<Country>) {
        adapter.submitList(list)
    }

    fun sortByCountries(list: List<Country>) {
        adapter.submitList(list)
    }

    fun sortByCase(list: List<Country>) {
        adapter.submitList(list)
    }

}
