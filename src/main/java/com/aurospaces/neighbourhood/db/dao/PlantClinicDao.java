
package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
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
	public List<PlantClinic> getplantClinicTransactions(PlantClinic plantClinic) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as strdate  from plant_clinic where Token_id='"+ plantClinic.getTokenId()+" ' and   Status ='In Process' ";
				//List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
		
		System.out.println(hql);
				List<PlantClinic> retlist = jdbcTemplate.query(hql,
						new Object[]{},
						ParameterizedBeanPropertyRowMapper.newInstance(PlantClinic.class));
				
				System.out.println(retlist);	
				
		return retlist;
	}
	public List<PlantClinic> getplantClinicTransactionsHistory(PlantClinic plantClinic) {
		jdbcTemplate = custom.getJdbcTemplate();
		String hql =" select *,Date(Date) as strdate  from plant_clinic where Token_id='"+ plantClinic.getTokenId()+"' and   Status ='Completed'   ";
				//List<Map<String,Object>>  retlist = jdbcTemplate.queryForList(hql,new Object[]{});
				
		System.out.println(hql);
				
				List<PlantClinic> retlist = jdbcTemplate.query(hql,	new Object[]{},	ParameterizedBeanPropertyRowMapper.newInstance(PlantClinic.class));
		return retlist;
	} 


}

