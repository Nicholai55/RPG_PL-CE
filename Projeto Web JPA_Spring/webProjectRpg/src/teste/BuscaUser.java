package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import projetoRpgWeb.usuario.EntidadeUsuario;

public class BuscaUser {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
		EntityManager manager = factory.createEntityManager();
		
		EntidadeUsuario user = manager.find(EntidadeUsuario.class, 1L);
		System.out.println(user.toString());
		
		String nome = "'Nome teste'";
		
		//EntidadeUsuario user2 = manager.find(EntidadeUsuario.class, nome);
		EntidadeUsuario user3 = (EntidadeUsuario) manager.createQuery(String.format(nome)).getSingleResult();
		
		
		//System.out.println(user2.toString());
		System.out.println(user3.toString());
		
		manager.close();
		factory.close();

	}
}
