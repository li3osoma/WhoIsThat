package com.example.whoisthat.model
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.example.whoisthat.model.Character

@Parcelize
data class Options(
    var charNum: Int,
    var charIndexes: MutableList<Int>,
    var charIndexesChosen1: MutableList<Int>,
    var charIndexesChosen2: MutableList<Int>
) : Parcelable {

    companion object {
        @JvmStatic
        val DEFAULT = Options(charNum = 15, charIndexes = ArrayList<Int>(), charIndexesChosen1= ArrayList<Int>(),charIndexesChosen2= ArrayList<Int>())
    }
}