
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

import com.aurospaces.neighbourhood.bean.LogisticsReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseLogisticsRegDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO logistics_reg( First_name, Last_name, Company_name, Date_of_incorp, Address, Status, Village, Mandal, District, State, Pincode, Mobile, Email, Vehicle_type, Vehicle_capacity, Vehicle_capcity_units, Token_id, Date_of_reg,Ccode,My_plan) values (?, ?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final LogisticsReg logisticsReg) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(logisticsReg.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(logisticsReg.getDateOfReg() == null)
					{
					logisticsReg.setDateOfReg( new Date());
					}
					java.sql.Timestamp DateOfReg = 
						new java.sql.Timestamp(logisticsReg.getDateOfReg().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, logisticsReg.getFirstName());
ps.setString(2, logisticsReg.getLastName());
ps.setString(3, logisticsReg.getCompanyName());
ps.setString(4, logisticsReg.getDateOfIncorp());
ps.setString(5, logisticsReg.getAddress());
ps.setString(6, logisticsReg.getStatus());
ps.setString(7, logisticsReg.getVillage());
ps.setString(8, logisticsReg.getMandal());
ps.setString(9, logisticsReg.getDistrict());
ps.setString(10, logisticsReg.getState());
ps.setInt(11, logisticsReg.getPincode());
ps.setString(12, logisticsReg.getMobile());
ps.setString(13, logisticsReg.getEmail());
ps.setString(14, logisticsReg.getVehicleType());
ps.setString(15, logisticsReg.getVehicleCapacity());
ps.setString(16, logisticsReg.getVehicleCapcityUnits());
ps.setString(17, logisticsReg.getTokenId());
ps.setTimestamp(18, DateOfReg);
ps.setString(19, logisticsReg.getCcode());
ps.setString(20, logisticsReg.getMyPlan());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				logisticsReg.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE logistics_reg  set First_name = ? ,Last_name = ? ,Company_name = ? ,Date_of_incorp = ? ,Address = ? ,Status = ? ,Village = ? ,Mandal = ? ,District = ? ,State = ? ,Pincode = ? ,Mobile = ? ,Email = ? ,Vehicle_type = ? ,Vehicle_capacity = ? ,Vehicle_capcity_units = ? ,Token_id = ?, Ccode =? , My_plan =?  where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{logisticsReg.getFirstName(),logisticsReg.getLastName(),logisticsReg.getCompanyName(),logisticsReg.getDateOfIncorp(),logisticsReg.getAddress(),logisticsReg.getStatus(),logisticsReg.getVillage(),logisticsReg.getMandal(),logisticsReg.getDistrict(),logisticsReg.getState(),logisticsReg.getPincode(),logisticsReg.getMobile(),logisticsReg.getEmail(),logisticsReg.getVehicleType(),logisticsReg.getVehicleCapacity(),logisticsReg.getVehicleCapcityUnits(),logisticsReg.getTokenId(),logisticsReg.getCcode(),logisticsReg.getMyPlan(),logisticsReg.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM logistics_reg WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public LogisticsReg getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from logistics_reg where Id = ? ";
			List<LogisticsReg> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(LogisticsReg.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
