package br.edu.cs.poo.ac.utils;

public class StringUtils {
	public static boolean estaVazia(String str) {
		if (str == null) {
			return true;
		}
		
		if (str.isEmpty()) {
			return true;
		}
		
		if (str.isBlank()) {
			return true;
		}
		
		return false;
	}
	
	public static boolean tamanhoExcedido(String str, int tamanho) {
		if (tamanho < 0) {
			return false;
		}
		
		if (str == null && tamanho == 0) {
			return false;
		} else if (str == null && tamanho > 0) {
			return true;
		}
		
		if (str.length() == tamanho) {
			return false;
		}
		
		if (str.length() < tamanho) {
			return false;
		}
				
		return true;
	}
	
	public static boolean emailValido(String email) {
		if (email == null) {
			return false;
		}
		
		if (email.contains("@")) {
			return true;
		} 
		
		return false;
	}
	
	public static boolean telefoneValido(String tel) {
		if (tel == null) {
			return false;
		}
		
		if (tel.contains("(") && tel.contains(")") && (tel.length() == 12 || tel.length() == 13)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean tamanhoMenor(String str, int tamanho) {
	    if (tamanho < 0) {
	        return false;
	    }

	    int length;
	    if (str == null) {
	        length = 0;
	    } else {
	        length = str.length();
	    }

	    if (length < tamanho) {
	        return true;
	    }
	    
	    return false;
	}
}
