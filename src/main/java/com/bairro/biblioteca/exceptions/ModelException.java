package com.bairro.biblioteca.exceptions;

public class ModelException extends Exception {

	private static final long serialVersionUID = 4049747126608813330L;

	/**
	 * Cria a exceção com a mensagem padrão da classe <code>Exeption</code>.
	 */
	public ModelException() {
		super();
	}

	/**
	 * Cria a exceção com a mensagem passada como parâmetro.
	 * 
	 * @param mensagem
	 *            Mensagem específica da exceção.
	 */
	public ModelException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Cria a exceção com a mensagem e causa passadas por parâmetro.
	 * 
	 * @param mensagem
	 *            Mensagem específica da exceção.
	 * @param causa
	 *            Indica a exceção que gerou essa exceçao.
	 */
	public ModelException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
