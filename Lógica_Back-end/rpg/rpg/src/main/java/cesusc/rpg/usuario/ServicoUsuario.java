package cesusc.rpg.usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import cesusc.rpg.excessoes.ExcessaoUserNaoEncontrado;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoUsuario {

	private final RepositorioUsuario userRepository;

	public List<EntidadeUsuario> lista() {
		return userRepository.findAll();
	}

	public EntidadeUsuario criar(EntidadeUsuario user) {
		return userRepository.save(user);
	}

	public EntidadeUsuario findById(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new ExcessaoUserNaoEncontrado("Usuario não encontrado."));		
	}
}
