package com.aurospaces.neighbourhood.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.FarRegs;
import com.aurospaces.neighbourhood.bean.LogisticsReg;
import com.aurospaces.neighbourhood.bean.StorageReg;
import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.bean.TraderReg;
import com.aurospaces.neighbourhood.bean.Users;
import com.aurospaces.neighbourhood.bean.VendorClinic;
import com.aurospaces.neighbourhood.bean.VendorReg;
import com.aurospaces.neighbourhood.bean.VendorTransactions;
import com.aurospaces.neighbourhood.bean.VendorsvdaTrans;
import com.aurospaces.neighbourhood.db.dao.StorageRegDao;
import com.aurospaces.neighbourhood.db.dao.VendorClinicDao;
import com.aurospaces.neighbourhood.db.dao.VendorRegDao;
import com.aurospaces.neighbourhood.db.dao.VendorTransactionsDao;
import com.aurospaces.neighbourhood.db.dao.VendorsVdaTransactions;
import com.aurospaces.neighbourhood.util.ImageDecoderUtility;

@Controller
public class VendorDashboardController {
	
	@Autowired VendorTransactionsDao vendorTransactionsDao;
	@Autowired VendorsVdaTransactions vendorsVdaTransactions;
	@Autowired VendorRegDao vendorRegDao;
	@Autowired StorageRegDao storageRegDao;
	@Autowired VendorClinicDao vendorClinicDao;
	@Autowired ImageDecoderUtility imageDecoderUtility;
		
