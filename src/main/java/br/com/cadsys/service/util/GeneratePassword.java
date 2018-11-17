package br.com.cadsys.service.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author aishac
 * 
 * Classe utilitária para auxilio na criptografia e descriptografia da senha.
 *
 */
public class GeneratePassword {
	
	/**
	 * @param password
	 * @return
	 */
	public static String cryptPassword(String password) {		
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	/**
	 * @param password
	 * @param hashPassword
	 * @return
	 */
	public static Boolean checkPass(String password, String hashPassword) {
		if (BCrypt.checkpw(password, hashPassword)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
//	public static void main(String args[]) {
//		String senhaGerada = BCrypt.hashpw("123", BCrypt.gensalt());
//		
//		System.out.println("SENHA GERADA \n");
//		
//		System.out.println(senhaGerada);
//		
//		System.out.println("SENHA descriptografada \n");
//		
//		if (BCrypt.checkpw("1233", "$2a$10$ygi0n6haIUsVBSr3bfHraOxK2tMrgwUYqtYODu81Ce1wjSToe2ntu")) {
//			System.out.println("senha correta");
//		} else {
//			System.out.println("senha incorreta");
//		}
//	}

}
