package wingeddev.example.examplezenquotesapiapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wingeddev.example.examplezenquotesapiapplication.data.ZenQuotesApi
import wingeddev.example.examplezenquotesapiapplication.data.repository.ZenQuotesRepositoryImpl
import wingeddev.example.examplezenquotesapiapplication.domain.repository.ZenQuotesRepository
import wingeddev.example.examplezenquotesapiapplication.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideZenQuotesApi(): ZenQuotesApi = Retrofit.Builder()
        .baseUrl(Constants().BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ZenQuotesApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: ZenQuotesApi): ZenQuotesRepository {
        return ZenQuotesRepositoryImpl(api)
    }
}