package com.raywenderlich.android.spacingout.images

import com.raywenderlich.android.spacingout.models.ApodImage
import com.raywenderlich.android.spacingout.network.SpacingOutApi
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.mock.Calls

class ImageListProviderTest() {
    @MockK
    private lateinit var api: SpacingOutApi

    @Before
    fun setup(){
        MockKAnnotations.init(this)
    }
    @Test
    fun `a list of 30 images is returned`() = runBlocking {


//https://square.github.io/retrofit/2.x/retrofit-mock/index.html?retrofit2/mock/Calls.html
        every { api.getImage(any()) } answers {
            Calls.response(
                ApodImage(
                    "08-11-2019",
                    "Test",
                    "www.testurl.com",
                    "test",
                    "www.testurl.com",
                    "video"
                )
            )
        }

        val provider = ImageListProvider(api)
        val images = provider.buildImageList()

        assertEquals(30, images.size)
    }
}