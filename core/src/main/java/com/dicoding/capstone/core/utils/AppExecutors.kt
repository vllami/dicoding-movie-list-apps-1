package com.dicoding.capstone.core.utils

import android.os.Handler
import android.os.Looper.getMainLooper
import androidx.annotation.VisibleForTesting
import com.dicoding.capstone.core.Constant.THREAD_COUNT
import java.util.concurrent.Executor
import java.util.concurrent.Executors.newFixedThreadPool
import java.util.concurrent.Executors.newSingleThreadExecutor

class AppExecutors @VisibleForTesting constructor(private val diskIO: Executor, private val networkIO: Executor, private val mainThread: Executor) {

    constructor() : this(
        newSingleThreadExecutor(),
        newFixedThreadPool(THREAD_COUNT),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

}