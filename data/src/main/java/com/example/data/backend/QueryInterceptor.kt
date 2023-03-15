package com.example.data.backend

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class QueryInterceptor(private val configProvider: BackendConfigProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(NetworkConstants.keyParam, configProvider.apiKey())
            .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}