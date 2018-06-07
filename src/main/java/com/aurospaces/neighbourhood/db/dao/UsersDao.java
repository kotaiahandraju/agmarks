
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.Users;
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
	public Users duplicatecheck(FarRegs farRegs,String userType){
		 List<Users> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `users` WHERE Mobile=? AND `Status1`='Farmer'   ";
			
			list = jdbcTemplate.query(sql, new Object[] {farRegs.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(Users.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	public Users duplicatecheck1(FarRegs farRegs,String userType){
		 List<Users> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `users` WHERE Mobile=? AND `Status1` IS NOT NULL AND `Status2`  IS NOT NULL AND `Status3`  IS NOT NULL AND `Status3`  IS NOT NULL  AND `Status4`  IS NOT NULL  ";
			
			list = jdbcTemplate.query(sql, new Object[] {farRegs.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(Users.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	public List<Users> mobiledata(String mobile){
		List<Users> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `users` WHERE Mobile=?  ";
			
			list = jdbcTemplate.query(sql, new Object[] {mobile },ParameterizedBeanPropertyRowMapper.newInstance(Users.class));
			if(list.size() > 0)
				return list;
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	public void updateStatus(String status,String columname,String mobile){
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "update  users set "+columname+"=? where Mobile=?";
		jdbcTemplate.update(sql, new Object[]{status,mobile});
		
	}
	
	

	 public List<Users> getUserByUsernameAndPassword(String username,String password) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from users where User_name = '"+username+"' and Password ='"+password+" '";
			List<Users> retlist = jdbcTemplate.query(sql,
			new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(Users.class));
			if(retlist.size() > 0)
				return retlist;
			return null;
		}
	public List<Map<String, Object>> getCategoryandInput() {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql ="select * from input group by Input order by Category";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	public List<Map<String, Object>> getUnits() {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql ="select * from units";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	
	
	public List<Map<String, Object>> getAnimalsInputsForBuy() {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql ="select * from mas_ah group by Breed order by Animal";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	
	
	
	public List<Map<String, Object>> getDairyProductsForBuy() {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql ="select * from mas_dairy group by Variety order by Product_name";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}


}

