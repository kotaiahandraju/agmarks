
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarmerTransactions;
import com.aurospaces.neighbourhood.bean.FdaTrans;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseFdaTransDao;




@Repository(value = "fdaTransDao")
public class FdaTransDao extends BaseFdaTransDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<Map<String, Object>> getFarmerTransactions(FarmerTransactions fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date from farmer_transactions where Token_id='"+ fdaTrans.getTokenId()+" ' and   Status ='In Process' ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}
	
	public List<Map<String, Object>> getFarmerTransactionsHistory(FarmerTransactions fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date  from farmer_transactions where Token_id='"+ fdaTrans.getTokenId()+" ' and   Status ='Completed'   ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}
	
	public List<Map<String, Object>> getFdaTransactions(FdaTrans fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date  from fda_trans where Token_id='"+ fdaTrans.getTokenId()+" ' and   Status ='In Process' ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}
	public List<Map<String, Object>> getFdaTransactionsHistory(FdaTrans fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date  from fda_trans where Token_id='"+ fdaTrans.getTokenId()+" ' and   Status ='Completed'   ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}

	public void editFdaTransactions(FdaTrans fdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();

		java.sql.Timestamp EDD = 
				new java.sql.Timestamp(fdaTrans.getEDD().getTime()); 
		
		String sql = "UPDATE farmer_transactions  set  Quantity = ?,Units = ? ,EDD =?  where S_no = ? ";
		
		jdbcTemplate.update(sql, new Object[]{fdaTrans.getQuantity(),fdaTrans.getUnit(),fdaTrans.getEDD(),fdaTrans.getSNo()});

	} 


}

