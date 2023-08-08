package com.example.feature.presentation.favoritefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.network.ImageLoader
import com.example.core.view.VerticalDecorator
import com.example.feature.R
import com.example.feature.databinding.FragmentFavoriteBinding
import com.example.feature.di.FeatureComponent
import com.example.feature.presentation.ViewModelFactory
import com.example.feature.presentation.mainfragment.MainFragmentViewModel
import com.example.feature.presentation.rvadapter.RvAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class FavoriteFragment : Fragment() {


    @Inject
    lateinit var imageLoader : ImageLoader
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: FavoriteFragmentViewModel
    private lateinit var recyclerViewAdapter: RvAdapter

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        setupDropdownMenu()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureComponent.init(requireContext()).injectFavoriteFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteFragmentViewModel::class.java]
        setupDropdownMenu()
        setupRecyclerView()
        observeViewModel()

    }

    private fun setupDropdownMenu(){
        val categories = FavoriteFragmentViewModel.SearchCategory.values().map { it.title }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, categories)
        binding.autoCompleteTextview.setText(viewModel.getCurrentCategory().title)
        binding.autoCompleteTextview.setAdapter(arrayAdapter)
        binding.autoCompleteTextview.setOnItemClickListener { parent, _, position, _ ->
            when(parent.getItemAtPosition(position).toString()){
                FavoriteFragmentViewModel.SearchCategory.Characters.title -> {
                    viewModel.setSearchCategory(FavoriteFragmentViewModel.SearchCategory.Characters)
                }
                FavoriteFragmentViewModel.SearchCategory.Starships.title -> {
                    viewModel.setSearchCategory(FavoriteFragmentViewModel.SearchCategory.Starships)
                }
                FavoriteFragmentViewModel.SearchCategory.Planets.title -> {
                    viewModel.setSearchCategory(FavoriteFragmentViewModel.SearchCategory.Planets)
                }
            }

        }
    }

    private fun observeViewModel() {
        viewModel.resultList.onEach {
            recyclerViewAdapter.submitList(it)
        }.launchIn(lifecycleScope)
        viewModel.searchCategory.onEach {
            when(it){
                FavoriteFragmentViewModel.SearchCategory.Characters -> {
                    viewModel.getData(it)
                }
                FavoriteFragmentViewModel.SearchCategory.Starships-> {
                    viewModel.getData(it)
                }
                FavoriteFragmentViewModel.SearchCategory.Planets -> {
                    viewModel.getData(it)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setupRecyclerView() {
        with(binding.recyclerview) {
            recyclerViewAdapter = RvAdapter()
            adapter = recyclerViewAdapter

            addItemDecoration(VerticalDecorator(8, 8, 76, 16, 16))
        }
        recyclerViewAdapter.onClickListener = { model, _ ->
            viewModel.removeFavorite(model)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}