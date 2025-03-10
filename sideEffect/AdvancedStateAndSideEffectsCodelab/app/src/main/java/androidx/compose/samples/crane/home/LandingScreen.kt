/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.samples.crane.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.samples.crane.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SplashWaitTime: Long = 2000
private  const val TAG = "LandingScreen"
@Composable
fun LandingScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val currentOnTimeOut by rememberUpdatedState(newValue = onTimeout)
        val coroutineScope = rememberCoroutineScope()
//        coroutineScope.launch {
//            Log.d(TAG, "LandingScreen: 1")
//            delay(SplashWaitTime)
//            Log.d(TAG, "LandingScreen: 2")
//            currentOnTimeOut()
//        }
        LaunchedEffect(key1 = true, block = {
            Log.d(TAG, "LandingScreen: 1")
            delay(SplashWaitTime)
            Log.d(TAG, "LandingScreen: 2")
            currentOnTimeOut()
        })
        Image(painterResource(id = R.drawable.ic_crane_drawer), contentDescription = null)
        Log.d(TAG, "LandingScreen: 3")
    }
}
