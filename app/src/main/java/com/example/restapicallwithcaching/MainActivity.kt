package com.example.restapicallwithcaching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.restapicallwithcaching.data.repository.CharacterRepository
import com.example.restapicallwithcaching.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CharacterRepository().getRepos("Android", "stars", 10).observe(
            this, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (!it.data.isNullOrEmpty()) {
                            it.data.map { data ->
                                Log.d("====REPO====", "${data.name}")
                            }
                        }
                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                    Resource.Status.LOADING ->
                        Log.d("====REPO====", "LOADING....")
                }
            }
        )

    }
}