package projetoRpgWeb.usuario;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register_e_login/adicionaUser")
public class AdicionaUsuarioServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		EntidadeUsuario user = new EntidadeUsuario();
		user.setNome(nome);
		user.setEmail(email);
		user.setSenha(senha);

		UsuarioDao dao = new UsuarioDao();
		dao.adiciona(user);

		out.println("<html>");
		out.println("<body>");
		out.println("Usuario " + user.getNome() + " adicionado com sucesso");
		out.println("</body>");
		out.println("</html>");
	}

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

		UsuarioDao dao = new UsuarioDao();
		dao.adiciona(user);

		out.println("<html>");
		out.println("<body>");
		out.println("Usuario " + user.getNome() + " adicionado com sucesso");
		out.println("</body>");
		out.println("</html>");
	}

}
