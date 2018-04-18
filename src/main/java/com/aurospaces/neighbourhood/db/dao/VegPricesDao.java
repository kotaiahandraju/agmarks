
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.VegPrices;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseVegPricesDao;




@Repository(value = "vegPricesDao")
public class VegPricesDao extends BaseVegPricesDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public List<Map<String,Object>> getVegPriceList(VegPrices vegPrice){
		List<Map<String,Object>> list=null;
		
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT `Id`,`State`,`District`,`Market`,`Vegetables`,`Local_rate`,`Date` FROM `veg_prices` WHERE `District`='"+vegPrice.getDistrict()+"'  AND `State`='"+vegPrice.getState()+"' GROUP BY  `Vegetables` ";
			list =jdbcTemplate.queryForList(sql, new Object[]{});
			System.out.println(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}

}

