/*
 * Copyright (C) 2018 The Android Open Source Project
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

package com.example.background

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.background.utility.UtilityWorkManager
import com.example.background.workers.BlurWorker
import com.example.background.workers.UtilityWorker
import com.example.background.workers.UtilityWorkerInterface
import com.example.background.workers.blurBitmap

private const val TAG = "UtilityWorker"
class BlurViewModel(application: Application) : ViewModel() {



    internal var imageUri: Uri? = null
    internal var outputUri: Uri? = null
   private val workManager = WorkManager.getInstance(application)
    val utilityWorkManager:UtilityWorkManager =UtilityWorkManager(application.applicationContext)

    init {
        imageUri = getImageUri(application.applicationContext)


    }
    /**
     * Create the WorkRequest to apply the blur and save the resulting image
     * @param blurLevel The amount to blur the image
     */
    internal fun applyBlur(blurLevel: Int) {
       // workManager.enqueue(OneTimeWorkRequest.from(BlurWorker::class.java))

        var intr : UtilityWorkerInterface = object : UtilityWorkerInterface {
            override fun utilityDoWork(inputData: Data): ListenableWorker.Result {
                // here user need to add their work
                    try{
                        Log.d(TAG,"User should add code")


                    }catch (ex:java.lang.Exception){

                        return ListenableWorker.Result.failure()
                    }

                return ListenableWorker.Result.success()


            };
        }

        UtilityWorker.initWorkInterface(intr)
        utilityWorkManager.createOneTimeRequestBuilder(imageUri.toString())

        /*val blurRequest = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(createInputDataForUri())
            .build()

        workManager.enqueue(blurRequest)*/
    }

    public fun getOneTimeWorkRequest(){


    }
    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

    private fun uriOrNull(uriString: String?): Uri? {
        return if (!uriString.isNullOrEmpty()) {
            Uri.parse(uriString)
        } else {
            null
        }
    }

    private fun getImageUri(context: Context): Uri {
        val resources = context.resources

        val imageUri = Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.android_cupcake))
            .appendPath(resources.getResourceTypeName(R.drawable.android_cupcake))
            .appendPath(resources.getResourceEntryName(R.drawable.android_cupcake))
            .build()

        return imageUri
    }

    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = uriOrNull(outputImageUri)
    }

    class BlurViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(BlurViewModel::class.java)) {
                BlurViewModel(application) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }


}
