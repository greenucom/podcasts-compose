package com.greencom.android.podcasts2.ui.common

import androidx.lifecycle.ViewModel
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    init {
        Timber.i("${this.javaClass.simpleName} created")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("${this.javaClass.simpleName} cleared")
    }

}
