
package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.Users;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseUsersDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO users( Token_id, Mobile, Email, User_name, Password, Status1, Status2, Status3, Status4) values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final Users users) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(users.getUserId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"UserId"});
	ps.setString(1, users.getTokenId());
ps.setString(2, users.getMobile());
ps.setString(3, users.getEmail());
ps.setString(4, users.getUser_name());
ps.setString(5, users.getPassword());
ps.setString(6, users.getStatus1());
ps.setString(7, users.getStatus2());
ps.setString(8, users.getStatus3());
ps.setString(9, users.getStatus4());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				users.setUserId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE users  set Token_id = ? ,Mobile = ? ,Email = ? ,User-name = ? ,Password = ? ,Status1 = ? ,Status2 = ? ,Status3 = ? ,Status4 = ?  where User_id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{users.getTokenId(),users.getMobile(),users.getEmail(),users.getUser_name(),users.getPassword(),users.getStatus1(),users.getStatus2(),users.getStatus3(),users.getStatus4(),users.getUserId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM users WHERE User_id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public Users getByUserId(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from users where User_id = ? ";
			List<Users> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(Users.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
