package com.casestudy.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casestudy.R
import com.casestudy.databinding.MeditationItemBinding
import com.casestudy.model.Meditation
import com.casestudy.util.loadCoil

class MeditationAdapter(var mediationList: List<Meditation>,
                        var meditationAdapterListener: MeditationAdapterListener):
    RecyclerView.Adapter<MeditationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MeditationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meditation = mediationList[position]
        holder.bind(meditation)
    }

    override fun getItemCount(): Int {
        return mediationList.size
    }

    inner class ViewHolder(var binding: MeditationItemBinding): RecyclerView.ViewHolder(binding.root){

        val cornerRadius = binding.root.resources.getDimensionPixelSize(R.dimen.corner_radius).toFloat()

        fun bind(meditation: Meditation){

            binding.medTView.text = meditation.title
            binding.medSubTView.text = meditation.subtitle
            binding.medIView.loadCoil(meditation.image.small, cornerRadius)

            binding.root.setOnClickListener {
                meditationAdapterListener.onSelectedItem(meditation)
            }

        }
    }

    interface MeditationAdapterListener{
        fun onSelectedItem(meditation: Meditation)
    }
}