package com.example.data.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsTest {

    @Test
    fun `given url, when http, then transformed to https format`() {
        val url = "http://example.com"
        val expected = "https://example.com"
        assertEquals(expected, url.toHttps())
    }

    @Test
    fun `given url, when absolute path, then transformed to https format`() {
        val url = "//example.com"
        val expected = "https://example.com"
        assertEquals(expected, url.toHttps())
    }

    @Test
    fun `given url, when other format, then keep same format`() {
        val url = "example.com"
        val expected = "example.com"
        assertEquals(expected, url.toHttps())
    }

}