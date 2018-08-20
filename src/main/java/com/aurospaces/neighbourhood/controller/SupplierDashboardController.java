package com.aurospaces.neighbourhood.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.StockLedger;
import com.aurospaces.neighbourhood.bean.SupplierIssues;
import com.aurospaces.neighbourhood.bean.SupplierReceipt;
import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.db.dao.SupplierReceiptsDao;
import com.aurospaces.neighbourhood.db.dao.SupplierRegDao;
import com.aurospaces.neighbourhood.db.dao.SupplierStockLedgerDao;
import com.aurospaces.neighbourhood.db.dao.SupplierrIssuesDao;

@Controller
public class SupplierDashboardController {
	
	@Autowired SupplierRegDao supplierRegDao;
	@Autowired SupplierReceiptsDao supplierReceiptsDao;
	@Autowired SupplierStockLedgerDao SupplierStockLedgerDao;
	@Autowired SupplierrIssuesDao supplierrIssuesDao;
	
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
			List<Map<String, Object>> supplier =supplierRegDao.searchsupplier(supplierReg);
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
	public @ResponseBody String saveSupplierReceipt( @RequestBody SupplierReceipt supplierReceipt, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		
		String strdate =supplierReceipt.getStrReceiptDate();
		
		
		try{
			
			supplierReceiptsDao.save(supplierReceipt);
			
		List<StockLedger> ledgerList =	SupplierStockLedgerDao.getLedgerByProductCode(supplierReceipt);
		StockLedger ledgerRecept =new StockLedger();
		
			if(ledgerList.isEmpty())
			{
		
			ledgerRecept.setMasterCode(supplierReceipt.getMasterCode());
			ledgerRecept.setMobile(supplierReceipt.getMobile());
			ledgerRecept.setProductCode(supplierReceipt.getProductCode());	
			ledgerRecept.setOpeningBalance("0");
			
			ledgerRecept.setReceipts(supplierReceipt.getReceivedQuantity());
			ledgerRecept.setIssues("0");
			ledgerRecept.setClosingBalance(supplierReceipt.getReceivedQuantity());
			ledgerRecept.setTokenId(supplierReceipt.getTokenId());
			ledgerRecept.setStrdate(strdate);
			}
			else
			{
				StockLedger ledger =ledgerList.get(0);
				
				Integer preclosingBal =Integer.parseInt(ledger.getClosingBalance());
				Integer  currentClosingBal =Integer.parseInt(supplierReceipt.getReceivedQuantity());
				String total = String.valueOf(preclosingBal+currentClosingBal);
				
				ledgerRecept.setMasterCode(supplierReceipt.getMasterCode());
				ledgerRecept.setMobile(supplierReceipt.getMobile());
				ledgerRecept.setProductCode(supplierReceipt.getProductCode());	
				ledgerRecept.setOpeningBalance(ledger.getClosingBalance());
				
				ledgerRecept.setReceipts(supplierReceipt.getReceivedQuantity());
				ledgerRecept.setIssues("0");
				
				
				
				ledgerRecept.setClosingBalance(total);
				ledgerRecept.setTokenId(supplierReceipt.getTokenId());
				ledgerRecept.setStrdate(strdate);
				
				
			}
			
			
			SupplierStockLedgerDao.save(ledgerRecept);
			
			objJSON.put("status", "success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	

	@RequestMapping(value = "/rest/savesupplierissue")
	public @ResponseBody String saveSupplierIssue( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		
		String strdate =supplierIssues.getStrIssueDate();
		
		
		try{
			
			supplierrIssuesDao.save(supplierIssues);
			
		List<StockLedger> ledgerList =	SupplierStockLedgerDao.getLedgerByProductCodeforIssues(supplierIssues);
		StockLedger ledgerRecept =new StockLedger();
		
			if(ledgerList.isEmpty())
			{
		
			ledgerRecept.setMasterCode(supplierIssues.getMasterCode());
			ledgerRecept.setMobile(supplierIssues.getMobile());
			ledgerRecept.setProductCode(supplierIssues.getProductCode());	
			ledgerRecept.setOpeningBalance("0");
			
			ledgerRecept.setReceipts("0");
			ledgerRecept.setIssues(supplierIssues.getIssueStock());
			ledgerRecept.setClosingBalance(supplierIssues.getIssueStock());
			ledgerRecept.setTokenId(supplierIssues.getTokenId());
			ledgerRecept.setStrdate(strdate);
			}
			else
			{
				StockLedger ledger =ledgerList.get(0);
				
				Integer preclosingBal =Integer.parseInt(ledger.getClosingBalance());
				Integer  currentClosingBal =Integer.parseInt(supplierIssues.getIssueStock());
				String total = String.valueOf(preclosingBal-currentClosingBal);
				
				ledgerRecept.setMasterCode(supplierIssues.getMasterCode());
				ledgerRecept.setMobile(supplierIssues.getMobile());
				ledgerRecept.setProductCode(supplierIssues.getProductCode());	
				ledgerRecept.setOpeningBalance(ledger.getClosingBalance());
				
				ledgerRecept.setReceipts("0");
				ledgerRecept.setIssues(supplierIssues.getIssueStock());
				
				
				
				ledgerRecept.setClosingBalance(total);
				ledgerRecept.setTokenId(supplierIssues.getTokenId());
				ledgerRecept.setStrdate(strdate);
				
				
			}
			
			
			SupplierStockLedgerDao.save(ledgerRecept);
			
			objJSON.put("status", "success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/latestsupplierissues")
	public @ResponseBody String latestSaplierIssues( @RequestBody SupplierIssues supplierIssues,HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			List<Map<String, Object>> suppliers =supplierrIssuesDao.getLatestIssues(supplierIssues);
			objJSON.put("status", suppliers);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	@RequestMapping(value = "/rest/supplieraddreceipt")
	public @ResponseBody String searchSupplierReceipt(@RequestBody SupplierReceipt supplierReceipt, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			List<Map<String, Object>> supplier =supplierRegDao.searchsupplierAddReceipt(supplierReceipt);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	@RequestMapping(value = "/rest/getledgerdata")
	public @ResponseBody String getledgerData( HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			List<Map<String, Object>> supplier =SupplierStockLedgerDao.getLedgerData();
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}


}
