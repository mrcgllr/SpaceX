package com.android.spacex.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.spacex.FilterType
import com.android.spacex.R
import com.android.spacex.databinding.FragmentLaunchesListBinding
import com.android.spacex.ui.adapter.LaunchListAdapter
import com.android.spacex.ui.list.dialog.DialogYearsFragment
import com.android.spacex.util.Util.BUN_ITEM_CLEAR
import com.android.spacex.util.Util.BUN_ITEM_YEAR
import com.android.spacex.util.Util.REQ_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchesListFragment : Fragment() {

    private var _binding: FragmentLaunchesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LaunchesListViewModel by viewModels()

    private var _adapterLaunch: LaunchListAdapter? = null
    private val adapterLaunch get() = _adapterLaunch!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewRoot = inflater.inflate(R.layout.fragment_launches_list, container, false)
        _binding = DataBindingUtil.bind(viewRoot)
        setUI()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQ_KEY) { _, resultBundle ->
            val year = resultBundle.getString(BUN_ITEM_YEAR)
            year?.let {
                viewModel.setSelectedYear(it)
            }
            val clearType = resultBundle.getParcelable<FilterType>(BUN_ITEM_CLEAR)
            clearType?.let {
                if (it == FilterType.CLEAR) {
                    viewModel.selectedClear()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.launchesListLD.observe(viewLifecycleOwner, { list ->
            list?.let {
                adapterLaunch.submitList(it)
            }
        })

        viewModel.launchesFilterList.observe(viewLifecycleOwner, { list ->
            list?.let {
                adapterLaunch.submitList(it)
            }
        })

        viewModel.getLaunchesList()
    }

    private fun setUI() {
        _adapterLaunch = LaunchListAdapter { launchItem ->
            findNavController().navigate(
                LaunchesListFragmentDirections.actionLaunchesListFragmentToLaunchDetailFragment(
                    launchItem
                )
            )
        }
        binding.recyclerLaunch.apply {
            adapter = adapterLaunch
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        binding.imgFilter.setOnClickListener {
            val dialogYears =
                DialogYearsFragment.newInstance(viewModel.launchesYear as ArrayList<String>)
            dialogYears.show(childFragmentManager, null)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getLaunchesList()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _adapterLaunch = null
        _binding = null
    }
}