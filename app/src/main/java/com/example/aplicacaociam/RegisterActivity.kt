package com.example.aplicacaociam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val dbFirestore : CollectionReference = FirebaseFirestore.getInstance().collection("User")

    lateinit var cbPT : CheckBox
    lateinit var cbEN : CheckBox
    var EN : Int = 0
    var PT : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btRegister.setOnClickListener{

            val nickname = etNickname.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            var aux = true

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                etEmail.error = "Insira um email válido"
                etEmail.requestFocus()
                aux = false
            }

            cbPT = findViewById(R.id.cbPT)
            cbEN = findViewById(R.id.cbEN)

            if (nickname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && (cbEN.isChecked || cbPT.isChecked) && aux == true) {
                if(cbEN.isChecked){
                    EN = 1
                }
                if(cbPT.isChecked){
                    PT = 1
                }
                registerUser(nickname,email, password, EN, PT)
            }else{
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                if(nickname.isEmpty()){
                    etNickname.error = "Preencha o nickname"
                    etNickname.requestFocus()

                }
                if(password.isEmpty()){
                    etPassword.error = "Preencha a password"
                    etPassword.requestFocus()
                }
                if(!cbEN.isChecked && !cbPT.isChecked){
                    cbEN.error= "Preencha pelo o menos um"
                    cbPT.error= "Preencha pelo o menos um"
                }
            }
        }
    }

    private fun registerUser(nickname: String, email: String, password: String, en: Int, pt: Int){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            when{
                it.isSuccessful -> {
                    Toast.makeText(this,"Utilizador registado", Toast.LENGTH_LONG).show()

                    val user = HashMap<String, Any>()
                    user["nickname"] = nickname
                    user["email"] = email
                    user["en"] = en
                    user["pt"] = pt
                    val uid = auth.uid.toString()
                    dbFirestore.document(uid).set(user).addOnCompleteListener {
                        when {
                            it.isSuccessful -> {
                                auth.signOut()
                            }
                        }
                    }

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                }
                else ->{
                    Toast.makeText(this,"Falha a registar o utilizador", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}