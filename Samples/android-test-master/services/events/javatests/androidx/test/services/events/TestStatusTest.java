/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.test.services.events;

import static com.google.common.truth.Truth.assertThat;

import android.os.Parcel;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.services.events.TestStatus.Status;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit tests for the parcelable {@link TestStatus}. We write and read from the parcel to test
 * everything is done correctly.
 */
@RunWith(AndroidJUnit4.class)
public class TestStatusTest {

  @Test
  public void testStatusToParcelableTest_ok() {

    TestStatus testStatus = new TestStatus(Status.ABORTED);
    Parcel parcel = Parcel.obtain();
    testStatus.writeToParcel(parcel, 0);

    parcel.setDataPosition(0);

    TestStatus testStatusFromParcel = TestStatus.CREATOR.createFromParcel(parcel);

    assertThat(testStatusFromParcel.status).isEqualTo(testStatus.status);
  }
}
