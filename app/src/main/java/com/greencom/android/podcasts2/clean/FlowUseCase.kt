package com.greencom.android.podcasts2.clean

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

abstract class FlowUseCase<in P, out R>(private val dispatcher: CoroutineDispatcher) {

    operator fun invoke(params: P): Flow<Result<R>> = execute(params)
        .catch { e ->
            Timber.e("Exception executing UseCase $this with parameters $params")
            Timber.e(e)
            emit(Result.failure(e))
        }
        .flowOn(dispatcher)

    protected abstract fun execute(parameters: P): Flow<Result<R>>

}
