package com.example.whoisthat.service

import com.example.whoisthat.R
import com.example.whoisthat.model.Character
import com.example.whoisthat.model.Options

typealias CharacterListener = (users: List<com.example.whoisthat.model.Character>) -> Unit

class CharacterService(
    options: Options
){
    private var characters1:MutableList<com.example.whoisthat.model.Character> = mutableListOf<com.example.whoisthat.model.Character>()
    private var characters2:MutableList<com.example.whoisthat.model.Character> = mutableListOf<com.example.whoisthat.model.Character>()
    private val listeners= mutableSetOf<CharacterListener>()

    init{
        if(options.charIndexes.isEmpty()) INDEXES.shuffle()
        characters1=(0 until options.charNum).map{
            Character(
                ind = INDEXES[it],
                passiveResource = PICTURES[INDEXES[it]] ,
                isChosen = options.charIndexesChosen1.contains(INDEXES[it]),
                resource =  if (!options.charIndexesChosen1.contains(INDEXES[it])) PICTURES[INDEXES[it]] else red
            )
            //if (options.charIndexes.size<options.charNum) options.charIndexes.add(options.charIndexes.size,)
        }.toMutableList()

        if(options.charIndexes.isEmpty()) {
            options.charIndexes = (0 until characters1.size).map {
                characters1[it].ind
            }.toMutableList()
        }

        characters2=(0 until options.charNum).map{
            Character(
                ind = INDEXES[it],
                passiveResource = PICTURES[INDEXES[it]] ,
                isChosen = options.charIndexesChosen2.contains(INDEXES[it]),
                resource =  if (!options.charIndexesChosen2.contains(INDEXES[it])) PICTURES[INDEXES[it]] else red
            )
        }.toMutableList()
    }

    fun getCharacters1():List<com.example.whoisthat.model.Character>{
        return characters1
    }

    fun getCharacters2():List<com.example.whoisthat.model.Character>{
        return characters2
    }

//    fun addListener(listener:CharacterListener){
//        listeners.add(listener)
//        listener.invoke(characters)
//    }
//
//    fun deleteListener(listener: CharacterListener){
//        listeners.remove(listener)
//        listener.invoke(characters)
//    }

    companion object{

        private val PICTURES= mutableListOf(
            R.drawable.aburame_shino,
            R.drawable.akamaru,
            R.drawable.akimichi_chouji,
            R.drawable.deidara,
            R.drawable.dosu,
            R.drawable.enma,
            R.drawable.gaara,
            R.drawable.gamabunta,
            R.drawable.haku,
            R.drawable.haruna_sakura,//10
            R.drawable.hatake_kakashi,
            R.drawable.hidan,
            R.drawable.huga_hinata,
            R.drawable.huga_neji,
            R.drawable.inuzuka_kiba,
            R.drawable.iruka,
            R.drawable.jiraiya,
            R.drawable.kabuto,
            R.drawable.kakuzo,
            R.drawable.kankuro,//20
            R.drawable.katsuyu,
            R.drawable.killer_bee,
            R.drawable.kisame,
            R.drawable.konan,
            R.drawable.manda,
            R.drawable.might_guy,
            R.drawable.mitarashi_anko,
            R.drawable.namikaze_minato,
            R.drawable.nara_shikamaru,
            R.drawable.orochimaru,//30
            R.drawable.pain,
            R.drawable.pakkun,
            R.drawable.rin,
            R.drawable.rock_lee,
            R.drawable.sarutobi_asuma,
            R.drawable.sarutobi_hiruzen,
            R.drawable.sasori,
            R.drawable.senju_hashirama,
            R.drawable.senju_tobirama,
            R.drawable.temari,//40
            R.drawable.tenten,
            R.drawable.tsunade,
            R.drawable.uchiha_itachi,
            R.drawable.uchiha_madara,
            R.drawable.uchiha_sasuke,
            R.drawable.uchiha_obito,
            R.drawable.uzumaki_kushina,
            R.drawable.uzumaki_naruto,
            R.drawable.yamanaka_ino,
            R.drawable.yuhi_kurenai,//50
            R.drawable.zabuza,
            R.drawable.zetsu,


        )

        private val INDEXES=(0 until PICTURES.size).map{
            it
        }.toMutableList()

        private val red=R.color.red
    }
}
