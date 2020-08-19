package com.example.newsFeed.mainFeed.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsFeed.R

class MainFeedFragment : Fragment() {

    private val listAdapter = MainFeedListAdapter()

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: View
    private lateinit var mainFeedViewModel: MainFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainFeedViewModel = ViewModelProvider(
            this,
            MainFeedViewModelFactory()
        ).get(MainFeedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_feed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emptyView = view.findViewById(R.id.empty_view)

        initToolbar(view)

        initRecyclerView(view)

        renderList(view)
    }


    private fun renderList(rootView: View) {
        if (listAdapter.itemList.isNotEmpty()) {
            emptyView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        } else {
            emptyView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    private fun initRecyclerView(rootView: View) {
        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        //        recyclerView.addItemDecoration(DividerItemDecoration(this.context, RecyclerView.VERTICAL))
    }

    private fun initToolbar(rootView: View) {
        (activity as AppCompatActivity).setSupportActionBar(rootView.findViewById(R.id.tool_bar))
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
    }
}