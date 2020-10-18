package nl.donyell.presentation.detail

import nl.donyell.artists.detail.model.Song

data class ArtistDetailUIModel(
    val artist: String,
    val songs: List<Song>
)
