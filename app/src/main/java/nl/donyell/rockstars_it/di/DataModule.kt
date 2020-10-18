package nl.donyell.rockstars_it.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import nl.donyell.DataRepository
import nl.donyell.data.DataRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideDataRepository(
        dataRepositoryImpl: DataRepositoryImpl
    ): DataRepository {
        return dataRepositoryImpl
    }
}
