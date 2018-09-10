/**
 * 
 */
package com.aurospaces.neighbourhood.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.castor.util.Base64Decoder;
import org.castor.util.Base64Encoder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.CommPrices;
import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.FarmerTransactions;
import com.aurospaces.neighbourhood.bean.FdaTrans;
import com.aurospaces.neighbourhood.bean.Input;
import com.aurospaces.neighbourhood.bean.LogisticsReg;
import com.aurospaces.neighbourhood.bean.Pincodedata;
import com.aurospaces.neighbourhood.bean.PlantClinic;
import com.aurospaces.neighbourhood.bean.ProcReg;
import com.aurospaces.neighbourhood.bean.StorageReg;
import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.bean.TraderReg;
import com.aurospaces.neighbourhood.bean.Users;
import com.aurospaces.neighbourhood.bean.VegPrices;
import com.aurospaces.neighbourhood.bean.VendorReg;
import com.aurospaces.neighbourhood.bean.WarehouseCapacity;
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
import com.aurospaces.neighbourhood.db.dao.PincodedataDao;
import com.aurospaces.neighbourhood.db.dao.PlantClinicDao;
import com.aurospaces.neighbourhood.db.dao.ProcRegDao;
import com.aurospaces.neighbourhood.db.dao.StorageRegDao;
import com.aurospaces.neighbourhood.db.dao.SupplierRegDao;
import com.aurospaces.neighbourhood.db.dao.TraderRegDao;
import com.aurospaces.neighbourhood.db.dao.UsersDao;
import com.aurospaces.neighbourhood.db.dao.VegPricesDao;
import com.aurospaces.neighbourhood.db.dao.VendorRegDao;
import com.aurospaces.neighbourhood.db.dao.WarehouseCapacityDao;
import com.aurospaces.neighbourhood.util.JsonReader;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;
import com.aurospaces.neighbourhood.util.SendSMS;
import com.fasterxml.jackson.databind.ObjectMapper;

import CommonUtils.CommonUtils;

/**
 * @author YOGI
 */
