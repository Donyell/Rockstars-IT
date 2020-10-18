package nl.donyell.data

import io.reactivex.Single
import nl.donyell.artists.model.Artist
import nl.donyell.DataRepository
import nl.donyell.artists.detail.model.Song
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val rockstarsITService: RockstarsITService
) : DataRepository {

    override fun getArtists(): Single<List<Artist>> {
        return rockstarsITService.getArtists()
            .map { artists ->
                artists.map { it.toArtist() }
            }
    }

    override fun getSongsFromArtist(artist: String): Single<List<Song>> {
        return rockstarsITService.getSongsFromArtist(artist)
            .map { songs ->
                songs.map { it.toSong() }
            }
    }
}
