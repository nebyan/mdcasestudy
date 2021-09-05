package com.casestudy.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casestudy.R
import com.casestudy.databinding.StoryItemBinding
import com.casestudy.model.Story
import com.casestudy.util.loadCoil

class StoryAdapter(var storyList: List<Story>,
                   var storyAdapterListener: StoryAdapterListener):
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val story = storyList[position]
        holder.bind(story)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    inner class ViewHolder(var binding: StoryItemBinding): RecyclerView.ViewHolder(binding.root){

        val cornerRadius = binding.root.resources.getDimensionPixelSize(R.dimen.corner_radius).toFloat()

        fun bind(story: Story){

            binding.storyTView.text = story.name
            binding.storySubTView.text = story.category
            binding.storyIView.loadCoil(story.image.small, cornerRadius)

            binding.root.setOnClickListener {
                storyAdapterListener.onSelectedItem(story)
            }
        }
    }

    interface StoryAdapterListener{
        fun onSelectedItem(story: Story)
    }
}