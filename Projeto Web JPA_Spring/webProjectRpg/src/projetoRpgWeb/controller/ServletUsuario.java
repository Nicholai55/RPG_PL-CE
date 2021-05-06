package projetoRpgWeb.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import projetoRpgWeb.usuario.EntidadeUsuario;
import projetoRpgWeb.usuario.JpaUserDao;

@WebServlet("/Register_e_login/adicionaUser")
public class ServletUsuario extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		EntidadeUsuario user = EntidadeUsuario.builder().nome(nome).email(email).senha(senha).build();

		JpaUserDao dao = new JpaUserDao();
		dao.adiciona(user);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		
		EntidadeUsuario user = EntidadeUsuario.builder().nome(nome).senha(senha).build();
		
		JpaUserDao dao = new JpaUserDao();
		if(dao.existeUser(user) == true) {
			System.out.println("logado com sucesso");
		}
	}
}