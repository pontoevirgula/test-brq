package com.germanofilho.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var montadoraResponse : Montadoras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        montadoraResponse = Gson().fromJson(getJsonFromAssets(), Montadoras::class.java/*TODO Change object*/)
        val teste = montadoraResponse
    }

    private fun getJsonFromAssets(): String {
        return application.assets.open("montadoras.json").bufferedReader().use {
            it.readText()
        }
    }




}


//
//data class Montadoras(var montadoraList: List<MontadoraItem>)
//
//data class MontadoraItem(var id : Int,var nome : String, var carros: List<Carros>)
//
//data class Carros (var id : Int, var nome : String, var pecas : List<Pecas>)
//
//data class Pecas (var id : Int, var nome : String)