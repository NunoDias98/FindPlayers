package com.example.aplicacaociam.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacaociam.Communicator
import com.example.aplicacaociam.DadosAdapter
import com.example.aplicacaociam.ExampleItem
import com.example.aplicacaociam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.Empty
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    lateinit var comm: Communicator

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val dbFirestore : FirebaseFirestore = FirebaseFirestore.getInstance()

    lateinit var filtrar : Button
    lateinit var recycler : RecyclerView
    lateinit var jogoFiltrado : String
    lateinit var rankFiltrado : String
    lateinit var tipoFiltrado : String
    lateinit var linguaFiltrada : String

    var result = ArrayList<ExampleItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        //recycler = view.findViewById(R.id.recyclerView)
        filtrar = view.findViewById(R.id.btFiltrar)

        filtrar.setOnClickListener {
            val fragment = FiltrarFragment()
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, "Filtrar")
                .addToBackStack(null)
                .commit()
        }


        jogoFiltrado  = arguments?.getString("jogo").toString()
        rankFiltrado  = arguments?.getString("rank").toString()
        tipoFiltrado  = arguments?.getString("tipo").toString()
        linguaFiltrada  = arguments?.getString("lingua").toString()

        Log.d("filtro1",jogoFiltrado.toString())
        Log.d("filtro2",rankFiltrado.toString())
        Log.d("filtro3",tipoFiltrado.toString())
        Log.d("filtro4",linguaFiltrada.toString())


        /*
        val exampleList = generateDummyList(500)
        view.recyclerView.adapter = DadosAdapter(exampleList)
        view.recyclerView.layoutManager = LinearLayoutManager(activity!!)*/


        //val item : List<ExampleItem> = readFirestore()

        if(jogoFiltrado== "League of Legends"){
            readFirestoreFilter {
                view.recyclerView.adapter = DadosAdapter(it)
                view.recyclerView.layoutManager = LinearLayoutManager(activity!!)
            }

        }else if(jogoFiltrado == "Counter Strike Global Ofensive"){
            readFirestoreFilter {
                view.recyclerView.adapter = DadosAdapter(it)
                view.recyclerView.layoutManager = LinearLayoutManager(activity!!)
            }
        }else if(jogoFiltrado == "COD Warzone"){
            readFirestoreFilter {
                view.recyclerView.adapter = DadosAdapter(it)
                view.recyclerView.layoutManager = LinearLayoutManager(activity!!)
            }
        }else if(jogoFiltrado == "Valorant"){
            readFirestoreFilter {
                view.recyclerView.adapter = DadosAdapter(it)
                view.recyclerView.layoutManager = LinearLayoutManager(activity!!)
            }
        }
        else{
            readFirestore {
                view.recyclerView.adapter = DadosAdapter(it)
                view.recyclerView.layoutManager = LinearLayoutManager(activity!!)
            }

        }


        result =  ArrayList<ExampleItem>();
        return view
    }

    fun readFirestore(myCallback: (ArrayList<ExampleItem>) -> Unit){
        var portugues = ""
        var ingles = ""

        //val result = ArrayList<ExampleItem>()
        dbFirestore.collection("League of Legends")
            .get()
            .addOnCompleteListener{
                if(it.isSuccessful){
                    for(document in it.result!!){
                        if(document.data.getValue("pt").toString() == "1"){
                            portugues = "PT"
                        }
                        if(document.data.getValue("en").toString() == "1"){
                            ingles = "EN"
                        }
                        val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                (document.data.getValue("rank").toString()) + " - " +
                                portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                        val item = ExampleItem("League of Legends", desc)

                        //Log.d("blah",item.toString())
                        result.add(item)
                        //Log.d("result", result.size.toString())
                    }
                }
            }
        dbFirestore.collection("Counter Strike Global Ofensive")
            .get()
            .addOnCompleteListener{
                if(it.isSuccessful){
                    for(document in it.result!!){
                        if(document.data.getValue("pt").toString() == "1"){
                            portugues = "PT"
                        }
                        if(document.data.getValue("en").toString() == "1"){
                            ingles = "EN"
                        }
                        val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                (document.data.getValue("rank").toString()) + " - " +
                                portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                        val item = ExampleItem("Counter Strike Global Ofensive", desc)

                        //Log.d("blah",desc)
                        result.add(item)
                        //Log.d("result", result.size.toString())
                    }
                }
            }
        dbFirestore.collection("Valorant")
            .get()
            .addOnCompleteListener{
                if(it.isSuccessful){
                    for(document in it.result!!){
                        if(document.data.getValue("pt").toString() == "1"){
                            portugues = "PT"
                        }
                        if(document.data.getValue("en").toString() == "1"){
                            ingles = "EN"
                        }
                        val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                (document.data.getValue("rank").toString()) + " - " +
                                portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                        val item = ExampleItem("Valorant", desc)

                        //Log.d("blah",desc)
                        result.add(item)
                        //Log.d("result", result.size.toString())
                    }
                }
            }
        dbFirestore.collection("COD Warzone")
            .get()
            .addOnCompleteListener{
                if(it.isSuccessful){
                    for(document in it.result!!){
                        if(document.data.getValue("pt").toString() == "1"){
                            portugues = "PT"
                        }
                        if(document.data.getValue("en").toString() == "1"){
                            ingles = "EN"
                        }
                        val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                (document.data.getValue("rank").toString()) + " - " +
                                portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                        val item = ExampleItem("COD Warzone", desc)

                        //Log.d("blah",desc)
                        result.add(item)
                        //Log.d("result", result.size.toString())
                    }
                    myCallback(result)
                }
            }

        //return result
    }

    fun readFirestoreFilter(myCallback: (ArrayList<ExampleItem>) -> Unit) {
        var portugues = ""
        var ingles = ""
        if(rankFiltrado == "Todos" && linguaFiltrada == "Todas" && tipoFiltrado == "Todos"){
            dbFirestore.collection(jogoFiltrado)
                .get()
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        for(document in it.result!!){
                            if(document.data.getValue("pt").toString() == "1"){
                                portugues = "PT"
                            }
                            if(document.data.getValue("en").toString() == "1"){
                                ingles = "EN"
                            }
                            val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                    (document.data.getValue("rank").toString()) + " - " +
                                    portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                            val item = ExampleItem(jogoFiltrado, desc)

                            //Log.d("blah",desc)
                            result.add(item)
                            //Log.d("result", result.size.toString())
                        }
                        myCallback(result)
                    }
                }
        }else if(rankFiltrado != "Todos" && linguaFiltrada == "Todas" && tipoFiltrado == "Todos"){
            dbFirestore.collection(jogoFiltrado).whereEqualTo("rank",rankFiltrado)
                .get()
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        for(document in it.result!!){
                            if(document.data.getValue("pt").toString() == "1"){
                                portugues = "PT"
                            }
                            if(document.data.getValue("en").toString() == "1"){
                                ingles = "EN"
                            }
                            val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                    (document.data.getValue("rank").toString()) + " - " +
                                    portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                            val item = ExampleItem(jogoFiltrado, desc)

                            //Log.d("blah",desc)
                            result.add(item)
                            //Log.d("result", result.size.toString())
                        }
                        myCallback(result)
                    }
                }
        }else if(rankFiltrado!= "Todos" && linguaFiltrada != "Todas" && tipoFiltrado == "Todos"){
            var pt = 0
            var en = 0
            if(linguaFiltrada == "PT"){
                pt = 1
                dbFirestore.collection(jogoFiltrado).whereEqualTo("pt",pt).whereEqualTo("rank",rankFiltrado)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }
            }else if(linguaFiltrada == "EN"){
                en = 1
                dbFirestore.collection(jogoFiltrado).whereEqualTo("en",en).whereEqualTo("rank",rankFiltrado)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }
            }
        }else if(rankFiltrado!= "Todos" && linguaFiltrada != "Todas" && tipoFiltrado != "Todos"){
            var pt = 0
            var en = 0
            if(linguaFiltrada == "PT"){
                pt = 1
                dbFirestore.collection(jogoFiltrado).whereEqualTo("pt",pt).whereEqualTo("rank",rankFiltrado).whereEqualTo("tipo",tipoFiltrado)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }
            }else if(linguaFiltrada == "EN"){
                en = 1
                dbFirestore.collection(jogoFiltrado).whereEqualTo("en",en).whereEqualTo("rank",rankFiltrado).whereEqualTo("tipo",tipoFiltrado)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }
            }
        }
        else if(linguaFiltrada != "Todas" && rankFiltrado == "Todos" && tipoFiltrado == "Todos"){
            var pt = 0
            var en = 0
            if(linguaFiltrada == "PT"){
                pt = 1
                dbFirestore.collection(jogoFiltrado).whereEqualTo("pt",pt)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }
            }else if(linguaFiltrada == "EN"){
                en = 1
                dbFirestore.collection(jogoFiltrado).whereEqualTo("en",en)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }
            }
        }
        else if(tipoFiltrado != "Todos" && rankFiltrado == "Todos" && linguaFiltrada == "Todas"){
                dbFirestore.collection(jogoFiltrado).whereEqualTo("tipo",tipoFiltrado)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }

        }else if(tipoFiltrado != "Todos" && rankFiltrado != "Todos" && linguaFiltrada =="Todas"){
            dbFirestore.collection(jogoFiltrado).whereEqualTo("tipo",tipoFiltrado).whereEqualTo("rank",rankFiltrado)
                .get()
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        for(document in it.result!!){
                            if(document.data.getValue("pt").toString() == "1"){
                                portugues = "PT"
                            }
                            if(document.data.getValue("en").toString() == "1"){
                                ingles = "EN"
                            }
                            val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                    (document.data.getValue("rank").toString()) + " - " +
                                    portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                            val item = ExampleItem(jogoFiltrado, desc)

                            //Log.d("blah",desc)
                            result.add(item)
                            //Log.d("result", result.size.toString())
                        }
                        myCallback(result)
                    }
                }
        }else if(tipoFiltrado != "Todos" && rankFiltrado == "Todos" && linguaFiltrada !="Todas"){
            var pt = 0
            var en = 0
            if(linguaFiltrada == "PT"){
                pt = 1
                dbFirestore.collection(jogoFiltrado).whereEqualTo("pt",pt).whereEqualTo("tipo",tipoFiltrado)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }
            }else if(linguaFiltrada == "EN"){
                en = 1
                dbFirestore.collection(jogoFiltrado).whereEqualTo("en",en).whereEqualTo("tipo",tipoFiltrado)
                    .get()
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            for(document in it.result!!){
                                if(document.data.getValue("pt").toString() == "1"){
                                    portugues = "PT"
                                }
                                if(document.data.getValue("en").toString() == "1"){
                                    ingles = "EN"
                                }
                                val desc : String = (document.data.getValue("nickname").toString()) + " - " +
                                        (document.data.getValue("rank").toString()) + " - " +
                                        portugues + " " + ingles + " - " + (document.data.getValue("tipo").toString())
                                val item = ExampleItem(jogoFiltrado, desc)

                                //Log.d("blah",desc)
                                result.add(item)
                                //Log.d("result", result.size.toString())
                            }
                            myCallback(result)
                        }
                    }
            }
        }

        /*
        // Create a reference to the cities collection
        val citiesRef = db.collection("cities")

// Create a query against the collection.
        val query = citiesRef.whereEqualTo("state", "CA")*/
    }

    companion object {

        fun newInstance() : SearchFragment = SearchFragment()
    }


}