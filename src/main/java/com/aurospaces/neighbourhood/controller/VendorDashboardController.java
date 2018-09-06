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

import com.aurospaces.neighbourhood.bean.TraderTransactions;
import com.aurospaces.neighbourhood.bean.VendorTransactions;
import com.aurospaces.neighbourhood.bean.VendorsvdaTrans;
import com.aurospaces.neighbourhood.db.dao.VendorTransactionsDao;
import com.aurospaces.neighbourhood.db.dao.VendorsVdaTransactions;

@Controller
public class VendorDashboardController {
	
	@Autowired VendorTransactionsDao vendorTransactionsDao;
	@Autowired VendorsVdaTransactions vendorsVdaTransactions;
	@RequestMapping(value = "/rest/savevendorTransaction")
    public @ResponseBody String saveFarmerTransactions(@RequestBody VendorTransactions  vendorTransactions,  HttpServletRequest request)  {
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
	
	
	

}
