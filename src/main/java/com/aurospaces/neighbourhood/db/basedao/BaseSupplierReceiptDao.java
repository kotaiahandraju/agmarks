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

import com.aurospaces.neighbourhood.bean.SupplierReceipt;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseSupplierReceiptDao {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO supplier_receipts( Master_code,Received_from, Mobile , Receipt_date , Receipt_no,Brand,Product_id,Product_code,Product_name,Variant,Quantity,Units,Received_quantity,Runits,Price, Token_id ,Status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final SupplierReceipt supplierReceipt) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(supplierReceipt.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setString(1, supplierReceipt.getMasterCode());
ps.setString(2,supplierReceipt.getReceivedFrom());
ps.setString(3, supplierReceipt.getMobile());
ps.setDate(4, java.sql.Date.valueOf((supplierReceipt.getStrReceiptDate())));
ps.setString(5,supplierReceipt.getReceiptNo());
ps.setString(6, supplierReceipt.getBrand());
ps.setString(7,supplierReceipt.getProductId());
ps.setString(8,supplierReceipt.getProductCode());
ps.setString(9, supplierReceipt.getProductName());
ps.setString(10, supplierReceipt.getVariant());
ps.setString(11, supplierReceipt.getQuantity());
ps.setString(12, supplierReceipt.getUnits());
ps.setString(13, supplierReceipt.getReceivedQuantity());
ps.setString(14, supplierReceipt.getRunits());
ps.setString(15, supplierReceipt.getPrice());
ps.setString(16, supplierReceipt.getTokenId());
ps.setString(17, supplierReceipt.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				supplierReceipt.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE supplier_receipts set Master_code = ? ,Received_from = ?, Mobile = ? , Receipt_date = ?, Receipt_no = ?, Brand = ? ,Product_id = ? ,Product_code = ? ,Product_name = ? ,Variant = ? ,Quantity = ? ,Units = ? ,Received_quantity = ? ,Runits = ?,Price = ?, Token_id =?, Status = ? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{supplierReceipt.getMasterCode(),supplierReceipt.getReceivedFrom(),supplierReceipt.getMobile(),supplierReceipt.getReceiptDate(),supplierReceipt.getReceiptNo(),supplierReceipt.getBrand(),supplierReceipt.getProductId(),supplierReceipt.getProductCode(),supplierReceipt.getProductName(),supplierReceipt.getVariant(),supplierReceipt.getQuantity(),supplierReceipt.getUnits(),supplierReceipt.getReceivedQuantity(),supplierReceipt.getRunits(),supplierReceipt.getPrice(),supplierReceipt.getTokenId(),supplierReceipt.getStatus(),supplierReceipt.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  supplier_receipts WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public SupplierReceipt getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from supplier_receipts where SNo = ? ";
			List<SupplierReceipt> retlist = jdbcTemplate.query(sql,new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SupplierReceipt.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}
