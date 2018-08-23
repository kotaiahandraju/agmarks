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

import com.aurospaces.neighbourhood.bean.StorageIssue;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

public class BaseStorageIssue {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;

	public final String INSERT_SQL = "INSERT INTO issues(Date_out,Date_in,Name,Mobile,Commodity,Issues,Chamber_no,Cs_no,Bond_no,Os_no,Cs_adj,Loan_amt,Bank,Price,Duration,Token_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final StorageIssue storageIssue) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(storageIssue.getSNo() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					
					
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"SNo"});
ps.setDate(1, java.sql.Date.valueOf(storageIssue.getStrDateOut()));
ps.setDate(2,java.sql.Date.valueOf(storageIssue.getStrDateIn()));
ps.setString(3, storageIssue.getName());
//ps.setDate(4, java.sql.Date.valueOf((supplierIssues.getStrReceiptDate())));
ps.setString(4, storageIssue.getMobile());
ps.setString(5,storageIssue.getCommodity());
ps.setString(6,storageIssue.getIssues() );
ps.setString(7,storageIssue.getChamberNo());
ps.setString(8,storageIssue.getCsNo());
ps.setString(9, storageIssue.getBondNo());
ps.setString(10, storageIssue.getOsNo());
ps.setString(11, storageIssue.getCsAdj());
ps.setString(12, storageIssue.getLoanAmt());
ps.setString(13, storageIssue.getBank());
ps.setString(14, storageIssue.getPrice());
ps.setString(15, storageIssue.getDuration());
ps.setString(16, storageIssue.getTokenId());
							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				storageIssue.setSNo(unId.intValue());
				
				

		}
		else
		{

			String sql = "UPDATE issues set  Date_out  = ? , Date_in  = ? , Name  = ? , Mobile  = ? , Commodity  = ? , Issues  = ? , Chamber_no  = ? , Cs_no  = ? , Bond_no  = ? , Os_no  = ? , Cs_adj  = ? , Loan_amt  = ? , Bank  = ? , Price  = ? , Duration  = ? , Token_id =? where S_no = ? ";
	
			jdbcTemplate.update(sql, new Object[]{storageIssue.getDateOut(),storageIssue.getDateIn(),storageIssue.getName(),storageIssue.getName(),storageIssue.getMobile(),storageIssue.getCommodity(),storageIssue.getIssues(),storageIssue.getChamberNo(),storageIssue.getCsNo(),storageIssue.getBondNo(),storageIssue.getOsNo(),storageIssue.getCsAdj(),storageIssue.getLoanAmt(),storageIssue.getBank(),storageIssue.getPrice(),storageIssue.getDuration(),storageIssue.getTokenId(),storageIssue.getSNo()});
		}
	}
		
		@Transactional
		public void delete(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "DELETE FROM  issues WHERE SNo=?";
			jdbcTemplate.update(sql, new Object[]{id});
		}
		

	 public StorageIssue getBySNo(int id) {
			jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from issues where SNo = ? ";
			List<StorageIssue> retlist = jdbcTemplate.query(sql,new Object[]{},
			ParameterizedBeanPropertyRowMapper.newInstance(StorageIssue.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}


}
