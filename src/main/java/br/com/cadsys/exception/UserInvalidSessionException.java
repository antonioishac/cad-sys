package br.com.cadsys.exception;

public class UserInvalidSessionException extends Exception {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -2894532774915123448L;
	
	public UserInvalidSessionException() {
		
	}
	
	public UserInvalidSessionException(String mensagem) {
		super(mensagem);
	}

}
