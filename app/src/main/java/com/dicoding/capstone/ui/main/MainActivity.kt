package com.dicoding.capstone.ui.main

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri.parse
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.core.Constant.WATCHLIST_URI
import com.dicoding.capstone.core.adapter.MoveeAdapter
import com.dicoding.capstone.core.source.Resource
import com.dicoding.capstone.databinding.ActivityMainBinding
import com.dicoding.capstone.databinding.ActivityMainBinding.inflate
import com.dicoding.capstone.ui.details.DetailsActivity
import com.dicoding.capstone.ui.details.DetailsActivity.Companion.EXTRA_DATA
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var moveeAdapter: MoveeAdapter

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = inflate(layoutInflater)
        activityMainBinding.apply {
            setContentView(root)

            moveeAdapter = MoveeAdapter()
            moveeAdapter.onItemClick = { movee ->
                Intent(this@MainActivity, DetailsActivity::class.java).also {
                    it.putExtra(EXTRA_DATA, movee)
                    startActivity(it)
                }
            }

            mainViewModel.movee.observe(this@MainActivity, {
                it.apply {
                    if (this != null) {
                        when (this) {
                            is Resource.Loading -> {
                                showLoading(true)
                            }
                            is Resource.Success -> {
                                showLoading(false)
                                moveeAdapter.setData(data)
                            }
                            is Resource.Error -> {
                                showLoading(false)
                                viewError.apply {
                                    root.visibility = GONE
                                    tvError.text = message
                                }
                            }
                        }
                    }
                }
            })

            btnWatchlist.setOnClickListener {
                val uri = parse(WATCHLIST_URI)
                startActivity(Intent(ACTION_VIEW, uri))
            }

            rvMovies.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = moveeAdapter
            }
        }
    }

    private fun showLoading(state: Boolean) {
        activityMainBinding.loading.visibility = if (state) VISIBLE else GONE
    }
}