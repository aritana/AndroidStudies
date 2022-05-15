/*
 * Copyright (C) 2015 The Android Open Source Project
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

package androidx.test.espresso.intent;

import android.content.Intent;

/** A {@link ResolvedIntent} that is also a {@link VerifiableIntent}. */
final class VerifiableIntentImpl implements VerifiableIntent {
  private final ResolvedIntent resolvedIntent;

  private boolean hasBeenVerified = false;

  public VerifiableIntentImpl(ResolvedIntent resolvedIntent) {
    this.resolvedIntent = resolvedIntent;
  }

  @Override
  public boolean canBeHandledBy(String appPackage) {
    return resolvedIntent.canBeHandledBy(appPackage);
  }

  @Override
  public Intent getIntent() {
    return resolvedIntent.getIntent();
  }

  @Override
  public boolean hasBeenVerified() {
    return hasBeenVerified;
  }

  @Override
  public void markAsVerified() {
    hasBeenVerified = true;
  }

  @Override
  public String toString() {
    return resolvedIntent.toString();
  }
}
