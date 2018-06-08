
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FdaTrans;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseFdaTransDao;




@Repository(value = "fdaTransDao")
public class FdaTransDao extends BaseFdaTransDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<Map<String, Object>> getFarmerTransactionsOfCrop(FdaTrans fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select * from farmer_transactions where Token_id='"+ fdaTrans.getTokenId()+" ' and   Status ='inprocess' and Transaction_type like '%crop%'  ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	public List<Map<String, Object>> getFarmerTransactionsOfVeg(FdaTrans fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select * from farmer_transactions where Token_id='"+ fdaTrans.getTokenId()+" ' and   Status ='inprocess' and Transaction_type like '%veg%'  ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	public List<Map<String, Object>> getFarmerTransactionsOfCropsHistory(FdaTrans fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select * from farmer_transactions where Token_id='"+ fdaTrans.getTokenId()+" ' and   Status ='completed' and Transaction_type like '%crop%'  ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	}
	public List<Map<String, Object>> getFarmerTransactionsOfVegHistory(FdaTrans fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select * from farmer_transactions where Token_id='"+ fdaTrans.getTokenId()+" ' and   Status ='completed' and Transaction_type like '%veg%'  ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		System.out.println(retlist);
		return retlist;
	} 


}

