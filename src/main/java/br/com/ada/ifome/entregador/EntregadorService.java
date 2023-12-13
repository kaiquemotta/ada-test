package br.com.ada.ifome.entregador;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.ada.ifome.usuario.exceptions.CnhInvalidoException;
import br.com.ada.ifome.usuario.exceptions.CpfInvalidoException;
import br.com.ada.ifome.usuario.exceptions.RgInvalidoException;
import br.com.ada.ifome.usuario.exceptions.ValidacaoException;
import br.com.ada.ifome.utils.ContaUtil;
import br.com.ada.ifome.utils.DocumentosUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EntregadorService {

	private final EntregadorRepository entregadorRepository;

	public Entregador salvar(Entregador entregador) throws ValidacaoException {

		if (!DocumentosUtils.validarCPF(entregador.getCpf())) {
			throw new CpfInvalidoException();
		}

		if (!DocumentosUtils.validarRG(entregador.getRg())) {
			throw new RgInvalidoException();
		}

		if (!DocumentosUtils.validarCNH(entregador.getCnh())) {
			throw new CnhInvalidoException();
		}

		ContaUtil.validarContaBancaria(entregador.getConta());

		return entregadorRepository.save(entregador);

	}

	public Entregador buscarPorId(Long id) {
		return entregadorRepository.findById(id).orElseThrow(EntregadorNotFoundException::new);
	}

	public void remover(Long id) {
		var entregador = this.buscarPorId(id);
		entregadorRepository.delete(entregador);
	}

}
