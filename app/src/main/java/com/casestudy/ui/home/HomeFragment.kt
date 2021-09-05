package com.casestudy.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.casestudy.MainActivity
import com.casestudy.R
import com.casestudy.databinding.FragmentHomeBinding
import com.casestudy.model.*
import com.casestudy.ui.BaseFragment
import com.casestudy.ui.detail.MediaDetailFragment
import com.casestudy.ui.home.adapter.*
import com.casestudy.util.Result
import com.casestudy.util.gone
import com.casestudy.util.toMediaDetailHolder
import com.casestudy.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: BaseFragment(), MeditationAdapter.MeditationAdapterListener, StoryAdapter.StoryAdapterListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewmodel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).setStatusBarColor(Color.TRANSPARENT)

        viewmodel.loadHomeData()

        lifecycleScope.launch {
            viewmodel.homeData.collect { result ->
                when(result){
                    is Result.Success -> {
                        result.data?.let {
                            setHomeRView(it)
                            binding.loadingPBar.gone()
                            binding.errorTView.gone()
                        }
                    }
                    is Result.Loading -> {
                        binding.loadingPBar.visible()
                        binding.errorTView.gone()
                    }
                    is Result.Error -> {
                        binding.loadingPBar.gone()
                        binding.errorTView.visible()
                    }
                }
            }
        }

    }

    private fun setHomeRView(homeData: HomeData){

        val concatAdapter = ConcatAdapter()

        concatAdapter.addAdapter(TitleAdapter(getString(R.string.meditations)))
        val mediationAdapter = MeditationAdapter(homeData.meditations, this)
        val horizontalSectionAdapter = HomeHorizontalSectionAdapter(mediationAdapter)
        concatAdapter.addAdapter(horizontalSectionAdapter)

        if (homeData.isBannerEnabled){
            concatAdapter.addAdapter(BannerAdapter(User.userName!!))
        }

        concatAdapter.addAdapter(TitleAdapter(getString(R.string.stories)))
        val storyAdapter = StoryAdapter(homeData.stories, this)
        val verticalGridSectionAdapter = HomeVerticalGridSectionAdapter(storyAdapter)
        concatAdapter.addAdapter(verticalGridSectionAdapter)

        binding.homeRView.adapter = concatAdapter
    }

    override fun onSelectedItem(meditation: Meditation) {

        val mediaDetailHolder = meditation.toMediaDetailHolder()
        showMediaDetailFragment(mediaDetailHolder)
    }

    override fun onSelectedItem(story: Story) {

        val mediaDetailHolder = story.toMediaDetailHolder()
        showMediaDetailFragment(mediaDetailHolder)
    }

    private fun showMediaDetailFragment(mediaDetailHolder: MediaDetailHolder){
        val bundle = Bundle().apply {
            putParcelable(MediaDetailFragment.ARG_TITLE, mediaDetailHolder)
        }
        findNavController().navigate(R.id.action_homeFragment_to_mediaDetailFragment, bundle)
    }

}