
package com.aurospaces.neighbourhood.db.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.StorageReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseStorageRegDao;




@Repository(value = "storageRegDao")
public class StorageRegDao extends BaseStorageRegDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public StorageReg duplicatecheck(StorageReg procReg){
		 List<StorageReg> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `storage_reg` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {procReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(StorageReg.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	public List<StorageReg> getStorageRegByMobile(String mobile) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from storage_reg where Mobile = '"+mobile+"'";
		List<StorageReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(StorageReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}



	public Set<StorageReg> getStoragedata(FarRegs farRegs) {
		jdbcTemplate = custom.getJdbcTemplate();
		String cropType =farRegs.getCropType();
		
		Set<StorageReg> storageSet  =new LinkedHashSet<StorageReg>();
		
		String  crops[] =cropType.split(",");
		
		for(String entry:crops)
		{
			String sql ="select * from storage_reg  where Crops like '%"+entry+"%';";
			
			List<StorageReg> storageList = jdbcTemplate.query(sql,
					new Object[]{},
					ParameterizedBeanPropertyRowMapper.newInstance(StorageReg.class));
			storageSet.addAll(storageList);
			
		}
		
		return storageSet;
	}



	public Set<StorageReg> getStorageDataOVegetablesf(FarRegs farRegs) {
		jdbcTemplate = custom.getJdbcTemplate();
		String cropType =farRegs.getVegetables();
		
		Set<StorageReg> storageSet  =new LinkedHashSet<StorageReg>();
		
		String  crops[] =cropType.split(",");
		
		for(String entry:crops)
		{
			String sql ="select * from storage_reg  where Vegetables like '%"+entry+"%';";
			System.out.println(sql);
			List<StorageReg> storageList = jdbcTemplate.query(sql,
					new Object[]{},
					ParameterizedBeanPropertyRowMapper.newInstance(StorageReg.class));
			storageSet.addAll(storageList);
			
			System.out.println(sql);
			
		}
		
		return storageSet;
	}



	public Set<StorageReg> getStorageDataOfDairy(FarRegs farRegs) {
		jdbcTemplate = custom.getJdbcTemplate();
		String cropType =farRegs.getDairy();
		
		Set<StorageReg> storageSet  =new LinkedHashSet<StorageReg>();
		
		String  crops[] =cropType.split(",");
		
		for(String entry:crops)
		{
			String sql ="select * from storage_reg  where Dairy like '%"+entry+"%';";
			System.out.println(sql);
			List<StorageReg> storageList = jdbcTemplate.query(sql,
					new Object[]{},
					ParameterizedBeanPropertyRowMapper.newInstance(StorageReg.class));
			storageSet.addAll(storageList);
			
			System.out.println(sql);
			
		}
		
		return storageSet;
	}



	public Set<StorageReg> getStorageDataOfAnimals(FarRegs farRegs) {
		jdbcTemplate = custom.getJdbcTemplate();
		String cropType =farRegs.getAniHus();
		
		Set<StorageReg> storageSet  =new LinkedHashSet<StorageReg>();
		
		String  crops[] =cropType.split(",");
		
		for(String entry:crops)
		{
			String sql ="select * from storage_reg  where Ani_husbandry like '%"+entry+"%';";
			System.out.println(sql);
			List<StorageReg> storageList = jdbcTemplate.query(sql,
					new Object[]{},
					ParameterizedBeanPropertyRowMapper.newInstance(StorageReg.class));
			storageSet.addAll(storageList);
			
			System.out.println(sql);
			
		}
		
		return storageSet;
	}
	public StorageReg getmysqlpath() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = " SELECT @@basedir AS companyName ";
		List<StorageReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(StorageReg.class));
		if(retlist.size() > 0)
		return retlist.get(0);
		return null;
		}


		

}

