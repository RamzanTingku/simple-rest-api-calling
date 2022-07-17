package com.example.restapicallwithcaching.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoOwner(
    val login: String?,
    val avatarUrl: String?
): Parcelable