package com.aurospaces.neighbourhood.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	public List<Map<String, Object>> getRegistrationsCounts() {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "select count(*) as counts ,'Farmer' as registeras from users where Status1='Farmer' or  Status2='Farmer' or  Status3='Farmer' or  Status4='Farmer' "
     +" union all "
     +" select count(*) as counts ,'Processor' as registeras from users where Status1='Processor' or  Status2='Processor' or  Status3='Processor' or  Status4='Processor' "
     +" union all  "
     +" select count(*) as counts ,'Supplier' as registeras from users where Status1='Supplier' or  Status2='Supplier' or  Status3='Supplier' or  Status4='Supplier' "
     +" union all "
     +" select count(*) as counts ,'Stroage' as registeras from users where Status1='Stroage' or  Status2='Stroage' or  Status3='Stroage' or  Status4='Stroage' "
     +" union all "
     +" select count(*) as counts ,'Trader' as registeras from users where Status1='Trader' or  Status2='Trader' or  Status3='Trader' or  Status4='Trader' "
     +" union all "
     +" select count(*) as counts ,'Vendor' as registeras from users where Status1='Vendor' or  Status2='Vendor' or  Status3='Vendor' or  Status4='Vendor' "
     +" union all "
     +" select count(*) as counts ,'Logistics' as registeras from users where Status1='Logistics' or  Status2='Logistics' or  Status3='Logistics' or  Status4='Logistics' ";

		System.out.println(sql);
		List<Map<String, Object>> retlist = jdbcTemplate.queryForList(sql);
		
		
		return retlist;
			
	}
	

}
