package projetoRpgWeb.usuario;

import java.util.List;

public interface UserDao {
	
	public boolean existeUser(EntidadeUsuario user);
	
	EntidadeUsuario buscaPorId(Long id);
	List<EntidadeUsuario> lista();
	EntidadeUsuario buscaPorNome(String nome);
	
	void adiciona(EntidadeUsuario user);
	void remove(EntidadeUsuario user);

}
