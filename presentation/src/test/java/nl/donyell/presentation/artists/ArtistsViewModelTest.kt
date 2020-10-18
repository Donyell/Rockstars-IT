package nl.donyell.presentation.artists

import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import nl.donyell.artists.GetArtists
import nl.donyell.artists.model.Artist
import nl.donyell.presentation.RxAndroidMockitoExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

@ExtendWith(RxAndroidMockitoExtension::class)
internal class ArtistsViewModelTest {

    @Mock
    lateinit var getArtists: GetArtists

    @InjectMocks
    lateinit var artistsViewModel: ArtistsViewModel

    @Test
    fun `When fetching artists succeeds, emit the artists`() {
        // Given
        val inputArtists = listOf(
            Artist(1, "a"),
            Artist(2, "b")
        )
        val artistUIModels = inputArtists.map { ArtistUIModel(it) }

        given(getArtists())
            .willReturn(Single.just(inputArtists))

        // When
        val result = artistsViewModel.artists.test()

        // Then
        result.assertValue(artistUIModels)
    }

    @Test
    fun `When fetching artists fails, emit the show error event`() {
        // Given
        given(getArtists())
            .willReturn(Single.error(Throwable()))

        // When
        val result = artistsViewModel.showError.test()
        artistsViewModel.artists.test()

        // Then
        result.assertValue(true)
    }

    @Test
    fun `When an artist is clicked, emit the artist detail page navigation event`() {
        // Given
        val artistUIModel = ArtistUIModel(1, "a")

        // When
        val result = artistsViewModel.navigateToArtistDetail.test()
        artistsViewModel.onArtistClicked(artistUIModel)

        // Then
        result.assertValue(artistUIModel)
    }
}
