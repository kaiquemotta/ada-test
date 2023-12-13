package br.com.ada.ifome.veiculo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Veiculo {
	
    @Id
    private Long id;
    private String renavam;
    
    @Enumerated(EnumType.STRING)
    private TipoVeiculoEnum tipo;
    private String marca;
    private String modelo;
    private String dataModelo;
    private String placa;
    private boolean ativo;


}
