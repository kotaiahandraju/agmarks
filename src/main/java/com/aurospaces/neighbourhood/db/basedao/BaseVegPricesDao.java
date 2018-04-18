
package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.VegPrices;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseVegPricesDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO veg_prices( State, District, Market, Vegetables, Local_rate, Date) values (?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final VegPrices vegPrices) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(vegPrices.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(vegPrices.getDate() == null)
					{
					vegPrices.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(vegPrices.getDate().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, vegPrices.getState());
ps.setString(2, vegPrices.getDistrict());
ps.setString(3, vegPrices.getMarket());
ps.setString(4, vegPrices.getVegetables());
ps.setString(5, vegPrices.getLocalRate());
ps.setTimestamp(6, Date);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				vegPrices.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE veg_prices  set State = ? ,District = ? ,Market = ? ,Vegetables = ? ,Local_rate = ? ,Date = ?  where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{vegPrices.getState(),vegPrices.getDistrict(),vegPrices.getMarket(),vegPrices.getVegetables(),vegPrices.getLocalRate(),vegPrices.getDate(),vegPrices.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM veg_prices WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public VegPrices getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from veg_prices where Id = ? ";
			List<VegPrices> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(VegPrices.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
