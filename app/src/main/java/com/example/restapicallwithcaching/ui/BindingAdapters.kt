package com.example.restapicallwithcaching.ui

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.restapicallwithcaching.data.model.RepoItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {

    companion object{
        @BindingAdapter("android:navigateToOwnerScreen")
        @JvmStatic
        fun navigateToOwnerScreen(view: ConstraintLayout, repo: RepoItem){
            view.setOnClickListener {
                Log.d("REPO: ", "$repo")
            }
        }
    }

}