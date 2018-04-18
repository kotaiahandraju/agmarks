
package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.Stor;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseStorDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO stor( State, District, Storage_name, Address, Pincode, capacity) values (?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final Stor stor) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(stor.getStorageName() == null)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL);
	ps.setString(1, stor.getState());
ps.setString(2, stor.getDistrict());
stor.setStorageName(UUID.randomUUID().toString());
		ps.setString(3, stor.getStorageName());
ps.setString(4, stor.getAddress());
ps.setInt(5, stor.getPincode());
ps.setInt(6, stor.getCapacity());

							return ps;
						}
				});
				

		}
		else
		{

			String sql = "UPDATE stor  set State = ? ,District = ? ,Address = ? ,Pincode = ? ,capacity = ?  where  = ? ";
	
			jdbcTemplate.update(sql, new Object[]{stor.getState(),stor.getDistrict(),stor.getAddress(),stor.getPincode(),stor.getCapacity()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM stor WHERE =?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public Stor getBy(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from stor where  = ? ";
			List<Stor> retlist = jdbcTemplate.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(Stor.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
