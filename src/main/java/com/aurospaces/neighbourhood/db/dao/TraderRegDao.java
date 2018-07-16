
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

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




	public List<TraderReg> getAlltradersData() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from trader_reg ";
		List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}


	public List<TraderReg> gettradersByStateAndDistic(TraderReg traderReg) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from trader_reg  where State = '"+traderReg.getState()+" ' and District ='"+traderReg.getDistrict()+"' ";
		List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

}

