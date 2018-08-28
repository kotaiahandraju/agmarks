
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.TraderTransactions;
import com.aurospaces.neighbourhood.bean.TradertdaTrans;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseTdaTransDao;




@Repository(value = "tdaTransDao")
public class TdaTransDao extends BaseTdaTransDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<Map<String, Object>> getTraderTransactions(TraderTransactions traderTransactions) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date from farmer_transactions where Token_id='"+ traderTransactions.getTokenId()+" ' and   Status ='In Process' ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}
	
	public List<Map<String, Object>> getTraderTransactionsHistory(TraderTransactions traderTransactions) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date  from farmer_transactions where Token_id='"+ traderTransactions.getTokenId()+" ' and   Status ='Completed'   ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}
	
	public List<Map<String, Object>> getTraderTransactions(TradertdaTrans tradertdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date  from fda_trans where Token_id='"+ tradertdaTrans.getTokenId()+" ' and   Status ='In Process' ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}
	public List<Map<String, Object>> getTraderTransactionsHistory(TradertdaTrans tradertdaTrans) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date  from fda_trans where Token_id='"+ tradertdaTrans.getTokenId()+" ' and   Status ='Completed'   ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	} 


}

