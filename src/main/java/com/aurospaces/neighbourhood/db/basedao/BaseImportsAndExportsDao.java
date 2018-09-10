package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.ImportExport;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseImportsAndExportsDao {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO import_export(Company_name, Ccode, Contact_number, Address, Village, District, State, Pincode, Crop_name, Variety,Quantity,Units,Quality,Packaging,Transport_type,Vehicle_type,Import_date,Export_date,Comment,Status,Mandal,User_status) values (?, ?,?, ?,?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final ImportExport importExport) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(importExport.getSNo() == 0)	{
		
	

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, importExport.getCompanyName());
ps.setString(2, importExport.getCcode());
ps.setString(3, importExport.getContactNumber());
ps.setString(4, importExport.getAddress());
ps.setString(5, importExport.getVillage());
ps.setString(6, importExport.getDistrict());
ps.setString(7, importExport.getState());
ps.setString(8, importExport.getPincode());
ps.setString(9, importExport.getCropName());
ps.setString(10, importExport.getVariety());


ps.setString(11, importExport.getQuantity());
ps.setString(12, importExport.getUnits());
ps.setString(13, importExport.getQuality());
ps.setString(14, importExport.getPackaging());
ps.setString(15, importExport.getTransportType());
ps.setString(16, importExport.getVehicleType());



if(importExport.getImportDate()!= null)
{
	ps.setDate(17, new java.sql.Date(importExport.getImportDate().getTime()));
	
}
else
{
	ps.setNull(17, java.sql.Types.DATE);
	
}

if(importExport.getExportDate() != null)
{
	
	ps.setDate(18, new java.sql.Date(importExport.getExportDate().getTime()));
	
}
else
{
	ps.setNull(18, java.sql.Types.DATE);
	
}





ps.setString(19, importExport.getComment());
ps.setString(20, importExport.getStatus());
ps.setString(21, importExport.getMandal());
ps.setString(22, importExport.getUserStatus());


							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				importExport.setSNo((unId.intValue()));
				

		}
		else
		{

			String sql = "UPDATE import_export  set Company_name = ? ,Ccode = ? ,Contact_number = ? ,Address = ? ,Village = ? ,District = ? ,State = ? ,Pincode = ? ,Crop_name = ? ,Variety = ? ,Quantity = ?,Units =?, Quality =?,Packaging =?,Transport_type =?,Vehicle_type =?,Comment =?,Status =?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{importExport.getCompanyName(),importExport.getCcode(),importExport.getContactNumber(),importExport.getAddress(),importExport.getVariety(),importExport.getDistrict(),importExport.getDistrict(),importExport.getState(),importExport.getPincode(),importExport.getCropName(),importExport.getVariety(),importExport.getQuantity(),importExport.getUnits(),importExport.getQuality(),importExport.getPackaging(),importExport.getTransportType(),importExport.getVehicleType(),importExport.getComment(),importExport.getStatus(),importExport.getSNo()});
		}
	}
		
		

}
