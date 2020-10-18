package nl.donyell.data.artists

import nl.donyell.artists.model.Artist

data class ArtistResponse(
    val id: Long,
    val name: String
) {
    fun toArtist(): Artist {
        return Artist(id, name)
    }
}
