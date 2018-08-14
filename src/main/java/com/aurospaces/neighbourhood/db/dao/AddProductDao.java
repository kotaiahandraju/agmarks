package com.aurospaces.neighbourhood.db.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.AddProduct;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseAddProductDao;

@Repository(value = "addProductDao ")
public class AddProductDao extends BaseAddProductDao
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<AddProduct>  searchProduct(AddProduct addproduct) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
		String hql="select * from catalogue where Product_code='"+addproduct.getProductCode()+"' and Product_name='"+addproduct.getProductName()+"' and Status='Active'  limit 1";
		List<AddProduct> retlist = jdbcTemplate.query(hql,
				new Object[]{},
				ParameterizedBeanPropertyRowMapper.newInstance(AddProduct.class));
		
		System.out.println(retlist); 
		
		if(retlist.size() > 0)
			return retlist;
		return Collections.emptyList();
	}
	public void removeProduct(AddProduct addproduct) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
		String hql="update catalogue set status='"+addproduct.getStatus()+"' where S_no='"+addproduct.getSNo()+"' ";
		jdbcTemplate.update(hql, new Object[]{});
	}
	public List<Map<String, Object>> listOfProductCatalogs() {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql="select * from catalogue where Status='Active' ";
		List<Map<String, Object>> retlist =jdbcTemplate.queryForList(hql);
		return retlist;
	} 

}
