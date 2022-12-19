package com.example.background.utility

import android.app.Application
import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.background.KEY_IMAGE_URI
import com.example.background.workers.BlurWorker
import com.example.background.workers.UtilityWorker

class UtilityWorkManager(application: Context) {

    private val workManager = WorkManager.getInstance(application)
    companion object {
        fun createInputData(data: String): Data {
            val builder = Data.Builder()
            data?.let {
                builder.putString(KEY_IMAGE_URI, data)
            }
            return builder.build()
        }
    }

    fun createOneTimeRequestBuilder(data: String){
        val utilityRequest = OneTimeWorkRequestBuilder<UtilityWorker>()
            .setInputData(createInputData(data))
            .build()

        workManager.enqueue(utilityRequest)
    }

}