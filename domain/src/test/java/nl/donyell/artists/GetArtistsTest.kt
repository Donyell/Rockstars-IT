package nl.donyell.artists

import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import nl.donyell.DataRepository
import nl.donyell.artists.model.Artist
import nl.donyell.util.RxMockitoExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

@ExtendWith(RxMockitoExtension::class)
internal class GetArtistsTest {

    @Mock
    lateinit var dataRepository: DataRepository

    @InjectMocks
    lateinit var getArtists: GetArtists

    @Test
    fun `When artists are requested without a filter, return all the artists from the data repository`() {
        // Given
        val inputArtists = listOf(
            Artist(1L, "A")
        )

        given(dataRepository.getArtists())
            .willReturn(Single.just(inputArtists))

        // When
        val result = getArtists().test()

        // Then
        result.assertValue(inputArtists)
    }

    @Test
    fun `When artists are requested using a filter, return the filtered artists from the data repository`() {
        // Given
        val unfilteredArtists = listOf(
            Artist(1L, "A"),
            Artist(2L, "B")
        )

        val filteredArtist = listOf(
            Artist(1L, "A")
        )

        given(dataRepository.getArtists())
            .willReturn(Single.just(unfilteredArtists))

        // When
        val result = getArtists("A").test()

        // Then
        result.assertValue(filteredArtist)
    }
}
