
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

import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseSupplierRegDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO supplier_reg( Token_id, First_name, Last_name, Company_name, Address, Village, Mandal, District, State, Pincode, Email, Mobile, Date_of_incorp, GST_number, Status, Bio, Botanical, Farm_machinery, Inorganic, Organic, Seed, Date_of_reg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final SupplierReg supplierReg) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(supplierReg.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(supplierReg.getDateOfIncorp() == null)
					{
					supplierReg.setDateOfIncorp( new Date());
					}
					java.sql.Timestamp DateOfIncorp = 
						new java.sql.Timestamp(supplierReg.getDateOfIncorp().getTime()); 
							
					if(supplierReg.getDateOfReg() == null)
					{
					supplierReg.setDateOfReg( new Date());
					}
					java.sql.Timestamp DateOfReg = 
						new java.sql.Timestamp(supplierReg.getDateOfReg().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, supplierReg.getTokenId());
ps.setString(2, supplierReg.getFirstName());
ps.setString(3, supplierReg.getLastName());
ps.setString(4, supplierReg.getCompanyName());
ps.setString(5, supplierReg.getAddress());
ps.setString(6, supplierReg.getVillage());
ps.setString(7, supplierReg.getMandal());
ps.setString(8, supplierReg.getDistrict());
ps.setString(9, supplierReg.getState());
ps.setInt(10, supplierReg.getPincode());
ps.setString(11, supplierReg.getEmail());
ps.setString(12, supplierReg.getMobile());
ps.setTimestamp(13, DateOfIncorp);
ps.setString(14, supplierReg.getGSTNumber());
ps.setString(15, supplierReg.getStatus());
ps.setString(16, supplierReg.getBio());
ps.setString(17, supplierReg.getBotanical());
ps.setString(18, supplierReg.getFarmMachinery());
ps.setString(19, supplierReg.getInorganic());
ps.setString(20, supplierReg.getOrganic());
ps.setString(21, supplierReg.getSeed());
ps.setTimestamp(22, DateOfReg);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				supplierReg.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE supplier_reg  set Token_id = ? ,First_name = ? ,Last_name = ? ,Company_name = ? ,Address = ? ,Village = ? ,Mandal = ? ,District = ? ,State = ? ,Pincode = ? ,Email = ? ,Mobile = ? ,Date_of_incorp = ? ,GST_number = ? ,Status = ? ,Bio = ? ,Botanical = ? ,Farm_machinery = ? ,Inorganic = ? ,Organic = ? ,Seed = ?  where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{supplierReg.getTokenId(),supplierReg.getFirstName(),supplierReg.getLastName(),supplierReg.getCompanyName(),supplierReg.getAddress(),supplierReg.getVillage(),supplierReg.getMandal(),supplierReg.getDistrict(),supplierReg.getState(),supplierReg.getPincode(),supplierReg.getEmail(),supplierReg.getMobile(),supplierReg.getDateOfIncorp(),supplierReg.getGSTNumber(),supplierReg.getStatus(),supplierReg.getBio(),supplierReg.getBotanical(),supplierReg.getFarmMachinery(),supplierReg.getInorganic(),supplierReg.getOrganic(),supplierReg.getSeed(),supplierReg.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM supplier_reg WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public SupplierReg getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from supplier_reg where Id = ? ";
			List<SupplierReg> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(SupplierReg.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
