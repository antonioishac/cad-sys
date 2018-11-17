package br.com.cadsys.exception;

public class UserException extends Exception {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -2894532774915123448L;
	
	public UserException() {
		
	}
	
	public UserException(String mensagem) {
		super(mensagem);
	}

}
