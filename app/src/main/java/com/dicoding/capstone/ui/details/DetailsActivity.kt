package com.dicoding.capstone.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.capstone.R
import com.dicoding.capstone.core.Constant.DETAILS_POSTER_RADIUS
import com.dicoding.capstone.core.Constant.IMAGE_BASE_URL
import com.dicoding.capstone.core.Constant.RADIUS
import com.dicoding.capstone.core.Constant.SAMPLING
import com.dicoding.capstone.core.domain.model.Movee
import com.dicoding.capstone.databinding.ActivityDetailsBinding
import com.dicoding.capstone.databinding.ActivityDetailsBinding.inflate
import com.dicoding.capstone.databinding.ContentDetailsBinding
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var activityDetailsBinding: ActivityDetailsBinding
    private lateinit var contentDetailsBinding: ContentDetailsBinding

    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsBinding = inflate(layoutInflater)
        activityDetailsBinding.apply {
            setContentView(root)
            contentDetailsBinding = details
        }

        intent.getParcelableExtra<Movee>(EXTRA_DATA).also {
            showDetails(it)
        }
    }

    private fun showDetails(movee: Movee?) {
        contentDetailsBinding.apply {
            movee?.apply {
                Glide
                    .with(this@DetailsActivity)
                    .load("$IMAGE_BASE_URL$backdrop")
                    .apply(placeholderOf(R.drawable.bg_loading_backdrop).error(R.drawable.bg_loading_poster))
                    .apply(bitmapTransform(BlurTransformation(RADIUS, SAMPLING)))
                    .into(imgBackdrop)
                Glide
                    .with(this@DetailsActivity)
                    .load("$IMAGE_BASE_URL$poster")
                    .apply(placeholderOf(R.drawable.bg_loading_poster).error(R.drawable.bg_loading_poster))
                    .transform(RoundedCorners(DETAILS_POSTER_RADIUS))
                    .into(imgPoster)
                tvTitle.text = title
                tvRating.text = rating.toString()
                tvReleaseDate.text = releaseDate
                tvSynopsis.text = synopsis

                var watchlistStatus = watchlist

                setWatchlistStatus(watchlistStatus)
                btnWatchlist.setOnClickListener {
                    watchlistStatus = !watchlistStatus
                    detailsViewModel.watchlist(this, watchlistStatus)
                    setWatchlistStatus(watchlistStatus)
                }
            }
        }
    }

    private fun setWatchlistStatus(watchlistStatus: Boolean) {
        contentDetailsBinding.btnWatchlist.apply {
            if (watchlistStatus) {
                setImageDrawable(getDrawable(this@DetailsActivity, R.drawable.ic_watchlist_true))
            } else {
                setImageDrawable(getDrawable(this@DetailsActivity, R.drawable.ic_watchlist_false))
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}