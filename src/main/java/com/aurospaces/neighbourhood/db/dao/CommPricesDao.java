
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.CommPrices;
import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.VegPrices;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseCommPricesDao;




@Repository(value = "commPricesDao")
public class CommPricesDao extends BaseCommPricesDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public List<Map<String,Object>> getCommpricesList(CommPrices commPrices){
		List<Map<String,Object>> list=null;
		
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  `Market`,`District`,`State`,`Market`,`Commodity`,`Variety`,`Units`,`Min_price`,`Max_price`,`Modal_price`,`Unit_of_price`,`Date` FROM `comm_prices` WHERE `District`='"+commPrices.getDistrict()+"'  AND `State`='"+commPrices.getState()+"' GROUP BY  `Commodity`";
			list =jdbcTemplate.queryForList(sql, new Object[]{});
			System.out.println(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	public List<Map<String,Object>> getCommList(String pincode){
		List<Map<String,Object>> list=null;
		
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT * FROM `comm_prices` cp,`pincodedata` pd WHERE cp.`District`=pd.`District` AND pd.`Pincode`=? GROUP BY cp.`Commodity`";
			list =jdbcTemplate.queryForList(sql, new Object[]{pincode});
			System.out.println(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public List<Map<String,Object>> getCommoditiesList(FarRegs farRegs){
		List<Map<String,Object>> list=null;
		
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="select * from comm_prices where Commodity IN ('"+farRegs.getCrop1()+"' , '"+farRegs.getCrop2()+"' ,'"+farRegs.getCrop3()+"' , '"+farRegs.getCrop4()+"' ,'"+farRegs.getCrop5()+"' ) " ; 
			
			list =jdbcTemplate.queryForList(sql, new Object[]{});
			System.out.println(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}

	public List<Map<String, Object>> getDistrictList(CommPrices commPrices) {
List<Map<String,Object>> list=null;
		
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql =" select State ,District from comm_prices group by District order by State " ; 
			
			list =jdbcTemplate.queryForList(sql, new Object[]{});
			System.out.println(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public List<Map<String, Object>> getDistrictList(VegPrices vegPrice) {
		List<Map<String,Object>> list=null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql =" select State ,District from veg_prices group by District order by State " ; 
			
			list =jdbcTemplate.queryForList(sql, new Object[]{});
			System.out.println(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	  
	  
	


}

