
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
import com.aurospaces.neighbourhood.bean.VendorClinic;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseVendorClinicDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO vendor_clinic( Vendor_name, Mobile, Token_Id, Img_name, Inventory_type,Status, Comment, Date,Transaction_type) values (?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final VendorClinic vendorClinic) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(vendorClinic.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(vendorClinic.getDate() == null)
					{
					vendorClinic.setDate( new Date());
					}
					java.sql.Timestamp Date = 
						new java.sql.Timestamp(vendorClinic.getDate().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
	ps.setString(1, vendorClinic.getVendorName());
ps.setString(2, vendorClinic.getMobile());
ps.setString(3, vendorClinic.getTokenId());
ps.setString(4, vendorClinic.getImgName());
ps.setString(5, vendorClinic.getInventoryType());
ps.setString(6, vendorClinic.getStatus());
ps.setString(7, vendorClinic.getComment());
ps.setTimestamp(8, Date);
ps.setString(9, vendorClinic.getTransactionType());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				vendorClinic.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE vendor_clinic  set Farmer_name = ? ,Mobile = ? ,Token_Id = ? ,Status = ? ,Comment = ? ,Transaction_type =? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{vendorClinic.getVendorName(),vendorClinic.getMobile(),vendorClinic.getTokenId(),vendorClinic.getStatus(),vendorClinic.getComment(),vendorClinic.getTransactionType(),vendorClinic.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM vendor_clinic WHERE S_no=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public VendorClinic getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from vendor_clinic where S_no = ? ";
			List<VendorClinic> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(VendorClinic.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
