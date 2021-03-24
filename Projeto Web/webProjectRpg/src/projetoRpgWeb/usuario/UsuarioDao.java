package projetoRpgWeb.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;

public class UsuarioDao {

	private Connection connection;

	public UsuarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(EntidadeUsuario user) {
		String sqlQuery = "insert into usuarios" + "(nome, email, senha)" + "values (?, ?, ?)";

		try {
			PreparedStatement state = connection.prepareStatement(sqlQuery);

			state.setString(1, user.getNome());
			state.setString(2, user.getEmail());
			state.setString(3, user.getSenha());

			state.execute();
			state.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<EntidadeUsuario> getLista() {

		PreparedStatement state;
		try {
			List<EntidadeUsuario> usuarios = new ArrayList<EntidadeUsuario>();

			state = this.connection.prepareStatement("select * from usuarios");
			ResultSet result = state.executeQuery();

			while (result.next()) {
				
				EntidadeUsuario user = new EntidadeUsuario();
				user.builder().nome(result.getString("nome")).email(result.getString("email")).build();
				
				usuarios.add(user);
			}
			result.close();
			state.close();
			return usuarios;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
