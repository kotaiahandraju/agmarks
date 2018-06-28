
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

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseFarRegsDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO far_regs( First_name, Last_name, Dob, Mobile, Aadhar, Address, Village, Mandal, District, State, Pincode, Crop_type, Crop1, Crop2, Crop3, Crop4, Crop5,  Vegetables, Veg1, Veg2, Veg3, Veg4, Veg5, Veg6, Veg7, Veg8,  Ani_hus, Ani_hus1, Ani_hus2, Ani_hus3,  Dairy, Dairy1, Dairy2, Dairy3, Token_id, Date_of_reg,Ccode) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final FarRegs farRegs) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(farRegs.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(farRegs.getDateOfReg() == null)
					{
					farRegs.setDateOfReg( new Date());
					}
					java.sql.Timestamp DateOfReg = 
						new java.sql.Timestamp(farRegs.getDateOfReg().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, farRegs.getFirstName());
ps.setString(2, farRegs.getLastName());
ps.setString(3, farRegs.getDob());
ps.setString(4, farRegs.getMobile());
ps.setString(5, farRegs.getAadhar());
ps.setString(6, farRegs.getAddress());
ps.setString(7, farRegs.getVillage());
ps.setString(8, farRegs.getMandal());
ps.setString(9, farRegs.getDistrict());
ps.setString(10, farRegs.getState());
ps.setString(11, farRegs.getPincode());
ps.setString(12, farRegs.getCropType());
ps.setString(13, farRegs.getCrop1());
ps.setString(14, farRegs.getCrop2());
ps.setString(15, farRegs.getCrop3());
ps.setString(16, farRegs.getCrop4());
ps.setString(17, farRegs.getCrop5());
ps.setString(18, farRegs.getVegetables());
ps.setString(19, farRegs.getVeg1());
ps.setString(20, farRegs.getVeg2());
ps.setString(21, farRegs.getVeg3());
ps.setString(22, farRegs.getVeg4());
ps.setString(23, farRegs.getVeg5());
ps.setString(24, farRegs.getVeg6());
ps.setString(25, farRegs.getVeg7());
ps.setString(26, farRegs.getVeg8());
ps.setString(27, farRegs.getAniHus());
ps.setString(28, farRegs.getAniHus1());
ps.setString(29, farRegs.getAniHus2());
ps.setString(30, farRegs.getAniHus3());
ps.setString(31, farRegs.getDairy());
ps.setString(32, farRegs.getDairy1());
ps.setString(33, farRegs.getDairy2());
ps.setString(34, farRegs.getDairy3());
ps.setString(35, farRegs.getTokenId());
ps.setTimestamp(36, DateOfReg);
ps.setString(37, farRegs.getCcode());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				farRegs.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE far_regs  set First_name = ? ,Last_name = ? ,Dob = ? ,Mobile = ? ,Aadhar = ? ,Address = ? ,Village = ? ,Mandal = ? ,District = ? ,State = ? ,Pincode = ? ,Crop_type = ? ,Crop1 = ? ,Crop2 = ? ,Crop3 = ? ,Crop4 = ? ,Crop5 = ? ,Vegetables = ? ,Veg1 = ? ,Veg2 = ? ,Veg3 = ? ,Veg4 = ? ,Veg5 = ? ,Veg6 = ? ,Veg7 = ? ,Veg8 = ? ,Ani_hus = ? ,Ani_hus1 = ? ,Ani_hus2 = ? ,Ani_hus3 = ? ,Dairy = ? ,Dairy1 = ? ,Dairy2 = ? ,Dairy3 = ? ,Ccode = ?  where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{farRegs.getFirstName(),farRegs.getLastName(),farRegs.getDob(),farRegs.getMobile(),farRegs.getAadhar(),farRegs.getAddress(),farRegs.getVillage(),farRegs.getMandal(),farRegs.getDistrict(),farRegs.getState(),farRegs.getPincode(),farRegs.getCropType(),farRegs.getCrop1(),farRegs.getCrop2(),farRegs.getCrop3(),farRegs.getCrop4(),farRegs.getCrop5(),farRegs.getVegetables(),farRegs.getVeg1(),farRegs.getVeg2(),farRegs.getVeg3(),farRegs.getVeg4(),farRegs.getVeg5(),farRegs.getVeg6(),farRegs.getVeg7(),farRegs.getVeg8(),farRegs.getAniHus(),farRegs.getAniHus1(),farRegs.getAniHus2(),farRegs.getAniHus3(),farRegs.getDairy(),farRegs.getDairy1(),farRegs.getDairy2(),farRegs.getDairy3(),farRegs.getCcode(),farRegs.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM far_regs WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public FarRegs getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from far_regs where Id = ? ";
			List<FarRegs> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(FarRegs.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
