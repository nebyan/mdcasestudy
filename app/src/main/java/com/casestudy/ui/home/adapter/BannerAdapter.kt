package com.casestudy.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casestudy.R
import com.casestudy.databinding.BannerItemBinding

class BannerAdapter(var userName: String): RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userName)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class ViewHolder(var binding: BannerItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(userName: String){
            val bannerMsg = binding.root.resources.getString(R.string.banner_msg, userName)
            binding.bannerTView.text = bannerMsg
        }
    }
}