
package com.aurospaces.neighbourhood.db.basedao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseUnitsDao{

@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;



}
