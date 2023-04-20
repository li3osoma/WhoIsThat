package com.example.whoisthat.contracts

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.example.whoisthat.model.Options

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator():Navigator{
    return requireActivity() as Navigator
}

interface Navigator {
    fun <T:Parcelable> publishResult(result : T)
    fun <T: Parcelable> listenResult(clazz: Class<T>, owner: LifecycleOwner, listener:ResultListener<T>)

    fun showPLayer1Screen(options: Options)
    fun showPLayer2Screen(options: Options)
    fun showAboutScreen()
    fun showSettingsScreen(options: Options)

    fun goBack()
    fun goToMenu()
}