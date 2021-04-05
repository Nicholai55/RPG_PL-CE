package teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import usuario.EntidadeUsuario;

public class busca_usuario {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");

		EntityManager manager = factory.createEntityManager();

		EntidadeUsuario usuario = manager.find(EntidadeUsuario.class, 1L);

		System.out.println(usuario.getNome());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getSenha());

		TypedQuery<EntidadeUsuario> query = manager.createQuery("SELECT u FROM EntidadeUsuario u",
				EntidadeUsuario.class);
		List<EntidadeUsuario> lista = query.getResultList();

		System.out.println(lista);

		for (EntidadeUsuario user : lista) {
			System.out.println(user);
			System.out.println(user.getNome());
			System.out.println(user.getEmail());
			System.out.println(user.getSenha());
			System.out.println("");
		}

		String nome = "vini0410";
		String senha = "vini0410";

		for (EntidadeUsuario user : lista) {
			if (user.getNome().equals(nome)) {
				System.out.println(true + nome + user.getNome());
				if (user.getSenha().equals(senha)) {
					System.out.println(true + senha + user.getSenha());
				} else {
					System.out.println(false + senha + user.getSenha());
				}
			} else {
				System.out.println(false + nome + user.getNome());
			}
		}

		EntidadeUsuario usuarioCorreto = new EntidadeUsuario();

		for (EntidadeUsuario user : lista) {
			if (user.getNome().equals(nome)) {
				if (user.getSenha().equals(senha)) {
					usuarioCorreto.setNome(user.getNome());
					usuarioCorreto.setSenha(user.getSenha());
					System.out.println("achoooou");
					System.out.println(usuarioCorreto);
				}
			}
			if (usuarioCorreto.getNome() == null) {
				System.out.println("nao foi possivel encontrar um usuario");
			} else {
				System.out.println("Usuario " + usuarioCorreto.getNome() + " encontrado");
				break;
			}

			manager.close();
			factory.close();

		}

	}
}
