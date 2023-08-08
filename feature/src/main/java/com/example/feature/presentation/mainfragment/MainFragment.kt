package com.example.feature.presentation.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.network.ImageLoader
import com.example.core.view.VerticalDecorator
import com.example.feature.R
import com.example.feature.databinding.FragmentMainBinding
import com.example.feature.di.FeatureComponent
import com.example.feature.presentation.ViewModelFactory
import com.example.feature.presentation.rvadapter.RvAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainFragmentViewModel

    private lateinit var recyclerViewAdapter: RvAdapter

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        setupDropdownMenu()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureComponent.init(requireContext()).injectMainFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
        setupRecyclerView()
        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.resultList.onEach {
            recyclerViewAdapter.submitList(it)
        }.launchIn(lifecycleScope)
        viewModel.searchCategory.onEach {
            when(it){
                MainFragmentViewModel.SearchCategory.Characters ->{
                    setSearchHint("Search characters...", it.title)
                }
                MainFragmentViewModel.SearchCategory.Planets ->{
                    setSearchHint("Search planets...", it.title)
                }
                MainFragmentViewModel.SearchCategory.Starships ->{
                    setSearchHint("Search starships...", it.title)
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
    }
    private fun setupDropdownMenu(){
        val categories = MainFragmentViewModel.SearchCategory.values().map { it.title }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, categories)
        binding.autoCompleteTextview.setText(viewModel.getCurrentCategory().title)
        binding.autoCompleteTextview.setAdapter(arrayAdapter)
        binding.autoCompleteTextview.setOnItemClickListener { parent, view, position, id ->
            when(parent.getItemAtPosition(position).toString()){
                MainFragmentViewModel.SearchCategory.Characters.title -> {
                    viewModel.setSearchCategory(MainFragmentViewModel.SearchCategory.Characters)
                }
                MainFragmentViewModel.SearchCategory.Starships.title -> {
                    viewModel.setSearchCategory(MainFragmentViewModel.SearchCategory.Starships)
                }
                MainFragmentViewModel.SearchCategory.Planets.title -> {
                    viewModel.setSearchCategory(MainFragmentViewModel.SearchCategory.Planets)
                }
            }

        }
    }
    private fun setSearchHint(searchHint: String, searchCategory: String){
        binding.search.hint = searchHint
        binding.searchHint.hint = searchHint
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}