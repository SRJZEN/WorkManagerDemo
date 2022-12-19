package com.example.background.workers

import android.app.Application
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.*
import com.example.background.KEY_IMAGE_URI



private const val TAG = "UtilityWorker"



  lateinit var workInterface: UtilityWorkerInterface

class UtilityWorker (ctx: Context, params: WorkerParameters): Worker(ctx,params) {

    init {

    }

    companion object{
        fun initWorkInterface( workerInterface: UtilityWorkerInterface){
            workInterface = workerInterface
        }
    }

    override fun doWork(): Result {
        val appContext = applicationContext


        /*   return try {

            Log.d(TAG,"INSIDE UTILITY WORKER")
            val resourceUri = inputData.getString(KEY_IMAGE_URI)
            if (TextUtils.isEmpty(resourceUri)) {
                Log.e(TAG, "Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }

            val resolver = appContext.contentResolver

            val picture = BitmapFactory.decodeStream(
                resolver.openInputStream(Uri.parse(resourceUri)))

            val output = blurBitmap(picture, appContext)

            // Write bitmap to a temp file
            val outputUri = writeBitmapToFile(appContext, output)

            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())

            makeStatusNotification("Output is $outputUri", appContext)
            Result.success(outputData)
        }catch (throwable:Throwable){
            Log.e("WORK_MANAGER_DEMO", "Error applying blur")
            Result.failure()
        }

    }*/


     return workInterface?.utilityDoWork(inputData);

    }
}


