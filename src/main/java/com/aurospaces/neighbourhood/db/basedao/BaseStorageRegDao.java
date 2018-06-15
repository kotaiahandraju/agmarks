
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

import com.aurospaces.neighbourhood.bean.StorageReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseStorageRegDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO storage_reg( Token_id, First_name, Last_name, Company_name, Status, Address, Village, Mandal, District, State, Pincode, Email, Mobile, Date_of_incorp, GST_number, Crops, Vegetables, Dairy, Storage_capacity, Units, Date_of_reg, Ani_husbandry) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final StorageReg storageReg) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(storageReg.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(storageReg.getDateOfIncorp() == null)
					{
					storageReg.setDateOfIncorp( new Date());
					}
					java.sql.Timestamp DateOfIncorp = 
						new java.sql.Timestamp(storageReg.getDateOfIncorp().getTime()); 
							
					if(storageReg.getDateOfReg() == null)
					{
					storageReg.setDateOfReg( new Date());
					}
					java.sql.Timestamp DateOfReg = 
						new java.sql.Timestamp(storageReg.getDateOfReg().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, storageReg.getTokenId());
ps.setString(2, storageReg.getFirstName());
ps.setString(3, storageReg.getLastName());
ps.setString(4, storageReg.getCompanyName());
ps.setString(5, storageReg.getStatus());
ps.setString(6, storageReg.getAddress());
ps.setString(7, storageReg.getVillage());
ps.setString(8, storageReg.getMandal());
ps.setString(9, storageReg.getDistrict());
ps.setString(10, storageReg.getState());
ps.setInt(11, storageReg.getPincode());
ps.setString(12, storageReg.getEmail());
ps.setString(13, storageReg.getMobile());
ps.setTimestamp(14, DateOfIncorp);
ps.setString(15, storageReg.getGSTNumber());
ps.setString(16, storageReg.getCrops());
ps.setString(17, storageReg.getVegetables());
ps.setString(18, storageReg.getDairy());
ps.setString(19, storageReg.getStorageCapacity());
ps.setString(20, storageReg.getUnits());
ps.setTimestamp(21, DateOfReg);
ps.setString(22, storageReg.getAniHusbandry());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				storageReg.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE storage_reg  set Token_id = ? ,First_name = ? ,Last_name = ? ,Company_name = ? ,Status = ? ,Address = ? ,Village = ? ,Mandal = ? ,District = ? ,State = ? ,Pincode = ? ,Email = ? ,Mobile = ? ,Date_of_incorp = ? ,GST_number = ? ,Crops = ? ,Vegetables = ? ,Dairy = ? ,Storage_capacity = ? ,Units = ? ,setString = ? where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{storageReg.getTokenId(),storageReg.getFirstName(),storageReg.getLastName(),storageReg.getCompanyName(),storageReg.getStatus(),storageReg.getAddress(),storageReg.getVillage(),storageReg.getMandal(),storageReg.getDistrict(),storageReg.getState(),storageReg.getPincode(),storageReg.getEmail(),storageReg.getMobile(),storageReg.getDateOfIncorp(),storageReg.getGSTNumber(),storageReg.getCrops(),storageReg.getVegetables(),storageReg.getDairy(),storageReg.getStorageCapacity(),storageReg.getUnits(),storageReg.getAniHusbandry(),storageReg.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM storage_reg WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public StorageReg getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from storage_reg where Id = ? ";
			List<StorageReg> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(StorageReg.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
