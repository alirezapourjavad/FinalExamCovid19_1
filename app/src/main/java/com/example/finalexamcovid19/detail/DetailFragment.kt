package com.example.finalexamcovid19.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalexamcovid19.R
import com.example.finalexamcovid19.databinding.FragmentDetailBinding
import com.example.finalexamcovid19.dbModel.Country
import com.example.finalexamcovid19.home.HomeViewModel
import kotlin.math.hypot


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    lateinit var homeViewModel: HomeViewModel
    lateinit var countries: List<Country>
    lateinit var item: Country

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setItem()

        binding.icBack?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    fun setItem() {
        val country = DetailFragmentArgs.fromBundle(requireArguments()).country
        binding.viewModel = country
    }
}

