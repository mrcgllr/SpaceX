package com.android.spacex.ui.list.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.spacex.FilterType
import com.android.spacex.R
import com.android.spacex.databinding.DialogYearsBinding
import com.android.spacex.ui.list.LaunchesListFragment
import com.android.spacex.ui.list.dialog.adapter.YearsAdapter
import com.android.spacex.util.Util.BUN_ITEM_CLEAR
import com.android.spacex.util.Util.BUN_ITEM_YEAR
import com.android.spacex.util.Util.REQ_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogYearsFragment : DialogFragment() {

    private var _binding: DialogYearsBinding? = null
    private val binding get() = _binding!!

    private var _adapterYears: YearsAdapter? = null
    private val adapterYears get() = _adapterYears!!

    private val viewModel: DialogYearsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewRoot = inflater.inflate(R.layout.dialog_years, container, false)
        _binding = DataBindingUtil.bind(viewRoot)
        setUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getStringArrayList(BUN_ITEM_YEAR)?.let {
            viewModel.setYears(it)
        }
        viewModel.yearsList.observe(viewLifecycleOwner, { yearsList ->
            yearsList?.let {
                adapterYears.submitList(it)
            }
        })
    }

    private fun setUI() {
        _adapterYears = YearsAdapter {
            (parentFragment as LaunchesListFragment).setFragmentResult(
                REQ_KEY,
                bundleOf(BUN_ITEM_YEAR to it)
            )
            dialog?.dismiss()
        }
        binding.recyclerYears.apply {
            adapter = adapterYears
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        binding.txtClearSelect.setOnClickListener {
            (parentFragment as LaunchesListFragment).setFragmentResult(
                REQ_KEY,
                bundleOf(BUN_ITEM_CLEAR to FilterType.CLEAR)
            )
            dialog?.dismiss()
        }
    }

    override fun getTheme(): Int = R.style.DialogYears

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapterYears = null
    }

    companion object {
        fun newInstance(yearList: ArrayList<String>) = DialogYearsFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(BUN_ITEM_YEAR, yearList)
            }
        }
    }
}