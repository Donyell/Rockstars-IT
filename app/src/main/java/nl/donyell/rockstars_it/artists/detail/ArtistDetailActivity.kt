package nl.donyell.rockstars_it.artists.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_artist_detail.*
import nl.donyell.presentation.detail.ArtistDetailUIModel
import nl.donyell.presentation.detail.ArtistDetailViewModel
import nl.donyell.rockstars_it.R

@AndroidEntryPoint
class ArtistDetailActivity: AppCompatActivity() {

    private val artistDetailViewModel: ArtistDetailViewModel by viewModels()

    private val adapter by lazy { SongAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)
        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(artist_detail_toolbar)
        artist_detail_toolbar.setNavigationOnClickListener {
            artistDetailViewModel.onBackClicked()
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            artist_detail_recycler_view.context,
            layoutManager.orientation
        )
        artist_detail_recycler_view.layoutManager = layoutManager
        artist_detail_recycler_view.addItemDecoration(dividerItemDecoration)
        artist_detail_recycler_view.adapter = adapter
    }

    private fun observeViewModel() {
        artistDetailViewModel.artistInfo.observe(this, ::bindArtistInfo)
        artistDetailViewModel.navigateBack.observe(this, ::navigateBack)
        artistDetailViewModel.showError.observe(this, ::showError)
    }

    private fun bindArtistInfo(artist: ArtistDetailUIModel) {
        title = artist.artist
        adapter.submitList(artist.songs)
    }

    private fun navigateBack(navigateBack: Boolean) {
        if (navigateBack){
            onBackPressed()
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            Toast.makeText(
                this,
                getString(R.string.generic_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {

        const val ARTIST_NAME_EXTRA = "ARTIST_NAME_EXTRA"

        fun createIntent(context: Context, artistName: String): Intent {
            return Intent(context, ArtistDetailActivity::class.java).apply {
                putExtra(ARTIST_NAME_EXTRA, artistName)
            }
        }
    }
}
