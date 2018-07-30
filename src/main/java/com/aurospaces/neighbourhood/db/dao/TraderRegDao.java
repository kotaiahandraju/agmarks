
package com.aurospaces.neighbourhood.db.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.TraderReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseTraderRegDao;




@Repository(value = "traderRegDao")
public class TraderRegDao extends BaseTraderRegDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public TraderReg duplicatecheck(TraderReg procReg){
		 List<TraderReg> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `trader_reg` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {procReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public List<Map<String, Object>> getTraderRegByMobile(String mobile) {
		
		List<Map<String,Object>> retlist=null;
		
		
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from trader_reg where Mobile = '"+mobile+"'";
		/*List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));*/
		
		retlist =jdbcTemplate.queryForList(sql, new Object[]{});
		if(retlist.size() > 0)
			return retlist;
		return null;
	}




	public Set<TraderReg> getAlltradersData(FarRegs farregbean) {
		jdbcTemplate = custom.getJdbcTemplate();
		//String sql = "SELECT * from trader_reg ";
		
		Set<TraderReg> tradersSet =new LinkedHashSet<TraderReg>();
		
		String cmts =farregbean.getCropType()+","+farregbean.getVegetables()+","+farregbean.getAniHus()+","+farregbean.getDairy();
		    
		String farStrings[]  = cmts.split(",");
		
		
		
		 String      sql="  SELECT * from  trader_reg   ";
             
		 System.out.println(sql);
		 
		List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
		
		if(retlist.size() > 0)
		{
		for(TraderReg  entry : retlist)
		{
			String allValues =entry.getCrops()+","+entry.getVegetables()+","+entry.getAniHusbandry()+","+entry.getDairy();
			for(String entry2:farStrings)
			{
				if(allValues.contains(entry2))
				{
					tradersSet.add(entry);
				}
				
			}
			
		}
		}
		else
		{
			return null;
			
		}
		
		
		return tradersSet;
	}


	public List<TraderReg> gettradersByStateAndDistic(TraderReg traderReg, FarRegs farregbean) {
		jdbcTemplate = custom.getJdbcTemplate();
		//String sql = "SELECT * from trader_reg  where State = '"+traderReg.getState()+" ' and District ='"+traderReg.getDistrict()+"' ";
		

		String cmts =farregbean.getCropType()+","+farregbean.getVegetables()+","+farregbean.getAniHus()+","+farregbean.getDairy();
		String sql = "SELECT * from trader_reg  where State = '"+traderReg.getState()+" ' and District ='"+traderReg.getDistrict()+"' "
				 +"FIND_IN_SET  (Crops,'"+cmts+" ')  or FIND_IN_SET  (Vegetables,'"+cmts+" ') or FIND_IN_SET  (Ani_husbandry,'"+cmts+" ') or FIND_IN_SET  (Vegetables,'"+cmts+" ')";

		
		List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

}

