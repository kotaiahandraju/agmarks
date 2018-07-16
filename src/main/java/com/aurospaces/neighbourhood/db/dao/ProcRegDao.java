
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.ProcReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseProcRegDao;




@Repository(value = "procRegDao")
public class ProcRegDao extends BaseProcRegDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public ProcReg duplicatecheck(ProcReg procReg){
		 List<ProcReg> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `proc_reg` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {procReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	public List<Map<String, Object>> getProcreggByMobile(String mobile) {
		jdbcTemplate = custom.getJdbcTemplate();
		
		List<Map<String,Object>> retlist=null;

		String sql = "SELECT * from proc_reg where Mobile = '"+mobile+"'";
		/*ist<ProcReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));*/
		
		retlist =jdbcTemplate.queryForList(sql, new Object[]{});
		if(retlist.size() > 0)
			return retlist;
		return null;
	}



	public List<ProcReg> getAllSupplierData() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from proc_reg ";
		List<ProcReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}



	public List<ProcReg> getProcessorsByStateAndDistic(ProcReg procReg) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from proc_reg  where State = '"+procReg.getState()+" ' and District ='"+procReg.getDistance()+"' ";
		List<ProcReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	

}

