package com.android.spacex.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.spacex.R
import com.android.spacex.databinding.FragmentLaunchDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentLaunchDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LaunchDetailViewModel by viewModels()

    private val args by navArgs<LaunchDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewRoot = inflater.inflate(R.layout.fragment_launch_detail, container, false)
        _binding = DataBindingUtil.bind(viewRoot)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setLaunchItem(args.launchModel)

        viewModel.launchDetailLD.observe(viewLifecycleOwner, { detailItem ->
            binding.model = detailItem
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}