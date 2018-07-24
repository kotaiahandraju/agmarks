package com.aurospaces.neighbourhood.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.AnimalPanchangam;
import com.aurospaces.neighbourhood.bean.CostCultivationCrops;
import com.aurospaces.neighbourhood.bean.CostCultivationVeg;
import com.aurospaces.neighbourhood.bean.InnovativeCrops;
import com.aurospaces.neighbourhood.bean.Panchangam;
import com.aurospaces.neighbourhood.bean.SeedTesting;
import com.aurospaces.neighbourhood.bean.SoilTesting;
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
	
	
	

	public List<AnimalPanchangam> getAnimalAndBreed() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select Animal_name,Breed from ani_panchangam  order by Animal_name  ";
		System.out.println(sql);
		List<AnimalPanchangam> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(AnimalPanchangam.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	
	
	public List<AnimalPanchangam> getAnimalPanchangam(AnimalPanchangam animalPanchangam) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select * from ani_panchangam  where Animal_name='"+animalPanchangam.getAnimalName()+" ' and Breed ='"+animalPanchangam.getBreed() +"'"; ;
		System.out.println(sql);
		List<AnimalPanchangam> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(AnimalPanchangam.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	
	public List<CostCultivationCrops> getCropsNamesForCostCultivationCrops() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select Crop_name from cost_cultivation_crops  order by Crop_name  ";
		System.out.println(sql);
		List<CostCultivationCrops> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(CostCultivationCrops.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	
	
	public List<CostCultivationCrops> getDetailsForCostCultivationCrops(CostCultivationCrops costCultivationCrops) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select * from cost_cultivation_crops  where Crop_name='"+costCultivationCrops.getCropName()+" '" ;
		System.out.println(sql);
		List<CostCultivationCrops> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(CostCultivationCrops.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	
	
	public List<CostCultivationVeg> getCropsNamesForCostCultivationVeg() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select Crop_name from `cost_cultivation_fruits&veg`  order by Crop_name  ";
		System.out.println(sql);
		List<CostCultivationVeg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(CostCultivationVeg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	
	
	public List<CostCultivationVeg> getDetailsForCostCultivationveg(CostCultivationVeg CostCultivationVeg) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select *,`Yield(MT/Hec)` as YieldMTHec from `cost_cultivation_fruits&veg` where Crop_name='"+CostCultivationVeg.getCropName()+" '" ;
		System.out.println(sql);
		List<CostCultivationVeg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(CostCultivationVeg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	
	public List<InnovativeCrops> getInnovativeCrops() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select * from innovative_crops    ";
		System.out.println(sql);
		List<InnovativeCrops> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(InnovativeCrops.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	
	
	public List<SeedTesting> getSeedTestingLabs() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select * from seed_testing";
		System.out.println(sql);
		List<SeedTesting> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(SeedTesting.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	public List<SoilTesting> getSoilTestingDistrictAndVillage() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select District,Town_village from soil_testing  order by District  ";
		System.out.println(sql);
		List<SoilTesting> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(SoilTesting.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	
	
	public List<SoilTesting> getSoilTestingLabsDetails(SoilTesting soilTesting) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select * from soil_testing  where District='"+soilTesting.getDistrict()+" ' and Town_village ='"+soilTesting.getTownVillage() +"'"; ;
		System.out.println(sql);
		List<SoilTesting> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(SoilTesting.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}
	

}
