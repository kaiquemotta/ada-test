package br.com.ada.ifome.utils;

import java.util.Set;
import java.util.regex.Pattern;

import br.com.ada.ifome.conta.Conta;
import br.com.ada.ifome.conta.TipoConta;
import br.com.ada.ifome.usuario.exceptions.ValidacaoException;

public class ContaUtil {
	
    private static final Pattern AGENCIA_PATTERN = Pattern.compile("\\d{4}");
    private static final Pattern CONTA_PATTERN = Pattern.compile("\\d{10}");
    private static final Set<TipoConta> TIPOS_CONTA_VALIDOS = Set.of(TipoConta.CORRENTE, TipoConta.POUPANCA);
    private static final Set<String> INSTITUICOES_BANCARIA_VALIDAS = Set.of("Bradesco", "Itau", "Santander");

    public static void validarContaBancaria(Conta contaBancaria) throws ValidacaoException {
        validarAgencia(contaBancaria.getAgencia());
        validarNumero(contaBancaria.getNumero());
        validarTipoConta(contaBancaria.getTipoConta());
        validarInstituicaoBancaria(contaBancaria.getInstituicaoBancaria());
    }

    private static void validarAgencia(String agencia) throws ValidacaoException {
        if (!AGENCIA_PATTERN.matcher(agencia).matches()) {
            throw new ValidacaoException("Agência deve conter 4 dígitos numéricos.");
        }
    }

    private static void validarNumero(String numero) throws ValidacaoException {
        if (!CONTA_PATTERN.matcher(numero).matches()) {
            throw new ValidacaoException("Numero deve conter 10 dígitos numéricos.");
        }
    }

    private static void validarTipoConta(TipoConta tipoConta) throws ValidacaoException {
        if (!TIPOS_CONTA_VALIDOS.contains(tipoConta)) {
            throw new ValidacaoException("Tipo de conta inválido.");
        }
    }

    private static void validarInstituicaoBancaria(String instituicaoBancaria) throws ValidacaoException {
        if (!INSTITUICOES_BANCARIA_VALIDAS.contains(instituicaoBancaria)) {
            throw new ValidacaoException("Instituição bancária inválida.");
        }
    }

}
