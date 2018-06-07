
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.Input;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseInputDao;




@Repository(value = "inputDao")
public class InputDao extends BaseInputDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public List<Input> getinputdata(){
		List<Input> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT `Category`,GROUP_CONCAT(DISTINCT `Input`) AS `input` FROM `input` GROUP BY `Category`   ";
			
			list = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(Input.class));
			if(list.size() > 0)
				return list;
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

}

