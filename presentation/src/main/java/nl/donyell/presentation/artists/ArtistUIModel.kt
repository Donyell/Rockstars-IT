package nl.donyell.presentation.artists

import nl.donyell.artists.model.Artist

data class ArtistUIModel(
    val id: Long,
    val name: String
) {
    constructor(artist: Artist): this (
        id = artist.id,
        name = artist.name
    )
}
