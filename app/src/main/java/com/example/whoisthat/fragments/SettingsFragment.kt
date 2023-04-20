package com.example.whoisthat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.whoisthat.R
import com.example.whoisthat.contracts.CustomAction
import com.example.whoisthat.contracts.HasCustomAction
import com.example.whoisthat.contracts.HasCustomTitle
import com.example.whoisthat.contracts.navigator
import com.example.whoisthat.databinding.FragmentSettingsBinding
import com.example.whoisthat.model.Options


class SettingsFragment : Fragment(),HasCustomTitle,HasCustomAction{

    private var options: Options = Options.DEFAULT
    lateinit var binding: FragmentSettingsBinding
    private var charNums: Array<Int> = arrayOf(15, 24, 33, 42,51)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        options =
            savedInstanceState?.getParcelable<Options>(KEY_OPTIONS) ?: arguments?.getParcelable(
                ARG_OPTIONS
            )
                    ?: throw IllegalArgumentException("You need to specify options to launch this fragment")
    }

//    private fun updateUi() {
//        val currentIndex = boxCountItems.indexOfFirst { it.count == options.boxCount }
//        binding.spinner.setSelection(currentIndex)
//    }

    private fun cancelOnPressed() {
        navigator().goBack()
    }

    private fun confirmOnPressed() {
        options.charNum=charNums[binding.spinner.selectedItemPosition]
        options.charIndexesChosen1.clear()
        options.charIndexesChosen2.clear()
        options.charIndexes.clear()
        navigator().publishResult(options)
        navigator().goBack()

    }

    private fun setUpSpinner() {
        val adapter: ArrayAdapter<Int> = ArrayAdapter(
            this.requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            charNums
        )
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.setSelection((options.charNum/3-5)/3)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        setUpSpinner()
        binding.confirmButton.setOnClickListener { confirmOnPressed() }
        binding.cancelButton.setOnClickListener { cancelOnPressed() }
        return binding.root
    }

    companion object {
        @JvmStatic
        private val ARG_OPTIONS = "ARG_OPTIONS"

        @JvmStatic
        private val KEY_OPTIONS = "KEY_OPTIONS"

        @JvmStatic
        fun newInstance(options: Options): SettingsFragment {
            val args = Bundle()
            args.putParcelable(ARG_OPTIONS, options)
            val fragment = SettingsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getTitleRes(): Int = R.string.settings_title

    override fun getCustomAction(): CustomAction {
        return CustomAction(
            iconRes = R.drawable.ic_done,
            textRes = R.string.confirm_button,
            onCustomAction = Runnable {
                confirmOnPressed()
            }
        )
    }
}