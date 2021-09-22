package com.example.aplicacaociam

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.aplicacaociam.fragments.AddGameFragment
import com.example.aplicacaociam.fragments.ProfileFragment
import com.example.aplicacaociam.fragments.SearchFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Communicator {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val searchFragment = SearchFragment()
    private val addGameFragment = AddGameFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(searchFragment)



        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_search -> replaceFragment(searchFragment)
                R.id.ic_profile -> replaceFragment(profileFragment)
                R.id.ic_addgame -> replaceFragment(addGameFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    override fun passDataCom(jogo: String, rank: String, tipo: String, lingua: String) {
        val bundle = bundleOf(
            "jogo" to jogo,
            "rank" to rank,
            "tipo" to tipo,
            "lingua" to lingua
        )

        val transaction =   this.supportFragmentManager.beginTransaction()
        val frag2 = SearchFragment()
        frag2.arguments = bundle

        transaction.replace(R.id.fragment_container, frag2)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }


}