
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.PlantClinic;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BasePlantClinicDao;




@Repository(value = "plantClinicDao")
public class PlantClinicDao extends BasePlantClinicDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	public List<Map<String, Object>> getplantClinicTransactions(PlantClinic plantClinic) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date  from plant_clinic where Token_id='"+ plantClinic.getTokenId()+" ' and   Status ='inprocess' ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	}
	public List<Map<String, Object>> getplantClinicTransactionsHistory(PlantClinic plantClinic) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as Date  from plant_clinic where Token_id='"+ plantClinic.getTokenId()+" ' and   Status ='completed'   ";
				List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		//System.out.println(retlist);
		return retlist;
	} 


}

