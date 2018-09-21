package com.aurospaces.neighbourhood.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.ProcReg;
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
public class FarmerDashboardprocessorsController {
	
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
	
	
	@RequestMapping(value = "/rest/processorAll")
	public @ResponseBody String getAllProcessors(  @RequestBody FarRegs farregbean,HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			//FarRegs	farregbean  =farRegsDao.getById(user.getTokenId());
			        
				
			List<ProcReg> processorList =	procRegDao.getAllSupplierData(farregbean);
			
			if(processorList == null)
			{
				objJSON.put("processorList",Collections.emptyList());
			}else
			{
				objJSON.put("processorList", processorList);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/distanceprocessors")
	public @ResponseBody String getprocessorsByDistance(@RequestBody Users user,  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
		     FarRegs	farreg  =farRegsDao.getByTokenId(user.getTokenId());
			
		//	List<FarRegs>	farregbean  =farRegsDao.getFarRegsByMobile(user.getUser_name());
			
			List<ProcReg> processorList =	procRegDao.getAllSupplierData(farreg);
			
			if(processorList == null)
			{
				objJSON.put("processors",Collections.emptyList());
				
			}else
			{
				Set<ProcReg> distanceStorageData=getProcessordataByDistence(farreg,processorList);
				objJSON.put("processors",distanceStorageData);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}



	private Set<ProcReg> getProcessordataByDistence(FarRegs farreg, List<ProcReg> processorList) throws IOException {
		Set<ProcReg> distenceProcessorsSet  =new LinkedHashSet<ProcReg>();
		
		for(ProcReg entry :processorList)
		{
		String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+farreg.getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
	    
	    
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
	    
	    distenceProcessorsSet.add(entry);
	    
	    
		}
		return distenceProcessorsSet; 
	}
	
	
	@RequestMapping(value = "/rest/processorsbydisticwise")
	public @ResponseBody String getProcessorsByStateAndDistic(@RequestBody ProcReg procReg, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			//FarRegs	farregbean  =farRegsDao.getById(procReg.getId());  
			
			 FarRegs	farreg  =farRegsDao.getByTokenId(procReg.getTokenId());
				
			List<ProcReg> processorList =	procRegDao.getProcessorsByStateAndDistic(procReg,farreg);
			
			if(processorList == null)
			{
				objJSON.put("processorList", Collections.emptyList());
			}else
			{
				objJSON.put("processorList", processorList);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	
	
	


}
