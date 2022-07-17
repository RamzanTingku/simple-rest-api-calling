package com.example.restapicallwithcaching.ui.list

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapicallwithcaching.R
import com.example.restapicallwithcaching.data.shared_pref.AppSharedPref
import com.example.restapicallwithcaching.databinding.FragmentListBinding
import com.example.restapicallwithcaching.utils.Resource
import com.example.restapicallwithcaching.utils.const.SharedPrefConst


class RepoListFragment : Fragment() , MenuProvider {

    private lateinit var binding: FragmentListBinding
    private val viewModel: RepoListViewModel by viewModels()
    private lateinit var adapter: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        updateView()
        viewModel.repos.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    private fun updateView() {
        val sort = AppSharedPref.getStringData(SharedPrefConst.CURRENT_SORT)
        sort?.let { viewModel.getRepos(it) }
    }

    private fun setupRecyclerView() {
        adapter = RepoListAdapter()
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.sort_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menu_stars -> {
                menuItem.isChecked = !menuItem.isChecked
                if(menuItem.isChecked) saveSort(SharedPrefConst.SORT_STARS)
            }
            R.id.menu_updates -> {
                menuItem.isChecked = !menuItem.isChecked
                if(menuItem.isChecked) saveSort(SharedPrefConst.SORT_UPDATES)
            }
        }
        updateView()
        return true
    }

    private fun saveSort(sort: String){
        AppSharedPref.saveStringData(SharedPrefConst.CURRENT_SORT, sort)
    }


}