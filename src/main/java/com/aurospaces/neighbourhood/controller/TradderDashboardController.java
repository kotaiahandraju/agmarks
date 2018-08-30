package com.aurospaces.neighbourhood.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
import com.aurospaces.neighbourhood.bean.ProcReg;
import com.aurospaces.neighbourhood.bean.StorageReg;
import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.bean.TraderReg;
import com.aurospaces.neighbourhood.bean.TraderTransactions;
import com.aurospaces.neighbourhood.bean.TradertdaTrans;
import com.aurospaces.neighbourhood.bean.Users;
import com.aurospaces.neighbourhood.db.dao.ProcRegDao;
import com.aurospaces.neighbourhood.db.dao.StorageRegDao;
import com.aurospaces.neighbourhood.db.dao.TdaTransDao;
import com.aurospaces.neighbourhood.db.dao.TraderRegDao;
import com.aurospaces.neighbourhood.db.dao.TraderTransactionsDao;

@Controller
public class TradderDashboardController {
	
	@Autowired
	TraderTransactionsDao traderTransactionsDao;
	
	@Autowired TdaTransDao tdaTransDao;
	
	@Autowired TraderRegDao traderRegDao;
	@Autowired StorageRegDao storageRegDao;
	@Autowired ProcRegDao procRegDao;
	
	
	 @RequestMapping(value = "/rest/savetradertransaction")
	    public @ResponseBody String saveFarmerTransactions(@RequestBody TraderTransactions  traderTransactions,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		
	    		
	    		traderTransactions.setStatus("In Process");
	    		traderTransactionsDao.save(traderTransactions);
					objJSON.put("status", "success");
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	 
	 
	 
	 @RequestMapping(value = "/rest/savetdatransaction")
	    public @ResponseBody String saveFdaTransactions(@RequestBody TradertdaTrans  tradertdaTrans,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		
	        		
	    		tradertdaTrans.setStatus("In Process");
	    		tdaTransDao.save(tradertdaTrans);
					objJSON.put("status", "success");
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	 
	 
	 
	 @RequestMapping(value = "/rest/traderpostings")
	    public @ResponseBody String viewPostingsOftraderTransactionsOfCrops(@RequestBody TraderTransactions  TraderTransactions,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		List<Map<String, Object>>	traderTransactionslist =tdaTransDao.getTraderTransactions(TraderTransactions);
					objJSON.put("status", traderTransactionslist);
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
	    }
	 
	 
	 
	 
	 @RequestMapping(value = "/rest/traderpostingshistory")
	    public @ResponseBody String viewhistoryOfFarmerTransactions(@RequestBody TraderTransactions  traderTransactions,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		List<Map<String, Object>>	traderTransactionslist =tdaTransDao.getTraderTransactionsHistory(traderTransactions);
					objJSON.put("status", traderTransactionslist);
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	 
	 
	 
	 @RequestMapping(value = "/rest/tdapostings")
	    public @ResponseBody String viewPostingsOfFdaTransactions(@RequestBody TradertdaTrans  tradertdaTrans,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		List<Map<String, Object>>	traderTransactionslist =tdaTransDao.getTraderTransactions(tradertdaTrans);
					objJSON.put("status", traderTransactionslist);
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
	    }
	    
	    
	    
	    @RequestMapping(value = "/rest/tdapostingshistory")
	    public @ResponseBody String viewhistoryOfFdaTransactions(@RequestBody TradertdaTrans  tradertdaTrans,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		List<Map<String, Object>>	traderTransactionslist =tdaTransDao.getTraderTransactionsHistory(tradertdaTrans);
					objJSON.put("status", traderTransactionslist);
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	    
	    
	    
	    @RequestMapping(value = "/rest/changestatustraderspostings")
	    public @ResponseBody String changeFarmerTransactionsStatus(@RequestBody TraderTransactions  traderTransactions,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		traderTransactions.setStatus("Completed");
	    		traderTransactionsDao.save(traderTransactions);
					objJSON.put("status", "success");
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	    
	    
	    
	    @RequestMapping(value = "/rest/changetdapostingsstatus")
	    public @ResponseBody String changeFdaTransactionsStatus(@RequestBody TradertdaTrans  TradertdaTrans,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	try {
	    		TradertdaTrans.setStatus("Completed");
	    		tdaTransDao.save(TradertdaTrans);
					objJSON.put("status", "success");
			} catch (JSONException e) {
				objJSON.put("status", "fail");
				e.printStackTrace();
			}
	    	
			return String.valueOf(objJSON);

	    	
	    }
	    
	    
	    
	    @RequestMapping(value = "/rest/commoditiesontrader")
	    public @ResponseBody String getCommoditiesOnTrader(@RequestBody TraderReg  traderReg,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	TraderReg tradeUser = traderRegDao.getById(traderReg.getId());
	    	
	    	List<Map<String, Object>>	traderTransactionslist = tdaTransDao.getTradersCommodites(tradeUser);
	    	objJSON.put("status", traderTransactionslist);
			return String.valueOf(objJSON);

	    	
	    }
	    
	    @RequestMapping(value = "/rest/vegitablesontrader")
	    public @ResponseBody String getVegitablesOnTrader(@RequestBody TraderReg  traderReg,  HttpServletRequest request)  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	TraderReg tradeUser = traderRegDao.getById(traderReg.getId());
	    	
	    	List<Map<String, Object>>	traderTransactionslist = tdaTransDao.getTradersCommodites(tradeUser);
	    	objJSON.put("status", traderTransactionslist);
			return String.valueOf(objJSON);

	    	
	    }
	    
	    
	    

		@RequestMapping(value = "/rest/commoditiesStorageOntraders")
	    public @ResponseBody String storageDataOfCommodities(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	
	    	List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(user.getUser_name());
	    	Set<StorageReg> storagedata=	storageRegDao.getStoragedata(traderegbean.get(0));
	    	  
	    	if(storagedata.isEmpty())
	    		objJSON.put("commoditiesStorage",Collections.emptyList());
	    	else
	    	{
	    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(traderegbean,storagedata);
	    		
	    	//objJSON.put("commoditiesStorage",storagedata);
	    	objJSON.put("commoditiesStorage",distanceStorageData);
	    	}
	    	
			return String.valueOf(objJSON);
	    	
	    	}
		
		
		
		
		
		@RequestMapping(value = "/rest/vegetablesStorageontraders")
	    public @ResponseBody String storageDataOfVegetables(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
	    	JSONObject objJSON = new JSONObject();
	    	
	    	List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(user.getUser_name());
	    	Set<StorageReg> storagedata=	storageRegDao.getStorageDataOVegetablesontraders(traderegbean.get(0));
	    	  
	    	if(storagedata.isEmpty())
	    		objJSON.put("vegetablesStorage","");
	    	else
	    	{
	    		
	    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(traderegbean,storagedata);
	    	//objJSON.put("vegetablesStorage",storagedata);
	    		objJSON.put("vegetablesStorage",distanceStorageData);
	    	}
	    	
			return String.valueOf(objJSON);
	    	
	    	}
		
		
		 @RequestMapping(value = "/rest/dairystorageontraders")
		    public @ResponseBody String storageDataOfDairy(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
		    	JSONObject objJSON = new JSONObject();
		    	
		    	List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(user.getUser_name());
		    	Set<StorageReg> storagedata=	storageRegDao.getStorageDataOfDairyOnTraders(traderegbean.get(0));
		    	  
		    	if(storagedata.isEmpty())
		    		objJSON.put("dairystorage","");
		    	else
		    	{
		    		
		    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(traderegbean,storagedata);
		    	//objJSON.put("dairystorage",storagedata);
		    		objJSON.put("dairystorage",distanceStorageData);
		    	
		    	}
		    	
				return String.valueOf(objJSON);
		    	
		    	}
		 
		 
		 
		 @RequestMapping(value = "/rest/animalstorageontraders")
		    public @ResponseBody String storageDataOfAnimals(@RequestBody Users user,  HttpServletRequest request) throws IOException  {
		    	JSONObject objJSON = new JSONObject();
		    	
		    	List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(user.getUser_name());
		    	Set<StorageReg> storagedata=	storageRegDao.getStorageDataOfAnimals(traderegbean.get(0));
		    	  
		    	if(storagedata.isEmpty())
		    		objJSON.put("animalstorage","");
		    	else
		    	{
		    		
		    		Set<StorageReg> distanceStorageData=getStoragedataByDistence(traderegbean,storagedata);
		    	//objJSON.put("animalstorage",storagedata);
		    	
		    	objJSON.put("animalstorage",distanceStorageData);
		    	
		    	}
		    	
				return String.valueOf(objJSON);
		    	
		    	}
		
		
		
		
	    
	    
		private Set<StorageReg> getStoragedataByDistence(List<TraderReg> traderegbean, Set<StorageReg> storagedata) throws IOException {

			Set<StorageReg> distencestorageSet  =new LinkedHashSet<StorageReg>();
			
			
			
			for(StorageReg entry :storagedata)
			{
			String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+traderegbean.get(0).getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
		    
		    
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
		
		
		
		
		
		
		@RequestMapping(value = "/rest/processorAllOntraders")
		public @ResponseBody String getAllProcessors(  @RequestBody TraderReg traderReg,HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				//FarRegs	farregbean  =farRegsDao.getById(user.getTokenId());
				        
					
				List<ProcReg> processorList =	procRegDao.getAllProcessorsDataOnTraders(traderReg);
				
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
		
		
		

		@RequestMapping(value = "/rest/processorsbydisticwiseontraders")
		public @ResponseBody String getProcessorsByStateAndDistic(@RequestBody ProcReg procReg, HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				//FarRegs	farregbean  =farRegsDao.getById(procReg.getId());  
				
				// FarRegs	farreg  =farRegsDao.getByTokenId(procReg.getTokenId());
				 
				 List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(procReg.getMobile());
					
				List<ProcReg> processorList =	procRegDao.getProcessorsByStateAndDisticOnTrders(procReg,traderegbean.get(0));
				
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
		
		
		
		@RequestMapping(value = "/rest/distanceprocessorsontraders")
		public @ResponseBody String getprocessorsByDistance(@RequestBody Users user,  HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				 
				 List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(user.getMobile());				
				
				List<ProcReg> processorList =	procRegDao.getAllProcessorsDataOnTraders(traderegbean.get(0));
				
				if(processorList == null)
				{
					objJSON.put("processors",Collections.emptyList());
					
				}else
				{
					Set<ProcReg> distanceStorageData=getProcessordataByDistenceOnTraders(traderegbean.get(0),processorList);
					objJSON.put("processors",distanceStorageData);
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}



		private Set<ProcReg> getProcessordataByDistenceOnTraders(TraderReg traderReg, List<ProcReg> processorList) throws IOException {
			Set<ProcReg> distenceProcessorsSet  =new LinkedHashSet<ProcReg>();
			
			for(ProcReg entry :processorList)
			{
			String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+traderReg.getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
		    
		    
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
		
		
		
		
		
		
		
		@RequestMapping(value = "/rest/farmersAllontraders")
		public @ResponseBody String getAllFarmersOnTraders( @RequestBody TraderReg traderReg,HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				//FarRegs	farregbean  =farRegsDao.getById(user.getTokenId());
				        
					
				List<FarRegs> farmersList =	traderRegDao.getAllFaramersTransactions(traderReg);
				
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
		
		
		@RequestMapping(value = "/rest/farmersbydisticwiseontraders")
		public @ResponseBody String getFarmersByStateAndDistic(@RequestBody FarRegs farRegs, HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				
				 
				 List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(farRegs.getMobile());
				 
				 
					
				List<FarRegs> processorList =	traderRegDao.getFaramersTransactionsBystateAndDistrict(farRegs,traderegbean.get(0));
				
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
		
		
		@RequestMapping(value = "/rest/distancefarmerssontraders")
		public @ResponseBody String getFarmersByDistance(@RequestBody Users user,  HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				 
				 List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(user.getMobile());	
				 
					List<FarRegs> farmersList =	traderRegDao.getAllFaramersTransactions(traderegbean.get(0));
				
				
				
				if(farmersList.isEmpty())
				{
					objJSON.put("farmers",Collections.emptyList());
					
				}else
				{
					Set<FarRegs> distanceStorageData=getfarmersDataByDistenceOnTraders(traderegbean.get(0),farmersList);
					objJSON.put("farmers",distanceStorageData);
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}
		
		
		private Set<FarRegs> getfarmersDataByDistenceOnTraders(TraderReg traderReg, List<FarRegs> farmersList) throws IOException {
Set<FarRegs> distenceFarmersSet  =new LinkedHashSet<FarRegs>();
			
			for(FarRegs entry :farmersList)
			{
			String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+traderReg.getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
		    
		    
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



		@RequestMapping(value = "/rest/distanceSupplierssOntraders")
		public @ResponseBody String getSuppliersByDistance(@RequestBody Users user,  HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				 
				 List<TraderReg>	traderegbean  =traderRegDao.getFarRegsByMobile(user.getMobile());	
				 
					List<SupplierReg> suppliersList =	traderRegDao.getAllSuppliers();
				
				
				
				if(suppliersList.isEmpty())
				{
					objJSON.put("farmers",Collections.emptyList());
					
				}else
				{
					Set<SupplierReg> distanceStorageData=getSuppliersdataByDistenceOnTraders(traderegbean.get(0),suppliersList);
					objJSON.put("farmers",distanceStorageData);
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}



		private Set<SupplierReg> getSuppliersdataByDistenceOnTraders(TraderReg traderReg, List<SupplierReg> suppliersList) throws IOException {
Set<SupplierReg> distenceSuppliersSet  =new LinkedHashSet<SupplierReg>();
			
			for(SupplierReg entry :suppliersList)
			{
			String requestUrl  = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+traderReg.getPincode()+"&destinations="+entry.getPincode()+"&key=AIzaSyCnMiHsbLVPD4LOhfTWCnEPasW0BR_pOY0";
		    
		    
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
		    
		    distenceSuppliersSet.add(entry);
		    
		    
			}
			return distenceSuppliersSet; 
		}
		
		
		
		
		
		
		
		
		
		
	    

}
