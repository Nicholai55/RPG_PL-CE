package teste;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projetoRpgWeb.usuario.EntidadeUsuario;

@WebServlet("/Register_e_login/User")
public class UsuarioServlet extends HttpServlet {

	// quando a requisicao for GET, buscar o usuario no banco e validar se as informaoes
	// sao validas ou nao
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
		EntityManager manager = factory.createEntityManager();
		
		EntidadeUsuario user = manager.find(EntidadeUsuario.class, nome);
		
		EntidadeUsuario user2 = (EntidadeUsuario) manager.createQuery(String.format(nome)).getSingleResult();
		
		if(user.getId() != null) {
			out.println("<html>");
			out.println("<body>");
			out.println("Usuario " + user.getNome() + " buscado com sucesso");
			out.println("Usuario " + user2.getNome() + " buscado com sucesso");
			out.println("</body>");
			out.println("</html>");
		} else {
			out.println("<html>");
			out.println("<body>");
			out.println("Usuario nao encontrado");
			out.println("</body>");
			out.println("</html>");
		}

		
	}

	
	// quando a requisicao for POST, criar um usuario e salvar no banco
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		EntidadeUsuario user = new EntidadeUsuario();
		user.setNome(nome);
		user.setEmail(email);
		user.setSenha(senha);

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		manager.close();

		out.println("<html>");
		out.println("<body>");
		out.println("Usuario " + user.getNome() + " adicionado com sucesso");
		out.println("</body>");
		out.println("</html>");
	}
	
	protected EntidadeUsuario buscaPorNome(String nome) {
		return null;
		
		
	}

}
