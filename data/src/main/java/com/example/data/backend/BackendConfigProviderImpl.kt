package com.example.data.backend

import com.example.data.BuildConfig
import javax.inject.Inject

class BackendConfigProviderImpl @Inject constructor() : BackendConfigProvider {

    override fun baseUrl(): String = BuildConfig.BASE_URL

    override fun apiKey(): String  = BuildConfig.API_KEY

}