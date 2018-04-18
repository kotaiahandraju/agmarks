
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

import com.aurospaces.neighbourhood.bean.VendorReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseVendorRegDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO vendor_reg( Token_id, First_name, Last_name, Company_name, Status, Address, Village, Mandal, District, State, Pincode, Email, Mobile, Date_of_incorp, GST_number, Vegetables, Ani_hus, Dairy, Date_of_reg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final VendorReg vendorReg) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(vendorReg.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(vendorReg.getDateOfReg() == null)
					{
					vendorReg.setDateOfReg( new Date());
					}
					java.sql.Timestamp DateOfReg = 
						new java.sql.Timestamp(vendorReg.getDateOfReg().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, vendorReg.getTokenId());
ps.setString(2, vendorReg.getFirstName());
ps.setString(3, vendorReg.getLastName());
ps.setString(4, vendorReg.getCompanyName());
ps.setString(5, vendorReg.getStatus());
ps.setString(6, vendorReg.getAddress());
ps.setString(7, vendorReg.getVillage());
ps.setString(8, vendorReg.getMandal());
ps.setString(9, vendorReg.getDistrict());
ps.setString(10, vendorReg.getState());
ps.setInt(11, vendorReg.getPincode());
ps.setString(12, vendorReg.getEmail());
ps.setString(13, vendorReg.getMobile());
ps.setString(14, vendorReg.getDateOfIncorp());
ps.setString(15, vendorReg.getGSTNumber());
ps.setString(16, vendorReg.getVegetables());
ps.setString(17, vendorReg.getAniHus());
ps.setString(18, vendorReg.getDairy());
ps.setTimestamp(19, DateOfReg);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				vendorReg.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE vendor_reg  set Token_id = ? ,First_name = ? ,Last_name = ? ,Company_name = ? ,Status = ? ,Address = ? ,Village = ? ,Mandal = ? ,District = ? ,State = ? ,Pincode = ? ,Email = ? ,Mobile = ? ,Date_of_incorp = ? ,GST_number = ? ,Vegetables = ? ,Ani_hus = ? ,Dairy = ?  where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{vendorReg.getTokenId(),vendorReg.getFirstName(),vendorReg.getLastName(),vendorReg.getCompanyName(),vendorReg.getStatus(),vendorReg.getAddress(),vendorReg.getVillage(),vendorReg.getMandal(),vendorReg.getDistrict(),vendorReg.getState(),vendorReg.getPincode(),vendorReg.getEmail(),vendorReg.getMobile(),vendorReg.getDateOfIncorp(),vendorReg.getGSTNumber(),vendorReg.getVegetables(),vendorReg.getAniHus(),vendorReg.getDairy(),vendorReg.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM vendor_reg WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public VendorReg getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from vendor_reg where Id = ? ";
			List<VendorReg> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
