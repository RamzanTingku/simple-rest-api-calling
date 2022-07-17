package com.example.restapicallwithcaching.ui.owner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.restapicallwithcaching.R
import com.example.restapicallwithcaching.data.model.RepoItem
import com.example.restapicallwithcaching.databinding.FragmentListBinding
import com.example.restapicallwithcaching.databinding.FragmentOwnerBinding
import com.example.restapicallwithcaching.utils.DateTimeFormatter

class OwnerFragment: Fragment() {

    private val args by navArgs<OwnerFragmentArgs>()

    private var _binding: FragmentOwnerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOwnerBinding.inflate(inflater, container, false)
        setViewData(args.repo)
        return binding.root
    }

    private fun setViewData(repo: RepoItem) {
        binding.tvName.text = repo.owner?.login
        binding.tvDesc.text = repo.description
        binding.tvDate.text = getString(R.string.updated_at)+DateTimeFormatter.getFormattedDate(repo.updated_at, "yyyy-MM-dd'T'HH:mm:ss", "MM-dd-yy HH:mm:ss")
        Glide.with(binding.root)
            .load(repo.owner?.avatar_url)
            .placeholder(R.drawable.user)
            .transform(CircleCrop())
            .into(binding.ivOwner)
    }
}