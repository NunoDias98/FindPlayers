package com.example.aplicacaociam.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import com.example.aplicacaociam.Communicator
import com.example.aplicacaociam.R


class FiltrarFragment : Fragment() {

    lateinit var comm: Communicator

    lateinit var Filtrar: Button
    lateinit var jogoEscolhido: Spinner
    lateinit var rankEscolhido: Spinner
    lateinit var tipoJogo: Spinner
    lateinit var linguaEscolhida: Spinner

    lateinit var jogo: String
    lateinit var rank: String
    lateinit var tipo: String
    lateinit var lingua: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filtrar, container, false)

        jogoEscolhido = view.findViewById(R.id.spJogo)
        rankEscolhido = view.findViewById(R.id.spRank)
        tipoJogo = view.findViewById(R.id.spTipo)
        linguaEscolhida = view.findViewById(R.id.spLingua)
        Filtrar = view.findViewById(R.id.btFiltrar)

        val jogos = arrayOf(
            "League of Legends",
            "Counter Strike : Global Ofensive",
            "Valorant",
            "COD Warzone"
        )

        val rankLOL = arrayOf(
            "Todos",
            "Iron",
            "Bronze",
            "Silver",
            "Gold",
            "Platinum",
            "Diamond",
            "Master",
            "Grand Master",
            "Challenger"
        )
        val rankCSGO = arrayOf(
            "Todos",
            "Silver",
            "Gold",
            "Master Guardian",
            "Dinstinguished Master Guardian",
            "Legendary Eagle",
            "Legendary Eagle Master",
            "Supreme",
            "Global Elite"
        )
        val rankValorant = arrayOf(
            "Todos",
            "Iron",
            "Bronze",
            "Silver",
            "Gold",
            "Platinum",
            "Diamond",
            "Immortal",
            "Radiant"
        )
        val rankCOD = arrayOf("Sem rank")

        val tipoJogador = arrayOf("Todos","Casual", "Ranked")

        val linguas = arrayOf("Todas","PT","Eng")

        jogoEscolhido.adapter =
            ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, jogos)

        tipoJogo.adapter =
            ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, tipoJogador)

        linguaEscolhida.adapter =
            ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, linguas)


        jogoEscolhido.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    jogo = "League of Legends"
                    rankEscolhido.adapter =
                        ArrayAdapter<String>(
                            activity!!,
                            android.R.layout.simple_list_item_1,
                            rankLOL
                        )
                } else if (position == 1) {
                    jogo = "Counter Strike Global Ofensive"
                    rankEscolhido.adapter =
                        ArrayAdapter<String>(
                            activity!!,
                            android.R.layout.simple_list_item_1,
                            rankCSGO
                        )
                } else if (position == 2) {
                    jogo = "Valorant"
                    rankEscolhido.adapter =
                        ArrayAdapter<String>(
                            activity!!,
                            android.R.layout.simple_list_item_1,
                            rankValorant
                        )
                } else if (position == 3) {
                    jogo = "COD Warzone"
                    rankEscolhido.adapter =
                        ArrayAdapter<String>(
                            activity!!,
                            android.R.layout.simple_list_item_1,
                            rankCOD
                        )
                }

            }
        }
        rankEscolhido.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (jogo == "League of Legends") {
                    if(position == 0) {
                        rank = "Todos"
                    }else if (position == 1) {
                        rank = "Iron"
                    } else if (position == 2) {
                        rank = "Bronze"
                    } else if (position == 3) {
                        rank = "Silver"
                    } else if (position == 4) {
                        rank = "Gold"
                    } else if (position == 5) {
                        rank = "Platinum"
                    } else if (position == 6) {
                        rank = "Diamond"
                    } else if (position == 7) {
                        rank = "Master"
                    } else if (position == 8) {
                        rank = "Grand Master"
                    } else if (position == 9) {
                        rank = "Challenger"
                    }
                } else if (jogo == "Counter Strike Global Ofensive") {
                    if(position == 0){
                        rank = "Todos"
                    }else if (position == 1) {
                        rank = "Silver"
                    } else if (position == 2) {
                        rank = "Gold"
                    } else if (position == 3) {
                        rank = "Master Guardian"
                    } else if (position == 4) {
                        rank = "DMG"
                    } else if (position == 5) {
                        rank = "Legendary Eagle"
                    } else if (position == 6) {
                        rank = "Legendary Eagle Master"
                    } else if (position == 7) {
                        rank = "Supreme"
                    } else if (position == 8) {
                        rank = "Global Elite"
                    }
                } else if (jogo == "Valorant") {
                    if(position == 0){
                        rank = "Todos"
                    }else if (position == 1) {
                        rank = "Iron"
                    } else if (position == 2) {
                        rank = "Bronze"
                    } else if (position == 3) {
                        rank = "Silver"
                    } else if (position == 4) {
                        rank = "Gold"
                    } else if (position == 5) {
                        rank = "Platinum"
                    } else if (position == 6) {
                        rank = "Diamond"
                    } else if (position == 7) {
                        rank = "Immortal"
                    } else if (position == 8) {
                        rank = "Radiant"
                    }
                } else if (jogo == "COD Warzone") {
                    rank = "Sem Rank"
                }
            }
        }

        tipoJogo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position == 0){
                    tipo = "Todos"
                } else if (position == 1) {
                    tipo = "Casual"
                } else if (position == 2) {
                    tipo = "Competitivo"
                }
            }
        }

        linguaEscolhida.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    lingua = "Todas"
                } else if (position == 1) {
                    lingua = "PT"
                }else if(position == 2){
                    lingua = "EN"
                }
            }
        }


        comm = activity as Communicator

        Filtrar.setOnClickListener{
            comm.passDataCom(jogo,rank,tipo,lingua)
        }

        return view
    }

    companion object {
        fun newInstance() : SearchFragment = SearchFragment()
    }

}