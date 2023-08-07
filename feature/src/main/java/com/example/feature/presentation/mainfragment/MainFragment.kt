package com.example.feature.presentation.mainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.core.network.ImageLoader
import com.example.feature.R
import com.example.feature.databinding.FragmentFavoriteBinding
import com.example.feature.databinding.FragmentMainBinding
import com.example.feature.di.FeatureComponent
import com.example.feature.presentation.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var imageLoader : ImageLoader

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainFragmentViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onResume() {
        super.onResume()
        val sample = resources.getStringArray(R.array.search_categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, sample)
        binding.autoCompleteTextview.setText(sample[0])
        binding.autoCompleteTextview.setAdapter(arrayAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureComponent.init(requireContext()).injectMainFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}