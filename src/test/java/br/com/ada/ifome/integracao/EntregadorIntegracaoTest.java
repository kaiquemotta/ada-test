package br.com.ada.ifome.integracao;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ada.ifome.conta.Conta;
import br.com.ada.ifome.conta.TipoConta;
import br.com.ada.ifome.entregador.Entregador;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntregadorIntegracaoTest {
	
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void salvarDeletarEntregador() throws Exception {
    	Entregador entregador = criarEntregador();
        var entregadorJson = converterEntregadorParaJson(entregador);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/entregadores")
                        .content(entregadorJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
        
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/entregadores/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());

    }
    
    @Test
    public void naoSalvarEntregadorComCPFInvalido() throws Exception {
    	Entregador entregador = criarEntregador();
    	entregador.setCpf("765");
        var entregadorJson = converterEntregadorParaJson(entregador);

        mockMvc.perform(
                MockMvcRequestBuilders
		                .post("/entregadores")
		                .content(entregadorJson)
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }
    
    @Test
    public void naoSalvarEntregadorComRGInvalido() throws Exception {
    	Entregador entregador = criarEntregador();
    	entregador.setRg("333");
        var entregadorJson = converterEntregadorParaJson(entregador);

        mockMvc.perform(
                MockMvcRequestBuilders
		                .post("/entregadores")
		                .content(entregadorJson)
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }
    
    @Test
    public void naoSalvarEntregadorComCNHInvalido() throws Exception {
    	Entregador entregador = criarEntregador();
    	entregador.setCnh("555");
        var entregadorJson = converterEntregadorParaJson(entregador);

        mockMvc.perform(
                MockMvcRequestBuilders
		                .post("/entregadores")
		                .content(entregadorJson)
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }

	public static String converterEntregadorParaJson(final Entregador obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	private static Entregador criarEntregador() {
		var entregador = new Entregador();
		entregador.setNome("Ragnar Lothbrok");
		entregador.setCpf("12345678900");
		entregador.setRg("1234567");
		entregador.setCnh("34567658478");
		entregador.setEmail("ragnar@gmail");
		
		var conta = new Conta();
		conta.setAgencia("1129");
		conta.setNumero("7654567653");
		conta.setTipoConta(TipoConta.CORRENTE);
		conta.setInstituicaoBancaria("Santander");
		
		entregador.setConta(conta);
		
		return entregador;
	}
}
