package com.example.background.workers

import androidx.work.Data

open interface UtilityWorkerInterface {

 fun utilityDoWork(inputData: Data) :androidx.work.ListenableWorker.Result

}