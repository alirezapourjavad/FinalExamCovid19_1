package com.example.finalexamcovid19.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalexamcovid19.R
import com.example.finalexamcovid19.databinding.FragmentHomeBinding
import com.example.finalexamcovid19.dbModel.Country
import com.example.finalexamcovid19.dbModel.Global
import java.util.*
import kotlin.math.hypot


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    var adapter =
        HomeAdapter() {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it
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
            it.global?.let { it1 -> setItem(it1) }
            binding.tvDate.text = it.date.toString()
        }

        binding.view.setOnClickListener {
            val width = binding.rvHome.width
            val height = binding.rvHome.height
            val radius = hypot(width / 2.0, height / 2.0).toFloat()

            val anim = ViewAnimationUtils.createCircularReveal(
                binding.rvHome,
                width / 2,
                height / 2,
                0f,
                radius
            )
            anim.duration = 5000
            anim.start()

        }

        binding.btnName.setOnClickListener {
            viewModel.response?.countries?.sortedBy { it.country }?.let { it1 ->
                newList(
                    it1 as MutableList<Country>
                )
            }
        }
        binding.btnDeath.setOnClickListener {
            viewModel.response?.countries?.sortedByDescending { it.totalDeaths }?.let { it1 ->
                newList(
                    it1 as MutableList<Country>
                )
            }
        }
        binding.btnCase.setOnClickListener {
            viewModel.response?.countries?.sortedByDescending { it.totalConfirmed }?.let { it1 ->
                newList(
                    it1 as MutableList<Country>
                )
            }
        }

        binding.etSearch.addTextChangedListener {
            val wordList = viewModel.searchWord(it.toString())
            newList(wordList)
        }

        binding.icRefresh.setOnClickListener {
            viewModel.refresh()
        }

    }

    private fun setAdapter(it: List<Country>?) {
        adapter.submitList(it)
        binding.rvHome.adapter = adapter
    }

    fun setItem(it: Global) {
        binding.tvGlobalTotalDeaths.text = it.totalDeaths.toString()
        binding.tvNumGlobalConfirmed.text = it.totalConfirmed.toString()

    }

    fun newList(list: MutableList<Country>) {
        adapter.submitList(list)
        adapter.notifyDataSetChanged()
    }
}
