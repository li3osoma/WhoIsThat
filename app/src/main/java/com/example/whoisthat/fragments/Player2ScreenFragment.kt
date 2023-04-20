package com.example.whoisthat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.whoisthat.App
import com.example.whoisthat.service.CharacterAdapter
import com.example.whoisthat.R
import com.example.whoisthat.contracts.CustomAction
import com.example.whoisthat.contracts.HasCustomAction
import com.example.whoisthat.contracts.HasCustomTitle
import com.example.whoisthat.contracts.navigator
import com.example.whoisthat.databinding.FragmentPlayer2ScreenBinding
import com.example.whoisthat.model.Options
import com.example.whoisthat.service.CharacterActionListener
import com.example.whoisthat.service.CharacterService

private const val OPTIONS_PARAM = "OPTIONS_PARAM"

class Player2ScreenFragment : Fragment(),HasCustomTitle,CharacterActionListener,HasCustomAction{

    private var options: Options=Options.DEFAULT

    private lateinit var adapter1: CharacterAdapter
    private lateinit var binding: FragmentPlayer2ScreenBinding
    private val characterService:CharacterService
        get() = (requireActivity().applicationContext as App).characterService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            options= it.getParcelable(OPTIONS_PARAM)!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPlayer2ScreenBinding.inflate(inflater,container,false)

        navigator().listenResult(Options::class.java,viewLifecycleOwner){
            this.options=it
        }

        setUpRecyclerView()

        binding.player2Button.isClickable=false
        binding.player2Button.setBackgroundColor(resources.getColor(R.color.white))
        binding.player1Button.setBackgroundColor(resources.getColor(R.color.purple_200))
        binding.player1Button.setOnClickListener{navigator().showPLayer1Screen(options)}

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(ARG_OPTIONS, options)
    }

    companion object {

        @JvmStatic
        private val ARG_OPTIONS = "ARG_OPTIONS"

        @JvmStatic
        private val KEY_OPTIONS = "KEY_OPTIONS"

        @JvmStatic
        fun newInstance(options:Options):Player2ScreenFragment{
            val arguments = Bundle()
            arguments.putParcelable(OPTIONS_PARAM, options)
            val fragment = Player2ScreenFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    private fun setUpRecyclerView(){
        adapter1=CharacterAdapter(this)
        adapter1.characters=CharacterService(options).getCharacters2()
        binding.recyclerView.adapter=adapter1

        var layoutManager= GridLayoutManager(context,3)
        binding.recyclerView.layoutManager=layoutManager
    }

    private fun characterOnPressed(){

    }

//    private val characterListener:CharacterListener={
//        adapter1.characters=it
//    }

    override fun getTitleRes(): Int=R.string.player2_screen_text

    override fun changeChosen(view: View,imageView: ImageView) {
        val character=view.tag as com.example.whoisthat.model.Character
        if(!character.isChosen){
            character.isChosen=true
            character.resource=R.color.red
            imageView.setImageResource(character.resource)
            options.charIndexesChosen2.add(options.charIndexesChosen2.size,character.ind)
        }
        else{
            character.isChosen=false
            character.resource=character.passiveResource
            imageView.setImageResource(character.resource)
            options.charIndexesChosen2.remove(character.ind)
        }
        adapter1.notifyDataSetChanged()
    }

    override fun getCustomAction(): CustomAction {
        return CustomAction(
            iconRes = R.drawable.ic_replay,
            textRes = R.string.replay,
            onCustomAction = Runnable {
                replayOnPressed()
            }
        )
    }

    private fun replayOnPressed() {
        options.charIndexesChosen2.clear()
        setUpRecyclerView()
    }
}