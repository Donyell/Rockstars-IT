package nl.donyell.data

import io.reactivex.Single
import nl.donyell.data.artists.ArtistResponse
import nl.donyell.data.artists.detail.SongResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RockstarsITService {

    @GET("/artists")
    fun getArtists(): Single<List<ArtistResponse>>

    @GET("/songs")
    fun getSongsFromArtist(@Query("artist") artist: String): Single<List<SongResponse>>
}
