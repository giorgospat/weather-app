package com.example.data.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ResponseUtilsTest {

    @Test
    fun `given retrofit response, when success, then returns success result`() = runTest {
        val mockResponse: Response<String> = Response.success(200, "")

        val result = wrapRequest { mockResponse }

        assertTrue(result.isSuccess)
    }

    @Test
    fun `given retrofit response, when success and null body, then returns error result`() =
        runTest {
            val mockResponse: Response<String> = Response.success(null)

            val result = wrapRequest { mockResponse }

            assertTrue(result.isFailure)
        }

    @Test
    fun `given retrofit response, when error, then returns error result`() = runTest {
        val mockResponse: Response<String> = Response.error(400, byteArrayOf().toResponseBody())

        val result = wrapRequest { mockResponse }

        assertTrue(result.isFailure)
    }

}