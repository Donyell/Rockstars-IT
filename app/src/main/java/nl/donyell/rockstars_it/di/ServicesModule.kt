package nl.donyell.rockstars_it.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import nl.donyell.data.RockstarsITService
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
class ServicesModule {

    @Provides
    fun provideRockstarsITService(
        retrofit: Retrofit
    ): RockstarsITService {
        return retrofit.create(RockstarsITService::class.java)
    }
}
