
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseFarRegsDao;




@Repository(value = "farRegsDao")
public class FarRegsDao extends BaseFarRegsDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public FarRegs duplicatecheck(FarRegs procReg){
		 List<FarRegs> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `far_regs` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {procReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(FarRegs.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public List<FarRegs> getFarRegsByMobile(String mobile) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from far_regs where Mobile = '"+mobile+"'";
		System.out.println(sql);
		List<FarRegs> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(FarRegs.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}


	public FarRegs getByTokenId(String tokenId) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from far_regs where  Token_id= '"+tokenId+"'";
		System.out.println(sql);
		List<FarRegs> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(FarRegs.class));
		if(retlist.size() > 0)
			return retlist.get(0);
		return null;
	}
	
	
	
	

}

