
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

import com.aurospaces.neighbourhood.bean.PlantClinic;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BasePlantClinicDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO plant_clinic( Farmer_name, Mobile, Token_Id, Type, Img_name, Status, Comment, Date,Transaction_type) values (?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final PlantClinic plantClinic) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(plantClinic.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(plantClinic.getDate() == null)
					{
					plantClinic.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(plantClinic.getDate().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, plantClinic.getFarmerName());
ps.setString(2, plantClinic.getMobile());
ps.setString(3, plantClinic.getTokenId());
ps.setString(4, plantClinic.getType());
ps.setString(5, plantClinic.getImgName());
ps.setString(6, plantClinic.getStatus());
ps.setString(7, plantClinic.getComment());
ps.setTimestamp(8, Date);
ps.setString(9, plantClinic.getTransactionType());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				plantClinic.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE plant_clinic  set Farmer_name = ? ,Mobile = ? ,Token_Id = ? ,Type = ? ,Status = ? ,Comment = ? ,Transaction_type =? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{plantClinic.getFarmerName(),plantClinic.getMobile(),plantClinic.getTokenId(),plantClinic.getType(),plantClinic.getStatus(),plantClinic.getComment(),plantClinic.getTransactionType(),plantClinic.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM plant_clinic WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public PlantClinic getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from plant_clinic where S_no = ? ";
			List<PlantClinic> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(PlantClinic.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
