package com.greencom.android.podcasts2.base.clean

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<in P, out R>(private val dispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(params: P): Result<R> {
        return try {
            withContext(dispatcher) {
                execute(params).let {
                    Result.success(it)
                }
            }
        } catch (e: Exception) {
            Timber.e("Exception executing UseCase $this with parameters $params")
            Timber.e(e)
            Result.failure(e)
        }
    }

    protected abstract suspend fun execute(params: P): R

}
