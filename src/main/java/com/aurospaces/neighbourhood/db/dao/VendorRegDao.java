
package com.aurospaces.neighbourhood.db.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.TraderReg;
import com.aurospaces.neighbourhood.bean.Users;
import com.aurospaces.neighbourhood.bean.VendorReg;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;
import com.aurospaces.neighbourhood.db.basedao.BaseVendorRegDao;




@Repository(value = "vendorRegDao")
public class VendorRegDao extends BaseVendorRegDao
{
@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate; 

	public VendorReg duplicatecheck(VendorReg  vendorReg){
		 List<VendorReg> list = null;
		try{
			jdbcTemplate = custom.getJdbcTemplate();
			String sql ="SELECT  *  FROM `vendor_reg` WHERE Mobile=?    ";
			
			list = jdbcTemplate.query(sql, new Object[] {vendorReg.getMobile() },ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
			if(list.size() > 0)
				return list.get(0);
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<Map<String, Object>> getVendorRegByMobile(String mobile) {
		jdbcTemplate = custom.getJdbcTemplate();
		
		List<Map<String,Object>> retlist=null;
		
		String sql = "SELECT * from vendor_reg where Mobile = '"+mobile+"'";
		/*List<VendorReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));*/
		retlist =jdbcTemplate.queryForList(sql, new Object[]{});
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

	public List<VendorReg> getAllVendorsData(Users user) {
		jdbcTemplate = custom.getJdbcTemplate();
		//String sql = "SELECT * from vendor_reg ";
		
		int id =user.getUserId();
		
		/*String sql ="SELECT * from  vendor_reg v  where "
            +" FIND_IN_SET  ((select f.Veg1 from  far_regs f where f.id="+id+"),v.Vegetables)  "
            + " or   FIND_IN_SET  ((select f.Veg2 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg3 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg4 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg5 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg6 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg7 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg8 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Ani_hus1 from  far_regs f where f.id="+id+"),v.Ani_hus) "
            + " or   FIND_IN_SET  ((select f.Ani_hus2 from  far_regs f where f.id="+id+"),v.Ani_hus) " 
            + " or   FIND_IN_SET  ((select f.Ani_hus3 from  far_regs f where f.id="+id+"),v.Ani_hus) "
            + " or   FIND_IN_SET  ((select f.Dairy1 from  far_regs f where f.id="+id+"),v.Dairy) "
            + " or   FIND_IN_SET  ((select f.Dairy2 from  far_regs f where f.id="+id+"),v.Dairy) "
            + " or   FIND_IN_SET  ((select f.Dairy3 from  far_regs f where f.id="+id+"),v.Dairy)" ;*/
		
		
		String sql = "select vr.* from vendor_transactions vt, far_regs fr,vendor_reg vr where vr.Token_id=vt.Token_id and FIND_IN_SET  (vt.Crop_name, fr.Vegetables) and vt.Status ='In Process' and Transaction_type like '%buy%' "
				+" and fr.Token_id ='"+user.getTokenId()+"' group by vt.Token_id "

				+" union all "


			 +"	select vr.* from vda_trans vdt, far_regs fr,vendor_reg vr where vr.Token_id=vdt.Token_id and FIND_IN_SET  (vdt.Live_stock, concat(fr.Ani_hus,',',fr.Dairy)) and vdt.Status ='In Process' and vdt.Transaction_type like '%buy%' "
			+"	and fr.Token_id ='"+user.getTokenId()+"' group by vdt.Token_id ";

		
		System.out.println(sql);
		
		
		List<VendorReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

	public List<VendorReg>  getVendorsByStateAndDistic(VendorReg vendorReg) {
		jdbcTemplate = custom.getJdbcTemplate();
		//String sql = "SELECT * from vendor_reg  where State = '"+vendorReg.getState()+" ' and District ='"+vendorReg.getDistance()+"' ";
		
		int id =vendorReg.getId();
		
		/*String sql ="SELECT * from  vendor_reg v  where  State = '"+vendorReg.getState()+" ' and District ='"+vendorReg.getDistrict()+"' and "
            +"( FIND_IN_SET  ((select f.Veg1 from  far_regs f where f.id="+id+"),v.Vegetables)  "
            + " or   FIND_IN_SET  ((select f.Veg2 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg3 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg4 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg5 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg6 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg7 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Veg8 from  far_regs f where f.id="+id+"),v.Vegetables) "
            + " or   FIND_IN_SET  ((select f.Ani_hus1 from  far_regs f where f.id="+id+"),v.Ani_hus) "
            + " or   FIND_IN_SET  ((select f.Ani_hus2 from  far_regs f where f.id="+id+"),v.Ani_hus) " 
            + " or   FIND_IN_SET  ((select f.Ani_hus3 from  far_regs f where f.id="+id+"),v.Ani_hus) "
            + " or   FIND_IN_SET  ((select f.Dairy1 from  far_regs f where f.id="+id+"),v.Dairy) "
            + " or   FIND_IN_SET  ((select f.Dairy2 from  far_regs f where f.id="+id+"),v.Dairy) "
            + " or   FIND_IN_SET  ((select f.Dairy3 from  far_regs f where f.id="+id+"),v.Dairy) )" ;
		*/
		
		
		
		String sql = "select vr.* from vendor_transactions vt, far_regs fr,vendor_reg vr where vr.Token_id=vt.Token_id and FIND_IN_SET  (vt.Crop_name, fr.Vegetables) and vt.Status ='In Process' and Transaction_type like '%buy%' "
				+" and vr.State = '"+vendorReg.getState()+" ' and vr.District ='"+vendorReg.getDistrict()+"' and fr.Token_id ='"+vendorReg.getTokenId()+" ' group by vt.Token_id "

				+" union all "


			 +"	select vr.* from vda_trans vdt, far_regs fr,vendor_reg vr where vr.Token_id=vdt.Token_id and FIND_IN_SET  (vdt.Live_stock, concat(fr.Ani_hus,',',fr.Dairy)) and vdt.Status ='In Process' and vdt.Transaction_type like '%buy%' "
			+"	and  vr.State = '"+vendorReg.getState()+" ' and vr.District ='"+vendorReg.getDistrict()+"' and fr.Token_id ='"+vendorReg.getTokenId()+"' group by vdt.Token_id ";

		
		System.out.println(sql);
		
		
		
		
		List<VendorReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
		if(retlist.size() > 0)
			return retlist;
		return null;
	}

	public List<VendorReg> getByVendorsbeanMobile(String mobile) {
		
		jdbcTemplate = custom.getJdbcTemplate();
		
		String sql ="select * from vendor_reg where Mobile ='"+mobile+"'";
		List<VendorReg> retlist = jdbcTemplate.query(sql,
				new Object[]{},
				ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
				if(retlist.size() > 0)
					return retlist;
				return Collections.emptyList();
	}

	public List<FarRegs> getAllFaramersTransactions(VendorReg vendorReg) {
		jdbcTemplate = custom.getJdbcTemplate();
		
        String sql = "select * from far_regs fr,farmer_transactions ft,vendor_reg vr "
        			+" where ft.Token_id =fr.Token_id and  FIND_IN_SET  (ft.Crop_name, vr.Vegetables)"
        			+ "and ft.Status ='In Process' and ft.Transaction_type like '%buy%' and  tr.Token_id ='"+vendorReg.getTokenId()+"'"
        			+ "group by ft.Token_id ";

	
	System.out.println(sql);
	
	List<FarRegs> retlist = jdbcTemplate.query(sql,
	new Object[]{},
	ParameterizedBeanPropertyRowMapper.newInstance(FarRegs.class));
	if(retlist.size() > 0)
		return retlist;
	return Collections.emptyList();
	}

	

	public List<VendorReg> getVendorRegsByMobile(String mobile) {
		jdbcTemplate = custom.getJdbcTemplate();
		String sql = "SELECT * from vendor_reg where Mobile = '"+mobile+"'";
		System.out.println(sql);
		List<VendorReg> retlist = jdbcTemplate.query(sql,
		new Object[]{},
		ParameterizedBeanPropertyRowMapper.newInstance(VendorReg.class));
		if(retlist.size() > 0)
			return retlist;
		return Collections.emptyList();
	}

	public List<FarRegs> getFaramersTransactionsBystateAndDistrict(VendorReg vendorReg, VendorReg vendorReg2) {
jdbcTemplate = custom.getJdbcTemplate();
		
        String sql = "select * from far_regs fr,farmer_transactions ft,vendor_reg vr "
        			+" where ft.Token_id =fr.Token_id and  FIND_IN_SET  (ft.Crop_name, vr.Vegetables)"
        			+ "and ft.Status ='In Process' and ft.Transaction_type like '%buy%' and  tr.Token_id ='"+vendorReg2.getTokenId()+"'"
        			+ "and fr.State = '"+vendorReg.getState()+" ' and fr.District ='"+vendorReg.getDistrict()+"'   "
        			+ "group by ft.Token_id ";

	
	System.out.println(sql);
	
	List<FarRegs> retlist = jdbcTemplate.query(sql,
	new Object[]{},
	ParameterizedBeanPropertyRowMapper.newInstance(FarRegs.class));
	if(retlist.size() > 0)
		return retlist;
	return Collections.emptyList();
	}

	
	
	
}

