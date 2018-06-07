
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseMasAhDao;




@Repository(value = "masAhDao")
public class MasAhDao extends BaseMasAhDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public List<Map<String,Object>>  getMasAh( ){
		List<Map<String,Object>>  list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT * FROM `mas_ah` GROUP BY `Animal` ";
			
			list = jdbcTemplate.queryForList(sql, new Object[] { });
			if(list.size() > 0)
				return list;
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

}

