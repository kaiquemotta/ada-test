package br.com.ada.ifome.veiculo;

public enum TipoVeiculoEnum {
	BICICLETA("Bicicleta"), 
	MOTOCICLETA("Motocicleta"), 
	CARRO("Carro");
	
    private final String valor;

    TipoVeiculoEnum(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return valor;
    }

}
