package br.com.ada.ifome;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ada.ifome.usuario.exceptions.BusinessException;
import br.com.ada.ifome.usuario.exceptions.CnhInvalidoException;
import br.com.ada.ifome.usuario.exceptions.CpfInvalidoException;
import br.com.ada.ifome.usuario.exceptions.RgInvalidoException;

@ControllerAdvice
public class CustomExceptionHandler {
	
    @ExceptionHandler({CpfInvalidoException.class, CnhInvalidoException.class, RgInvalidoException.class } )
    public ResponseEntity<String> captureDocumentNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Void> captureBusinessException() {
        return ResponseEntity.badRequest().build();
    }

}
