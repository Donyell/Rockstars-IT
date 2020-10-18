package nl.donyell.data.artists.detail

import nl.donyell.artists.detail.model.Song

data class SongResponse(
    val id: Long,
    val name: String,
    val year: Int,
    val artist: String,
    val shortName: String?,
    val bpm: Int?,
    val duration: Long,
    val genre: String,
    val spotifyId: String?,
    val album: String?
) {

    fun toSong(): Song {
        return Song(
            id,
            name,
            year,
            artist,
            shortName,
            bpm,
            duration,
            genre,
            spotifyId,
            album
        )
    }
}
