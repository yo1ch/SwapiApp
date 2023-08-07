package com.example.feature.presentation.favoritefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.core.network.ImageLoader
import com.example.feature.R
import com.example.feature.databinding.FragmentFavoriteBinding
import com.example.feature.di.FeatureComponent
import com.example.feature.presentation.ViewModelFactory
import com.example.feature.presentation.mainfragment.MainFragmentViewModel
import javax.inject.Inject


class FavoriteFragment : Fragment() {


    @Inject
    lateinit var imageLoader : ImageLoader
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: FavoriteFragmentViewModel

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    override fun onResume() {
        super.onResume()
        val sample = resources.getStringArray(R.array.search_categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, sample)
        binding.autoCompleteTextview.setText(sample[2])
        binding.autoCompleteTextview.setAdapter(arrayAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureComponent.init(requireContext()).injectFavoriteFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavoriteFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}