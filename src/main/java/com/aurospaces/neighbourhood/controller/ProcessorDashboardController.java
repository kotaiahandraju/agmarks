package com.aurospaces.neighbourhood.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.InventoryManagement;
import com.aurospaces.neighbourhood.bean.ProcReg;
import com.aurospaces.neighbourhood.bean.ProcessorTransactions;
import com.aurospaces.neighbourhood.bean.StorageReg;
import com.aurospaces.neighbourhood.bean.Users;
import com.aurospaces.neighbourhood.db.dao.InventoryManagementDao;
import com.aurospaces.neighbourhood.db.dao.ProcRegDao;
import com.aurospaces.neighbourhood.db.dao.ProcessorTransactionsDao;
import com.aurospaces.neighbourhood.db.dao.StorageRegDao;
import com.aurospaces.neighbourhood.util.ImageDecoderUtility;

@Controller
public class ProcessorDashboardController {
	
	@Autowired
	ProcessorTransactionsDao processorTransactionsDao;
	
	@Autowired 
	StorageRegDao storageRegDao;
	
	@Autowired
	ProcRegDao ProcRegDao;
	@Autowired ImageDecoderUtility imageDecoderUtility;
	@Autowired InventoryManagementDao inventoryManagementDao;
	
	
	

	 @RequestMapping(value = "/rest/saveprocessortransaction")
	    public @ResponseBody String saveFarmerTransactions(@RequestBody ProcessorTransactions  processorTransactions,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		
	    		
	    		processorTransactions.setStatus("In Process");
	    		processorTransactionsDao.save(processorTransactions);
					objJSON.put("status", "success");
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	 
	 
	 
	 
	 @RequestMapping(value = "/rest/rmStorageOnprocessor")
	    public @ResponseBody String storageDataOfrawMaterialStorage(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	
	    	ProcReg procReg  =ProcRegDao.getById(user.getUserId());
	    			
	    	Set<StorageReg> storagedata= storageRegDao.getStoragedata(procReg);
	    	  
	    	if(storagedata.isEmpty())
	    		objJSON.put("rawMaterialStorage",Collections.emptyList());
	    	else
	    	{
	    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(procReg,storagedata);
	    		
	    	//objJSON.put("commoditiesStorage",storagedata);
	    	objJSON.put("rawMaterialStorage",distanceStorageData);
	    	}
	    	
			return String.valueOf(objJSON);
	    	
	    	}




	 
		private Set<StorageReg> getStoragedataByDistence(ProcReg procReg, Set<StorageReg> storagedata) throws IOException {

			Set<StorageReg> distencestorageSet  =new LinkedHashSet<StorageReg>();
			
			
			
			for(StorageReg entry :storagedata)
			{
			String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+procReg.getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
		    
		    
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
		
		
		
		
		 @RequestMapping(value = "/rest/svacliniconprocessor")
		    public @ResponseBody String savePlantClinic(@RequestBody InventoryManagement  inventoryManagement,  HttpServletRequest request) throws IOException  {
		    	JSONObject objJSON = new JSONObject();
		    	
		    	int randomPIN = (int)(Math.random()*9000)+1000;
		    	
		    	String ramdomString =String.valueOf(randomPIN);
		    	
		    	String impgpath ="";
		    	
		    	//System.out.println(plantClinic.getImgName());
		    	
		    	try {
		    		inventoryManagement.setStatus("In Process");
		    		//decoder(plantClinic.getImgName(), request,ramdomString);
		    		if(!inventoryManagement.getImgName().isEmpty())
		    		{
		    		 impgpath = imageDecoderUtility.imgDecoder(inventoryManagement.getImgName(),request);
		    		
		    		System.out.println(impgpath);
		    		inventoryManagement.setImgName(impgpath);
		    		}
		    		
		    		inventoryManagementDao.save(inventoryManagement);
						objJSON.put("status", "success");
				} catch (JSONException e) {
					objJSON.put("status", "fail");
					e.printStackTrace();
				}
		    	
				return String.valueOf(objJSON);

		    	
		    }
		 
		

}
