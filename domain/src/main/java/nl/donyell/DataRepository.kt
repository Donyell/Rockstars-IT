package nl.donyell

import io.reactivex.Single
import nl.donyell.artists.model.Artist
import nl.donyell.artists.detail.model.Song

interface DataRepository {

    fun getArtists(): Single<List<Artist>>

    fun getSongsFromArtist(artist: String): Single<List<Song>>
}
