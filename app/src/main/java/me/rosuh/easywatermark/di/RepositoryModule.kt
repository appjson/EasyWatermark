package site.appjson.easywatermark.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import site.appjson.easywatermark.data.db.dao.TemplateDao
import site.appjson.easywatermark.data.repo.MemorySettingRepo
import site.appjson.easywatermark.data.repo.TemplateRepository
import site.appjson.easywatermark.data.repo.UserConfigRepository
import site.appjson.easywatermark.data.repo.WaterMarkRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Named("UserPreferences")
    @Provides
    @Singleton
    fun provideUserRepository(dataStore: DataStore<Preferences>): UserConfigRepository {
        return UserConfigRepository(dataStore)
    }

    @Named("WaterMarkPreferences")
    @Provides
    @Singleton
    fun provideWaterMarkRepository(dataStore: DataStore<Preferences>): WaterMarkRepository {
        return WaterMarkRepository(dataStore)
    }

    @Named("WaterMarkPreferences")
    @Provides
    @Singleton
    fun provideMemorySettingRepository(): MemorySettingRepo {
        return MemorySettingRepo()
    }

    @Provides
    fun provideTemplateRepository(dao: TemplateDao?): TemplateRepository {
        return TemplateRepository(dao)
    }
}