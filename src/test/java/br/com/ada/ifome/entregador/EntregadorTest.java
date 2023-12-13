package br.com.ada.ifome.entregador;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ada.ifome.conta.Conta;
import br.com.ada.ifome.conta.TipoConta;
import br.com.ada.ifome.usuario.exceptions.CnhInvalidoException;
import br.com.ada.ifome.usuario.exceptions.CpfInvalidoException;
import br.com.ada.ifome.usuario.exceptions.RgInvalidoException;
import br.com.ada.ifome.usuario.exceptions.ValidacaoException;

@SpringBootTest
public class EntregadorTest {
	
	@InjectMocks
	private EntregadorService entregadorService;
	
	@Mock
	private EntregadorRepository  entregadorRepository;
	
    @Test
    public void entregadorCpfInvalidoComLetra() {
        var entregador = new Entregador();
        entregador.setCpf("1234567891e");
        assertThrows(CpfInvalidoException.class, () -> entregadorService.salvar(entregador));
    }

    @Test
    public void entregadorCpfInvalidoCom12Digitos() {
        var usuario = new Entregador();
        usuario.setCpf("123456789123");
        assertThrows(CpfInvalidoException.class, () -> entregadorService.salvar(usuario));
    }
    
    @Test
    public void entregadorRGInvalidoComLetra() {
        var entregador = new Entregador();
        entregador.setCpf("04455566633");
        entregador.setRg("123491e");
        assertThrows(RgInvalidoException.class, () -> entregadorService.salvar(entregador));
    }
    
    @Test
    public void entregadorCNHInvalidoTamanho() {
        var entregador = new Entregador();
        entregador.setRg("1236547");
        entregador.setCpf("04455566633");
        entregador.setCnh("123456789102");
        assertThrows(CnhInvalidoException.class, () -> entregadorService.salvar(entregador));
    }


    @Test
    public void entregadorComDocumentosValidos() throws ValidacaoException {
    	var conta = new Conta();
    	conta.setAgencia("1234");
    	conta.setNumero("6453726485");
    	conta.setInstituicaoBancaria("Santander");
    	conta.setTipoConta(TipoConta.CORRENTE);
    	
        var entregador = new Entregador();
        entregador.setCpf("04455566633");
        entregador.setCnh("43274866600");
        entregador.setRg("1236547");
        
        entregador.setConta(conta);
        
        when(entregadorRepository.save(any())).thenReturn(entregador);
        var usuarioSalvo = entregadorService.salvar(entregador);

        assertNotNull(usuarioSalvo);
        verify(entregadorRepository, Mockito.times(1)).save(entregador);
    }

}
