package br.com.ada.ifome.veiculo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ada.ifome.usuario.exceptions.VeiculoDataModeloInvalidaException;
import br.com.ada.ifome.usuario.exceptions.VeiculoPlacaInvalidaException;
import br.com.ada.ifome.usuario.exceptions.VeiculoRenavamInvalidoException;

@SpringBootTest
public class VeiculoTeste {

	@InjectMocks
	private VeiculoService veiculoService;

	@Mock
	private VeiculoRepository veiculoRepository;

	@Test
	public void salvarDeveLancarVeiculoDataModeloInvalidaExceptionQuandoAnoModeloInvalido()
			throws VeiculoPlacaInvalidaException, VeiculoRenavamInvalidoException {
		Veiculo veiculo = new Veiculo();
		veiculo.setDataModelo("01012009");

		assertThrows(VeiculoDataModeloInvalidaException.class, () -> veiculoService.salvar(veiculo));
	}

	@Test
	public void salvarDeveLancarVeiculoPlacaInvalidaExceptionQuandoPlacaInvalida()
			throws VeiculoDataModeloInvalidaException, VeiculoRenavamInvalidoException {
		Veiculo veiculo = new Veiculo();
		veiculo.setDataModelo("01012010");
		veiculo.setPlaca("ABC1234");

		assertThrows(VeiculoPlacaInvalidaException.class, () -> veiculoService.salvar(veiculo));
	}

	@Test
	public void salvarDeveLancarVeiculoRenavamInvalidoExceptionQuandoRenavamInvalido()
			throws VeiculoDataModeloInvalidaException, VeiculoPlacaInvalidaException {
		Veiculo veiculo = new Veiculo();
		veiculo.setDataModelo("01012015");
		veiculo.setPlaca("ABC123");
		veiculo.setRenavam("1234567890");

		assertThrows(VeiculoRenavamInvalidoException.class, () -> veiculoService.salvar(veiculo));

	}

	@Test
	public void salvarDeveSalvarVeiculoQuandoValidacoesEstiveremCorretas()
			throws VeiculoDataModeloInvalidaException, VeiculoPlacaInvalidaException, VeiculoRenavamInvalidoException {
		Veiculo veiculo = new Veiculo();
		veiculo.setDataModelo("01012015");
		veiculo.setPlaca("ABC123");
		veiculo.setRenavam("12345678901");

		veiculoService.salvar(veiculo);

		verify(veiculoRepository, Mockito.times(1)).save(veiculo);
	}

}
