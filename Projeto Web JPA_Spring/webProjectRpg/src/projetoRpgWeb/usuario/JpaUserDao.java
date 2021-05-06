package projetoRpgWeb.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class JpaUserDao implements UserDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void adiciona(EntidadeUsuario user) {
		manager.persist(user);

	}

	@Override
	public void remove(EntidadeUsuario user) {
		manager.remove(user);

	}

	@Override
	public List<EntidadeUsuario> lista() {
		return manager.createQuery("select t from usuarios t").getResultList();

	}

	@Override
	public EntidadeUsuario buscaPorId(Long id) {
		return manager.find(EntidadeUsuario.class, id);
	}

	@Override
	public EntidadeUsuario buscaPorNome(String nome) {
		return manager.find(EntidadeUsuario.class, nome);
	}

	@Override
	public boolean existeUser(EntidadeUsuario user) {
		List<EntidadeUsuario> lista = manager
				.createQuery("select u from usuarios u " + "where u.nome = :nome and u.senha = :senha",
						EntidadeUsuario.class)
				.setParameter("nome", user.getNome()).setParameter("senha", user.getSenha()).getResultList();

		if (lista.isEmpty())
			return false;
		else
			return true;
	}
}
