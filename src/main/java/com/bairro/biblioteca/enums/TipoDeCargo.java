package com.bairro.biblioteca.enums;

/**
 * Enum para definir uma letra para representar os tipos de cargos:
 * 
 * Onde "B" -> Bibliotecário e "D" -> Diretor;
 * 
 * No banco será gravado somente a letra;
 * 
 */
public enum TipoDeCargo {

	BIBLIOTECARIO("B"), DIRETOR("D");

	private String letra;

	private TipoDeCargo(String letra) {
		this.letra = letra;
	}

	public String getLetra() {
		return letra;
	}
}
