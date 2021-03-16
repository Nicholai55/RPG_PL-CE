package cesusc.rpg.usuario.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cesusc.rpg.usuario.EntidadeUsuario;

public class UserRowMapper implements RowMapper<EntidadeUsuario>{
	
	@Override
	public EntidadeUsuario mapRow(ResultSet rs, int arg) throws SQLException {
		EntidadeUsuario user = new EntidadeUsuario();
		user.setNome(rs.getString("nome"));
		user.setSenha(rs.getString("senha"));
		return user;
		
	}

}
