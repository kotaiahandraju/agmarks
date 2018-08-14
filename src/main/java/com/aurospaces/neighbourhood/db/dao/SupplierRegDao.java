
package com.aurospaces.neighbourhood.db.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.AddProduct;
import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseSupplierRegDao;




@Repository(value = "supplierRegDao")
public class SupplierRegDao extends BaseSupplierRegDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 

	public SupplierReg duplicatecheck(SupplierReg  supplierReg){
		 List<SupplierReg> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *, date(Date_of_incorp) as DateOfIncorp    FROM `vendor_reg` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {supplierReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(SupplierReg.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	public List<Map<String, Object>> getSupplierRegByMobile(String mobile) {
		jdbcTemplate = custom.getJdbcTemplate();
		
		List<Map<String,Object>> retlist=null;
		String sql = "SELECT *,STR_TO_DATE(Date_of_incorp,'%d-%M-%Y') as DateOfIncorp from supplier_reg where Mobile = '"+mobile+"'";
		/*List<SupplierReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(SupplierReg.class));
		*/
		
		retlist =jdbcTemplate.queryForList(sql, new Object[]{});
		
		
		
		
		if(retlist.size() > 0)
			return retlist;
		return null;
		
		
	}



	public void removeSupplier(SupplierReg supplierReg) {
		// TODO Auto-generated method stub
		jdbcTemplate = custom.getJdbcTemplate();
		String hql="update supplier_reg set Active_status='"+supplierReg.getActiveStatus()+"' where Id='"+supplierReg.getId()+"' ";
		jdbcTemplate.update(hql, new Object[]{});
	}



	public List<SupplierReg> searchsupplier(SupplierReg supplierReg) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql="select * from supplier_reg where Branch_code='"+supplierReg.getBranchCode()+"' and Mobile='"+supplierReg.getMobile()+"' and Active_status='Active'  limit 1";
		List<SupplierReg> retlist = jdbcTemplate.query(hql,
				new Object[]{},
				ParameterizedBeanPropertyRowMapper.newInstance(SupplierReg.class));
		
		System.out.println(retlist); 
		
		if(retlist.size() > 0)
			return retlist;
		return Collections.emptyList();
		
	}



	public List<Map<String, Object>> getAllUnitSuppliers() {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql="select * from supplier_reg where   Active_status='Active' ";
		List<Map<String, Object>> retlist =jdbcTemplate.queryForList(hql);
		return retlist;
	}



}

