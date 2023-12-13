package br.com.ada.ifome.conta;

public enum TipoConta {
	
	CORRENTE("Corrente"), 
	POUPANCA("Poupanca");
	
    private final String valor;

    TipoConta(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return valor;
    }

}
