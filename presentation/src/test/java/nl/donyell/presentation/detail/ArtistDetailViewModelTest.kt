package nl.donyell.presentation.detail

import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import nl.donyell.artists.detail.GetSongsFromArtist
import nl.donyell.artists.detail.model.Song
import nl.donyell.presentation.RxAndroidMockitoExtension
import nl.donyell.presentation.generic.Provider
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

@ExtendWith(RxAndroidMockitoExtension::class)
internal class ArtistDetailViewModelTest {

    @Mock
    lateinit var artistNameProvider: Provider<String?>

    @Mock
    lateinit var getSongsFromArtist: GetSongsFromArtist

    @InjectMocks
    lateinit var artistDetailViewModel: ArtistDetailViewModel

    @Test
    fun `When fetching songs succeeds, emit the songs`() {
        // Given
        val inputSong =  Song(
            1L,
            "name",
            1123,
            "Piet",
            null,
            null,
            4L,
            "Rock",
            null,
            null
        )
        val inputSongs = listOf(
            inputSong,
            inputSong.copy(id = 2L),
            inputSong.copy(id = 3L)
        )

        val artistInfo = ArtistDetailUIModel(
            inputSong.artist,
            inputSongs
        )

        given(artistNameProvider.value)
            .willReturn(inputSong.artist)

        given(getSongsFromArtist(inputSong.artist))
            .willReturn(Single.just(inputSongs))

        // When
        val result = artistDetailViewModel.artistInfo.test()

        // Then
        result.assertValue(artistInfo)
    }

    @Test
    fun `When fetching songs fails, emit the show error event`() {
        // Given
        val artist = "Piet"

        given(artistNameProvider.value)
            .willReturn(artist)

        given(getSongsFromArtist(artist))
            .willReturn(Single.error(Throwable()))

        // When
        val result = artistDetailViewModel.showError.test()
        artistDetailViewModel.artistInfo.test()

        // Then
        result.assertValue(true)
    }

    @Test
    fun `When the back button is clicked, emit the navigate back event`() {
        // When
        val result = artistDetailViewModel.navigateBack.test()
        artistDetailViewModel.onBackClicked()

        // Then
        result.assertValue(true)
    }
}
