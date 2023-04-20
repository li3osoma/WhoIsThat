package com.example.whoisthat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.whoisthat.contracts.navigator
import com.example.whoisthat.databinding.FragmentMainMenuBinding
import com.example.whoisthat.model.Options

class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding
    private lateinit var options:Options

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        options=savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
    }

    private fun playOnPressed(){
        navigator().showPLayer1Screen(options)
    }

    private fun exitOnPressed(){
        navigator().goBack()
    }

    private fun settingsOnPressed(){
        navigator().showSettingsScreen(options)
    }

    private fun aboutOnPressed(){
        navigator().showAboutScreen()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentMainMenuBinding.inflate(inflater,container,false)

        navigator().listenResult(Options::class.java,viewLifecycleOwner){
            this.options=it
        }
        binding.aboutButton.setOnClickListener{ aboutOnPressed()}
        binding.settingsButton.setOnClickListener{ settingsOnPressed()}
        binding.exitButton.setOnClickListener{ exitOnPressed()}
        binding.playButton.setOnClickListener{ playOnPressed()}

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    companion object {
        @JvmStatic private val OPTIONS_PARAM = "OPTIONS_PARAM"
        @JvmStatic private val KEY_OPTIONS = "KEY_OPTIONS"
    }
}