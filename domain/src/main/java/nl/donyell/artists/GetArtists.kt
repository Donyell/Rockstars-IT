package nl.donyell.artists

import io.reactivex.Single
import nl.donyell.DataRepository
import nl.donyell.artists.model.Artist
import javax.inject.Inject

class GetArtists @Inject constructor(
    private val dataRepository: DataRepository
) {

    operator fun invoke(filter: String? = null): Single<List<Artist>> {
        return filter?.let { filterUsed ->
            dataRepository.getArtists()
                .map { artists ->
                    artists.filter { it.name.contains(filterUsed, true) }
                }
        } ?: run {
            dataRepository.getArtists()
        }
    }
}
