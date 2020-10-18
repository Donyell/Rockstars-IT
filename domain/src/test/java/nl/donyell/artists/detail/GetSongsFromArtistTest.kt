package nl.donyell.artists.detail

import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import nl.donyell.DataRepository
import nl.donyell.artists.detail.model.Song
import nl.donyell.util.RxMockitoExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

@ExtendWith(RxMockitoExtension::class)
internal class GetSongsFromArtistTest {

    @Mock
    lateinit var dataRepository: DataRepository

    @InjectMocks
    lateinit var getSongsFromArtist: GetSongsFromArtist

    @Test
    fun `When songs are requested, return the data repository songs`() {
        // Given
        val inputSongs = emptyList<Song>()
        val inputArtist = "Piet"

        given(dataRepository.getSongsFromArtist(inputArtist))
            .willReturn(Single.just(inputSongs))

        // When
        val result = getSongsFromArtist(inputArtist).test()

        // Then
        result.assertValue(inputSongs)
    }
}
