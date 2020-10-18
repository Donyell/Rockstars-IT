package nl.donyell.presentation.artists

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import nl.donyell.artists.GetArtists
import nl.donyell.artists.model.Artist
import nl.donyell.presentation.generic.SingleLiveEvent

class ArtistsViewModel @ViewModelInject constructor(
    private val getArtists: GetArtists
) : ViewModel() {

    private var _artists = MutableLiveData<List<ArtistUIModel>>()
    val artists: LiveData<List<ArtistUIModel>> by lazy {
        fetchArtists()
        _artists
    }

    private var _navigateToArtistDetail = SingleLiveEvent<ArtistUIModel>()
    val navigateToArtistDetail: LiveData<ArtistUIModel> = _navigateToArtistDetail

    private var _showError = SingleLiveEvent<Boolean>()
    val showError: LiveData<Boolean> = _showError

    private val disposables = CompositeDisposable()

    fun onFilterUsed(filter: String) {
        fetchArtists(filter)
    }

    fun onArtistClicked(artist: ArtistUIModel) {
        _navigateToArtistDetail.postValue(artist)
    }

    private fun fetchArtists(filter: String? = null) {
        getArtists(filter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onFetchArtistsSuccess, ::onFetchArtistsFail)
            .addTo(disposables)
    }

    private fun onFetchArtistsSuccess(artists: List<Artist>) {
        _artists.postValue(artists.map { ArtistUIModel(it) })
    }

    private fun onFetchArtistsFail(throwable: Throwable) {
        _showError.postValue(true)
        Log.e(javaClass.simpleName, "onFetchArtistsFail", throwable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
