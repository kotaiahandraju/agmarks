
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

import com.aurospaces.neighbourhood.bean.TraderReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseTraderRegDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO trader_reg( Token_id, First_name, Last_name, Company_name, Status, Address, Village, Mandal, District, State, Pincode, Email, Mobile, Date_of_incorp, GST_number, Crops, Vegetables, Ani_husbandry, Dairy, Date_of_reg, Ccode, My_plan) values (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final TraderReg traderReg) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(traderReg.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					/*if(traderReg.getDateOfIncorp() == null)
					{
					traderReg.setDateOfIncorp( new Date());
					}
					java.sql.Timestamp DateOfIncorp = 
						new java.sql.Timestamp(traderReg.getDateOfIncorp().getTime()); */
							
					if(traderReg.getDateOfReg() == null)
					{
					traderReg.setDateOfReg( new Date());
					}
					java.sql.Timestamp DateOfReg = 
						new java.sql.Timestamp(traderReg.getDateOfReg().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"Id"});
	ps.setString(1, traderReg.getTokenId());
ps.setString(2, traderReg.getFirstName());
ps.setString(3, traderReg.getLastName());
ps.setString(4, traderReg.getCompanyName());
ps.setString(5, traderReg.getStatus());
ps.setString(6, traderReg.getAddress());
ps.setString(7, traderReg.getVillage());
ps.setString(8, traderReg.getMandal());
ps.setString(9, traderReg.getDistrict());
ps.setString(10, traderReg.getState());
ps.setInt(11, traderReg.getPincode());
ps.setString(12, traderReg.getEmail());
ps.setString(13, traderReg.getMobile());
ps.setString(14, traderReg.getStrdateOfIncorp());
ps.setString(15, traderReg.getGSTNumber());
ps.setString(16, traderReg.getCrops());
ps.setString(17, traderReg.getVegetables());
ps.setString(18, traderReg.getAniHusbandry());
ps.setString(19, traderReg.getDairy());
ps.setTimestamp(20, DateOfReg);

ps.setString(21, traderReg.getCcode());
ps.setString(22, traderReg.getMyPlan());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				traderReg.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE trader_reg  set Token_id = ? ,First_name = ? ,Last_name = ? ,Company_name = ? ,Status = ? ,Address = ? ,Village = ? ,Mandal = ? ,District = ? ,State = ? ,Pincode = ? ,Email = ? ,Mobile = ? ,Date_of_incorp = ? ,GST_number = ? ,Crops = ? ,Vegetables = ? ,Ani_husbandry = ? ,Dairy = ?,Ccode =? , My_plan =?  where Id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{traderReg.getTokenId(),traderReg.getFirstName(),traderReg.getLastName(),traderReg.getCompanyName(),traderReg.getStatus(),traderReg.getAddress(),traderReg.getVillage(),traderReg.getMandal(),traderReg.getDistrict(),traderReg.getState(),traderReg.getPincode(),traderReg.getEmail(),traderReg.getMobile(),traderReg.getDateOfIncorp(),traderReg.getGSTNumber(),traderReg.getCrops(),traderReg.getVegetables(),traderReg.getAniHusbandry(),traderReg.getDairy(),traderReg.getCcode(),traderReg.getMyPlan(),traderReg.getId()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM trader_reg WHERE Id=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public TraderReg getById(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from trader_reg where Id = ? ";
			List<TraderReg> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}
