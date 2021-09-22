package com.example.aplicacaociam.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.aplicacaociam.LoginActivity
import com.example.aplicacaociam.MainActivity
import com.example.aplicacaociam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val dbFirestore : CollectionReference = FirebaseFirestore.getInstance().collection("User")

    lateinit var email : TextView

    lateinit var logout : Button
    lateinit var editar : Button
    lateinit var meusJogos : Button
    lateinit var submeter : Button

    lateinit var user : EditText
    lateinit var passNova : EditText
    lateinit var passCNova : EditText


    lateinit var cbPT : CheckBox
    lateinit var cbEN : CheckBox

    var EN : Int = 0
    var PT : Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)



        email = view.findViewById(R.id.tvEmail)
        user = view.findViewById(R.id.etUsername)
        user.isEnabled = false
        logout = view.findViewById(R.id.btLogout)
        editar = view.findViewById(R.id.btEditar)
        submeter = view.findViewById(R.id.btSubmeter)
        passNova = view.findViewById(R.id.etPasswordNova)
        passCNova = view.findViewById(R.id.etCPasswordNova)
        cbPT = view.findViewById(R.id.cbPT)
        cbEN = view.findViewById(R.id.cbEN)

        val currentUser = auth.currentUser?.email

        email.text = currentUser


        val docRef = dbFirestore.document(auth.uid.toString())
        docRef.get().addOnSuccessListener {document ->
            if(document != null){
                user.setText(document.data?.get("nickname").toString())
                if(document.data?.get("en").toString() == "1"){
                    cbEN.isChecked = true
                }
                if(document.data?.get("pt").toString() == "1"){
                    cbPT.isChecked = true
                }
            }else{
                Toast.makeText(activity,"Falha a carregar o documento",Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener{ execption ->
            Toast.makeText(activity,execption.toString(),Toast.LENGTH_LONG).show()
        }

        editar.setOnClickListener{
            user.isEnabled = true
            editar.visibility = View.GONE
            submeter.visibility = View.VISIBLE

            passNova.visibility = View.VISIBLE
            passCNova.visibility = View.VISIBLE
            cbPT.isClickable = true
            cbEN.isClickable = true
        }

        submeter.setOnClickListener{
            if (user.text.isNotEmpty() && (cbEN.isChecked || cbPT.isChecked)) {
                if(cbEN.isChecked){
                    EN = 1
                }
                if(cbPT.isChecked){
                    PT = 1
                }

                val valoresEditados = hashMapOf(
                    "nickname" to user.text.toString(),
                    "pt" to PT,
                    "en" to EN

                )

                val uid = auth.uid.toString()


                val novaPW = passNova.text.toString()
                val cNovaPW = passCNova.text.toString()
                if(novaPW.isNotEmpty()){
                    if(novaPW != cNovaPW){
                        Toast.makeText(activity,"Passwords não são iguais",Toast.LENGTH_LONG).show()
                    }else{
                        auth.currentUser?.updatePassword(novaPW)?.addOnCompleteListener{
                            if(it.isSuccessful){
                                //Toast.makeText(activity,"Password alterada",Toast.LENGTH_LONG).show()
                            }else{
                                Toast.makeText(activity,"Erro a alterar password",Toast.LENGTH_LONG).show()
                            }
                        }
                        dbFirestore.document(uid).set(valoresEditados).addOnCompleteListener {
                            when {
                                it.isSuccessful -> {
                                    Toast.makeText(activity,"Alterações efetuadas com sucesso",Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                }else{
                    dbFirestore.document(uid).set(valoresEditados).addOnCompleteListener {
                        when {
                            it.isSuccessful -> {
                                Toast.makeText(activity,"Alterações efetuadas com sucesso",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
            //manda para o perfil
            val fragment = ProfileFragment()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment, "Dados Alterados")
                .addToBackStack(null)
                .commit()
        }



        logout.setOnClickListener{
            auth.signOut()

            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {

        fun newInstance() : ProfileFragment = ProfileFragment()


    }

}