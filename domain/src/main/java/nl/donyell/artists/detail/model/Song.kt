package nl.donyell.artists.detail.model

data class Song(
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
)
