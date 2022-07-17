package com.example.restapicallwithcaching.ui

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.restapicallwithcaching.R
import com.example.restapicallwithcaching.data.model.RepoItem


class BindingAdapters {

    companion object{
        @BindingAdapter("android:navigateToOwnerScreen")
        @JvmStatic
        fun navigateToOwnerScreen(view: ConstraintLayout, repo: RepoItem){
            view.setOnClickListener {
                val navController = Navigation.findNavController(view)
                val bundle = Bundle()
                bundle.putParcelable("repo", repo)
                navController.navigate(R.id.action_listFragment_to_ownerFragment2, bundle)
            }
        }
    }

}