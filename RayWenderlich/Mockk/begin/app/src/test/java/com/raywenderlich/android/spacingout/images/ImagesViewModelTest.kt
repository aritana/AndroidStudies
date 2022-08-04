package com.raywenderlich.android.spacingout.images

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.raywenderlich.android.spacingout.CoroutinesTestRule
import com.raywenderlich.android.spacingout.SpacingAnalytics
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class ImagesViewModelTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule: CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `An analytics call is made when are loaded`() {
        val provider = mockk<ImageListProvider>()
        val analytics = mockk<SpacingAnalytics>()

        val vieModel = ImagesViewModel(provider, analytics)
        verify(exactly = 1) { analytics.logEvent("Fetching images") }//atleast atmost
    }
}