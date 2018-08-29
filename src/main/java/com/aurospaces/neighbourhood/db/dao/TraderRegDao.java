
package com.aurospaces.neighbourhood.db.dao;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.TraderReg;
import com.aurospaces.neighbourhood.bean.Users;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseTraderRegDao;




@Repository(value = "traderRegDao")
public class TraderRegDao extends BaseTraderRegDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 
	public TraderReg duplicatecheck(TraderReg procReg){
		 List<TraderReg> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `trader_reg` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {procReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public List<Map<String, Object>> getTraderRegByMobile(String mobile) {
		
		List<Map<String,Object>> retlist=null;
		
		
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from trader_reg where Mobile = '"+mobile+"'";
		/*List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));*/
		
		retlist =jdbcTemplate.queryForList(sql, new Object[]{});
		if(retlist.size() > 0)
			return retlist;
		return null;
	}




	public List<TraderReg> getAlltradersData(Users user) {
		jdbcTemplate = custom.getJdbcTemplate();
		//String sql = "SELECT * from trader_reg ";
		
		/*
		int id =user.getUserId();
		
		String sql ="SELECT * from  trader_reg v  where "
            +" FIND_IN_SET  ((select f.Veg1 from  far_regs f where f.id="+id+"),v.Vegetables)  "
            + " or   FIND_IN_SET  ((select f.Veg2 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg3 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg4 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg5 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg6 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg7 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg8 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Ani_hus1 from  far_regs f where f.id="+id+"),v.Ani_husbandry) "
            + " or   FIND_IN_SET  ((select f.Ani_hus2 from  far_regs f where f.id="+id+"),v.Ani_husbandry) " 
            + " or   FIND_IN_SET  ((select f.Ani_hus3 from  far_regs f where f.id="+id+"),v.Ani_husbandry) "
            + " or   FIND_IN_SET  ((select f.Dairy1 from  far_regs f where f.id="+id+"),v.Dairy) "
            + " or   FIND_IN_SET  ((select f.Dairy2 from  far_regs f where f.id="+id+"),v.Dairy) "
            + " or   FIND_IN_SET  ((select f.Dairy3 from  far_regs f where f.id="+id+"),v.Dairy)" 
            + " or   FIND_IN_SET  ((select f.Crop1 from  far_regs f where f.id="+id+"),v.Crops) " 
            + " or   FIND_IN_SET  ((select f.Crop2 from  far_regs f where f.id="+id+"),v.Crops) "
            + " or   FIND_IN_SET  ((select f.Crop3 from  far_regs f where f.id="+id+"),v.Crops) "
            + " or   FIND_IN_SET  ((select f.Crop4 from  far_regs f where f.id="+id+"),v.Crops) "
            + " or   FIND_IN_SET  ((select f.Crop5 from  far_regs f where f.id="+id+"),v.Crops)" ;
		*/
		
		
		String sql = "select tr.* from trader_transactions tt, far_regs fr,trader_reg tr where tr.Token_id=tt.Token_id and FIND_IN_SET  (tt.Crop_name,  concat(fr.Crop_type,',',fr.Vegetables)) and tt.Status ='inprocess' and Transaction_type like '%buy%' "
					+" and fr.Token_id ='"+user.getTokenId()+"' group by tt.Token_id "

					+" union all"


				+" select tr.* from tda_trans tdt, far_regs fr,trader_reg tr where tr.Token_id=tdt.Token_id and FIND_IN_SET  (tdt.Live_stock, concat(fr.Ani_hus,',',fr.Dairy)) and tdt.Status ='inprocess' and tdt.Transaction_type like '%buy%' " 
				+"and fr.Token_id ='"+user.getTokenId()+"' group by tdt.Token_id ";

		
		System.out.println(sql);
		 
		List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}


	public List<TraderReg> gettradersByStateAndDistic(TraderReg traderReg, FarRegs farregbean) {
		jdbcTemplate = custom.getJdbcTemplate();
		//String sql = "SELECT * from trader_reg  where State = '"+traderReg.getState()+" ' and District ='"+traderReg.getDistrict()+"' ";
		
/*

		int id =traderReg.getId();
		
		String sql ="SELECT * from  trader_reg v  where State = '"+traderReg.getState()+" ' and District ='"+traderReg.getDistrict()+"' and "
            +"( FIND_IN_SET  ((select f.Veg1 from  far_regs f where f.id="+id+"),v.Vegetables)  "
            + " or   FIND_IN_SET  ((select f.Veg2 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg3 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg4 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg5 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg6 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg7 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg8 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Ani_hus1 from  far_regs f where f.id="+id+"),v.Ani_husbandry) "
            + " or   FIND_IN_SET  ((select f.Ani_hus2 from  far_regs f where f.id="+id+"),v.Ani_husbandry) " 
            + " or   FIND_IN_SET  ((select f.Ani_hus3 from  far_regs f where f.id="+id+"),v.Ani_husbandry) "
            + " or   FIND_IN_SET  ((select f.Dairy1 from  far_regs f where f.id="+id+"),v.Dairy) "
            + " or   FIND_IN_SET  ((select f.Dairy2 from  far_regs f where f.id="+id+"),v.Dairy) "
            + " or   FIND_IN_SET  ((select f.Dairy3 from  far_regs f where f.id="+id+"),v.Dairy)" 
            + " or   FIND_IN_SET  ((select f.Crop1 from  far_regs f where f.id="+id+"),v.Crops) " 
            + " or   FIND_IN_SET  ((select f.Crop2 from  far_regs f where f.id="+id+"),v.Crops) "
            + " or   FIND_IN_SET  ((select f.Crop3 from  far_regs f where f.id="+id+"),v.Crops) "
            + " or   FIND_IN_SET  ((select f.Crop4 from  far_regs f where f.id="+id+"),v.Crops) "
            + " or   FIND_IN_SET  ((select f.Crop5 from  far_regs f where f.id="+id+"),v.Crops) )" ;
		*/
		
		
		
		
		String sql = "select tr.* from trader_transactions tt, far_regs fr,trader_reg tr where   tr.State = '"+traderReg.getState()+" ' and tr.District ='"+traderReg.getDistrict()+"' and tr.Token_id=tt.Token_id and FIND_IN_SET  (tt.Crop_name, fr.Dairy) and tt.Status ='inprocess' and Transaction_type like '%buy%' "
				+" and fr.Token_id ='"+traderReg.getTokenId()+"' group by tt.Token_id "

				+" union all"


			+" select tr.* from tda_trans tdt, far_regs fr,trader_reg tr where  tr.State = '"+traderReg.getState()+" ' and tr.District ='"+traderReg.getDistrict()+"' and  tr.Token_id=tdt.Token_id and FIND_IN_SET  (tdt.Live_stock, concat(fr.Ani_hus,',',fr.Dairy)) and tdt.Status ='inprocess' and tdt.Transaction_type like '%buy%' " 
			+"and fr.Token_id ='"+traderReg.getTokenId()+"' group by tdt.Token_id ";

	
	System.out.println(sql);
			
		
		List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}


	public List<TraderReg> getFarRegsByMobile(String userName) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from trader_reg where Mobile = '"+userName+"'";
		System.out.println(sql);
		List<TraderReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(TraderReg.class));
		if(retlist.size() > 0)
			return retlist;
		return Collections.emptyList();
	}

}

