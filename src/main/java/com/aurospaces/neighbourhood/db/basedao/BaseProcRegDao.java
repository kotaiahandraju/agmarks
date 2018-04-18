
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

import com.aurospaces.neighbourhood.bean.ProcReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseProcRegDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO proc_reg( Token_id, First_name, Last_name, Company_name, Address, Village, Mandal, District, State, Pincode, Email, Mobile, Date_of_incorp, GST_number, Status, Raw_material, Raw1, Raw2, Raw3, Add_raw, Fin_product, Packaging, Date_of_reg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final ProcReg procReg) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(procReg.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(procReg.getDateOfIncorp() == null)
					{
					procReg.setDateOfIncorp( new Date());
					}
					java.sql.Timestamp DateOfIncorp = 
						new java.sql.Timestamp(procReg.getDateOfIncorp().getTime()); 
							
					if(procReg.getDateOfReg() == null)
					{
					procReg.setDateOfReg( new Date());
					}
					java.sql.Timestamp DateOfReg = 
						new java.sql.Timestamp(procReg.getDateOfReg().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, procReg.getTokenId());
ps.setString(2, procReg.getFirstName());
ps.setString(3, procReg.getLastName());
ps.setString(4, procReg.getCompanyName());
ps.setString(5, procReg.getAddress());
ps.setString(6, procReg.getVillage());
ps.setString(7, procReg.getMandal());
ps.setString(8, procReg.getDistrict());
ps.setString(9, procReg.getState());
ps.setString(10, procReg.getPincode());
ps.setString(11, procReg.getEmail());
ps.setString(12, procReg.getMobile());
ps.setTimestamp(13, DateOfIncorp);
ps.setString(14, procReg.getGSTNumber());
ps.setString(15, procReg.getStatus());
ps.setString(16, procReg.getRawMaterial());
ps.setString(17, procReg.getRaw1());
ps.setString(18, procReg.getRaw2());
ps.setString(19, procReg.getRaw3());
ps.setString(20, procReg.getAddRaw());
ps.setString(21, procReg.getFinProduct());
ps.setString(22, procReg.getPackaging());
ps.setTimestamp(23, DateOfReg);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				procReg.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE proc_reg  set Token_id = ? ,First_name = ? ,Last_name = ? ,Company_name = ? ,Address = ? ,Village = ? ,Mandal = ? ,District = ? ,State = ? ,Pincode = ? ,Email = ? ,Mobile = ? ,Date_of_incorp = ? ,GST_number = ? ,Status = ? ,Raw_material = ? ,Raw1 = ? ,Raw2 = ? ,Raw3 = ? ,Add_raw = ? ,Fin_product = ? ,Packaging = ?  where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{procReg.getTokenId(),procReg.getFirstName(),procReg.getLastName(),procReg.getCompanyName(),procReg.getAddress(),procReg.getVillage(),procReg.getMandal(),procReg.getDistrict(),procReg.getState(),procReg.getPincode(),procReg.getEmail(),procReg.getMobile(),procReg.getDateOfIncorp(),procReg.getGSTNumber(),procReg.getStatus(),procReg.getRawMaterial(),procReg.getRaw1(),procReg.getRaw2(),procReg.getRaw3(),procReg.getAddRaw(),procReg.getFinProduct(),procReg.getPackaging(),procReg.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM proc_reg WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public ProcReg getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from proc_reg where Id = ? ";
			List<ProcReg> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
