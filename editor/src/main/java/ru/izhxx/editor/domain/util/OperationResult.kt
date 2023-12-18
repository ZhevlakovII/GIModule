package ru.izhxx.editor.domain.util

import androidx.annotation.StringRes

internal sealed class OperationResult<T> {

    class Success<T>(val resultData: T) : OperationResult<T>()

    class Error<T>(
        @StringRes val messageStringResId: Int,
        val message: String? = null,
        val cause: Throwable? = null
    ) : OperationResult<T>()

    fun collect(
        onSuccess: (data: T) -> Unit,
        onError: (error: Error<T>) -> Unit
    ) {
        when (this) {
            is Success -> onSuccess.invoke(this.resultData)
            is Error -> onError.invoke(this)
        }
    }
}