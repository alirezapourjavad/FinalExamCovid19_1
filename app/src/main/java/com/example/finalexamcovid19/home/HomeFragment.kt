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
import com.example.finalexamcovid19.dbModel.Global
import java.util.*


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    var adapter =
        HomeAdapter() {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it.iD
                )
            )
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mainData.observe(viewLifecycleOwner) {
            setAdapter(it.countries)
        }
        viewModel.mainData.observe(viewLifecycleOwner){
            it.global?.let { it1 -> setItem(it1) }
        }
        viewModel.mainData.observe(viewLifecycleOwner){
            binding.tvDate.text = it.date.toString()
        }




        binding.btnName.setOnClickListener {
            viewModel.response?.countries?.sortedBy { it.country }?.let { it1 ->
                sortByCountries(
                    it1
                )
            }
            adapter.notifyDataSetChanged()
        }
        binding.btnDeath.setOnClickListener {
            viewModel.response?.countries?.sortedByDescending { it.totalDeaths }?.let { it1 ->
                sortByDeaths(
                    it1
                )
            }
            adapter.notifyDataSetChanged()
        }
        binding.btnCase.setOnClickListener {
            viewModel.response?.countries?.sortedByDescending { it.totalConfirmed }?.let { it1 ->
                sortByCase(
                    it1
                )
            }
            adapter.notifyDataSetChanged()
        }

        binding.etSearch.addTextChangedListener {
            val wordList = viewModel.searchWord(it.toString())
            setSearchAdapter(wordList)

            adapter.notifyDataSetChanged()
        }

        binding.icRefresh.setOnClickListener {
            viewModel.refresh() }

    }

    private fun setAdapter(it: List<Country>?) {
        adapter.submitList(it)
        binding.rvHome.adapter = adapter
    }

    fun setItem(it:Global) {
        binding.tvGlobalTotalDeaths.text = it.totalDeaths.toString()
        binding.tvNumGlobalConfirmed.text = it.totalConfirmed.toString()

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
