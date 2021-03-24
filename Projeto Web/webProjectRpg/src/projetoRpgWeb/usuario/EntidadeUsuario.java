package projetoRpgWeb.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntidadeUsuario {

	private String nome;
	private String email;
	private String senha;

}
