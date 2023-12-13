package br.com.ada.ifome.veiculo;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ada.ifome.usuario.exceptions.VeiculoDataModeloInvalidaException;
import br.com.ada.ifome.usuario.exceptions.VeiculoPlacaInvalidaException;
import br.com.ada.ifome.usuario.exceptions.VeiculoRenavamInvalidoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VeiculoService {
	
	private final VeiculoRepository veiculoRepository;

	private static final String PLACA_PATTERN = "[A-Z]{3}[0-9]{3}";
	private static final String RENAVAM_PATTERN = "^[0-9]{11}$";
	
	@Transactional
	public Veiculo salvar(Veiculo veiculo) throws VeiculoDataModeloInvalidaException, VeiculoPlacaInvalidaException, VeiculoRenavamInvalidoException {
		validarSituacaoVeiculo(veiculo);
		return veiculoRepository.save(veiculo);
	}

	public static void validarDataModelo(Veiculo veiculo) throws VeiculoDataModeloInvalidaException {
		int anoModelo = Integer.parseInt(veiculo.getDataModelo().substring(4, 8));
		if (anoModelo < 2010) {
			throw new VeiculoDataModeloInvalidaException("Ano do modelo deve ser igual ou superior a 2010");
		}
	}

	public static void validarPlaca(Veiculo veiculo) throws VeiculoPlacaInvalidaException {
		if (!veiculo.getPlaca().matches(PLACA_PATTERN)) {
			throw new VeiculoPlacaInvalidaException("Placa deve seguir o padrão [A-Z]{3}[0-9]{3}");
		}
	}

	public static void validarRenavam(Veiculo veiculo) throws VeiculoRenavamInvalidoException {
		if (!veiculo.getRenavam().matches(RENAVAM_PATTERN)) {
			throw new VeiculoRenavamInvalidoException("RENAVAM deve ser um número de 11 dígitos");
		}
	}

	public static boolean validarSituacaoVeiculo(Veiculo veiculo)
			throws VeiculoDataModeloInvalidaException, VeiculoPlacaInvalidaException, VeiculoRenavamInvalidoException {
		validarDataModelo(veiculo);
		validarPlaca(veiculo);
		validarRenavam(veiculo);
		return true;
	}

}
