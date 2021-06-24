package com.dicoding.capstone.watchlist.ui

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.core.adapter.MoveeAdapter
import com.dicoding.capstone.ui.details.DetailsActivity
import com.dicoding.capstone.ui.details.DetailsActivity.Companion.EXTRA_DATA
import com.dicoding.capstone.watchlist.databinding.ActivityWatchlistBinding
import com.dicoding.capstone.watchlist.databinding.ActivityWatchlistBinding.inflate
import com.dicoding.capstone.watchlist.di.watchlistModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class WatchlistActivity : AppCompatActivity() {

    private lateinit var activityWatchlistBinding: ActivityWatchlistBinding

    private val watchlistViewModel: WatchlistViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityWatchlistBinding = inflate(layoutInflater)
        activityWatchlistBinding.apply {
            setContentView(root)

            loadKoinModules(watchlistModule)

            btnBack.setNavigationOnClickListener {
                onBackPressed()
            }

            val moveeAdapter = MoveeAdapter()
            
            moveeAdapter.onItemClick = { movee ->
                Intent(this@WatchlistActivity, DetailsActivity::class.java).also {
                    it.putExtra(EXTRA_DATA, movee)
                    startActivity(it)
                }
            }

            watchlistViewModel.moviesWatchlist.observe(this@WatchlistActivity, {
                moveeAdapter.setData(it)
                viewEmpty.root.visibility = if (it.isNotEmpty()) GONE else VISIBLE
            })

            rvMovies.apply {
                layoutManager = LinearLayoutManager(this@WatchlistActivity)
                setHasFixedSize(true)
                adapter = moveeAdapter
            }
        }
    }

}