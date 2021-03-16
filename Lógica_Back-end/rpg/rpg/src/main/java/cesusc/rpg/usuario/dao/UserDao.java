package cesusc.rpg.usuario.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import cesusc.rpg.usuario.EntidadeUsuario;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Data
public class UserDao {

	private NamedParameterJdbcTemplate template;

	public UserDao(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	public List<EntidadeUsuario> findAll() {
		return template.query("select * from user", new UserRowMapper());
	}

	public void insertUser(EntidadeUsuario user) {
		final String sql = "insert into user(nome, senha) values(:nome,:senha)";

		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("nome", user.getNome())
				.addValue("senha", user.getSenha());
		template.update(sql, param, holder);
	
	}
	
	
}
