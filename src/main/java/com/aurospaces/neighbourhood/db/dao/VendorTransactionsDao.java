package com.aurospaces.neighbourhood.db.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.VendorReg;
import com.aurospaces.neighbourhood.bean.VendorTransactions;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseVendorTransactionsDao;

@Repository(value = "vendorTransactionsDao")
public class VendorTransactionsDao extends BaseVendorTransactionsDao{
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getVendorsVegTransactions(VendorTransactions vendorTransactions) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date from vendor_transactions where Token_id='"+ vendorTransactions.getTokenId()+" ' and   Status ='In Process' ";
		System.out.println(hql);
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}

	public List<Map<String, Object>> getVendorsVdaTransactions(VendorTransactions vendorTransactions) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date from vda_trans where Token_id='"+ vendorTransactions.getTokenId()+" ' and   Status ='In Process' ";
		System.out.println(hql);
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}

	public List<Map<String, Object>> getVendorsVegTransactionsHistory(VendorTransactions vendorTransactions) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date from vendor_transactions where Token_id='"+ vendorTransactions.getTokenId()+" ' and   Status ='Completed' ";
		System.out.println(hql);
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}

	public List<Map<String, Object>> getVendorsVdaTransactionsHistory(VendorTransactions vendorTransactions) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date from vda_trans where Token_id='"+ vendorTransactions.getTokenId()+" ' and   Status ='Completed' ";
		System.out.println(hql);
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}

	public List<Map<String, Object>> getTradersCommodites(VendorReg vendorBean) {
List<Map<String, Object>> list = Collections.emptyList() ;
		
		String vegitables =vendorBean.getVegetables().replace(",", "' ,'");
		
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="select * from veg_prices where Vegetables IN ( '"+vegitables+"' ) " ; 
			
			 list =jdbcTemplate.queryForList(sql, new Object[]{});
			System.out.println(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return list;
	} 

}
