package com.example.aplicacaociam.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.aplicacaociam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.mutation.Precondition.exists
import com.google.protobuf.Empty
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*


class AddGameFragment : Fragment() {


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val dbFirestore = FirebaseFirestore.getInstance()

    lateinit var adicionarJogo: Button
    lateinit var jogoEscolhido: Spinner
    lateinit var rankEscolhido: Spinner
    lateinit var tipoJogo: Spinner
    lateinit var nickJogo: EditText

    lateinit var jogo: String
    lateinit var rank: String
    lateinit var tipo: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_game, container, false)

        jogoEscolhido = view.findViewById(R.id.spJogo)
        rankEscolhido = view.findViewById(R.id.spRank)
        tipoJogo = view.findViewById(R.id.spTipo)
        adicionarJogo = view.findViewById(R.id.btAdicionarJogo)

        var portugues = 0
        var ingles = 0
        nickJogo = view.findViewById(R.id.etNickname)

        val jogos = arrayOf(
            "League of Legends",
            "Counter Strike : Global Ofensive",
            "Valorant",
            "COD Warzone"
        )

        val rankLOL = arrayOf(
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

        val tipoJogador = arrayOf("Casual", "Ranked")

        jogoEscolhido.adapter =
            ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, jogos)

        tipoJogo.adapter =
            ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, tipoJogador)


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
                    if (position == 0) {
                        rank = "Iron"
                    } else if (position == 1) {
                        rank = "Bronze"
                    } else if (position == 2) {
                        rank = "Silver"
                    } else if (position == 3) {
                        rank = "Gold"
                    } else if (position == 4) {
                        rank = "Platinum"
                    } else if (position == 5) {
                        rank = "Diamond"
                    } else if (position == 6) {
                        rank = "Master"
                    } else if (position == 7) {
                        rank = "Grand Master"
                    } else if (position == 8) {
                        rank = "Challenger"
                    }
                } else if (jogo == "Counter Strike Global Ofensive") {
                    if (position == 0) {
                        rank = "Silver"
                    } else if (position == 1) {
                        rank = "Gold"
                    } else if (position == 2) {
                        rank = "Master Guardian"
                    } else if (position == 3) {
                        rank = "DMG"
                    } else if (position == 4) {
                        rank = "Legendary Eagle"
                    } else if (position == 5) {
                        rank = "Legendary Eagle Master"
                    } else if (position == 6) {
                        rank = "Supreme"
                    } else if (position == 7) {
                        rank = "Global Elite"
                    }
                } else if (jogo == "Valorant") {
                    if (position == 0) {
                        rank = "Iron"
                    } else if (position == 1) {
                        rank = "Bronze"
                    } else if (position == 2) {
                        rank = "Silver"
                    } else if (position == 3) {
                        rank = "Gold"
                    } else if (position == 4) {
                        rank = "Platinum"
                    } else if (position == 5) {
                        rank = "Diamond"
                    } else if (position == 6) {
                        rank = "Immortal"
                    } else if (position == 7) {
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
                if (position == 0) {
                    tipo = "Casual"
                } else if (position == 1) {
                    tipo = "Competitivo"
                }
            }
        }

        adicionarJogo.setOnClickListener {

            val nick = nickJogo.text.toString()

            if (nick.isNotEmpty()) {
                val docRef = dbFirestore.collection("User").document(auth.uid.toString())
                docRef.get().addOnSuccessListener { document ->
                    if (document != null) {
                        if (document.data?.get("en").toString() == "1") {
                            ingles = 1
                        }
                        if (document.data?.get("pt").toString() == "1") {
                            portugues = 1
                        }
                        saveFirestore(jogo, nick, rank, tipo, ingles, portugues)
                    } else {
                        Toast.makeText(activity, "Falha a carregar o documento", Toast.LENGTH_LONG)
                            .show()
                        saveFirestore(jogo, nick, rank, tipo, ingles, portugues)

                    }
                }.addOnFailureListener { execption ->
                    Toast.makeText(activity, execption.toString(), Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(activity, "Preencha o username", Toast.LENGTH_LONG).show()

            }
        }

        return view
    }

    fun saveFirestore(jogo: String, nick: String, rank: String, tipo: String, en: Int, pt: Int) {

        val valores = hashMapOf(
            "user" to auth.uid.toString(),
            "rank" to rank,
            "tipo" to tipo,
            "en" to en,
            "pt" to pt,
            "nickname" to nick

        )


        if (jogo == "League of Legends") {
            dbFirestore.collection(jogo).document(nick).get().addOnSuccessListener {
                if (it.exists()) {
                    Toast.makeText(
                        activity,
                        "Já existe uma conta com esse username",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    dbFirestore.collection(jogo).document(nick).set(valores)
                        .addOnCompleteListener() {
                            when {
                                it.isSuccessful -> {
                                    Toast.makeText(
                                        activity,
                                        "Jogo adicionado com Sucesso",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val fragment = ProfileFragment()
                                    activity!!.supportFragmentManager.beginTransaction()
                                        .replace(
                                            R.id.fragment_container,
                                            fragment,
                                            "Perfil"
                                        )
                                        .addToBackStack(null)
                                        .commit()
                                }
                            }
                        }
                }


            }
        } else if (jogo == "Counter Strike Global Ofensive") {
            dbFirestore.collection(jogo).document(nick).get().addOnSuccessListener {
                if (it.exists()) {
                    Toast.makeText(
                        activity,
                        "Já existe uma conta com esse username",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    dbFirestore.collection(jogo).document(nick).set(valores)
                        .addOnCompleteListener() {
                            when {
                                it.isSuccessful -> {
                                    Toast.makeText(
                                        activity,
                                        "Jogo adicionado com Sucesso",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val fragment = ProfileFragment()
                                    activity!!.supportFragmentManager.beginTransaction()
                                        .replace(
                                            R.id.fragment_container,
                                            fragment,
                                            "Perfil"
                                        )
                                        .addToBackStack(null)
                                        .commit()
                                }
                            }
                        }
                }

            }
        } else if (jogo == "Valorant") {
                    dbFirestore.collection(jogo).document(nick).get().addOnSuccessListener {
                        if (it.exists()) {
                            Toast.makeText(
                                activity,
                                "Já existe uma conta com esse username",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            dbFirestore.collection(jogo).document(nick).set(valores)
                                .addOnCompleteListener() {
                                    when {
                                        it.isSuccessful -> {
                                            Toast.makeText(
                                                activity,
                                                "Jogo adicionado com Sucesso",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            val fragment = ProfileFragment()
                                            activity!!.supportFragmentManager.beginTransaction()
                                                .replace(
                                                    R.id.fragment_container,
                                                    fragment,
                                                    "Perfil"
                                                )
                                                .addToBackStack(null)
                                                .commit()
                                        }
                                    }
                                }
                        }

            }
        } else if (jogo == "COD Warzone") {
                    dbFirestore.collection(jogo).document(nick).get().addOnSuccessListener {
                        if (it.exists()) {
                            Toast.makeText(
                                activity,
                                "Já existe uma conta com esse username",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            dbFirestore.collection(jogo).document(nick).set(valores)
                                .addOnCompleteListener() {
                                    when {
                                        it.isSuccessful -> {
                                            Toast.makeText(
                                                activity,
                                                "Jogo adicionado com Sucesso",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            val fragment = ProfileFragment()
                                            activity!!.supportFragmentManager.beginTransaction()
                                                .replace(
                                                    R.id.fragment_container,
                                                    fragment,
                                                    "Perfil"
                                                )
                                                .addToBackStack(null)
                                                .commit()
                                        }
                                    }
                                }
                        }

            }
        }
        etNickname.text.clear()
    }

    companion object {
        fun newInstance(): AddGameFragment = AddGameFragment()
    }
}