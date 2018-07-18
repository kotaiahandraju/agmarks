package com.aurospaces.neighbourhood.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.Panchangam;
import com.aurospaces.neighbourhood.bean.Users;
import com.aurospaces.neighbourhood.db.dao.CommPricesDao;
import com.aurospaces.neighbourhood.db.dao.FarRegsDao;
import com.aurospaces.neighbourhood.db.dao.FarmerTransactionsDao;
import com.aurospaces.neighbourhood.db.dao.FdaTransDao;
import com.aurospaces.neighbourhood.db.dao.InputDao;
import com.aurospaces.neighbourhood.db.dao.LogisticsRegDao;
import com.aurospaces.neighbourhood.db.dao.MasAhDao;
import com.aurospaces.neighbourhood.db.dao.MasComDao;
import com.aurospaces.neighbourhood.db.dao.MasDairyDao;
import com.aurospaces.neighbourhood.db.dao.MasVegDao;
import com.aurospaces.neighbourhood.db.dao.PanchangamDao;
import com.aurospaces.neighbourhood.db.dao.PincodedataDao;
import com.aurospaces.neighbourhood.db.dao.PlantClinicDao;
import com.aurospaces.neighbourhood.db.dao.ProcRegDao;
import com.aurospaces.neighbourhood.db.dao.StorageRegDao;
import com.aurospaces.neighbourhood.db.dao.SupplierRegDao;
import com.aurospaces.neighbourhood.db.dao.TraderRegDao;
import com.aurospaces.neighbourhood.db.dao.UsersDao;
import com.aurospaces.neighbourhood.db.dao.VegPricesDao;
import com.aurospaces.neighbourhood.db.dao.VendorRegDao;
import com.aurospaces.neighbourhood.util.JsonReader;

@Controller
public class AgriPanchangamController {
	
	
	@Autowired ServletContext objContext;
	@Autowired CommPricesDao commPricesDao;
	@Autowired UsersDao usersDao;
	@Autowired VegPricesDao vegPricesDao;
	@Autowired PincodedataDao pincodedataDao;
	@Autowired FarRegsDao farRegsDao;
	@Autowired ProcRegDao procRegDao;
	@Autowired MasComDao masComDao;
	@Autowired MasVegDao masVegDao;
	@Autowired MasDairyDao masDairyDao;
	@Autowired StorageRegDao storageRegDao;
	@Autowired TraderRegDao traderRegDao;
	@Autowired VendorRegDao vendorRegDao;
	@Autowired SupplierRegDao supplierRegDao;
	@Autowired MasAhDao masAhDao;
	@Autowired InputDao  inputDao;
	@Autowired LogisticsRegDao logisticsRegDao;
	@Autowired PlantClinicDao plantClinicDao;
	@Autowired FdaTransDao fdaTransDao;
	
	@Autowired CommPricesDao CommPricesDao;
	
	@Autowired FarmerTransactionsDao farmerTransactionsDao;
	
	@Autowired HttpServletRequest request ;
	
	@Autowired JsonReader jsonReader;
	
	@Autowired PanchangamDao panchangamDao;
	
	

	@RequestMapping(value = "/rest/getcropandseason")
	public @ResponseBody String getCropsAndSeason(  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				System.out.println("hello");
				
			List<Panchangam> cropandseasonlistList =	panchangamDao.getCropTypeAndSeason();
			
			if(cropandseasonlistList == null)
			{
				objJSON.put("cropandseasonlist", "");
			}else
			{
				objJSON.put("cropandseasonlist", cropandseasonlistList);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	@RequestMapping(value = "/rest/getagripanchangam")
	public @ResponseBody String getAllVendors( @RequestBody Panchangam panchangam, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				System.out.println("hello");
				
			List<Panchangam> cropandseasonlistList =	panchangamDao.getagriPanchangam(panchangam);
			
			if(cropandseasonlistList == null)
			{
				objJSON.put("cropandseasonlist", "");
			}else
			{
				objJSON.put("cropandseasonlist", cropandseasonlistList);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	

}
