
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.ProcReg;
import com.aurospaces.neighbourhood.bean.VendorReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseVendorRegDao;




@Repository(value = "vendorRegDao")
public class VendorRegDao extends BaseVendorRegDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 

	public VendorReg duplicatecheck(VendorReg  vendorReg){
		 List<VendorReg> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `vendor_reg` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {vendorReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<Map<String, Object>> getVendorRegByMobile(String mobile) {
		jdbcTemplate = custom.getJdbcTemplate();
		
		List<Map<String,Object>> retlist=null;
		
		String sql = "SELECT * from vendor_reg where Mobile = '"+mobile+"'";
		/*List<VendorReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));*/
		retlist =jdbcTemplate.queryForList(sql, new Object[]{});
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

	public List<VendorReg> getAllVendorsData() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from vendor_reg ";
		List<VendorReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

	public List<VendorReg>  getVendorsByStateAndDistic(VendorReg vendorReg) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from vendor_reg  where State = '"+vendorReg.getState()+" ' and District ='"+vendorReg.getDistance()+"' ";
		List<VendorReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

	
	
	
}

