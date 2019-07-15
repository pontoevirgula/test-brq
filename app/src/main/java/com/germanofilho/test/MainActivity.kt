package com.germanofilho.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var montadoraResponse : Montadoras
    var contadorCarros : Int = 0
    var contadorPecas : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        montadoraResponse = Gson().fromJson(getJsonFromAssets(), Montadoras::class.java/*TODO Change object*/)
        if (montadoraResponse.montadoras != null && montadoraResponse.montadoras!!.isNotEmpty()) {
            montadoraResponse.montadoras?.let {
                exibirQuantidadeDeCarrosEPecas(ordenarListaDeMontadoras(it))
            }
        }
    }

    private fun ordenarListaDeMontadoras(montadoraList: List<MontadorasItem?>) : List<MontadorasItem?> {
        val listaOrdenada = montadoraList.toMutableList()
        for (montadora : MontadorasItem? in montadoraList) {
            if (montadora?.id == 3) {
                listaOrdenada.remove(montadora)
                listaOrdenada.add(montadora)
            }
        }
        return listaOrdenada
    }

    private fun exibirQuantidadeDeCarrosEPecas(montadoraList: List<MontadorasItem?>) {

        val montadoraItemList: MutableList<MontadorasItem?> = ArrayList()
        lateinit var carroItemList: MutableList<CarrosItem?>

        for (montadoraItem : MontadorasItem? in montadoraList) {
            montadoraItemList.add(montadoraItem)
            if (montadoraItem?.carros != null){
                contadorCarros = montadoraItem.carros.size
                Log.i("NUMERO DE CARROS", "A montadora ${montadoraItem.nome} tem $contadorCarros carros")
                carroItemList = montadoraItem.carros.toMutableList()
                for (carro: CarrosItem? in carroItemList) {
                    if (carro?.nome == null) carro?.nome = "com nome desconhecido"
                    contadorPecas = carro?.pecas?.size ?: 0
                    Log.i("NUMERO DE PEÇAS", "O carro ${carro?.nome} tem $contadorPecas peças")
                }
            }else{
                contadorCarros = 0
                contadorPecas = 0
                val mensagemSemCarros = "A ${montadoraItem?.nome} tem $contadorPecas peças por ter $contadorCarros carros"
                Log.i("SEM CARROS", mensagemSemCarros)
            }
        }
    }

    private fun getJsonFromAssets(): String {
        return application.assets.open("montadoras.json").bufferedReader().use {
            it.readText()
        }
    }
}

