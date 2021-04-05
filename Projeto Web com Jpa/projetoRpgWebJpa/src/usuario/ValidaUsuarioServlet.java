package usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register_e_login/validaUser")
public class ValidaUsuarioServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
		EntityManager manager = factory.createEntityManager();

		TypedQuery<EntidadeUsuario> query = manager.createQuery("SELECT u FROM EntidadeUsuario u",
				EntidadeUsuario.class);
		List<EntidadeUsuario> lista = query.getResultList();

		EntidadeUsuario usuarioCorreto = new EntidadeUsuario();

		for (EntidadeUsuario user : lista) {
			if (user.getNome().equals(nome)) {
				if (user.getSenha().equals(senha)) {
					usuarioCorreto.setNome(user.getNome());
					usuarioCorreto.setSenha(user.getSenha());
					break;
				}
			}
		}
		if (usuarioCorreto.getNome() == null) {
			out.println("<html>");
			out.println("<body>");
			out.println("Nao foi possivel encontrar o usuario");
			out.println("</body>");
			out.println("</html>");
		} else {
			out.println("<html>");
			out.println("<body>");
			out.println("Usuario " + usuarioCorreto.getNome() + " logado com sucesso");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
