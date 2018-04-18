
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CommPrices;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseUsersDao;




@Repository(value = "usersDao")
public class UsersDao extends BaseUsersDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public int getcounts(String name){
		 int count = 0;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  COUNT(`Status1`) as count FROM `users` where Status1=  ? ";
			
			  count = jdbcTemplate.queryForInt(sql, new Object[] {name  });
			
			System.out.println(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
		
	}

}

