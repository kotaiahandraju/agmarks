package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.SupplierIssues;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseSupplierissuesDao {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

	public final String INSERT_SQL = "INSERT INTO supplier_issues(Master_code,Branch_code,Consumer_name,Mobile,Address,Issue_date,Os_number,In_stock,Brand,Product_id,Product_code,Product_name,Variant,Issue_stock,Units,Price,due_amount,Token_id,Status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final SupplierIssues supplierIssues) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(supplierIssues.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setString(1, supplierIssues.getMasterCode());
ps.setString(2,supplierIssues.getBranchCode());
ps.setString(3, supplierIssues.getConsumerName());
//ps.setDate(4, java.sql.Date.valueOf((supplierIssues.getStrReceiptDate())));
ps.setString(4, supplierIssues.getMobile());
ps.setString(5,supplierIssues.getAddress());
ps.setDate(6, java.sql.Date.valueOf((supplierIssues.getStrIssueDate())));
ps.setString(7,supplierIssues.getOsNumber());
ps.setString(8,supplierIssues.getInStock());
ps.setString(9, supplierIssues.getBrand());
ps.setString(10, supplierIssues.getProductId());
ps.setString(11, supplierIssues.getProductCode());
ps.setString(12, supplierIssues.getProductName());
ps.setString(13, supplierIssues.getVariant());
ps.setString(14, supplierIssues.getIssueStock());
ps.setString(15, supplierIssues.getUnits());
ps.setString(16, supplierIssues.getPrice());
ps.setString(17, supplierIssues.getDueAmount());
ps.setString(18, supplierIssues.getTokenId());
ps.setString(19, supplierIssues.getStatus());
							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				supplierIssues.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE supplier_issues set  Master_code = ? ,Branch_code = ? ,Consumer_name = ? ,Mobile = ? ,Address = ? ,Os_number = ? ,In_stock = ? ,Brand = ? ,Product_id = ? ,Product_code = ? ,Product_name = ? ,Variant = ? ,Issue_stock = ? ,Units = ? ,Price = ? ,due_amount = ? ,Token_id = ? ,Status = ? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{supplierIssues.getMasterCode(),supplierIssues.getBranchCode(),supplierIssues.getConsumerName(),supplierIssues.getMobile(),supplierIssues.getAddress(),supplierIssues.getStrIssueDate(),supplierIssues.getOsNumber(),supplierIssues.getInStock(),supplierIssues.getBrand(),supplierIssues.getProductId(),supplierIssues.getProductName(),supplierIssues.getVariant(),supplierIssues.getIssueStock(),supplierIssues.getUnits(),supplierIssues.getPrice(),supplierIssues.getDueAmount(), supplierIssues.getTokenId(),supplierIssues.getStatus(),supplierIssues.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  supplier_issues WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public SupplierIssues getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from supplier_issues where S_no = ? ";
			List<SupplierIssues> retlist = jdbcTemplate.query(sql,new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(SupplierIssues.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}


}

