package br.edu.cs.poo.ac.utils;

public class ValidadorCPFCNPJ {
	
	public static ResultadoValidacaoCPFCNPJ validarCPFCNPJ(String cpfCnpj) {
		if (cpfCnpj == null || cpfCnpj.isBlank()) {
			return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ);
		}
		
		if (cpfCnpj.length() == 11) {
			if (isDigitoVerificadorValidoCPF(cpfCnpj)) {
				return new ResultadoValidacaoCPFCNPJ(true, false, null);
			} else {
				return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO);
			}
		}
		
		if (cpfCnpj.length() == 14) {
			if (isDigitoVerificadorValidoCNPJ(cpfCnpj)) {
				return new ResultadoValidacaoCPFCNPJ(false, true, null);
			} else {
				return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO);
			}
		}
		
		return new ResultadoValidacaoCPFCNPJ(false, false, ErroValidacaoCPFCNPJ.CPF_CNPJ_NAO_E_CPF_NEM_CNPJ);

	}
	
	public static boolean isCPF(String valor) {
		if (valor.length() == 11) {
			return true;
		} 
		
		return false;
	}
	
	public static boolean isCNPJ(String valor) {
		if (valor.length() == 14) {
			return true;
		} 
		
		return false;
	}
	
	public static ErroValidacaoCPFCNPJ validarCPF(String cpf) {
	    if (cpf == null || cpf.isEmpty()) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_NULO_OU_BRANCO;
	    }

	    if (!isCPF(cpf)) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_TAMANHO_INVALIDO;
	    }

	    if (!isDigitoVerificadorValidoCPF(cpf)) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO;
	    }

	    return null;
	}

	public static ErroValidacaoCPFCNPJ validarCNPJ(String cnpj) {
	    if (cnpj == null || cnpj.isEmpty()) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_NULO_OU_BRANCO;
	    }

	    if (!isCNPJ(cnpj)) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_TAMANHO_INVALIDO;
	    }

	    if (!isDigitoVerificadorValidoCNPJ(cnpj)) {
	        return ErroValidacaoCPFCNPJ.CPF_CNPJ_COM_DV_INVALIDO;
	    }

	    return null;
	}

	
	private static boolean isDigitoVerificadorValidoCPF(String cpf) {
		if (isCPF(cpf)) {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }
            
            int digito1 = (soma * 10) % 11;
            
            if (digito1 == 10) {
                digito1 = 0;
            }

            if (digito1 != (cpf.charAt(9) - '0')) {
                return false;
            }
            
            soma = 0;
            
            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }
            
            int digito2 = (soma * 10) % 11;
            
            if (digito2 == 10) {
                digito2 = 0;
            }
            
            if (digito2 != (cpf.charAt(10) - '0')) {
                return false;
            }
        
            return true;
        }

		return false;
    }

	private static boolean isDigitoVerificadorValidoCNPJ(String cnpj) {
		if (isCNPJ(cnpj)) {
            int[] pesos1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
            int[] pesos2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += (cnpj.charAt(i) - '0') * pesos1[i];
            }
            int resto = soma % 11;
            int digito1;
            if (resto < 2) {
                digito1 = 0;
            } else {
                digito1 = 11 - resto;
            }
            if (digito1 != (cnpj.charAt(12) - '0')) {
                return false;
            }

            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += (cnpj.charAt(i) - '0') * pesos2[i];
            }
            resto = soma % 11;
            int digito2;
            if (resto < 2) {
                digito2 = 0;
            } else {
                digito2 = 11 - resto;
            }
            if (digito2 != (cnpj.charAt(13) - '0')) {
                return false;
            }
            return true;
        }
        return false;
    }
	
}
