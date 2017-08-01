package com.bairro.biblioteca.exceptions;

public class ModelException extends Exception {

	private static final long serialVersionUID = 4049747126608813330L;

	/**
	 * Cria a exce��o com a mensagem padr�o da classe <code>Exeption</code>.
	 */
	public ModelException() {
		super();
	}

	/**
	 * Cria a exce��o com a mensagem passada como par�metro.
	 * 
	 * @param mensagem
	 *            Mensagem espec�fica da exce��o.
	 */
	public ModelException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Cria a exce��o com a mensagem e causa passadas por par�metro.
	 * 
	 * @param mensagem
	 *            Mensagem espec�fica da exce��o.
	 * @param causa
	 *            Indica a exce��o que gerou essa exce�ao.
	 */
	public ModelException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
