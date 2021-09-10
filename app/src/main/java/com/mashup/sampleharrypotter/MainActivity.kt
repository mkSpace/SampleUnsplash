package com.mashup.sampleharrypotter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mashup.sampleharrypotter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        bindViewModels()
    }

    private fun setupViews() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = null
            setHasFixedSize(true)
            adapter = photoAdapter
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchImages()
        }
    }

    private fun bindViewModels() {
        viewModel.photos
            .onEach {
                photoAdapter.submitList(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
            .launchIn(lifecycleScope)
    }
}