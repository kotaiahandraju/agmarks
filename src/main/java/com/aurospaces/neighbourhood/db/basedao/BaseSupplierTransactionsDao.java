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

import com.aurospaces.neighbourhood.bean.SupplierTransactions;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseSupplierTransactionsDao 
{
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO supplier_transactions( Master_code,Branch_code,Category,Sub_category,Brand,Product_code,Product_name,Variant,Product_description,Crops_supported,Bid_price,Quantity,Units,Status,SRD,Posted_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final SupplierTransactions supplierTransactions) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(supplierTransactions.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
						
						if(supplierTransactions.getPostedDate() == null)
						{
							supplierTransactions.setPostedDate( new Date());
						}
						java.sql.Timestamp Date = 
							new java.sql.Timestamp(supplierTransactions.getPostedDate().getTime()); 
	
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setString(1, supplierTransactions.getMasterCode());
ps.setString(2, supplierTransactions.getBranchCode());
ps.setString(3,supplierTransactions.getCategory());
ps.setString(4, supplierTransactions.getSubCategory());
ps.setString(5,supplierTransactions.getBrand());
ps.setString(6, supplierTransactions.getProductCode());
ps.setString(7,supplierTransactions.getProductName());
ps.setString(8,supplierTransactions.getVariant());
ps.setString(9, supplierTransactions.getProductDescription());
ps.setString(10, supplierTransactions.getCropsSupported());
ps.setString(11, supplierTransactions.getBidPrice());
ps.setString(12, supplierTransactions.getQuantity());
ps.setString(13, supplierTransactions.getUnits());
ps.setString(14, supplierTransactions.getStatus());
ps.setString(15, supplierTransactions.getSRD());

ps.setTimestamp(16, Date);

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				supplierTransactions.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE supplier_transactions set Master_code = ? , Branch_code = ? ,Category = ? ,Sub_category = ? ,Brand = ? ,Product_code = ? ,Product_name = ? ,Variant = ? ,Product_description = ? ,Crops_supported = ? , Bid_price = ?,Quantity = ? ,Units = ?,Status = ? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{supplierTransactions.getMasterCode(),supplierTransactions.getBranchCode(),supplierTransactions.getCategory(),supplierTransactions.getSubCategory(),supplierTransactions.getBrand(), supplierTransactions.getProductCode(),supplierTransactions.getProductName(),supplierTransactions.getVariant(),supplierTransactions.getProductDescription(), supplierTransactions.getCropsSupported(),supplierTransactions.getBidPrice(),supplierTransactions.getQuantity(),supplierTransactions.getUnits(),supplierTransactions.getStatus(),supplierTransactions.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  supplier_transactions WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public SupplierTransactions getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from supplier_transactions where SNo = ? ";
			List<SupplierTransactions> retlist = jdbcTemplate.query(sql,new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(SupplierTransactions.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}
}
