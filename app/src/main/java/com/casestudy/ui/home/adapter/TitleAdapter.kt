package com.casestudy.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casestudy.databinding.TitleItemBinding

class TitleAdapter(var title: String): RecyclerView.Adapter<TitleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(title)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(var binding: TitleItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(title: String){
            binding.titleTView.text = title
        }
    }
}