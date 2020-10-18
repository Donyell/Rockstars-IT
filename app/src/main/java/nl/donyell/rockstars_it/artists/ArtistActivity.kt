package nl.donyell.rockstars_it.artists

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_artists.*
import nl.donyell.presentation.artists.ArtistUIModel
import nl.donyell.presentation.artists.ArtistsViewModel
import nl.donyell.rockstars_it.R
import nl.donyell.rockstars_it.artists.detail.ArtistDetailActivity
import nl.donyell.rockstars_it.generic.DelayedOnQueryTextListener


@AndroidEntryPoint
class ArtistsActivity : AppCompatActivity() {

    private val artistsViewModel: ArtistsViewModel by viewModels()

    private val adapter by lazy {
        ArtistsAdapter().apply {
            onArtistClick = { artistsViewModel.onArtistClicked(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artists)
        setupToolbar()
        setupRecyclerView()
        setupSearchView()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(artists_toolbar)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
            artists_recycler_view.context,
            layoutManager.orientation
        )
        artists_recycler_view.layoutManager = layoutManager
        artists_recycler_view.addItemDecoration(dividerItemDecoration)
        artists_recycler_view.adapter = adapter
    }

    private fun setupSearchView() {
        artists_search_view.setOnQueryTextListener(object: DelayedOnQueryTextListener() {
            override fun onDelayerQueryTextChange(query: String) {
                artistsViewModel.onFilterUsed(query)
            }
        })
    }

    private fun observeViewModel() {
        artistsViewModel.artists.observe(this, adapter::submitList)
        artistsViewModel.navigateToArtistDetail.observe(this, ::navigateToArtistDetail)
        artistsViewModel.showError.observe(this, ::showError)
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

    private fun navigateToArtistDetail(artist: ArtistUIModel) {
        startActivity(ArtistDetailActivity.createIntent(this, artist.name))
    }
}
