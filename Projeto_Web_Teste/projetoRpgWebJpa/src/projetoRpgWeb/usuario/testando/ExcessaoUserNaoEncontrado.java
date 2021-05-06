package projetoRpgWeb.usuario.testando;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExcessaoUserNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcessaoUserNaoEncontrado(String mensagem) {
		super(mensagem);
	}

}
