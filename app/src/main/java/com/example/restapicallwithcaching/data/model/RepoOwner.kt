package com.example.restapicallwithcaching.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoOwner(
    val login: String?,
    val avatar_url: String?
): Parcelable