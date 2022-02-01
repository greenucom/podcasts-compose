package com.greencom.android.podcasts2.clean

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<in P, out R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.success(it)
                }
            }
        } catch (e: Exception) {
            Timber.e("Exception executing UseCase $this with parameters $parameters")
            Timber.e(e)
            Result.failure(e)
        }
    }

    protected abstract fun execute(parameters: P): R

}
