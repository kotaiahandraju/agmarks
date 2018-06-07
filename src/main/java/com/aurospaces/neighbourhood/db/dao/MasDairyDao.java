
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.MasCom;
import com.aurospaces.neighbourhood.bean.MasDairy;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseMasDairyDao;




@Repository(value = "masDairyDao")
public class MasDairyDao extends BaseMasDairyDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 

	public List<Map<String,Object>>  getMasDairy( ){
		List<Map<String,Object>>  list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT * FROM `mas_dairy` GROUP BY `Product_name` ";
			
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

