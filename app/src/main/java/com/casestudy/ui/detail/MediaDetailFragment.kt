package com.casestudy.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.casestudy.MainActivity
import com.casestudy.R
import com.casestudy.databinding.FragmentMediaDetailBinding
import com.casestudy.model.MEDIA_TYPE
import com.casestudy.model.MediaDetailHolder
import com.casestudy.player.AppPlayer
import com.casestudy.player.AppPlayerListener
import com.casestudy.ui.BaseFragment
import com.casestudy.util.Utils
import com.casestudy.util.gone
import com.casestudy.util.visible

class MediaDetailFragment: BaseFragment(), AppPlayerListener {

    private var _binding: FragmentMediaDetailBinding? = null
    private val binding get() = _binding!!

    private val appPlayer by lazy {
        AppPlayer(requireContext(),this)
    }

    private val mediaDetailHolder: MediaDetailHolder? by lazy {
        arguments?.getParcelable(ARG_TITLE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setToolbar()
        setContent()
        setPlayBtn()
    }

    private fun setToolbar(){
        val mainActivity = activity as MainActivity
        mainActivity.setSupportActionBar(binding.toolbar)
        mainActivity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { mainActivity.onBackPressed() }
        setTitle()
    }

    private fun setTitle(){

        val title = when(mediaDetailHolder?.type){
            MEDIA_TYPE.MEDITATION -> {
                 getString(R.string.meditation_detail)
            }
            MEDIA_TYPE.STORY -> {
                 getString(R.string.story_detail)
            }
            else -> ""
        }

        binding.toolbar.title = title
    }

    private fun setContent(){

        mediaDetailHolder?.let {
            binding.titleTView.text = it.title
            binding.descTView.text = it.desc
            binding.bgIView.load(it.imageUrl)
            binding.dateTView.text = Utils.getDate(it.date)
        }

    }

    private fun setPlayBtn(){
        binding.playPauseIBtn.setOnClickListener {
            appPlayer.play(STREAM_URL)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appPlayer.destroy()
    }

    override fun started() {
        binding.playPauseIView.setImageResource(R.drawable.pause_icon)
        binding.layerViewDark.visible()
    }

    override fun paused() {
        binding.playPauseIView.setImageResource(R.drawable.play_icon)
        binding.layerViewDark.gone()
    }

    companion object {
        val ARG_TITLE = "mediaDetailHolder"
        val STREAM_URL = "https://d2r0ihkco3hemf.cloudfront.net/bgxupraW2spUpr_y2.mp3"
    }
}