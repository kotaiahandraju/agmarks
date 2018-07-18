package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.Panchangam;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;

@Repository(value = "panchangamDao")
public class PanchangamDao {
	
	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
	
	
	public List<Panchangam> getCropTypeAndSeason() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select Crop_name,Season from panchangam  order by Crop_name  ";
		System.out.println(sql);
		List<Panchangam> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(Panchangam.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}


	public List<Panchangam> getagriPanchangam(Panchangam panchangam) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select * from panchangam  where Crop_name='"+panchangam.getCropName()+" ' and Season ='"+panchangam.getSeason() +"'"; ;
		System.out.println(sql);
		List<Panchangam> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(Panchangam.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

}
