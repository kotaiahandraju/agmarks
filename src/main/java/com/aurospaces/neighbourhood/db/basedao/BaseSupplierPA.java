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

import com.aurospaces.neighbourhood.bean.SupplierPA;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseSupplierPA {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	

	public final String INSERT_SQL = "INSERT INTO supplier_pa(Master_code,Branch_code,Consumer_name,Incharge_mobile,Address,T_date,In_stock,Brand,Product_id,Product_code,Product_name,Variant,Receipts,Issues,Units,Total_price,Token_id,Status values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final SupplierPA supplierPA) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(supplierPA.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setString(1, supplierPA.getMasterCode());
ps.setString(2,supplierPA.getBranchCode());
ps.setString(3, supplierPA.getBranchName());
//ps.setDate(4, java.sql.Date.valueOf((supplierPA.getStrReceiptDate())));
ps.setString(4, supplierPA.getConsumerName());
ps.setString(5,supplierPA.getInchargeMobile());
ps.setString(6,supplierPA.getAddress());

//ps.setDate(6, java.sql.Date.valueOf((supplierPA.getStrIssueDate())));
ps.setDate(7,java.sql.Date.valueOf((supplierPA.getStrtdate())));
ps.setString(8,supplierPA.getInStock());
ps.setString(9, supplierPA.getBrand());
ps.setString(10, supplierPA.getProductId());
ps.setString(11, supplierPA.getProductCode());
ps.setString(12, supplierPA.getProductName());
ps.setString(13, supplierPA.getVariant());
ps.setString(14, supplierPA.getReceipts());
ps.setString(15, supplierPA.getIssues());
ps.setString(16, supplierPA.getUnits());
ps.setString(17, supplierPA.getTotalPrice());
ps.setString(18, supplierPA.getTokenId());
ps.setString(19, supplierPA.getStatus());
							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				supplierPA.setSNo(unId.intValue());
				

		}
	
	
	
		else
		{

			String sql = "UPDATE supplier_pa set  Master_code = ? ,Branch_code = ? ,Consumer_name = ? ,Incharge_mobile = ? ,Address = ? ,T_date = ? ,In_stock = ? ,Brand = ? ,Product_id = ? ,Product_code = ? ,Product_name = ? ,Variant = ? ,Receipts = ? ,Issues = ? ,Units = ? ,Total_price = ? ,Token_id = ?,Status =? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{supplierPA.getMasterCode(),supplierPA.getBranchCode(),supplierPA.getBranchName(),supplierPA.getConsumerName(),supplierPA.getInchargeMobile(),supplierPA.getAddress(),supplierPA.getTdate(),supplierPA.getInStock(),supplierPA.getBrand(),supplierPA.getProductId(),supplierPA.getProductCode(),supplierPA.getProductName(),supplierPA.getVariant(),supplierPA.getReceipts(),supplierPA.getIssues(),supplierPA.getUnits(),supplierPA.getTotalPrice(), supplierPA.getTokenId(),supplierPA.getStatus(),supplierPA.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  supplier_pa WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public SupplierPA getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from supplier_pa where SNo = ? ";
			List<SupplierPA> retlist = jdbcTemplate.query(sql,new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SupplierPA.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}



}
