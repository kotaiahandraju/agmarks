
package com.aurospaces.neighbourhood.db.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.ProcReg;
import com.aurospaces.neighbourhood.bean.ProcessorTransActions;
import com.aurospaces.neighbourhood.bean.TraderReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseProcRegDao;




@Repository(value = "procRegDao")
public class ProcRegDao extends BaseProcRegDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public ProcReg duplicatecheck(ProcReg procReg){
		 List<ProcReg> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `proc_reg` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {procReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	public List<Map<String, Object>> getProcreggByMobile(String mobile) {
		jdbcTemplate = custom.getJdbcTemplate();
		
		List<Map<String,Object>> retlist=null;

		String sql = "SELECT * from proc_reg where Mobile = '"+mobile+"'";
		/*ist<ProcReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));*/
		
		retlist =jdbcTemplate.queryForList(sql, new Object[]{});
		if(retlist.size() > 0)
			return retlist;
		return null;
	}



	public List<ProcReg> getAllSupplierData(FarRegs farregbean) {
		jdbcTemplate = custom.getJdbcTemplate();
		 
		 
		 String sql  ="select pr.*  from proc_transactions pt, far_regs fr,proc_reg pr where pr.Token_id=pt.Token_id and FIND_IN_SET  (pt.Name, concat(fr.Crop_type,',',fr.Vegetables)) and pt.Status ='In Process' and T_status like '%buy%' " 
                      + " and fr.Token_id ='"+farregbean.getTokenId()+"' group by pt.Token_id ";


				      
		
		System.out.println(sql);
		
		List<ProcReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}



	public List<ProcReg> getProcessorsByStateAndDistic(ProcReg procReg,FarRegs farregbean) {
		jdbcTemplate = custom.getJdbcTemplate();
		
		 
		 String sql  ="select pr.*  from proc_transactions pt, far_regs fr,proc_reg pr where pr.Token_id=pt.Token_id and FIND_IN_SET  (pt.Name, concat(fr.Crop_type,',',fr.Vegetables)) and pt.Status ='In Process' and T_status like '%buy%' " 
                     + "  and pr.State = '"+procReg.getState()+" ' and pr.District ='"+procReg.getDistrict()+"' and fr.Token_id ='"+farregbean.getTokenId()+"' group by pt.Token_id ";

		 System.out.println(sql);
		
		List<ProcReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}



	public List<ProcReg> getAllProcessorsDataOnTraders(TraderReg traderReg) {
		jdbcTemplate = custom.getJdbcTemplate();
		 
		 
		 String sql  ="select pr.*  from proc_transactions pt, trader_reg tr,proc_reg pr where pr.Token_id=pt.Token_id and FIND_IN_SET  (pt.Name, concat(tr.Crop_type,',',tr.Vegetables)) and pt.Status ='In Process' and pt.T_status like '%buy%' " 

                     + " and tr.Token_id ='"+traderReg.getTokenId()+"' group by pt.Token_id ";


				      
		
		System.out.println(sql);
		
		List<ProcReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(ProcReg.class));
		if(retlist.size() > 0)
			return retlist;
		return Collections.emptyList();
	}
	

}

