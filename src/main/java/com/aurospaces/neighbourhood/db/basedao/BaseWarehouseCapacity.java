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

import com.aurospaces.neighbourhood.bean.AddProduct;
import com.aurospaces.neighbourhood.bean.WarehouseCapacity;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseWarehouseCapacity {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
 
	public final String INSERT_SQL = "INSERT INTO warehouse_cap(Token_id,Total_capacity,Occupancy,Avail_capacity,Ask_price,Date ) values (?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final WarehouseCapacity warehouseCapacity) 
	{
		
		
		jdbcTemplate = custom.getJdbcTemplate();
	if(warehouseCapacity.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
										
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setString(1, warehouseCapacity.getTokenId());
ps.setString(2,warehouseCapacity.getTotalCapacity());
ps.setString(3, warehouseCapacity.getOccupancy());
ps.setString(4,warehouseCapacity.getAvailCapacity());
ps.setString(5,warehouseCapacity.getAskPrice());
ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				warehouseCapacity.setSNo(unId.intValue());
				

		}
	
	
	
		else
		{

			String sql = "UPDATE warehouse_cap set Token_id = ? ,Total_capacity = ? ,Occupancy = ? ,Avail_capacity = ? ,Ask_price = ? , where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{ warehouseCapacity.getTokenId(),warehouseCapacity.getTotalCapacity(), warehouseCapacity.getOccupancy(),warehouseCapacity.getAvailCapacity(),warehouseCapacity.getAskPrice(),warehouseCapacity.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  warehouse_cap WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public WarehouseCapacity getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from warehouse_cap where SNo = ? ";
			List<WarehouseCapacity> retlist = jdbcTemplate.query(sql,new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(WarehouseCapacity.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
