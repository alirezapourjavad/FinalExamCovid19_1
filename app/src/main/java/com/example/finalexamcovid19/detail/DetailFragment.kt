package com.example.finalexamcovid19.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalexamcovid19.databinding.FragmentDetailBinding
import com.example.finalexamcovid19.dbModel.Country
import com.example.finalexamcovid19.home.HomeViewModel


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    lateinit var homeViewModel: HomeViewModel
    lateinit var item: Country

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val country: List<Country>? = homeViewModel.response?.countries

        val countryId = DetailFragmentArgs.fromBundle(requireArguments()).countryId

        if (country != null) {
            for (data in country) {
                if (countryId == data.iD) {
                    item = data
                    binding.tvCountryDetail.text = item.country
                    binding.tvDateDetail.text = item.date.toString()
                    binding.tvNconfirmedDetail.text = item.newConfirmed.toString()
                    binding.tvNumTconfrmedDetail.text = item.totalConfirmed.toString()
                    binding.tvNdeathsDetail.text = item.newDeaths.toString()
                    binding.tvNumTdeatsDetail.text = item.totalDeaths.toString()
                    binding.tvNumNrecoveredDetail.text = item.newRecovered.toString()
                    binding.tvNumTrecoveredDetail.text = item.totalRecovered.toString()
                }
            }
        }




        binding.icBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}