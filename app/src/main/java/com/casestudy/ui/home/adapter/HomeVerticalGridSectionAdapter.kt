package com.casestudy.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casestudy.databinding.VerticalGridSectionItemBinding

class HomeVerticalGridSectionAdapter(var storyAdapter: StoryAdapter): RecyclerView.Adapter<HomeVerticalGridSectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = VerticalGridSectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(storyAdapter)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(val binding: VerticalGridSectionItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(adapter: StoryAdapter){
            binding.horizontalSectionRView.adapter = adapter
        }
    }
}