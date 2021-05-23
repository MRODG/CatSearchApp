package di

import api.ApiService
import api.NetworkManager
import client.SearchClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { NetworkManager(get()) }
    single { SearchClient(get()) }

    single {
        Retrofit
            .Builder()
            .baseUrl(NetworkManager.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}