@Controller
public class RestController {
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
	@Autowired WarehouseCapacityDao warehouseCapacityDao;
	
	

	
	@RequestMapping(value = "/rest/getCommoditiesList")
	public @ResponseBody String getCommodities(@RequestBody CommPrices commPrices,  HttpServletRequest request) throws Exception {
		List<Map<String,Object>> list=null;
		List<Map<String,Object>> districtList=null;
		
		JSONObject objJSON = new JSONObject();
		try{
			list = commPricesDao.getCommpricesList(commPrices);
			districtList = commPricesDao.getDistrictList(commPrices);
			if(list != null){
				objJSON.put("commodities", list);
				objJSON.put("districtList", districtList);
				
				
				
			}else{
				objJSON.put("commodities", "");
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/getcounts")
	public @ResponseBody String getcounts(  HttpServletRequest request) throws Exception {
		int count=0;
		JSONObject objJSON = new JSONObject();
		try{
			count = usersDao.getcounts("Farmer");
				objJSON.put("Farmer", count);
				
				count=0;
			
			count = usersDao.getcounts("Processor");
				objJSON.put("Processor", count);
				count=0;
			
			count = usersDao.getcounts("Warehouse");
				objJSON.put("Warehouse", count);
				count=0;
			
			count = usersDao.getcounts("Trader");
				objJSON.put("Trader", count);
				
				count=0;
			
			count = usersDao.getcounts("Supplier");
				objJSON.put("Supplier", count);
				
				count=0;
			
			count = usersDao.getcounts("Logistics");
				objJSON.put("Logistics", count);
				
				count=0;
			
			count = usersDao.getcounts("Vendor");
				objJSON.put("Vendor", count);
				
			
			

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/getvegpriceList")
	public @ResponseBody String getvegpriceList(@RequestBody VegPrices vegPrice,  HttpServletRequest request) throws Exception {
		List<Map<String,Object>> list=null;
		List<Map<String,Object>> districtList=null;
		JSONObject objJSON = new JSONObject();
		try{
			list = vegPricesDao.getVegPriceList(vegPrice);
			districtList = commPricesDao.getDistrictList(vegPrice);
			if(list != null){
				objJSON.put("vegpriceList", list);
				objJSON.put("districtList", districtList);
				
			}else{
				objJSON.put("vegpriceList", "");
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/getPincodeData")
	public @ResponseBody String getPincodeData(@RequestBody Pincodedata pincodedata,  HttpServletRequest request) throws Exception {
		List<Map<String,Object>> list=null;
		JSONObject objJSON = new JSONObject();
		try{
			list = pincodedataDao.getPincodeData(pincodedata);
			if(list != null){
				objJSON.put("pincodedata", list);
				
			}else{
				objJSON.put("pincodedata", "");
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}

	/**  here generating commodities and veg prices based on picode
	 * @param farRegs
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rest/getmobileandpincode")
	public @ResponseBody String getmobileandpincode(@RequestBody FarRegs farRegs,  HttpServletRequest request)  {
		JSONObject objJSON = new JSONObject();
		try{
			FarRegs	list = farRegsDao.duplicatecheck(farRegs);
			if(list != null){
				objJSON.put("msg", "Alredy exist ");
				return String.valueOf(objJSON);
			}else{
				objJSON.put("msg", "not exist ");
			}
			
			List<Users> userBean = usersDao.mobiledata(farRegs.getMobile());
			if(userBean!= null){
				
				objJSON.put("userlist", userBean);
			}else{
				objJSON.put("userlist", "");
			}
			List<Map<String,Object>> veglist = 	vegPricesDao.getVegitables(farRegs.getPincode());
		objJSON.put("veglist", veglist);
		
		List<Map<String,Object>> commlist = commPricesDao.getCommList(farRegs.getPincode());
		objJSON.put("commlist", commlist);
		}catch(Exception e){
			e.printStackTrace();
			objJSON.put("msg", "fail");
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/sendOtp")
	public @ResponseBody String sendOtp(@RequestBody FarRegs farRegs,  HttpServletRequest request)  {
		List<Map<String,Object>> list=null;
		JSONObject objJSON = new JSONObject();
		InputStream input = null;
		
		 Properties prop = new Properties();
		
		try{
			
			 String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
			 
			 input = new FileInputStream(propertiespath);
			 
			 prop.load(input);
			 
			 String otp =CommonUtils.generatePIN();
				String msg = prop.getProperty("otp");
//				Thank you for registering with Agmarks \nYou are successfully registered as a _type_ \n Username:_phone_ \n PIN:_password_ \nFrom: www.agmarks.com
				msg= msg.replaceAll("_otp_",otp );
			 
			 
		
		SendSMS.sendSMS(msg, farRegs.getMobile(), objContext);
			
			objJSON.put("otp", otp);
			
		}catch(Exception e){
			e.printStackTrace();
			objJSON.put("msg", "fail");
		}
		return String.valueOf(objJSON);
	}
@RequestMapping(value = "/rest/farRegistation")
public @ResponseBody String farRegistation(@RequestBody FarRegs farRegs,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	InputStream input = null;
	String body = null;
	 Properties prop = new Properties();
	try{
		

		 String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
			//String propertiespath = "C:\\PRO\\Database.properties";
	
			input = new FileInputStream(propertiespath);
			// load a properties file
			prop.load(input);
			String msg = prop.getProperty("registration_message");
//			Thank you for registering with Agmarks \nYou are successfully registered as a _type_ \n Username:_phone_ \n PIN:_password_ \nFrom: www.agmarks.com
			msg= msg.replaceAll("_type_", "Farmer");
			msg= msg.replaceAll("_phone_", farRegs.getMobile());
			
			if(farRegs.getId()!=0)
			{
				farRegsDao.save(farRegs);
				
				objJSON.put("Updated", "success");
				return String.valueOf(objJSON);
				
			}
			
			
		KhaibarGasUtil utils=new KhaibarGasUtil();
		String tokenId = utils.randNum(150);
		farRegs.setTokenId(tokenId);
		farRegsDao.save(farRegs);
		List<Users> userBean = usersDao.mobiledata(farRegs.getMobile());
		if(userBean != null){
			for(Users user : userBean){
				if(StringUtils.isBlank(user.getStatus1())){
					usersDao.updateStatus("Farmer","Status1",farRegs.getMobile());
				}else if(StringUtils.isBlank(user.getStatus2())){
					usersDao.updateStatus("Farmer","Status2",farRegs.getMobile());
				}else if(StringUtils.isBlank(user.getStatus3())){
					usersDao.updateStatus("Farmer","Status3",farRegs.getMobile());
				}else if(StringUtils.isBlank(user.getStatus4())){
					usersDao.updateStatus("Farmer","Status4",farRegs.getMobile());
				}
				msg= msg.replaceAll("_password_", user.getPassword());
				
			}
		}else{
			Users usersBean = new Users(); 
			usersBean.setMobile(farRegs.getMobile());
			usersBean.setTokenId(tokenId);
			usersBean.setUser_name(farRegs.getFirstName());
			usersBean.setStatus1("Farmer");
			usersBean.setPassword(CommonUtils.generatePIN());
			usersBean.setCcode(farRegs.getCcode());
			usersDao.save(usersBean);
			msg= msg.replaceAll("_password_", usersBean.getPassword());
		}
		
		
			
			SendSMS.sendSMS(msg, farRegs.getMobile(), objContext);
			
		
		objJSON.put("msg", "success");
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/procduplicatecheck")
public @ResponseBody String procduplicatecheck(@RequestBody ProcReg procReg,  HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		ProcReg	list = procRegDao.duplicatecheck(procReg);
		if(list != null){
			objJSON.put("msg", "Alredy exist ");
			return String.valueOf(objJSON);
		}else{
			objJSON.put("msg", "not exist ");
		}
		
		return String.valueOf(objJSON);
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}


@RequestMapping(value = "/rest/mobileduplicate")
public @ResponseBody String mobileduplicate(@RequestBody ProcReg procReg,  HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		
		List<Users> userBean = usersDao.mobiledata(procReg.getMobile());
		ObjectMapper objectMapper = new ObjectMapper();
		if(userBean!= null){
			
			objJSON.put("userlist", userBean);
		}else{
			objJSON.put("userlist", "");
		}
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/getrawdata")
public @ResponseBody String getrawdata(@RequestBody ProcReg procReg,  HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		
		objJSON.put("masCom", masComDao.getMasCom()) ;
		objJSON.put("masVeg", masVegDao.getVegitables()) ;
		objJSON.put("masDairy", masDairyDao.getMasDairy()) ;
		objJSON.put("masAh", masAhDao.getMasAh()) ;
		
		
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}

@RequestMapping(value = "/rest/processorreg")
public @ResponseBody String processorreg(@RequestBody ProcReg procReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	InputStream input = null;
	String body = null;
	 Properties prop = new Properties();
	try{
		
		
		String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
		//String propertiespath = "C:\\PRO\\Database.properties";

		input = new FileInputStream(propertiespath);
		// load a properties file
		prop.load(input);
		String msg = prop.getProperty("registration_message");
//		Thank you for registering with Agmarks \nYou are successfully registered as a _type_ \n Username:_phone_ \n PIN:_password_ \nFrom: www.agmarks.com
		msg= msg.replaceAll("_type_", "processor");
		msg= msg.replaceAll("_phone_", procReg.getMobile());
		
		
		KhaibarGasUtil utils=new KhaibarGasUtil();
		String tokenId = utils.randNum(150);
		procReg.setTokenId(tokenId);
		procReg.setMyPlan("12121212");
		procRegDao.save(procReg);
		
		List<Users> userBean = usersDao.mobiledata(procReg.getMobile());
		if(userBean != null){
			for(Users user : userBean){
				if(StringUtils.isBlank(user.getStatus1())){
					usersDao.updateStatus("Processor","Status1",procReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus2())){
					usersDao.updateStatus("Processor","Status2",procReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus3())){
					usersDao.updateStatus("Processor","Status3",procReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus4())){
					usersDao.updateStatus("Processor","Status4",procReg.getMobile());
				}
				msg= msg.replaceAll("_password_", user.getPassword());
			}
		}else{
			Users usersBean = new Users(); 
			usersBean.setMobile(procReg.getMobile());
			usersBean.setTokenId(tokenId);
			usersBean.setUser_name(procReg.getFirstName());
			usersBean.setStatus1("Processor");
			usersBean.setPassword(CommonUtils.generatePIN());
			usersBean.setCcode(procReg.getCcode());
			usersDao.save(usersBean);
			msg= msg.replaceAll("_password_", usersBean.getPassword());
		}
		
		 
			
			SendSMS.sendSMS(msg, procReg.getMobile(), objContext);
			objJSON.put("msg", "success");
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}

@RequestMapping(value = "/rest/stroageduplicatecheck")
public @ResponseBody String stroageduplicatecheck(@RequestBody StorageReg storageReg,  HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		StorageReg	list = storageRegDao.duplicatecheck(storageReg);
		if(list != null){
			objJSON.put("msg", "Alredy exist ");
			return String.valueOf(objJSON);
		}else{
			objJSON.put("msg", "not exist ");
		}
		
		return String.valueOf(objJSON);
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/stroagereg")
public @ResponseBody String stroagereg(@RequestBody StorageReg storageReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	InputStream input = null;
	String body = null;
	 Properties prop = new Properties();
	 
	try{
		/*if(StringUtils.isNotBlank(storageReg.getStrdateOfIncorp())){
			storageReg.setDateOfIncorp(KhaibarGasUtil.dateFormate(storageReg.getStrdateOfIncorp()));
		
		}*/
		
		
		String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
		//String propertiespath = "C:\\PRO\\Database.properties";

		input = new FileInputStream(propertiespath);
		// load a properties file
		prop.load(input);
		String msg = prop.getProperty("registration_message");
//		Thank you for registering with Agmarks \nYou are successfully registered as a _type_ \n Username:_phone_ \n PIN:_password_ \nFrom: www.agmarks.com
		msg= msg.replaceAll("_type_", "Stroage");
		msg= msg.replaceAll("_phone_", storageReg.getMobile());
	
		
		
		KhaibarGasUtil utils=new KhaibarGasUtil();
		String tokenId = utils.randNum(150);
		storageReg.setTokenId(tokenId);
		storageReg.setMyPlan("12121212");
		storageRegDao.save(storageReg);
		
		List<Users> userBean = usersDao.mobiledata(storageReg.getMobile());
		if(userBean != null){
			for(Users user : userBean){
				if(StringUtils.isBlank(user.getStatus1())){
					usersDao.updateStatus("Storage","Status1",storageReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus2())){
					usersDao.updateStatus("Storage","Status2",storageReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus3())){
					usersDao.updateStatus("Storage","Status3",storageReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus4())){
					usersDao.updateStatus("Storage","Status4",storageReg.getMobile());
				}
				msg= msg.replaceAll("_password_", user.getPassword());
			}
		}else{
			Users usersBean = new Users(); 
			usersBean.setMobile(storageReg.getMobile());
			usersBean.setTokenId(tokenId);
			usersBean.setUser_name(storageReg.getFirstName());
			usersBean.setStatus1("Storage");
			usersBean.setPassword(CommonUtils.generatePIN());
			usersBean.setCcode(storageReg.getCcode());
			
			usersDao.save(usersBean);
			WarehouseCapacity storageCapacity =new WarehouseCapacity();
			storageCapacity.setTokenId(storageReg.getTokenId());
			storageCapacity.setTotalCapacity(storageReg.getStorageCapacity());
			storageCapacity.setAskPrice("100");
			storageCapacity.setOccupancy("0");
			storageCapacity.setAvailCapacity(storageReg.getStorageCapacity());
			
			
			warehouseCapacityDao.save(storageCapacity);
			msg= msg.replaceAll("_password_", usersBean.getPassword());
		}
		
		 
			
			SendSMS.sendSMS(msg, storageReg.getMobile(), objContext);
			objJSON.put("msg", "success");
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/tradeduplicatecheck")
public @ResponseBody String tradeduplicatecheck(@RequestBody TraderReg traderReg,  HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		TraderReg	list = traderRegDao.duplicatecheck(traderReg);
		if(list != null){
			objJSON.put("msg", "Alredy exist ");
			return String.valueOf(objJSON);
		}else{
			objJSON.put("msg", "not exist ");
		}
		
		return String.valueOf(objJSON);
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/traderreg")
public @ResponseBody String traderreg(@RequestBody TraderReg traderReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	
	InputStream input = null;
	String body = null;
	 Properties prop = new Properties();
	try{
		/*if(StringUtils.isNotBlank(traderReg.getStrdateOfIncorp())){
			traderReg.setDateOfIncorp(KhaibarGasUtil.dateFormate(traderReg.getStrdateOfIncorp()));
		
		}*/
		
		
		String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
		//String propertiespath = "C:\\PRO\\Database.properties";

		input = new FileInputStream(propertiespath);
		// load a properties file
		prop.load(input);
		String msg = prop.getProperty("registration_message");
//		Thank you for registering with Agmarks \nYou are successfully registered as a _type_ \n Username:_phone_ \n PIN:_password_ \nFrom: www.agmarks.com
		msg= msg.replaceAll("_type_", "Trader");
		msg= msg.replaceAll("_phone_", traderReg.getMobile());
		
		
		
		
		KhaibarGasUtil utils=new KhaibarGasUtil();
		String tokenId = utils.randNum(150);
		traderReg.setTokenId(tokenId);
		traderReg.setMyPlan("12121212");
		traderRegDao.save(traderReg);
		
		List<Users> userBean = usersDao.mobiledata(traderReg.getMobile());
		if(userBean != null){
			for(Users user : userBean){
				if(StringUtils.isBlank(user.getStatus1())){
					usersDao.updateStatus("Trader","Status1",traderReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus2())){
					usersDao.updateStatus("Trader","Status2",traderReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus3())){
					usersDao.updateStatus("Trader","Status3",traderReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus4())){
					usersDao.updateStatus("Trader","Status4",traderReg.getMobile());
				}
				msg= msg.replaceAll("_password_", user.getPassword());
			}
		}else{
			Users usersBean = new Users(); 
			usersBean.setMobile(traderReg.getMobile());
			usersBean.setTokenId(tokenId);
			usersBean.setUser_name(traderReg.getFirstName());
			usersBean.setStatus1("Trader");
			usersBean.setPassword(CommonUtils.generatePIN());
			usersBean.setCcode(traderReg.getCcode());
			usersDao.save(usersBean);
			msg= msg.replaceAll("_password_",usersBean.getPassword());
		}
		
		 
			
			SendSMS.sendSMS(msg, traderReg.getMobile(), objContext);
			objJSON.put("msg", "success");
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}


@RequestMapping(value = "/rest/edittraderreg")
public @ResponseBody String EditTraderRegistration(@RequestBody TraderReg traderReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	try{
	traderRegDao.save(traderReg);
	objJSON.put("msg", "success");
}catch(Exception e){
	e.printStackTrace();
	objJSON.put("msg", "fail");
}
	
	
	return String.valueOf(objJSON);
}



@RequestMapping(value = "/rest/vendorduplicatecheck")
public @ResponseBody String vendorduplicatecheck(@RequestBody VendorReg vendorReg,  HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		VendorReg	list = vendorRegDao.duplicatecheck(vendorReg);
		if(list != null){
			objJSON.put("msg", "Alredy exist ");
			return String.valueOf(objJSON);
		}else{
			objJSON.put("msg", "not exist ");
		}
		
		return String.valueOf(objJSON);
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/vendorreg")
public @ResponseBody String vendorreg(@RequestBody VendorReg vendorReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	
	InputStream input = null;
	String body = null;
	 Properties prop = new Properties();
	try{
		String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
		//String propertiespath = "C:\\PRO\\Database.properties";

		input = new FileInputStream(propertiespath);
		// load a properties file
		prop.load(input);
		String msg = prop.getProperty("registration_message");
//		Thank you for registering with Agmarks \nYou are successfully registered as a _type_ \n Username:_phone_ \n PIN:_password_ \nFrom: www.agmarks.com
		msg= msg.replaceAll("_type_", "Vendor");
		msg= msg.replaceAll("_phone_", vendorReg.getMobile());
	
		
		
		KhaibarGasUtil utils=new KhaibarGasUtil();
		String tokenId = utils.randNum(150);
		vendorReg.setTokenId(tokenId);
		vendorReg.setMyPlan("12121212");
		vendorRegDao.save(vendorReg);
		
		List<Users> userBean = usersDao.mobiledata(vendorReg.getMobile());
		if(userBean != null){
			for(Users user : userBean){
				if(StringUtils.isBlank(user.getStatus1())){
					usersDao.updateStatus("Vendor","Status1",vendorReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus2())){
					usersDao.updateStatus("Vendor","Status2",vendorReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus3())){
					usersDao.updateStatus("Vendor","Status3",vendorReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus4())){
					usersDao.updateStatus("Vendor","Status4",vendorReg.getMobile());
				}
				msg= msg.replaceAll("_password_", user.getPassword());
			}
		}else{
			Users usersBean = new Users(); 
			usersBean.setMobile(vendorReg.getMobile());
			usersBean.setTokenId(tokenId);
			usersBean.setUser_name(vendorReg.getFirstName());
			usersBean.setStatus1("Vendor");
			usersBean.setPassword(CommonUtils.generatePIN());
			usersBean.setCcode(vendorReg.getCcode());
			usersDao.save(usersBean);
			msg= msg.replaceAll("_password_",usersBean.getPassword());
		}
		
		 
			
			SendSMS.sendSMS(msg, vendorReg.getMobile(), objContext);
			objJSON.put("msg", "success");
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}


@RequestMapping(value = "/rest/editvendorreg")
public @ResponseBody String editVendorRegistration(@RequestBody VendorReg vendorReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	try
	{
		vendorRegDao.save(vendorReg);
	objJSON.put("msg", "success");
}
	catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	
	return String.valueOf(objJSON);
	
}



@RequestMapping(value = "/rest/supplierduplicatecheck")
public @ResponseBody String supplierduplicatecheck(@RequestBody SupplierReg supplierReg,  HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		SupplierReg	list = supplierRegDao.duplicatecheck(supplierReg);
		if(list != null){
			objJSON.put("msg", "Alredy exist ");
			return String.valueOf(objJSON);
		}else{
			objJSON.put("msg", "not exist ");
		}
		
		return String.valueOf(objJSON);
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/supplierreg")
public @ResponseBody String supplierreg(@RequestBody SupplierReg supplierReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	
	InputStream input = null;
	String body = null;
	 Properties prop = new Properties();
	try{
//		
//		if(StringUtils.isNotBlank(supplierReg.getStrdateOfIncorp())){
//			supplierReg.setDateOfIncorp(KhaibarGasUtil.dateFormate(supplierReg.getStrdateOfIncorp()));
//		
//		}
		
		String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
		//String propertiespath = "C:\\PRO\\Database.properties";

		input = new FileInputStream(propertiespath);
		// load a properties file
		prop.load(input);
		String msg = prop.getProperty("registration_message");
//		Thank you for registering with Agmarks \nYou are successfully registered as a _type_ \n Username:_phone_ \n PIN:_password_ \nFrom: www.agmarks.com
		msg= msg.replaceAll("_type_", "Supplier");
		msg= msg.replaceAll("_phone_", supplierReg.getMobile());
		
		
		KhaibarGasUtil utils=new KhaibarGasUtil();
		String tokenId = utils.randNum(150);
		supplierReg.setTokenId(tokenId);
		supplierReg.setMyPlan("12121212");
		supplierRegDao.save(supplierReg);
		
		List<Users> userBean = usersDao.mobiledata(supplierReg.getMobile());
		if(userBean != null){
			for(Users user : userBean){
				if(StringUtils.isBlank(user.getStatus1())){
					usersDao.updateStatus("Supplier","Status1",supplierReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus2())){
					usersDao.updateStatus("Supplier","Status2",supplierReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus3())){
					usersDao.updateStatus("Supplier","Status3",supplierReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus4())){
					usersDao.updateStatus("Supplier","Status4",supplierReg.getMobile());
				}
				msg= msg.replaceAll("_password_", user.getPassword());
			}
		}else{
			Users usersBean = new Users(); 
			usersBean.setMobile(supplierReg.getMobile());
			usersBean.setTokenId(tokenId);
			usersBean.setUser_name(supplierReg.getFirstName());
			usersBean.setStatus1("Supplier");
			usersBean.setPassword(CommonUtils.generatePIN());
			usersBean.setCcode(supplierReg.getCcode());
			usersDao.save(usersBean);
			msg= msg.replaceAll("_password_",usersBean.getPassword());
		}
		
		 
			
			SendSMS.sendSMS(msg, supplierReg.getMobile(), objContext);
			objJSON.put("msg", "success");
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}


@RequestMapping(value = "/rest/editsupplierreg")
public @ResponseBody String editSupplierRegistration(@RequestBody SupplierReg supplierReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	
	supplierRegDao.save(supplierReg);
	
	objJSON.put("msg", "success");
	
	return String.valueOf(objJSON);
}




@RequestMapping(value = "/rest/supplierslist")
public @ResponseBody String supplierslist( HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		List<Input>	list = inputDao.getinputdata();
		if(list != null){
			objJSON.put("list", list);
			return String.valueOf(objJSON);
		}
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/logisticsduplicatecheck")
public @ResponseBody String logisticsduplicatecheck(@RequestBody LogisticsReg logisticsReg, HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
		LogisticsReg	list = logisticsRegDao.duplicatecheck(logisticsReg);
		if(list != null){
			objJSON.put("msg", "Alredy exist ");
			return String.valueOf(objJSON);
		}else{
			objJSON.put("msg", "not exist ");
		}
		return String.valueOf(objJSON);
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}
@RequestMapping(value = "/rest/logisticsreg")
public @ResponseBody String logisticsreg(@RequestBody LogisticsReg logisticsReg,  HttpServletRequest request)  {
	List<Map<String,Object>> list=null;
	JSONObject objJSON = new JSONObject();
	InputStream input = null;
	String body = null;
	 Properties prop = new Properties();
	try{
		
		
		
		String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
		//String propertiespath = "C:\\PRO\\Database.properties";

		input = new FileInputStream(propertiespath);
		// load a properties file
		prop.load(input);
		String msg = prop.getProperty("registration_message");
//		Thank you for registering with Agmarks \nYou are successfully registered as a _type_ \n Username:_phone_ \n PIN:_password_ \nFrom: www.agmarks.com
		msg= msg.replaceAll("_type_", "Logistics");
		msg= msg.replaceAll("_phone_", logisticsReg.getMobile());
		
		
		KhaibarGasUtil utils=new KhaibarGasUtil();
		String tokenId = utils.randNum(150);
		logisticsReg.setTokenId(tokenId);
		logisticsReg.setMyPlan("12121212");
		logisticsRegDao.save(logisticsReg);
		
		List<Users> userBean = usersDao.mobiledata(logisticsReg.getMobile());
		if(userBean != null){
			for(Users user : userBean){
				if(StringUtils.isBlank(user.getStatus1())){
					usersDao.updateStatus("Logistics","Status1",logisticsReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus2())){
					usersDao.updateStatus("Logistics","Status2",logisticsReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus3())){
					usersDao.updateStatus("Logistics","Status3",logisticsReg.getMobile());
				}else if(StringUtils.isBlank(user.getStatus4())){
					usersDao.updateStatus("Logistics","Status4",logisticsReg.getMobile());
				}
				msg= msg.replaceAll("_password_", user.getPassword());
			}
		}else{
			Users usersBean = new Users(); 
			usersBean.setMobile(logisticsReg.getMobile());
			usersBean.setTokenId(tokenId);
			usersBean.setUser_name(logisticsReg.getFirstName());
			usersBean.setStatus1("Logistics");
			usersBean.setPassword(CommonUtils.generatePIN());
			usersBean.setCcode(logisticsReg.getCcode());
			
			usersDao.save(usersBean);
			msg= msg.replaceAll("_password_",usersBean.getPassword());
		}
		
		 
		
			
			SendSMS.sendSMS(msg, logisticsReg.getMobile(), objContext);
			objJSON.put("msg", "success");
		
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}




@RequestMapping(value = "/rest/userloggedcheck")
public @ResponseBody String userLogggedChecking(@RequestBody Users user,  HttpServletRequest request)  {
	JSONObject objJSON = new JSONObject();
	try{
	List<Users>	list = usersDao.getUserByUsernameAndPassword(user.getUser_name(), user.getPassword(),user.getCcode());
	
	
	List<FarRegs>	farregbean  =farRegsDao.getFarRegsByMobile(user.getUser_name());
	List<Map<String,Object>> listcommonprices =null;
	
	List<Map<String,Object>> vegitablespriceslist =null;
	
	if(farregbean == null)
	{
	 listcommonprices =  null;
	 vegitablespriceslist =null;
	}
	
	else
	{
		 listcommonprices =commPricesDao.getCommoditiesList(farregbean.get(0));
		  vegitablespriceslist =vegPricesDao.getVegitablePrices(farregbean.get(0));
		
	}
	 if(listcommonprices == null)
		 objJSON.put("listcommonprices","");
	 else
		 objJSON.put("commoditiespriceslist",listcommonprices);
	 
	 if(vegitablespriceslist == null)
		 objJSON.put("vegitablespriceslist","");
	 else
		 objJSON.put("vegitablespriceslist",vegitablespriceslist);
	 
	
	Object  status1 =null;
	Object  status2 =null;
	Object  status3 =null;
	Object  status4 =null;
	
		if(list != null){
			objJSON.put("status", "success");
			objJSON.put("userbean",list);
			 
			if(list.get(0).getStatus1() != null)
			   status1 =CheckStatus1Bean(list);
			else
				status1="";
			if(list.get(0).getStatus2() != null)
				   status2 =CheckStatus2Bean(list);
			else
				status2="";
			if(list.get(0).getStatus3() != null)
				   status3 =CheckStatus3Bean(list);
			else
				status3="";
			if(list.get(0).getStatus4() != null)
				   status4 =CheckStatus4Bean(list);
			else
				status4="";
		
			objJSON.put("fstatus1",status1);
			objJSON.put("fstatus2",status2);
			objJSON.put("fstatus3",status3);
			objJSON.put("fstatus4",status4);
			
			
			
			return String.valueOf(objJSON);
		}else{
			objJSON.put("status", "fail");
		}
		
		return String.valueOf(objJSON);
	}catch(Exception e){
		e.printStackTrace();
		objJSON.put("msg", "fail");
	}
	return String.valueOf(objJSON);
}



           public Object CheckStatus1Bean(List<Users> list){
        	   
        	   Users user1 =list.get(0);
        	   Object status1Bean = null;
        	   
        	  
   	        
   	        
   	                   if(user1.getStatus1().equals("Farmer"))
   	                   {
   	                	   
   	                	status1Bean =	   farRegsDao.getFarRegsByMobile(user1.getMobile());
   	                   } 
   	                   else  if(user1.getStatus1().equals("Storage"))
   	                   {
   	                	status1Bean =	storageRegDao.getStorageRegByMobile(user1.getMobile());
   	                	   
   	                   }
   	                else  if(user1.getStatus1().equals("Trader"))
	                   {
   	                	status1Bean        =traderRegDao.getTraderRegByMobile(user1.getMobile());
	                	   
	                   }
   	             else  if(user1.getStatus1().equals("Processor"))
                 {
   	            	status1Bean =	procRegDao.getProcreggByMobile(user1.getMobile());
              	   
                 }
   	          else  if(user1.getStatus1().equals("Logistics"))
              {
   	        	status1Bean       = logisticsRegDao.getLogisticsRegByMobile(user1.getMobile());
           	   
              }
   	       else  if(user1.getStatus1().equals("Supplier"))
           {
   	    	status1Bean       = supplierRegDao.getSupplierRegByMobile(user1.getMobile());
        	   
           }
   	    else  if(user1.getStatus1().equals("Vendor"))
        {
     	   
   	    	status1Bean   =vendorRegDao.getVendorRegByMobile(user1.getMobile());
        }
   	                   
   	                   
   	        
			
			return status1Bean;
        	   
        	   
        	   
        	   
           }
    public Object CheckStatus2Bean(List<Users> list){
        	   
        	   Users user1 =list.get(0);
        	   Object status1Bean = null;
        	   
        	  
   	        
   	        
   	                   if(user1.getStatus2().equals("Farmer"))
   	                   {
   	                	   
   	                	status1Bean =	   farRegsDao.getFarRegsByMobile(user1.getMobile());
   	                   } 
   	                   else  if(user1.getStatus2().equals("Storage"))
   	                   {
   	                	status1Bean =	storageRegDao.getStorageRegByMobile(user1.getMobile());
   	                	   
   	                   }
   	                else  if(user1.getStatus2().equals("Trader"))
	                   {
   	                	status1Bean        =traderRegDao.getTraderRegByMobile(user1.getMobile());
	                	   
	                   }
   	             else  if(user1.getStatus2().equals("Processor"))
                 {
   	            	status1Bean =	procRegDao.getProcreggByMobile(user1.getMobile());
              	   
                 }
   	          else  if(user1.getStatus2().equals("Logistics"))
              {
   	        	status1Bean       = logisticsRegDao.getLogisticsRegByMobile(user1.getMobile());
           	   
              }
   	       else  if(user1.getStatus2().equals("Supplier"))
           {
   	    	status1Bean       = supplierRegDao.getSupplierRegByMobile(user1.getMobile());
        	   
           }
   	    else  if(user1.getStatus2().equals("Vendor"))
        {
     	   
   	    	status1Bean   =vendorRegDao.getVendorRegByMobile(user1.getMobile());
        }
   	                   
   	                   
   	        
			
			return status1Bean;
        	   
        	   
        	   
        	   
           }
    public Object CheckStatus3Bean(List<Users> list){
 	   
 	   Users user1 =list.get(0);
 	   Object status1Bean = null;
 	   
 	  
        
        
                   if(user1.getStatus3().equals("Farmer"))
                   {
                	   
                	status1Bean =	   farRegsDao.getFarRegsByMobile(user1.getMobile());
                   } 
                   else  if(user1.getStatus3().equals("Storage"))
                   {
                	status1Bean =	storageRegDao.getStorageRegByMobile(user1.getMobile());
                	   
                   }
                else  if(user1.getStatus3().equals("Trader"))
                {
                	status1Bean        =traderRegDao.getTraderRegByMobile(user1.getMobile());
             	   
                }
             else  if(user1.getStatus3().equals("Processor"))
          {
            	status1Bean =	procRegDao.getProcreggByMobile(user1.getMobile());
       	   
          }
          else  if(user1.getStatus3().equals("Logistics"))
       {
        	status1Bean       = logisticsRegDao.getLogisticsRegByMobile(user1.getMobile());
    	   
       }
       else  if(user1.getStatus3().equals("Supplier"))
    {
    	status1Bean       = supplierRegDao.getSupplierRegByMobile(user1.getMobile());
 	   
    }
    else  if(user1.getStatus3().equals("Vendor"))
 {
	   
    	status1Bean   =vendorRegDao.getVendorRegByMobile(user1.getMobile());
 }
                   
                   
        
		
		return status1Bean;
 	   
 	   
 	   
 	   
    }
    public Object CheckStatus4Bean(List<Users> list){
 	   
 	   Users user1 =list.get(0);
 	   Object status1Bean = null;
 	   
 	  
        
        
                   if(user1.getStatus4().equals("Farmer"))
                   {
                	   
                	status1Bean =	   farRegsDao.getFarRegsByMobile(user1.getMobile());
                   } 
                   else  if(user1.getStatus4().equals("Storage"))
                   {
                	status1Bean =	storageRegDao.getStorageRegByMobile(user1.getMobile());
                	   
                   }
                else  if(user1.getStatus4().equals("Trader"))
                {
                	status1Bean        =traderRegDao.getTraderRegByMobile(user1.getMobile());
             	   
                }
             else  if(user1.getStatus4().equals("Processor"))
          {
            	status1Bean =	procRegDao.getProcreggByMobile(user1.getMobile());
       	   
          }
          else  if(user1.getStatus4().equals("Logistics"))
       {
        	status1Bean       = logisticsRegDao.getLogisticsRegByMobile(user1.getMobile());
    	   
       }
       else  if(user1.getStatus4().equals("Supplier"))
    {
    	status1Bean       = supplierRegDao.getSupplierRegByMobile(user1.getMobile());
 	   
    }
    else  if(user1.getStatus4().equals("Vendor"))
 {
	   
    	status1Bean   =vendorRegDao.getVendorRegByMobile(user1.getMobile());
 }
                   
                   
        
		
		return status1Bean;
 	   
 	   
 	   
 	   
    }



    @RequestMapping(value = "/rest/buy")
    public @ResponseBody String buyCommediates( HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	List<Map<String, Object>>	list = usersDao.getCategoryandInput();
    	
    	objJSON.put("buylist", list);
    	
    	List<Map<String, Object>>	units = usersDao.getUnits();
    	objJSON.put("units", units);
    	
		return String.valueOf(objJSON);
    }
    @RequestMapping(value = "/rest/savefarmertransaction")
    public @ResponseBody String saveFarmerTransactions(@RequestBody FarmerTransactions  farmerTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		
    		if(StringUtils.isNotBlank(farmerTransactions.getStrEDD())){
    			farmerTransactions.setEDD(KhaibarGasUtil.dateFormate(farmerTransactions.getStrEDD()));
    		
    		}
    		farmerTransactions.setStatus("In Process");
			farmerTransactionsDao.save(farmerTransactions);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
    
    
    @RequestMapping(value = "/rest/editfarmertransaction")
    public @ResponseBody String editFarmerTransactions(@RequestBody FarmerTransactions  farmerTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		
    		if(StringUtils.isNotBlank(farmerTransactions.getStrEDD())){
    			farmerTransactions.setEDD(KhaibarGasUtil.dateFormate(farmerTransactions.getStrEDD()));
    		
    		}
			farmerTransactionsDao.editFarmerTransactions(farmerTransactions);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
    @RequestMapping(value = "/rest/saveplantclinic")
    public @ResponseBody String savePlantClinic(@RequestBody PlantClinic  plantClinic,  HttpServletRequest request) throws IOException  {
    	JSONObject objJSON = new JSONObject();
    	
    	int randomPIN = (int)(Math.random()*9000)+1000;
    	
    	String ramdomString =String.valueOf(randomPIN);
    	
    	String impgpath ="";
    	
    	//System.out.println(plantClinic.getImgName());
    	
    	try {
    		plantClinic.setStatus("In Process");
    		//decoder(plantClinic.getImgName(), request,ramdomString);
    		if(!plantClinic.getImgName().isEmpty())
    		{
    		 impgpath = imgdecoder(plantClinic.getImgName(),request);
    		
    		System.out.println(impgpath);
    		plantClinic.setImgName(impgpath);
    		}
    		
    		plantClinicDao.save(plantClinic);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
   
	@RequestMapping(value = "/rest/commoditiesStorage")
    public @ResponseBody String storageDataOfCommodities(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
    	JSONObject objJSON = new JSONObject();
    	
    	
    	List<FarRegs>	farregbean  =farRegsDao.getFarRegsByMobile(user.getUser_name());
    	Set<StorageReg> storagedata=	storageRegDao.getStoragedata(farregbean.get(0));
    	  
    	if(storagedata.isEmpty())
    		objJSON.put("commoditiesStorage","");
    	else
    	{
    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(farregbean,storagedata);
    		
    	//objJSON.put("commoditiesStorage",storagedata);
    	objJSON.put("commoditiesStorage",distanceStorageData);
    	}
    	
		return String.valueOf(objJSON);
    	
    	}
    
   
	@RequestMapping(value = "/rest/vegetablesStorage")
    public @ResponseBody String storageDataOfVegetables(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
    	JSONObject objJSON = new JSONObject();
    	
    	List<FarRegs>	farregbean  =farRegsDao.getFarRegsByMobile(user.getUser_name());
    	Set<StorageReg> storagedata=	storageRegDao.getStorageDataOVegetablesf(farregbean.get(0));
    	  
    	if(storagedata.isEmpty())
    		objJSON.put("vegetablesStorage","");
    	else
    	{
    		
    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(farregbean,storagedata);
    	//objJSON.put("vegetablesStorage",storagedata);
    		objJSON.put("vegetablesStorage",distanceStorageData);
    	}
    	
		return String.valueOf(objJSON);
    	
    	}
    @RequestMapping(value = "/rest/dairystorage")
    public @ResponseBody String storageDataOfDairy(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
    	JSONObject objJSON = new JSONObject();
    	
    	List<FarRegs>	farregbean  =farRegsDao.getFarRegsByMobile(user.getUser_name());
    	Set<StorageReg> storagedata=	storageRegDao.getStorageDataOfDairy(farregbean.get(0));
    	  
    	if(storagedata.isEmpty())
    		objJSON.put("dairystorage","");
    	else
    	{
    		
    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(farregbean,storagedata);
    	//objJSON.put("dairystorage",storagedata);
    		objJSON.put("dairystorage",distanceStorageData);
    	
    	}
    	
		return String.valueOf(objJSON);
    	
    	}
    @RequestMapping(value = "/rest/animalstorage")
    public @ResponseBody String storageDataOfAnimals(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
    	JSONObject objJSON = new JSONObject();
    	
    	List<FarRegs>	farregbean  =farRegsDao.getFarRegsByMobile(user.getUser_name());
    	Set<StorageReg> storagedata=	storageRegDao.getStorageDataOfAnimals(farregbean.get(0));
    	  
    	if(storagedata.isEmpty())
    		objJSON.put("animalstorage","");
    	else
    	{
    		
    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(farregbean,storagedata);
    	//objJSON.put("animalstorage",storagedata);
    	
    	objJSON.put("animalstorage",distanceStorageData);
    	
    	}
    	
		return String.valueOf(objJSON);
    	
    	}
    

    @RequestMapping(value = "/rest/animalbuy")
    public @ResponseBody String buyAnimals( HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	List<Map<String, Object>>	list = usersDao.getAnimalsInputsForBuy();
    	
    	objJSON.put("animalbuylist", list);
    	
		return String.valueOf(objJSON);
    }
    
    
    @RequestMapping(value = "/rest/dairyproductsbuy")
    public @ResponseBody String DairyProductsBuy( HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	List<Map<String, Object>>	list = usersDao.getDairyProductsForBuy();
    	
    	objJSON.put("dairyproductsbuylist", list);
    	
    	List<Map<String, Object>>	units = usersDao.getDairyProductsUnits();
    	objJSON.put("units", units);
    	
		return String.valueOf(objJSON);
    }
    
    
    @RequestMapping(value = "/rest/savefdatransaction")
    public @ResponseBody String saveFdaTransactions(@RequestBody FdaTrans  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		
        		if(StringUtils.isNotBlank(fdaTrans.getStrEDD())){
        			fdaTrans.setEDD(KhaibarGasUtil.dateFormate(fdaTrans.getStrEDD()));
        		
        		}
    		fdaTrans.setStatus("In Process");
    		fdaTransDao.save(fdaTrans);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
    
    
    
    @RequestMapping(value = "/rest/editfdatransaction")
    public @ResponseBody String editFdaTransactions(@RequestBody FdaTrans  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		
        		if(StringUtils.isNotBlank(fdaTrans.getStrEDD())){
        			fdaTrans.setEDD(KhaibarGasUtil.dateFormate(fdaTrans.getStrEDD()));
        		
        		}
    		fdaTransDao.editFdaTransactions(fdaTrans);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
    
    
    @RequestMapping(value = "/rest/farmerpostings")
    public @ResponseBody String viewPostingsOfFarmerTransactionsOfCrops(@RequestBody FarmerTransactions  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	farmerTransactionslist =fdaTransDao.getFarmerTransactions(fdaTrans);
				objJSON.put("farmerpostings", farmerTransactionslist);
		} catch (JSONException e) {
			objJSON.put("farmerpostings", "fail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
    }
    
    /*@RequestMapping(value = "/rest/vegpostings")
    public @ResponseBody String viewPostingsOfFarmerTransactionsOfVeg(@RequestBody FdaTrans  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	farmerTransactionslist =fdaTransDao.getFarmerTransactionsOfVeg(fdaTrans);
				objJSON.put("vegpostings", farmerTransactionslist);
		} catch (JSONException e) {
			objJSON.put("vegpostings", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    */
    
    
    @RequestMapping(value = "/rest/farmerpostingshistory")
    public @ResponseBody String viewhistoryOfFarmerTransactions(@RequestBody FarmerTransactions  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	farmerTransactionslist =fdaTransDao.getFarmerTransactionsHistory(fdaTrans);
				objJSON.put("farmerhistory", farmerTransactionslist);
		} catch (JSONException e) {
			objJSON.put("farmerhistory", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
  /*  @RequestMapping(value = "/rest/veghistory")
    public @ResponseBody String viewHistoryOfFarmerTransactionsOfVeg(@RequestBody FdaTrans  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	farmerTransactionslist =fdaTransDao.getFarmerTransactionsOfVegHistory(fdaTrans);
				objJSON.put("veghistory", farmerTransactionslist);
		} catch (JSONException e) {
			objJSON.put("veghistory", "");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }*/
    
    
    @RequestMapping(value = "/rest/changepostingsstatus")
    public @ResponseBody String changeFarmerTransactionsStatus(@RequestBody FarmerTransactions  farmerTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		farmerTransactions.setStatus("Completed");
			farmerTransactionsDao.save(farmerTransactions);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
    @RequestMapping(value = "/rest/fdapostings")
    public @ResponseBody String viewPostingsOfFdaTransactions(@RequestBody FdaTrans  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	farmerTransactionslist =fdaTransDao.getFdaTransactions(fdaTrans);
				objJSON.put("fdaspostings", farmerTransactionslist);
		} catch (JSONException e) {
			objJSON.put("fdapostings", "fail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
    }
    
    
    
    @RequestMapping(value = "/rest/fdapostingshistory")
    public @ResponseBody String viewhistoryOfFdaTransactions(@RequestBody FdaTrans  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	farmerTransactionslist =fdaTransDao.getFdaTransactionsHistory(fdaTrans);
				objJSON.put("fdahistory", farmerTransactionslist);
		} catch (JSONException e) {
			objJSON.put("fdahistory", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
    @RequestMapping(value = "/rest/changefdapostingsstatus")
    public @ResponseBody String changeFdaTransactionsStatus(@RequestBody FdaTrans  fdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		fdaTrans.setStatus("Completed");
			fdaTransDao.save(fdaTrans);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    

    @SuppressWarnings("unused")
	@RequestMapping(value = "/rest/forgetpassword")
    public @ResponseBody String forgetpassword(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
    	JSONObject objJSON = new JSONObject();
    	InputStream input = null; 
    	Properties prop = new Properties();

    	String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");

    	 input = new FileInputStream(propertiespath);

    	prop.load(input);
    	
    	 String  msg = prop.getProperty("forget_password");

    	List<Users>	list = usersDao.getUserByUsername(user.getUser_name());
    	
    	 
    	
    	
    	try {
    		if(list == null)
    		{
    			objJSON.put("status", "notFound");
    		}
    		else{
    			msg =msg.replace("_phone_", list.get(0).getMobile());
    			 msg =msg.replace("_password_", list.get(0).getPassword());
			SendSMS.sendSMS(msg, user.getUser_name(), objContext);
			objJSON.put("status", "success");
    		}
		} catch (Exception e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);

    }
    
    @RequestMapping(value = "/rest/clinicpostings")
    public @ResponseBody String viewPostingsOfFdaPostClinic(@RequestBody PlantClinic  plantClinic,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	ArrayList<HashMap<String, String>> prodArrayList = new ArrayList<HashMap<String, String>>();
    	
    
    	
    	try {
    		List<PlantClinic>	plantClinicTransactionslist =plantClinicDao.getplantClinicTransactions(plantClinic);
				if(plantClinicTransactionslist.isEmpty())
				{
    		
    		objJSON.put("clinicpostings", plantClinicTransactionslist);
				}
				else
				{
					objJSON.put("clinicpostings", plantClinicTransactionslist);
					
					
					/*for(PlantClinic entry :plantClinicTransactionslist){
						 HashMap<String,String> imageList =new LinkedHashMap<String,String>();
						
						imageList.put(entry.getImgName(), imgEncoder(entry.getImgName()));
						
						prodArrayList.add(imageList);
						 */
						
					
					
				}		//objJSON.put("imageList", prodArrayList);
				
		} catch (JSONException e) {
			objJSON.put("clinicpostings", "fail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
    }
    
    @RequestMapping(value = "/rest/clinicpostingshistory")
    public @ResponseBody String viewhistoryOfPlantclinicTransactions(@RequestBody  PlantClinic  plantClinic,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	 
    	 ArrayList<HashMap<String, String>> prodArrayList = new ArrayList<HashMap<String, String>>();
    	
    	try {
    		List<PlantClinic>	clinicTransactionslist =plantClinicDao.getplantClinicTransactionsHistory(plantClinic);
				if(clinicTransactionslist.isEmpty())
				{
					objJSON.put("clinichistory", clinicTransactionslist);
				}
				else
				{
    		
    		objJSON.put("clinichistory", clinicTransactionslist);
    		
    		/*for(PlantClinic entry :clinicTransactionslist){
				
    			HashMap<String,String> imageList =new LinkedHashMap<String,String>();
				
				imageList.put(entry.getImgName(), imgEncoder(entry.getImgName()));
				
				prodArrayList.add(imageList);
				 
				
			}
			
			objJSON.put("imageList", prodArrayList);*/
				}
		} catch (JSONException e) {
			objJSON.put("clinichistory", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    
    @RequestMapping(value = "/rest/changeclinicpostingsstatus")
    public @ResponseBody String changeclinicTransactionsStatus(@RequestBody PlantClinic  plantClinic,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		plantClinic.setStatus("Completed");
    		plantClinicDao.save(plantClinic);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
    
    private String  imgdecoder(String imgData, HttpServletRequest request) {
    	
    	String filepath = null;
    	
    	FileOutputStream osf;
    	
    	KhaibarGasUtil utils=new KhaibarGasUtil();
    	
    	String id =utils.randNum(4);
    	
			Base64Decoder decoder = new Base64Decoder(); 
			//byte[] imgBytes = decoder.decode(imgData.split(",")[1]);
			
			byte[] imgBytes = decoder.decode(imgData);
			/*name=name.substring(n + 1);
			name=name+".png";*/
			
			long millis = System.currentTimeMillis() % 1000;
			filepath= id+millis+".png";

		       // String rootPath = request.getSession().getServletContext().getRealPath("/");
			
			String rootPath = System.getProperty("catalina.base");
		       // File dir = new File(rootPath + File.separator + "img");
			
			File dir = new File(rootPath + File.separator + "webapps"+ File.separator + "img");
		        if (!dir.exists()) {
		            dir.mkdirs();
		        }
		        
		        System.out.println(dir);
		        
		        try {
		        	osf = new FileOutputStream(new File(dir.getAbsolutePath() + File.separator + filepath));
		        	osf.write(imgBytes);
					 osf.flush();
		        } catch (IOException e) {
		            System.out.println("error : " + e);
		        }


             // filepath= filepath;
    	
    	return  filepath;
    	
		
	}
    
    
    
private String  imgEncoder(String imgname) {
	
    	String encodedfile = null;
		Base64Encoder encoder =new Base64Encoder();
         //String rootPath = request.getSession().getServletContext().getRealPath("/");
		
		String rootPath = System.getProperty("catalina.base");
		
		File file = new File(rootPath + File.separator + "webapps"+ File.separator +imgname);
		        
		      //  File file = new File(rootPath + File.separator + imgname);
		        
		        System.out.println(file);
		        
		        try {
	                @SuppressWarnings("resource")
					FileInputStream fileInputStreamReader = new FileInputStream(file);
	                byte[] bytes = new byte[(int)file.length()];
	                fileInputStreamReader.read(bytes);
	                encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }


    	
    	return  encodedfile;
    	
		
	}
    	
    
    
@RequestMapping(value = "/rest/distanceAPI")
public @ResponseBody String distenceAPI(@RequestBody PlantClinic  plantClinic,  HttpServletRequest request) throws IOException  {
	JSONObject objJSON = new JSONObject();
	
	String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=521121&destinations=521328&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
    /*URL url = new URL(requestUrl);
    HttpURLConnection uc = (HttpURLConnection)url.openConnection();
    String response = uc.getResponseMessage();
    System.out.println("SMS response:"+response);
    uc.disconnect();
    objJSON.put("test",response );*/
    
    URL obj = new URL(requestUrl);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    // optional default is GET
    con.setRequestMethod("GET");
    //add request header
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    int responseCode = con.getResponseCode();
    
    
    
    
    
    
    String distence[] =null;
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer responses = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
    	System.out.println(inputLine);
    	
    	if(inputLine.contains("text") && !inputLine.contains("mins"))
    			{
    		distence =inputLine.split(":");
    		
    			}
    	
    	responses.append(inputLine);
    }
    
    for(String entry:distence)
    {
    	System.out.println(entry);
    }
    
    System.out.println(distence[1].length());
    
    
    String  finaldistence=distence[1].substring(2, distence[1].indexOf(" mi"));
    
    System.out.println(finaldistence);
    
    
    double d=Double.parseDouble(finaldistence);
    if(d <6.3)
    {
    	
    } 
    
	return null;

	
}


private Set<StorageReg> getStoragedataByDistence(List<FarRegs> farregbean, Set<StorageReg> storagedata) throws IOException {

	Set<StorageReg> distencestorageSet  =new LinkedHashSet<StorageReg>();
	
	
	
	for(StorageReg entry :storagedata)
	{
	String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+farregbean.get(0).getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
    
    
    URL obj = new URL(requestUrl);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    // optional default is GET
    con.setRequestMethod("GET");
    //add request header
    con.setRequestProperty("User-Agent", "Mozilla/5.0");
    int responseCode = con.getResponseCode();
    
    String distence[] =null;
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer responses = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
    	//System.out.println(inputLine);
    	
    	if(inputLine.contains("text") && !inputLine.contains("mins"))
    			{
    		distence =inputLine.split(":");
    		
    			}
    	
    	responses.append(inputLine);
    }
    
  
   // System.out.println(distence[1].length());
    
    
    String  finaldistence=distence[1].substring(2, distence[1].indexOf(" mi"));
    
   // System.out.println(finaldistence);
    
    double d=Double.parseDouble(finaldistence);
    
    
    double distanceInKM =d*1.60934;        //converting distance miles to km .
    
    String result = String.format("%.2f", distanceInKM);
    
  
    entry.setDistance(result);   
    
    distencestorageSet.add(entry);
    
    
    
	}
	return distencestorageSet; 
}


@RequestMapping(value = "/rest/weatherreport")
public @ResponseBody String getWeatherReportByPincode(@RequestBody Users user,  HttpServletRequest request) throws JSONException, IOException  {
	
	 JSONObject  weatherJsonReport = new JSONObject();
	 
	 String picode=user.getUser_name().trim();
	
	String url ="https://maps.googleapis.com/maps/api/geocode/json?address="+picode+"&sensor=false";
	
	System.out.println(url);
	
	JSONObject json = jsonReader.readJsonFromUrl(url);
	
	 
	  JSONArray jsonarray = json.getJSONArray("results");
	  JSONObject obj =null;;
	if (jsonarray.length() > 0) 
	{
           obj = jsonarray.getJSONObject(0);
           
	}
	
	  else
	  {
		  weatherJsonReport.put("weatherJsonReport", "invalidrequest");
		  
		  return String.valueOf(weatherJsonReport);
	  }
	  
	
	
	 JSONObject obj1 =(JSONObject) obj.get("geometry");
	 
	 
	 System.out.println(obj1.get("location"));
	 
	 JSONObject obj2 =(JSONObject) obj1.get("location");
	 
	 
	 String lng = String.valueOf(obj2.get("lng"));
	 
	 String lat =String.valueOf(obj2.get("lat"));
	 
	 String url2 ="https://api.openweathermap.org/data/2.5/forecast?lat="+lng+"&lon="+lat+"&mode=json&appid=990274466483cd969f2a96f84311fb8d&units=metric";
	 
	 
	 System.out.println(url2);
	 JSONObject weatherJson = jsonReader.readJsonFromUrl(url2);
	 
	 
	 
	 JSONArray  weatherObj = weatherJson.getJSONArray("list");
	 System.out.println(weatherObj);
	 
	 //JSONObject  weatherJsonReport = new JSONObject();
	 
	 List<JSONObject>  weatherJsonList = new ArrayList<JSONObject>();
	 
	 
	 for(int i=0; i<weatherObj.length(); i++)
	 {
		 JSONObject  weatherJsonobj = new JSONObject();
		 
		
		 
		 JSONObject     weatherJsonmain =   (JSONObject) weatherObj.getJSONObject(i).get("main");
		 
		 JSONArray     weatherJsonweatherarry =    weatherObj.getJSONObject(i).getJSONArray("weather");
		 
		 String     weatherJsondt_txt =  (String) weatherObj.getJSONObject(i).get("dt_txt");
		 
		 JSONObject     weatherJsonweather =   (JSONObject) weatherJsonweatherarry.getJSONObject(0);
		 
		 
		 weatherJsonobj.put("weather",weatherJsonweather);
		 weatherJsonobj.put("main",weatherJsonmain);
		 
		 weatherJsonobj.put("dt_txt",weatherJsondt_txt);
		 
		 weatherJsonList.add(weatherJsonobj);
		 
		 
		 
		
		 
	 }
	 
	
	 weatherJsonReport.put("weatherJsonReport", weatherJsonList);
	 
	 
	 
	
	
	return String.valueOf(weatherJsonReport);


}
    	
    	
    	
    }



	