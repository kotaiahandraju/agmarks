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

import com.aurospaces.neighbourhood.bean.StockLedger;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseStockLedgerDao {
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

 
	public final String INSERT_SQL = "INSERT INTO stock_ledger( Master_code,Mobile,Branch_code,Product_code,Opening_balance,Receipts,Issues,Closing_balance,Pay_amount,Bill_amoun,Re_Bal,Token_id,Date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final StockLedger stockLedger) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(stockLedger.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setString(1, stockLedger.getMasterCode());
ps.setString(2,stockLedger.getMobile());
ps.setString(3, stockLedger.getBranchCode());
ps.setString(4,stockLedger.getProductCode());
ps.setString(5, stockLedger.getOpeningBalance());
ps.setString(6,stockLedger.getReceipts());
ps.setString(7,stockLedger.getIssues());
ps.setString(8, stockLedger.getClosingBalance());
ps.setString(9, stockLedger.getPayAmount());
ps.setString(10, stockLedger.getBillAmoun());
ps.setString(11, stockLedger.getReBal());
ps.setString(12, stockLedger.getTokenId());
ps.setDate(13, java.sql.Date.valueOf(stockLedger.getStrdate()));



							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				stockLedger.setSNo(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE supplier_receipts set  Master_code = ? ,Mobile = ? ,Branch_code = ? ,Product_code = ? ,Opening_balance = ? ,Receipts = ? ,Issues = ? ,Closing_balance = ? ,Pay_amount = ? ,Bill_amoun = ? ,Re_Bal = ? ,Token_id = ? ,Date =?  where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{stockLedger.getMasterCode(),stockLedger.getMobile() ,stockLedger.getBranchCode(),stockLedger.getProductCode(),stockLedger.getOpeningBalance(),stockLedger.getReceipts(),stockLedger.getIssues(),stockLedger.getClosingBalance(),stockLedger.getPayAmount(),stockLedger.getBillAmoun(),stockLedger.getReBal(),stockLedger.getTokenId(),stockLedger.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  stock_ledger WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public StockLedger getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from stock_ledger where SNo = ? ";
			List<StockLedger> retlist = jdbcTemplate.query(sql,new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(StockLedger.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

}



