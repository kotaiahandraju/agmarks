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
import com.aurospaces.neighbourhood.bean.SupplierPA;
import com.aurospaces.neighbourhood.bean.SupplierReceipt;
import com.aurospaces.neighbourhood.bean.SupplierReg;
import com.aurospaces.neighbourhood.bean.SupplierTransactions;
import com.aurospaces.neighbourhood.db.dao.SupplierPaDao;
import com.aurospaces.neighbourhood.db.dao.SupplierReceiptsDao;
import com.aurospaces.neighbourhood.db.dao.SupplierRegDao;
import com.aurospaces.neighbourhood.db.dao.SupplierStockLedgerDao;
import com.aurospaces.neighbourhood.db.dao.SupplierTransactionsdao;
import com.aurospaces.neighbourhood.db.dao.SupplierrIssuesDao;

@Controller
public class SupplierDashboardController {
	
	@Autowired SupplierRegDao supplierRegDao;
	@Autowired SupplierReceiptsDao supplierReceiptsDao;
	@Autowired SupplierStockLedgerDao SupplierStockLedgerDao;
	@Autowired SupplierrIssuesDao supplierrIssuesDao;
	@Autowired SupplierPaDao supplierPaDao;
	@Autowired	SupplierTransactionsdao supplierTransactionsdao;
	
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
	public @ResponseBody String listOfUnits( @RequestBody SupplierReg supplierReg, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			List<Map<String, Object>> suppliers =supplierRegDao.getAllUnitSuppliers(supplierReg);
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
		
		supplierIssues.setDueAmount(supplierIssues.getPrice());
		String strdate =supplierIssues.getStrIssueDate();
		
		
		try{
			
			supplierrIssuesDao.save(supplierIssues);
			
		List<StockLedger> ledgerList =	SupplierStockLedgerDao.getLedgerByProductCodeforIssues(supplierIssues);
		StockLedger ledgerRecept =new StockLedger();
		
			if(ledgerList.isEmpty())
			{
		
			ledgerRecept.setMasterCode(supplierIssues.getMasterCode());
			ledgerRecept.setBranchCode(supplierIssues.getBranchCode());
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
				
				SupplierStockLedgerDao.save(ledgerRecept);
				
				SupplierPA supplierPA =new SupplierPA();
				
				
				supplierPA.setMasterCode(supplierIssues.getMasterCode());
				supplierPA.setBranchCode(supplierIssues.getBranchCode());
				supplierPA.setBranchName(supplierIssues.getBranchName());
				supplierPA.setConsumerName(supplierIssues.getConsumerName());
				supplierPA.setInchargeMobile(supplierIssues.getMobile());
				supplierPA.setAddress(supplierIssues.getAddress());
				supplierPA.setStrtdate(strdate);
				supplierPA.setBrand(supplierIssues.getBrand());
				supplierPA.setProductId(supplierIssues.getProductId());
				supplierPA.setProductCode(supplierIssues.getProductCode());
				
				supplierPA.setProductName(supplierIssues.getProductName());
				supplierPA.setVariant(supplierIssues.getVariant());
				
				supplierPA.setIssues(supplierIssues.getIssueStock());
				
				supplierPA.setUnits(supplierIssues.getUnits());
				supplierPA.setTotalPrice(supplierIssues.getPrice());
				supplierPA.setInStock(total);
				
				supplierPA.setReceipts(ledger.getClosingBalance());
				
				supplierPA.setStatus("in process");
				supplierPA.setTokenId(supplierIssues.getTokenId());
				
				supplierPaDao.save(supplierPA);
				
				
				
			}
			
			
			
			
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
	public @ResponseBody String getledgerData( @RequestBody SupplierReceipt supplierReceipt,HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			List<Map<String, Object>> supplier =SupplierStockLedgerDao.getLedgerData(supplierReceipt);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	

	@RequestMapping(value = "/rest/issuedueamt")
	public @ResponseBody String saveDueAmtOnSupplierissue( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		
		SupplierIssues sIssue =	supplierrIssuesDao.getBySNo(supplierIssues.getSNo());
		Integer dueAmount =Integer.parseInt(sIssue.getDueAmount());
		
		Integer currentAmount  = Integer.parseInt(supplierIssues.getPayAmount());
		
		String finalAmount =String.valueOf(dueAmount-currentAmount);
		
		if(finalAmount.equals("0"))
		{
			supplierIssues.setStatus("Completed");
		}else
		{
			supplierIssues.setStatus("In Process");
			
		}
		supplierIssues.setDueAmount(finalAmount);
		
		try{
			supplierrIssuesDao.saveDueAmount(supplierIssues);
			objJSON.put("status", "success");
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	
	
	@RequestMapping(value = "/rest/issuedueamtlist")
	public @ResponseBody String showListOfPaymnetPendingIssues( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getDueAmtListsenderwise(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	@RequestMapping(value = "/rest/statesenderwise")
	public @ResponseBody String stateSenderWise( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getDueAmtListSenderwise(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	
	

	@RequestMapping(value = "/rest/productsbyissues")
	public @ResponseBody String showProductsbasedOnissues( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getProductsByIssues(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	

	@RequestMapping(value = "/rest/analyticscharts")
	public @ResponseBody String analyticsCharts( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplierReceipts =supplierrIssuesDao.getAnalaticsData(supplierIssues);
			
			List<Map<String, Object>> supplierissueschartdata =supplierReceiptsDao.getAnalaticsDataFor(supplierIssues);
			
			objJSON.put("receiptschartdata", supplierReceipts);
			objJSON.put("issueschartdata", supplierissueschartdata);
			
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	@RequestMapping(value = "/rest/branchq1")
	public @ResponseBody String showIssuesBranchQuarter1( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesBybranchQ1(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	@RequestMapping(value = "/rest/branchq2")
	public @ResponseBody String showIssuesBranchQuarter2( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesBybranchQ2(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	@RequestMapping(value = "/rest/branchq3")
	public @ResponseBody String showIssuesBranchQuarter3( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesBybranchQ3(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	@RequestMapping(value = "/rest/branchq4")
	public @ResponseBody String showIssuesBranchQuarter4( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesBybranchQ4(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	
	@RequestMapping(value = "/rest/branchh1")
	public @ResponseBody String showIssuesBranchHalf1( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByBranchH1(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	@RequestMapping(value = "/rest/branchh2")
	public @ResponseBody String showIssuesBranchHalf2( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByBranchH2(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	@RequestMapping(value = "/rest/branchhyearly")
	public @ResponseBody String showIssuesBranchyearly( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByBranchYearly(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	
	@RequestMapping(value = "/rest/quentitychecking")
	public @ResponseBody String quentityChecking( @RequestBody StockLedger stockLedger, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getClosingBalOfProduct(stockLedger);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/rest/productq1")
	public @ResponseBody String showIssuesProductQuarter1( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByProductQ1(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	@RequestMapping(value = "/rest/productq2")
	public @ResponseBody String showIssuesProductQuarter2( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByProductQ2(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	@RequestMapping(value = "/rest/productq3")
	public @ResponseBody String showIssuesProductQuarter3( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByProductQ3(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	@RequestMapping(value = "/rest/productq4")
	public @ResponseBody String showIssuesProductQuarter4( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByProductQ4(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	
	@RequestMapping(value = "/rest/producth1")
	public @ResponseBody String showIssuesProductHalf1( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByProductH1(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	@RequestMapping(value = "/rest/producth2")
	public @ResponseBody String showIssuesProductHalf2( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByProductH2(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	@RequestMapping(value = "/rest/productyearly")
	public @ResponseBody String showIssuesProductyearly( @RequestBody SupplierIssues supplierIssues, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<Map<String, Object>> supplier =supplierrIssuesDao.getIssuesByProductYearly(supplierIssues);
			objJSON.put("status", supplier);
		}catch(Exception e){
		
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
		
	}
	
	
	
	@RequestMapping(value = "/rest/saveStockRequest")
	public @ResponseBody String saveStockRequest( @RequestBody SupplierTransactions supplierTransactions, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			supplierTransactions.setStatus("In Process");
			supplierTransactionsdao.save(supplierTransactions);
			objJSON.put("status", "success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	@RequestMapping(value = "/rest/stockRequestCounts")
	public @ResponseBody String getStockRequestCounts( @RequestBody SupplierReg supplierReg, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			
			List<Map<String, Object>> supplierRequestCounts = supplierTransactionsdao.getStocksRequestCounts(supplierReg);
			objJSON.put("status", supplierRequestCounts);
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	
	
	@RequestMapping(value = "/rest/stockRequestdetails")
	public @ResponseBody String getStockRequestDetails( @RequestBody SupplierReg supplierReg, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		
		try{
			
			List<Map<String, Object>> supplierRequestCounts = supplierTransactionsdao.getStocksRequestDetails(supplierReg);
			objJSON.put("status", supplierRequestCounts);
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}





}
