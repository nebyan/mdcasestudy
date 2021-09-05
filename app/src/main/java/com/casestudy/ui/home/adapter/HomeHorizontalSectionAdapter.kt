package com.casestudy.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casestudy.databinding.HorizontalSectionItemBinding

class HomeHorizontalSectionAdapter(var meditationAdapter: MeditationAdapter): RecyclerView.Adapter<HomeHorizontalSectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HorizontalSectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(meditationAdapter)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(val binding: HorizontalSectionItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(adapter: MeditationAdapter){
            binding.horizontalSectionRView.adapter = adapter
        }
    }
}