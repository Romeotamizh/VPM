package com.optisol.vpm.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.KeyListener
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.optisol.vpm.VPMApplication
import com.optisol.vpm.common.formatToLocale
import com.optisol.vpm.common.formatToString
import com.optisol.vpm.data.model.Feed
import com.optisol.vpm.databinding.CreateFeedEntryBinding
import com.optisol.vpm.databinding.FragmentFeedBinding
import com.optisol.vpm.ui.adapter.FeedAdapter
import com.optisol.vpm.ui.viewmodel.feed.FeedViewModel
import com.optisol.vpm.ui.viewmodel.feed.FeedViewModelFactory
import java.util.*
import kotlin.collections.ArrayList


class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private val createFeedEntryBinding: CreateFeedEntryBinding by lazy {
        CreateFeedEntryBinding.inflate(
            layoutInflater
        )
    }
    private val feeds = ArrayList<Feed>()
    private val bottomSheetDialog: BottomSheetDialog by lazy {
        BottomSheetDialog(requireContext()).apply {
            setContentView(
                createFeedEntryBinding.root
            )
        }
    }

    private val feedViewModel: FeedViewModel by lazy {
        ViewModelProvider(
            this,
            FeedViewModelFactory(VPMApplication.getFeedRepository(requireActivity().applicationContext))
        )
            .get(FeedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFeedBinding.inflate(
        inflater,
        container,
        false
    ).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObservers()
    }

    private fun initObservers() = binding.recyclerview.adapter?.let {

        feedViewModel.getFeeds().observe(viewLifecycleOwner, { it_ ->
            it_?.let { feedList ->
                val prevCount = it.itemCount
                feeds.addAll(feedList)
                it.notifyItemRangeInserted(prevCount, feedList.size)

            }
        })

    }

    private fun initUI() = with(binding) {
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = FeedAdapter(feeds, onFeedClick)

        }
        fab.setOnClickListener { showCreateFeedDialog() }
    }

    private val onFeedClick =
        fun(feed: Feed) { activity?.let { showCreateFeedDialog(feed) } }

    @SuppressLint("SetTextI18n")
    private fun showCreateFeedDialog(feed_: Feed? = null) {
        val feed = feed_ ?: Feed("", true, Date().formatToString())
        val isNew = feed_ == null

        bottomSheetDialog.show()
        with(createFeedEntryBinding) {
            editName.apply {
                setText(if (isNew) "" else feed.name)
                Log.d(TAG, "showCreateFeedDialog: ${feed.name}")
                keyListener?.let { tag = it }
                keyListener = if (!isNew) null else tag as KeyListener
                setImeActionLabel("ENTER", KeyEvent.KEYCODE_ENTER)

            }
            isLiveCheck.isChecked = feed.isLive
            createOrEditText.text = if (isNew) "Create" else "Edit"
            createdOn.text = "Created on : ${feed.createdDateTime.formatToLocale()}"

            createButton.setOnClickListener {
                val text = editName.text?.toString()?.trim()
                text?.let {
                    feed.apply {
                        name = it
                        isLive = isLiveCheck.isChecked
                    }
                }

                if (!isNew || (text != null && text.isNotEmpty()))
                    insertFeed(feed)
                else
                    closeCreateFeedDialog()
            }
            cancelButton.setOnClickListener { closeCreateFeedDialog() }
        }


    }


    private fun closeCreateFeedDialog(isFeedEdited: Boolean = false) = with(bottomSheetDialog) {
        requireActivity().runOnUiThread {
            hide()
            if (isFeedEdited) {
                feeds.clear()
                binding.recyclerview.adapter = FeedAdapter(feeds, onFeedClick)
                initObservers()
            }
        }

    }


    companion object {
        private const val TAG = "FeedFragment"
        fun newInstance() = FeedFragment()
    }

    private fun insertFeed(feed: Feed) = feedViewModel.insertFeed(feed)
        .invokeOnCompletion { closeCreateFeedDialog(true) }


}