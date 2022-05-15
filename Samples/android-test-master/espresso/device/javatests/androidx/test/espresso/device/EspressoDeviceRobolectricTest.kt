/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.test.espresso.device

import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.action.ScreenOrientation
import androidx.test.espresso.device.action.setScreenOrientation
import java.lang.UnsupportedOperationException
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EspressoDeviceRobolectricTest {
  @Test
  fun onDevice_setScreenOrientationThrowsUnsupportedOperationException() {
    assertThrows(UnsupportedOperationException::class.java) {
      onDevice().perform(setScreenOrientation(ScreenOrientation.LANDSCAPE))
    }
  }

  @Test
  fun onDevice_setFlatModeThrowsUnsupportedOperationException() {
    assertThrows(UnsupportedOperationException::class.java) { onDevice().setFlatMode() }
  }
}
