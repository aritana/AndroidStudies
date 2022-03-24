package com.raywenderlich.android.spacingout.images


import com.raywenderlich.android.spacingout.models.ApodImage
import com.raywenderlich.android.spacingout.network.SpacingOutApi
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.mock.Calls

class ImageListProviderTest {

    //Coroutines uses the runblocking to assure that the coroutines will finish
    //by the time the unit test finishes
    @Test
    fun `a list of 30 images is returned`() = runBlocking {

        val api = mockk<SpacingOutApi>()
        every { api.getImage(any()) } returns Calls.response(

            ApodImage(
                date = "08-11-2019",
                explanation = "Test",
                hdurl = "www.testurl.com",
                title = "test",
                url = "www.testeurl.com",
                media_type = "video"

            )
        )

        val provider = ImageListProvider(api)
        val images = provider.buildImageList()
        assertEquals(30,images.size)

    }
}