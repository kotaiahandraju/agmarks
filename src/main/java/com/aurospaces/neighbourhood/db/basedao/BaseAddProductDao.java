package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.AddProduct;
import com.aurospaces.neighbourhood.bean.CommPrices;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseAddProductDao 
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO catalogue( Mobile,Category,Sub_category,Brand,Product_id,Product_code,Product_name,Variant,Crop_type,Image,Quantity,Unit,Price,Status,Product_description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final AddProduct addproduct) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(addproduct.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setString(1, addproduct.getMobile());
ps.setString(2,addproduct.getCategory());
ps.setString(3, addproduct.getSubCategory());
ps.setString(4,addproduct.getBrand());
ps.setString(5,addproduct.getProductId());
ps.setString(6, addproduct.getProductCode());
ps.setString(7,addproduct.getProductName());
ps.setString(8,addproduct.getVariant());
ps.setString(9, addproduct.getCropType());
ps.setString(10, addproduct.getImage());
ps.setString(11, addproduct.getQuantity());
ps.setString(12, addproduct.getUnit());
ps.setString(13, addproduct.getPrice());
ps.setString(14, addproduct.getStatus());
ps.setString(15, addproduct.getProductDescription());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				addproduct.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE catalogue set Mobile = ? ,Category = ? ,Sub_category = ? ,Brand = ? ,Product_id = ? ,Product_code = ? ,Product_name = ? ,Variant = ? ,Crop_type = ? ,Image = ? ,Quantity = ? ,Unit = ?,Price = ?,Status = ? ,Product_description = ? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{addproduct.getMobile(),addproduct.getCategory(),addproduct.getSubCategory(),addproduct.getBrand(),addproduct.getProductId(),addproduct.getProductCode(),addproduct.getProductName(),addproduct.getVariant(),addproduct.getCropType(),addproduct.getImage(),addproduct.getQuantity(),addproduct.getUnit(),addproduct.getPrice(),addproduct.getStatus(),addproduct.getProductDescription(),addproduct.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  AddProduct WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public AddProduct getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from AddProduct where SNo = ? ";
			List<AddProduct> retlist = jdbcTemplate.query(sql,new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(AddProduct.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
}
