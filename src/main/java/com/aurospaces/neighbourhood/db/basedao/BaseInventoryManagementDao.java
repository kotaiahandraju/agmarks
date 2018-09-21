
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

import com.aurospaces.neighbourhood.bean.InventoryManagement;
import com.aurospaces.neighbourhood.bean.PlantClinic;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseInventoryManagementDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO inventory_management( Proc_name, Mobile, Token_Id, Inventory_type, Img_name, Status, Comment, Date,Transaction_type) values (?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final InventoryManagement inventoryManagement) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(inventoryManagement.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(inventoryManagement.getDate() == null)
					{
					inventoryManagement.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(inventoryManagement.getDate().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, inventoryManagement.getProcName());
ps.setString(2, inventoryManagement.getMobile());
ps.setString(3, inventoryManagement.getTokenId());
ps.setString(4, inventoryManagement.getInventoryType());
ps.setString(5, inventoryManagement.getImgName());
ps.setString(6, inventoryManagement.getStatus());
ps.setString(7, inventoryManagement.getComment());
ps.setTimestamp(8, Date);
ps.setString(9, inventoryManagement.getTransactionType());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				inventoryManagement.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE inventory_management  set Farmer_name = ? ,Mobile = ? ,Token_Id = ? ,Type = ? ,Status = ? ,Comment = ? ,Transaction_type =? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{inventoryManagement.getProcName(),inventoryManagement.getMobile(),inventoryManagement.getTokenId(),inventoryManagement.getInventoryType(),inventoryManagement.getStatus(),inventoryManagement.getComment(),inventoryManagement.getTransactionType(),inventoryManagement.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM inventory_management WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public InventoryManagement getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from inventory_management where S_no = ? ";
			List<InventoryManagement> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(InventoryManagement.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
