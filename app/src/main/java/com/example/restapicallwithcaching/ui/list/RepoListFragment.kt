package com.example.restapicallwithcaching.ui.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restapicallwithcaching.R
import com.example.restapicallwithcaching.data.shared_pref.AppSharedPref
import com.example.restapicallwithcaching.databinding.FragmentListBinding
import com.example.restapicallwithcaching.utils.Resource
import com.example.restapicallwithcaching.utils.const.SharedPrefConst
import kotlinx.coroutines.launch


class RepoListFragment : Fragment() , MenuProvider {

    private lateinit var binding: FragmentListBinding
    private val viewModel: RepoListViewModel by viewModels()
    private lateinit var adapter: RepoListAdapter
    private var sort: String? = null

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
        getSortData()
        setupRecyclerView()
        setupObservers()
    }

    private fun getSortData() {
        sort = AppSharedPref.getStringData(SharedPrefConst.CURRENT_SORT)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect { resource ->
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        if (!resource.data.isNullOrEmpty()) adapter.setItems(ArrayList(resource.data))
                    }
                    Resource.Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun fetchData() {
        getSortData()
        viewModel.refresh()
    }

    private fun setupRecyclerView() {
        adapter = RepoListAdapter()
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.sort_menu, menu)
        if(sort == SharedPrefConst.SORT_STARS){
            menu.findItem(R.id.menu_stars).isChecked = true
        }else{
            menu.findItem(R.id.menu_updates).isChecked = true
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menu_title -> {
                return false
            }
            R.id.menu_stars -> {
                menuItem.isChecked = !menuItem.isChecked
                if(menuItem.isChecked) saveSort(SharedPrefConst.SORT_STARS)
            }
            R.id.menu_updates -> {
                menuItem.isChecked = !menuItem.isChecked
                if(menuItem.isChecked) saveSort(SharedPrefConst.SORT_UPDATES)
            }
        }
        fetchData()
        return true
    }

    private fun saveSort(sort: String){
        AppSharedPref.saveStringData(SharedPrefConst.CURRENT_SORT, sort)
    }


}