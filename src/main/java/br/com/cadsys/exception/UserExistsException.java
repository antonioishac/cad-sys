package br.com.cadsys.exception;

public class UserExistsException extends Exception {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -2894532774915123448L;
	
	public UserExistsException() {
		
	}
	
	public UserExistsException(String mensagem) {
		super(mensagem);
	}

}
