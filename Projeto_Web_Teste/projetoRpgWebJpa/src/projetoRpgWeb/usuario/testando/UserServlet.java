package projetoRpgWeb.usuario.testando;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import projetoRpgWeb.usuario.EntidadeUsuario;


@Data
@WebServlet("/usuarios")
public class UserServlet extends HttpServlet{
	private ServicoUsuario userService;
	private  RepositorioUsuario userRepository;
		
	public void init() {
		userService = new ServicoUsuario(userRepository);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		listaUsuarios(request, response);
	}
	
	private void listaUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<EntidadeUsuario> lista = userService.lista();
		request.setAttribute("listaUsuarios", lista);
		RequestDispatcher disp = request.getRequestDispatcher("listaUser.htlm");
		disp.forward(request, response);		
	}
	
//	
//	private void busca(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		EntidadeUsuario user = userService.findById(id);
//		
		
	}
	
	
	
