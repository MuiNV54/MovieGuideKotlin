package com.example.nguyenvanmui.myapplication.util

import io.reactivex.disposables.Disposable

/**
 * Created by nguyen.van.mui on 02/02/2018.
 */
object RxUtils {
    fun unsubscribe(subscription: Disposable?) {
        if (subscription != null && !subscription.isDisposed) {
            subscription.dispose()
        }
    }
}