package com.greencom.android.podcasts2.clean

abstract class SimpleUseCase<in P, out R> {
    abstract operator fun invoke(params: P): R
}
