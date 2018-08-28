package com.aurospaces.neighbourhood.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.FarmerTransactions;
import com.aurospaces.neighbourhood.bean.FdaTrans;
import com.aurospaces.neighbourhood.bean.TraderTransactions;
import com.aurospaces.neighbourhood.bean.TradertdaTrans;
import com.aurospaces.neighbourhood.db.dao.TdaTransDao;
import com.aurospaces.neighbourhood.db.dao.TraderTransactionsDao;

@Controller
public class TradderDashboardController {
	
	@Autowired
	TraderTransactionsDao traderTransactionsDao;
	
	@Autowired TdaTransDao tdaTransDao;
	
	
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
	    
	    

}
