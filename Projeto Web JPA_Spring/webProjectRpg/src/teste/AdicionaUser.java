package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import projetoRpgWeb.usuario.EntidadeUsuario;

public class AdicionaUser {

	public static void main(String[] args) {
		
		EntidadeUsuario user = new EntidadeUsuario(null, "Nome teste", "nome@teste.com", "SenhaSenha");
				
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		manager.close();

	}
}