	@RequestMapping(value = "/rest/savevendorTransaction")
    public @ResponseBody String saveVendorTransactions(@RequestBody VendorTransactions  vendorTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		
    		
    		vendorTransactions.setStatus("In Process");
    		vendorTransactionsDao.save(vendorTransactions);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
	
	@RequestMapping(value = "/rest/savevdatransaction")
    public @ResponseBody String saveFdaTransactions(@RequestBody VendorsvdaTrans  vendorsvdaTrans,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		
        		
    		vendorsvdaTrans.setStatus("In Process");
    		vendorsVdaTransactions.save(vendorsvdaTrans);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
	
	
	@RequestMapping(value = "/rest/vendorvegpostings")
    public @ResponseBody String viewPostingsOfvendorVegTransactionsOfCrops(@RequestBody VendorTransactions  vendorTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	vendorTransactionslist =vendorTransactionsDao.getVendorsVegTransactions(vendorTransactions);
				objJSON.put("status", vendorTransactionslist);
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
    }
	
	@RequestMapping(value = "/rest/vendorvdapostings")
    public @ResponseBody String viewPostingsOfVendorVdaTransactions(@RequestBody VendorTransactions  vendorTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	vendorTransactionslist =vendorTransactionsDao.getVendorsVdaTransactions(vendorTransactions);
				objJSON.put("status", vendorTransactionslist);
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
    }
	
	
	
	@RequestMapping(value = "/rest/vendorvegpostingshistory")
    public @ResponseBody String viewPostingsOfvendorVegTransactionsHistory(@RequestBody VendorTransactions  vendorTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	vendorTransactionslist =vendorTransactionsDao.getVendorsVegTransactionsHistory(vendorTransactions);
				objJSON.put("status", vendorTransactionslist);
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
    }
	
	
	
	@RequestMapping(value = "/rest/vendorvdapostingsHistory")
    public @ResponseBody String viewPostingsOfVendorVdaTransactionsHistory(@RequestBody VendorTransactions  vendorTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
    	try {
    		List<Map<String, Object>>	vendorTransactionslist =vendorTransactionsDao.getVendorsVdaTransactionsHistory(vendorTransactions);
				objJSON.put("status", vendorTransactionslist);
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
    }
	
	
	@RequestMapping(value = "/rest/changevtstatus")
    public @ResponseBody String changeVendorTransactionsStatus(@RequestBody VendorTransactions  vendorTransactions,  HttpServletRequest request)  {
    	JSONObject objJSON = new JSONObject();
    	
try {
    		
    		
    		vendorTransactions.setStatus("Completed");
    		vendorTransactionsDao.save(vendorTransactions);
				objJSON.put("status", "success");
		} catch (JSONException e) {
			objJSON.put("status", "fail");
			e.printStackTrace();
		}
    	
		return String.valueOf(objJSON);

    	
    }
	
	
	
	 @RequestMapping(value = "/rest/changevdatstatus")
	    public @ResponseBody String changeVendorVDAStatus(@RequestBody VendorsvdaTrans  vendorsvdaTrans,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		
        		
	    		vendorsvdaTrans.setStatus("Completed");
	    		vendorsVdaTransactions.save(vendorsvdaTrans);
					objJSON.put("status", "success");
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	 
	 
	 
	 
	 @RequestMapping(value = "/rest/vegitablesonvendor")
	    public @ResponseBody String getVegitablesOnVendor(@RequestBody VendorReg  vendorReg,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	VendorReg vendorBean = vendorRegDao.getById(vendorReg.getId());
	    	
	    	List<Map<String, Object>>	vendorsVegitableslist = vendorTransactionsDao.getTradersCommodites(vendorBean);
	    	objJSON.put("status", vendorsVegitableslist);
			return String.valueOf(objJSON);

	    	
	    }
	 
	 
	 @RequestMapping(value = "/rest/vegetablesStorageonvendors")
	    public @ResponseBody String storageDataOfVegetablesOnVendors(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	List<VendorReg>	vendorRegBean  =vendorRegDao.getByVendorsbeanMobile(user.getMobile());
	    	Set<StorageReg> storagedata=	storageRegDao.getStorageDataOVegetablesonVendors(vendorRegBean.get(0));
	    	  
	    	if(storagedata.isEmpty())
	    		objJSON.put("vegetablesStorage","");
	    	else
	    	{
	    		
	    		Set<StorageReg> distanceStorageData=getStorageDataByDistence(vendorRegBean,storagedata);
	    	//objJSON.put("vegetablesStorage",storagedata);
	    		objJSON.put("vegetablesStorage",distanceStorageData);
	    	}
	    	
			return String.valueOf(objJSON);
	    	
	    	}

	private Set<StorageReg> getStorageDataByDistence(List<VendorReg> vendorRegBean, Set<StorageReg> storagedata) throws IOException {
		Set<StorageReg> distencestorageSet  =new LinkedHashSet<StorageReg>();
		
		
		
		for(StorageReg entry :storagedata)
		{
		String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+vendorRegBean.get(0).getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
	    
	    
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
	
	
	
	 @RequestMapping(value = "/rest/savevendorplantclinic")
	    public @ResponseBody String savePlantClinic(@RequestBody VendorClinic  plantClinic,  HttpServletRequest request) throws IOException  {
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
	    		 impgpath = imageDecoderUtility.imgDecoder(plantClinic.getImgName(),request);
	    		
	    		System.out.println(impgpath);
	    		plantClinic.setImgName(impgpath);
	    		}
	    		
	    		vendorClinicDao.save(plantClinic);
					objJSON.put("status", "success");
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	 
	 
	 @RequestMapping(value = "/rest/farmersAllonvendors")
		public @ResponseBody String getAllFarmersOnTraders( @RequestBody VendorReg vendorReg,HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				//FarRegs	farregbean  =farRegsDao.getById(user.getTokenId());
				        
					
				List<FarRegs> farmersList =	vendorRegDao.getAllFaramersTransactions(vendorReg);
				
				if(farmersList.isEmpty())
				{
					objJSON.put("farmers",Collections.emptyList());
				}else
				{
					objJSON.put("farmers", farmersList);
				}

				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		} 
	 
	 
	 
	 @RequestMapping(value = "/rest/farmersbydisticwiseonvendors")
		public @ResponseBody String getFarmersByStateAndDisticOnTraders(@RequestBody VendorReg vendorReg, HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				
				 
				 List<VendorReg>	vendorRegbean  =vendorRegDao.getVendorRegsByMobile(vendorReg.getMobile());
				 
				 
					
				List<FarRegs> processorList =	vendorRegDao.getFaramersTransactionsBystateAndDistrict(vendorReg,vendorRegbean.get(0));
				
				if(processorList == null)
				{
					objJSON.put("farmers", Collections.emptyList());
				}else
				{
					objJSON.put("farmers", processorList);
				}

				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}
	 
	 
	 
	 @RequestMapping(value = "/rest/distancefarmerssonvendors")
		public @ResponseBody String getFarmersByDistance(@RequestBody Users user,  HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				 
				 List<VendorReg>	vendorRegbean  =vendorRegDao.getVendorRegsByMobile(user.getMobile());
				 
					List<FarRegs> farmersList =	vendorRegDao.getAllFaramersTransactions(vendorRegbean.get(0));
				
				
				
				if(farmersList.isEmpty())
				{
					objJSON.put("farmers",Collections.emptyList());
					
				}else
				{
					Set<FarRegs> distanceStorageData=getfarmersDataByDistenceOnVendors(vendorRegbean.get(0),farmersList);
					objJSON.put("farmers",distanceStorageData);
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}

	private Set<FarRegs> getfarmersDataByDistenceOnVendors(VendorReg vendorReg, List<FarRegs> farmersList) throws IOException {
		Set<FarRegs> distenceFarmersSet  =new LinkedHashSet<FarRegs>();
		
		for(FarRegs entry :farmersList)
		{
		String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+vendorReg.getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
	    
	    
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
	    
	    distenceFarmersSet.add(entry);
	    
	    
		}
		return distenceFarmersSet; 
	}
	 
	 
	 
	 
	@RequestMapping(value = "/rest/logisticsAllonvendors")
	public @ResponseBody String getAllLogisticsOnVendors( @RequestBody LogisticsReg logisticsReg,HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			//FarRegs	farregbean  =farRegsDao.getById(user.getTokenId());
			        
				
			List<LogisticsReg> farmersList =	vendorRegDao.getAllLogistics();
			
			if(farmersList.isEmpty())
			{
				objJSON.put("farmers",Collections.emptyList());
			}else
			{
				objJSON.put("farmers", farmersList);
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	} 
	
	
	
	
	@RequestMapping(value = "/rest/distancelogisticsOnvendors")
	public @ResponseBody String getSuppliersByDistance(@RequestBody Users user,  HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			 
			List<VendorReg>	vendorRegbean  =vendorRegDao.getVendorRegsByMobile(user.getMobile());
			 
				List<LogisticsReg> logisticsList =	vendorRegDao.getAllLogistics();
			
			
			
			if(logisticsList.isEmpty())
			{
				objJSON.put("status",Collections.emptyList());
				
			}else
			{
				Set<LogisticsReg> distanceStorageData=getLogisticsdataByDistenceOnVendors(vendorRegbean.get(0),logisticsList);
				objJSON.put("status",distanceStorageData);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}

	private Set<LogisticsReg> getLogisticsdataByDistenceOnVendors(VendorReg vendorReg,
			List<LogisticsReg> logisticsList) throws IOException {
Set<LogisticsReg> distenceLogisticsSet  =new LinkedHashSet<LogisticsReg>();
		
		for(LogisticsReg entry :logisticsList)
		{
		String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+vendorReg.getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
	    
	    
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
	    
	    distenceLogisticsSet.add(entry);
	    
	    
		}
		return distenceLogisticsSet; 
	}
	
	 @RequestMapping(value = "/rest/logisticsbydisticwiseonvendors")
		public @ResponseBody String geLogisticstByStateAndDisticOnTraders(@RequestBody VendorReg vendorReg, HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				
				List<LogisticsReg> processorList =	vendorRegDao.getLogisticsBystateAndDistrict(vendorReg);
				
				if(processorList == null)
				{
					objJSON.put("farmers", Collections.emptyList());
				}else
				{
					objJSON.put("farmers", processorList);
				}

				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}
	 
	 
	 
	 
	 @RequestMapping(value = "/rest/tradersAllOnvendors")
		public @ResponseBody String getAllTradaersonVendors( @RequestBody Users user, HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				 List<VendorReg>	vendorRegbean  =vendorRegDao.getVendorRegsByMobile(user.getMobile());
				        
					System.out.println("hello");
					
				List<TraderReg> tradersList =	vendorRegDao.getAlltradersDataOnvendors(user);
				
				if(tradersList == null)
				{
					objJSON.put("tradersList", Collections.emptyList());
				}else
				{
					objJSON.put("tradersList", tradersList);
				}

				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}
	 
	 
	 
	 @RequestMapping(value = "/rest/distancetradersonvendors")
		public @ResponseBody String getTraddersByDistance(@RequestBody Users user,  HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				        
				List<VendorReg>	vendorRegbean  =vendorRegDao.getVendorRegsByMobile(user.getMobile());
				
				List<TraderReg> tradeorsList =	vendorRegDao.getAlltradersDataOnvendors(user);
				
				if(tradeorsList == null)
				{
					objJSON.put("tradersList", Collections.emptyList());
					
				}else
				{
					Set<TraderReg> distanceStorageData=gettradersdataByDistence(vendorRegbean.get(0),tradeorsList);
					objJSON.put("tradersList",distanceStorageData);
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}

		
	 
	 private Set<TraderReg> gettradersdataByDistence(VendorReg vendorReg, List<TraderReg> tradeorsList) throws IOException {
			Set<TraderReg> distencetradersSet  =new LinkedHashSet<TraderReg>();
			
			for(TraderReg entry :tradeorsList)
			{
			String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+vendorReg.getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
		    
		    
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
		    
		    distencetradersSet.add(entry);
		    
		    
			}
			return distencetradersSet; 
		}
	 
	 
	 
	 
	 @RequestMapping(value = "/rest/tradersbydisticwiseonvendors")
		public @ResponseBody String gettradersByStateAndDisticOnVendors(@RequestBody TraderReg traderReg, HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				List<VendorReg>	vendorRegbean  =vendorRegDao.getVendorRegsByMobile(traderReg.getMobile());
					
				List<TraderReg> tradersList =	vendorRegDao.getTradersByStateAndDisticOnvendors(traderReg,vendorRegbean.get(0));
				
				if(tradersList == null)
				{
					objJSON.put("tradersList",  Collections.emptyList());
				}else
				{
					objJSON.put("tradersList", tradersList);
				}

				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}
	
	

}
