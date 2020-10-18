package nl.donyell.presentation.detail

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nl.donyell.artists.detail.GetSongsFromArtist
import nl.donyell.artists.detail.model.Song
import nl.donyell.presentation.generic.Provider
import nl.donyell.presentation.generic.SingleLiveEvent

class ArtistDetailViewModel @ViewModelInject constructor(
    private val artistNameProvider: Provider<String?>,
    private val getSongsFromArtist: GetSongsFromArtist
): ViewModel() {

    private var _artistInfo = MutableLiveData<ArtistDetailUIModel>()
    val artistInfo: LiveData<ArtistDetailUIModel> by lazy {
        fetchSongs()
        _artistInfo
    }

    private var _navigateBack = SingleLiveEvent<Boolean>()
    val navigateBack: LiveData<Boolean> = _navigateBack

    private var _showError = SingleLiveEvent<Boolean>()
    val showError: LiveData<Boolean> = _showError

    fun onBackClicked() {
        _navigateBack.postValue(true)
    }

    private fun fetchSongs() {
        artistNameProvider.value?.let {
            getSongsFromArtist(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::onFetchSongsSuccess, ::onFetchSongFail)
        }
    }

    private fun onFetchSongsSuccess(songs: List<Song>) {
        artistNameProvider.value?.let {
            _artistInfo.postValue(ArtistDetailUIModel(it, songs))
        }
    }

    private fun onFetchSongFail(throwable: Throwable) {
        _showError.postValue(true)
        Log.e(ArtistDetailViewModel::class.simpleName, "onFetchSongFail", throwable)
    }
}
