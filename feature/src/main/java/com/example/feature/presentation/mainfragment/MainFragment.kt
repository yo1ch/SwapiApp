package com.example.feature.presentation.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
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
        setupSearch()
    }


    private fun observeViewModel() {
        viewModel.resultList.onEach {
            recyclerViewAdapter.submitList(it)
        }.launchIn(lifecycleScope)
        viewModel.searchCategory.onEach {
            when(it){
                MainFragmentViewModel.SearchCategory.Characters ->{
                    setSearchHint("Search characters...")
                }
                MainFragmentViewModel.SearchCategory.Planets ->{
                    setSearchHint("Search planets...")
                }
                MainFragmentViewModel.SearchCategory.Starships ->{
                    setSearchHint("Search starships...")
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
        recyclerViewAdapter.onClickListener = { dataModel, favoriteState ->
            viewModel.toggleFavorite(dataModel,favoriteState)
        }
    }
    private fun setupDropdownMenu(){
        val categories = MainFragmentViewModel.SearchCategory.values().map { it.title }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, categories)
        binding.autoCompleteTextview.setText(viewModel.getCurrentCategory().title)
        binding.autoCompleteTextview.setAdapter(arrayAdapter)
        binding.autoCompleteTextview.setOnItemClickListener { parent, _, position, _ ->
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
    private fun setSearchHint(searchHint: String){
        binding.searchHint.text?.clear()
        binding.searchHint.hint = searchHint
    }

    private fun setupSearch() {
        binding.search.editText!!.getQueryTextChangeStateFlow()
            .debounce(500)
            .filter { it.isNotEmpty() && it.length > 2 }
            .distinctUntilChanged()
            .flowOn(Dispatchers.Default)
            .onEach { query ->
                viewModel.getData(query, viewModel.getCurrentCategory())
            }
            .launchIn(lifecycleScope)
    }

    private fun EditText.getQueryTextChangeStateFlow(): StateFlow<String> {
        val query = MutableStateFlow("")
        addTextChangedListener { query.value = it.toString() }
        return query
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}