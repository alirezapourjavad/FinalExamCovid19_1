package com.example.finalexamcovid19.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexamcovid19.databinding.RecycleItemBinding
import com.example.finalexamcovid19.dbModel.Country

class HomeAdapter(val itemClick: (country: Country) -> Unit) :
    ListAdapter<Country, HomeAdapter.HomeViewHolder>(CountryDiffUtil()) {

    class HomeViewHolder(val binding: RecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = currentList[position]
        holder.binding.viewModel = item
        holder.itemView.setOnClickListener { itemClick.invoke(item)
        }
    }
}

class CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }

}





