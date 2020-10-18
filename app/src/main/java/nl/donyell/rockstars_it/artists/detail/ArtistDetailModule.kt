package nl.donyell.rockstars_it.artists.detail

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import nl.donyell.presentation.generic.Provider
import nl.donyell.rockstars_it.artists.detail.ArtistDetailActivity.Companion.ARTIST_NAME_EXTRA

@Module
@InstallIn(ActivityComponent::class)
interface ArtistDetailModule {

    companion object {

        @Provides
        fun provideArtistName(activity: Activity): Provider<String?> {
            return Provider(activity.intent.getStringExtra(ARTIST_NAME_EXTRA))
        }
    }
}
