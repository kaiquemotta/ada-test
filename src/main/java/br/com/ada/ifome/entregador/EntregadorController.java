package br.com.ada.ifome.entregador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.ifome.usuario.exceptions.ValidacaoException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/entregadores")
@RequiredArgsConstructor
public class EntregadorController {
	
	private final EntregadorService entregadorService;
	
    @PostMapping
    public ResponseEntity<Entregador> salvar(@RequestBody Entregador entregador) throws ValidacaoException {
        return ResponseEntity.ok(entregadorService.salvar(entregador));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
    	entregadorService.remover(id);
        return ResponseEntity.ok().build();
    }

}
