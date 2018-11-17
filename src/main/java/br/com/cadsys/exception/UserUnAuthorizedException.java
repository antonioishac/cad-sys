package br.com.cadsys.exception;

public class UserUnAuthorizedException extends Exception {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -2894532774915123448L;
	
	public UserUnAuthorizedException() {
		
	}
	
	public UserUnAuthorizedException(String mensagem) {
		super(mensagem);
	}

}
