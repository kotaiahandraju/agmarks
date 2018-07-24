package com.aurospaces.neighbourhood.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.AnimalPanchangam;
import com.aurospaces.neighbourhood.bean.ContractBusiness;
import com.aurospaces.neighbourhood.bean.ContractFarmer;
import com.aurospaces.neighbourhood.bean.CostCultivationCrops;
import com.aurospaces.neighbourhood.bean.CostCultivationVeg;
import com.aurospaces.neighbourhood.bean.ImportExport;
import com.aurospaces.neighbourhood.bean.InnovativeCrops;
import com.aurospaces.neighbourhood.bean.Panchangam;
import com.aurospaces.neighbourhood.bean.Pincodedata;
import com.aurospaces.neighbourhood.bean.SeedTesting;
import com.aurospaces.neighbourhood.bean.SoilTesting;
import com.aurospaces.neighbourhood.db.dao.CommPricesDao;
import com.aurospaces.neighbourhood.db.dao.ContractBusinessDao;
import com.aurospaces.neighbourhood.db.dao.ContractFarmerDao;
import com.aurospaces.neighbourhood.db.dao.FarRegsDao;
import com.aurospaces.neighbourhood.db.dao.FarmerTransactionsDao;
import com.aurospaces.neighbourhood.db.dao.FdaTransDao;
import com.aurospaces.neighbourhood.db.dao.ImportsAndExportsDao;
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
	@Autowired ImportsAndExportsDao importsAndExportsDao;
	
	@Autowired CommPricesDao CommPricesDao;
	
	@Autowired FarmerTransactionsDao farmerTransactionsDao;
	
	@Autowired HttpServletRequest request ;
	
	@Autowired JsonReader jsonReader;
	
	@Autowired PanchangamDao panchangamDao;
	@Autowired ContractBusinessDao contractBusinessDao;
	
	@Autowired ContractFarmerDao ContractFarmerDao;
	
	
	

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
	
	
	
	@RequestMapping(value = "/rest/getanimalandbreed")
	public @ResponseBody String getAnimalAndBreed(  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				System.out.println("hello");
				
			List<AnimalPanchangam> animalPanchangamList =	panchangamDao.getAnimalAndBreed();
			
			if(animalPanchangamList == null)
			{
				objJSON.put("animalPanchangamList", "");
			}else
			{
				objJSON.put("animalPanchangamList", animalPanchangamList);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/getanimalpanchangam")
	public @ResponseBody String getAnimalPanchangamDetails( @RequestBody AnimalPanchangam animalPanchangam, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				System.out.println("hello");
				
			List<AnimalPanchangam> animalPanchangamList =	panchangamDao.getAnimalPanchangam(animalPanchangam);
			
			if(animalPanchangamList == null)
			{
				objJSON.put("animalPanchangamList", "");
			}else
			{
				objJSON.put("animalPanchangamList", animalPanchangamList);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	@RequestMapping(value = "/rest/getcropnamesforcost")
	public @ResponseBody String getCropsforCostCultivationCrops(  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				
			List<CostCultivationCrops> cropsNames =	panchangamDao.getCropsNamesForCostCultivationCrops();
			
			if(cropsNames == null)
			{
				objJSON.put("cropsNames", "");
			}else
			{
				objJSON.put("cropsNames", cropsNames);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/getCostDetails")
	public @ResponseBody String getDetailsoOfCost( @RequestBody CostCultivationCrops costCultivationCrops, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				
			List<CostCultivationCrops> cropcostdetails =	panchangamDao.getDetailsForCostCultivationCrops(costCultivationCrops);
			
			if(cropcostdetails == null)
			{
				objJSON.put("cropcostdetails", "");
			}else
			{
				objJSON.put("cropcostdetails", cropcostdetails);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	

	@RequestMapping(value = "/rest/getcropnamesforvegcost")
	public @ResponseBody String getCropsforCostCultivationVeg(  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				
			List<CostCultivationVeg> cropsNames =	panchangamDao.getCropsNamesForCostCultivationVeg();
			
			if(cropsNames == null)
			{
				objJSON.put("cropsNames", "");
			}else
			{
				objJSON.put("cropsNames", cropsNames);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/getCostDetailsVeg")
	public @ResponseBody String getDetailsoOfCostVeg( @RequestBody CostCultivationVeg costCultivationVeg, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				
			List<CostCultivationVeg> cropcostdetails =	panchangamDao.getDetailsForCostCultivationveg(costCultivationVeg);
			
			if(cropcostdetails == null)
			{
				objJSON.put("cropcostdetails", "");
			}else
			{
				objJSON.put("cropcostdetails", cropcostdetails);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	
	@RequestMapping(value = "/rest/getInnovativeCrops")
	public @ResponseBody String getInnovativeCrops(  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				
			List<InnovativeCrops> innovativeCropsDetails =	panchangamDao.getInnovativeCrops();
			
			if(innovativeCropsDetails == null)
			{
				objJSON.put("innovativeCropsDetails", "");
			}else
			{
				objJSON.put("innovativeCropsDetails", innovativeCropsDetails);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/getSeedTestingLabs")
	public @ResponseBody String getSeedTestingLabs(  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				
			List<SeedTesting> seedTestingLabs =	panchangamDao.getSeedTestingLabs();
			
			if(seedTestingLabs == null)
			{
				objJSON.put("seedTestingLabs", "");
			}else
			{
				objJSON.put("seedTestingLabs", seedTestingLabs);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/getDistrictandVillage")
	public @ResponseBody String getSoilTestingDistrictandVillage(  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				
			List<SoilTesting> soilTestingLabs =	panchangamDao.getSoilTestingDistrictAndVillage();
			
			if(soilTestingLabs == null)
			{
				objJSON.put("soilTestingLabs", "");
			}else
			{
				objJSON.put("soilTestingLabs", soilTestingLabs);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	@RequestMapping(value = "/rest/getsoiltestinglabs")
	public @ResponseBody String getSoilTestingLabs( @RequestBody SoilTesting soilTesting, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			        
				
			List<SoilTesting> soiltestlabsDetails =	panchangamDao.getSoilTestingLabsDetails(soilTesting);
			
			if(soiltestlabsDetails == null)
			{
				objJSON.put("soiltestlabsDetails", "");
			}else
			{
				objJSON.put("soiltestlabsDetails", soiltestlabsDetails);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/saveimports")
	public @ResponseBody String saveImportsAndExports( @RequestBody ImportExport importExport, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
				importsAndExportsDao.save(importExport);

			objJSON.put("ImportExportDaoList", "insertedSuccesfully");
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	@RequestMapping(value = "/rest/saveContractBusiness")
    public @ResponseBody String saveContractBusiness(@RequestBody ContractBusiness  contractBusiness,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		
    		
    		contractBusinessDao.save(contractBusiness);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
	
	
	@RequestMapping(value = "/rest/savecontractfarmer")
    public @ResponseBody String saveContractFarmer(@RequestBody ContractFarmer  contractFarmer,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		
    		
    		ContractFarmerDao.save(contractFarmer);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
	
	@RequestMapping(value = "/rest/getPincodes")
	public @ResponseBody String getPincodeData(@RequestBody Pincodedata pincodedata,  HttpServletRequest request) throws Exception {
		List<Map<String,Object>> list=null;
		JSONObject objJSON = new JSONObject();
		try{
			list = pincodedataDao.getPincodes(pincodedata);
			if(list != null){
				objJSON.put("pincodes", list);
				
			}else{
				objJSON.put("pincodes", "");
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	

}
