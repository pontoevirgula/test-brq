package com.germanofilho.test

data class Montadoras(
	val montadoras: List<MontadorasItem?>? = null
)

data class MontadorasItem(
		val nome: String? = null,
		val id: Int? = null,
		val carros: List<CarrosItem?>? = null
)


data class CarrosItem(
		val pecas: List<PecasItem?>? = null,
		var nome: String? = null,
		val id: Int? = null
)

data class PecasItem(
		val nome: String? = null,
		val id: Int? = null
)



