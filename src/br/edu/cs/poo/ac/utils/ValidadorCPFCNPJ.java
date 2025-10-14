package br.edu.cs.poo.ac.utils;

public class ValidadorCPFCNPJ {
	public static ResultadoValidacaoCPFCNPJ validarCPFCNPJ(String cpfCnpj) {
		if (cpfCnpj == null || cpfCnpj.isBlank()) {
			return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ);
		}
		
		if (cpfCnpj.length() == 11) {
			if (isCPF(cpfCnpj)) {
				return new ResultadoValidacaoCPFCNPJ(true, false, null);
			} else {
				return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO);
			}
		}
		
		if (cpfCnpj.length() == 14) {
			if (isCNPJ(cpfCnpj)) { 
				return new ResultadoValidacaoCPFCNPJ(false, true, null);
			} else {
				return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO);
			}
		}
		
		return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ);
	}
	
	public static boolean isCPF(String valor) {
		return isDigitoVerificadorValidoCPF(valor);
	}
	
	public static boolean isCNPJ(String valor) {
		return isDigitoVerificadorValidoCNPJ(valor);
	}
	
	public static ErroValidacaoCPFCNPJ validarCPF(String cpf) {
	    if (cpf == null || cpf.isEmpty()) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_NULO_OU_BRANCO;
	    }
	    if (!isCPF(cpf)) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO;
	    }
	    return null;
	}

	public static ErroValidacaoCPFCNPJ validarCNPJ(String cnpj) {
	    if (cnpj == null || cnpj.isEmpty()) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_NULO_OU_BRANCO;
	    }
	    if (!isCNPJ(cnpj)) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO;
	    }
	    return null;
	}

	
	private static boolean isDigitoVerificadorValidoCPF(String cpf) {
		if (cpf == null || cpf.length() != 11) {
            return false;
        }

        char primeiroDigito = cpf.charAt(0);
        boolean todosIguais = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != primeiroDigito) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 >= 10) {
            digito1 = 0;
        }
        if (digito1 != (cpf.charAt(9) - '0')) {
            return false;
        }
        
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 >= 10) {
            digito2 = 0;
        }
        if (digito2 != (cpf.charAt(10) - '0')) {
            return false;
        }
    
        return true;
    }

	private static boolean isDigitoVerificadorValidoCNPJ(String cnpj) {
		if (cnpj == null || cnpj.length() != 14) {
            return false;
        }
        
        int[] pesos1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] pesos2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos1[i];
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;
        
        if (digito1 != (cnpj.charAt(12) - '0')) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * pesos2[i];
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        if (digito2 != (cnpj.charAt(13) - '0')) {
            return false;
        }
        return true;
    }
}