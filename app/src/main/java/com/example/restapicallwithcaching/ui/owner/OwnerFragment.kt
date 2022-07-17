package com.example.restapicallwithcaching.ui.owner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.restapicallwithcaching.databinding.FragmentListBinding
import com.example.restapicallwithcaching.databinding.FragmentOwnerBinding

class OwnerFragment: Fragment() {

    private val args by navArgs<OwnerFragmentArgs>()

    private var _binding: FragmentOwnerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOwnerBinding.inflate(inflater, container, false)
        binding.repo = args.repo
        Log.d("REPO: ", "${args.repo}")
        return binding.root
    }
}