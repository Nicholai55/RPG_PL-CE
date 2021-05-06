package projetoRpgWeb.usuario.testando;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import projetoRpgWeb.usuario.EntidadeUsuario;



@RequiredArgsConstructor
@Service
public class ServicoUsuario {

	private final RepositorioUsuario userRepository;
	
	public List<EntidadeUsuario> lista() {
		return userRepository.findAll();
	}

	public String criar(EntidadeUsuario user) {
		userRepository.save(user);
		return user.getNome();
	}

	public EntidadeUsuario findById(Long id) throws ExcessaoUserNaoEncontrado {
		return userRepository.findById(id).orElseThrow(() -> new ExcessaoUserNaoEncontrado("usuario nao encontrado"));
	}

	public String deletar(Long id) throws ExcessaoUserNaoEncontrado {
		EntidadeUsuario user = findById(id);
		userRepository.delete(user);
		return ("Usuario deletado com sucesso");
	}

	/*
	 * public String findByNome() { return null; }
	 */
}
