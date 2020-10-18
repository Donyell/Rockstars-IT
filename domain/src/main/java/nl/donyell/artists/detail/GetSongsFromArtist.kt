package nl.donyell.artists.detail

import io.reactivex.Single
import nl.donyell.DataRepository
import nl.donyell.artists.detail.model.Song
import javax.inject.Inject

class GetSongsFromArtist @Inject constructor(
    private val dataRepository: DataRepository
) {

    operator fun invoke(artistName: String): Single<List<Song>> {
        return dataRepository.getSongsFromArtist(artistName)
    }
}
