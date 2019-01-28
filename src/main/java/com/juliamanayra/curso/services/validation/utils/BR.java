package com.juliamanayra.curso.services.validation.utils;

public class BR {
	
	private static final int[] pesoCpf = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private static final int[] pesoCnpj = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	
	private static int calculo(final String str, final int[] peso) {
        int cont = 0;
        for (int i = str.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            cont += digit * peso[peso.length - str.length() + i];
        }
        cont = 11 - cont % 11;
        return cont > 9 ? 0 : cont;
	}
	
	 /**
     * Valida CPF
     *
     * @param ssn
     * @return
     */
	
	public static boolean isValidCpf(final String cpf) {
		  if ((cpf == null) || (cpf.length() != 11) || cpf.matches(cpf.charAt(0) + "{11}")) return false;

	        final Integer digit1 = calculo(cpf.substring(0, 9), pesoCpf);
	        final Integer digit2 = calculo(cpf.substring(0, 9) + digit1, pesoCpf);
	        return cpf.equals(cpf.substring(0, 9) + digit1.toString() + digit2.toString());
		
	}
	/**
     * Valida CNPJ
     *
     * @param tin
     * @return
     */

	public static boolean isValidCnpj(final String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14) || cnpj.matches(cnpj.charAt(0) + "{14}")) return false;

        final Integer digit1 = calculo(cnpj.substring(0, 12), pesoCnpj);
        final Integer digit2 = calculo(cnpj.substring(0, 12) + digit1, pesoCnpj);
        return cnpj.equals(cnpj.substring(0, 12) + digit1.toString() + digit2.toString());
		
	}
}
