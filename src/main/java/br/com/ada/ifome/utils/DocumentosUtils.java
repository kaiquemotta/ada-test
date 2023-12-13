package br.com.ada.ifome.utils;

public class DocumentosUtils {
	
    private static final String CPF_PATTERN = "^[0-9]{11}$";
    private static final String RG_PATTERN = "^[0-9]{7}$";
    private static final String CNH_PATTERN = "^[0-9]{11}$";

    public static boolean validarCPF(String cpf) {
        return cpf.matches(CPF_PATTERN);
    }

    public static boolean validarRG(String rg) {
        return rg.matches(RG_PATTERN);
    }

    public static boolean validarCNH(String cnh) {
        return cnh.matches(CNH_PATTERN);
    }

}
