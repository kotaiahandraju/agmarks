package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.ContractFarmer;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseContractFarmer {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO contract_farmer(Farmer_name, Ccode, Mobile,Address,Village,District,State,Pincode,Cultivable_land,Place,Crop_to_grown,Other,Comments,Status,Units,User_status,Mandal) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final ContractFarmer contractFarmer) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(contractFarmer.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, contractFarmer.getFarmerName());
ps.setString(2, contractFarmer.getCcode());
ps.setString(3, contractFarmer.getMobile());
ps.setString(4, contractFarmer.getAddress());
ps.setString(5, contractFarmer.getVillage());
ps.setString(6, contractFarmer.getDistrict());
ps.setString(7, contractFarmer.getState());
ps.setInt(8, Integer.parseInt(contractFarmer.getPincode()));
ps.setString(9, contractFarmer.getCultivableLand());
ps.setString(10, contractFarmer.getPlace());


ps.setString(11, contractFarmer.getCropToGrown());
ps.setString(12, contractFarmer.getOther());
ps.setString(13, contractFarmer.getComments());
ps.setString(14, contractFarmer.getStatus());

ps.setString(15, contractFarmer.getUnits());
ps.setString(16, contractFarmer.getUserStatus());
ps.setString(17, contractFarmer.getMandal());





							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				contractFarmer.setSNo((unId.intValue()));
				

		}
		else
		{

			String sql = "UPDATE contract_farmer  set Farmer_name = ? ,Ccode = ? ,Mobile = ? ,Address = ? ,Village = ? ,District = ? ,State = ? ,Pincode = ? ,Cultivable_land = ? ,Place = ?  ,Crop_to_grown =?,Other =? ,Comments =?,Status =? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{contractFarmer.getFarmerName(),contractFarmer.getCcode(),contractFarmer.getCcode(),contractFarmer.getMobile(),contractFarmer.getAddress(),contractFarmer.getVillage(),contractFarmer.getDistrict(),contractFarmer.getState(),contractFarmer.getPincode(),contractFarmer.getCultivableLand(),contractFarmer.getPlace(),contractFarmer.getCropToGrown(),contractFarmer.getOther(),contractFarmer.getComments(),contractFarmer.getStatus(),contractFarmer.getSNo()});
		}
	}
		
		

}
