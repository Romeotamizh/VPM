package com.optisol.vpm.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.optisol.vpm.VPMApplication
import com.optisol.vpm.data.model.Video
import com.optisol.vpm.data.retrofit.Status.*
import com.optisol.vpm.databinding.FragmentVideoBinding
import com.optisol.vpm.ui.adapter.VideoAdapter
import com.optisol.vpm.common.android.PaginatedOnScrollListener
import com.optisol.vpm.databinding.CreateFeedEntryBinding
import com.optisol.vpm.ui.viewmodel.video.VideoViewModel
import com.optisol.vpm.ui.viewmodel.video.VideoViewModelFactory


class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding
    private val videoList = ArrayList<Video>()
    private val videoAdapter: VideoAdapter by lazy { VideoAdapter(videoList) }
    private val videoViewModel: VideoViewModel by lazy {
        ViewModelProvider(
            this,
            VideoViewModelFactory(VPMApplication.getVideoRepository())
        ).get(VideoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentVideoBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun loadPage(pageNumber: Int) = with(binding) {
        videoViewModel.getVideos(pageNumber).observe(viewLifecycleOwner, {
            it?.let { result ->
                when (result.status) {
                    SUCCESS -> {
                        result.data?.let { data ->
                            val prevCount = videoAdapter.itemCount
                            videoList.addAll(data.data)
                            videoAdapter.notifyItemRangeInserted(prevCount, data.data.size)
                            isLoading = false
                        }


                    }
                    FAILURE -> {
                        Log.d("TAG", "loadPage: " + result.message)
                        isLoading = false
                    }
                    LOADING -> {
                        isLoading = true
                        Log.d("TAG", "loadPage: " + result.message)
                    }
                }
            }


        })

    }


    private fun initUI() = with(binding) {

        currentPage = 1
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = videoAdapter
            addOnScrollListener(object :
                PaginatedOnScrollListener(layoutManager as LinearLayoutManager) {
                override fun isLoading() = isLoading ?: true
                override fun isLastPage() = currentPage ?: 1 > MAX_PAGES
                override fun loadMorePages() {
                    isLoading = true
                    currentPage?.let {
                        if (it < MAX_PAGES) {
                            loadPage(it + 1)
                            currentPage = it + 1
                        }
                    }


                }

            })
        }
        loadPage(1)


    }


    companion object {

        fun newInstance() = VideoFragment()
        private const val TAG = "VideoFragment"
        const val MAX_PAGES = 2
    }
}