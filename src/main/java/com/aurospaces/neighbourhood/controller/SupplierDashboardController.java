package com.aurospaces.neighbourhood.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.SupplierReceipt;
import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.db.dao.SupplierReceiptsDao;
import com.aurospaces.neighbourhood.db.dao.SupplierRegDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;

@Controller
public class SupplierDashboardController {
	
	@Autowired SupplierRegDao supplierRegDao;
	@Autowired SupplierReceiptsDao supplierReceiptsDao;
	
	@RequestMapping(value = "/rest/removesupplier")
	public @ResponseBody String saveAddProducts(@RequestBody SupplierReg supplierReg, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			supplierRegDao.removeSupplier(supplierReg);
			objJSON.put("status", "success");
		}catch(Exception e){
			objJSON.put("status", "removeFail");
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	@RequestMapping(value = "/rest/searchsupplier")
	public @ResponseBody String searchSupplier(@RequestBody SupplierReg supplierReg, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			List<SupplierReg> supplier =supplierRegDao.searchsupplier(supplierReg);
			objJSON.put("supplier", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/unitsuppliers")
	public @ResponseBody String listOfUnits( HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			List<Map<String, Object>> suppliers =supplierRegDao.getAllUnitSuppliers();
			objJSON.put("supplier", suppliers);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	
	
	
	@RequestMapping(value = "/rest/savesupplierreceipt")
	public @ResponseBody String saveAddProducts( @RequestBody SupplierReceipt supplierReceipt, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		
		
		try{
			
			supplierReceiptsDao.save(supplierReceipt);
			objJSON.put("status", "insertedSuccesfully");
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}


}
