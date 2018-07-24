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

import com.aurospaces.neighbourhood.bean.ContractBusiness;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseContractBusiness {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO contract_business(Company_name, Ccode, Mobile,GST,Company_id,Area_int_contract,Required_crops,Cultivable_land,Comment,Status) values (?,?,?,?,?,?,?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final ContractBusiness contractBusiness) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(contractBusiness.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, contractBusiness.getCompanyName());
ps.setString(2, contractBusiness.getCcode());
ps.setString(3, contractBusiness.getMobile());
ps.setString(4, contractBusiness.getGST());
ps.setString(5, contractBusiness.getCompanyId());
ps.setString(6, contractBusiness.getAreaIntContract());
ps.setString(7, contractBusiness.getRequiredCrops());
ps.setString(8, contractBusiness.getCultivableLand());
ps.setString(9, contractBusiness.getComment());
ps.setString(10, contractBusiness.getStatus());





							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				contractBusiness.setSNo((unId.intValue()));
				

		}
		else
		{

			String sql = "UPDATE contract_business  set Company_name = ? ,Ccode = ? ,Mobile = ? ,GST = ? ,Company_id = ? ,Area_int_contract = ? ,Required_crops = ? ,Cultivable_land = ? ,Comment = ? ,Status = ?   where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{contractBusiness.getCompanyName(),contractBusiness.getCcode(),contractBusiness.getMobile(),contractBusiness.getGST(),contractBusiness.getCompanyId(),contractBusiness.getAreaIntContract(),contractBusiness.getRequiredCrops(),contractBusiness.getCultivableLand(),contractBusiness.getComment(),contractBusiness.getStatus(),contractBusiness.getSNo()});
		}
	}
		
		

}
