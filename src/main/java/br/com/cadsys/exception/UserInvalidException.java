package br.com.cadsys.exception;

public class UserInvalidException extends Exception {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -2894532774915123448L;
	
	public UserInvalidException() {
		
	}
	
	public UserInvalidException(String mensagem) {
		super(mensagem);
	}

}
