/*package com.aurospaces.neighbourhood.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aurospaces.neighbourhood.bean.CompanymasterBean;
import com.aurospaces.neighbourhood.bean.CylinderTypesBean;
import com.aurospaces.neighbourhood.bean.CylindermasterBean;
import com.aurospaces.neighbourhood.bean.ItemsBean;
import com.aurospaces.neighbourhood.bean.LpoitemsBean;
import com.aurospaces.neighbourhood.bean.LpomasterBean;
import com.aurospaces.neighbourhood.bean.StoresmasterBean;
import com.aurospaces.neighbourhood.db.dao.CompanymasterDao;
import com.aurospaces.neighbourhood.db.dao.CylindermasterDao;
import com.aurospaces.neighbourhood.db.dao.ItemsDao;
import com.aurospaces.neighbourhood.db.dao.StoresmasterDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;
import com.fasterxml.jackson.databind.ObjectMapper;




@Controller
@RequestMapping(value = "/admin")
public class CylinderController {
	
	
	private Logger logger = Logger.getLogger(CylinderController.class);
	@Autowired
	CylindermasterDao cylindermasterDao;
	@Autowired StoresmasterDao storesmasterDao;
	@Autowired CompanymasterDao companymasterDao;
	@Autowired ItemsDao objItemsDao;
	@RequestMapping(value = "/CylinderHome")
	public String cylinderHome(@Valid @ModelAttribute("cylinderForm") CylindermasterBean objCylindermasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			listOrderBeans = cylindermasterDao.getCylinders("1");
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderHome";
	}
	
	@RequestMapping(value = "/addcylinder", method = RequestMethod.POST)
	public String addCylinder( @ModelAttribute("cylinderForm") CylindermasterBean objCylindermasterBean,
			BindingResult bindingresults, Model model,RedirectAttributes redir) {
		
		//List<CylindermasterBean> cylinderMaster=null;
		
		int id = 0;
		String size=null; 
		
		try
		{
			if(objCylindermasterBean.getSize().equals("1")){
				objCylindermasterBean.setCapacity("11");
			}
			if(objCylindermasterBean.getSize().equals("2")){
				objCylindermasterBean.setCapacity("22");
			}
			if(objCylindermasterBean.getSize().equals("3")){
				objCylindermasterBean.setCapacity("44");
			}
			
			if(StringUtils.isNotBlank(objCylindermasterBean.getExpirtdate1())){
				Date date=  KhaibarGasUtil.dateFormate(objCylindermasterBean.getExpirtdate1());
				objCylindermasterBean.setExpirydate(date);
			}
			objCylindermasterBean.setStatus("1");
			CylindermasterBean cylindermasterBean = cylindermasterDao.getByCylinderId(objCylindermasterBean);
			int dummyId =0;
			if(cylindermasterBean != null){
				dummyId = cylindermasterBean.getId();
			}
			if(objCylindermasterBean.getId() != 0)
			{
				id = objCylindermasterBean.getId();
				if(id == dummyId || cylindermasterBean == null )
				{
					
					
					String capacity = objCylindermasterBean.getCapacity();
					//changing capcity to Id
					int capacityId = cylindermasterDao.getCylinderIdByCapacity(capacity);
					objCylindermasterBean.setCapacity(String.valueOf(capacityId));
					
					cylindermasterDao.save(objCylindermasterBean);
					redir.addFlashAttribute("msg", "Record Updated Successfully");
					redir.addFlashAttribute("cssMsg", "warning");
				}
				else
				{
					redir.addFlashAttribute("msg", "Already Record Exist");
					redir.addFlashAttribute("cssMsg", "danger");
				}
			}
			if(objCylindermasterBean.getId() == 0 && cylindermasterBean == null)
			{
				objCylindermasterBean.setCylinderstatus("1");
				cylindermasterDao.save(objCylindermasterBean);
				
				redir.addFlashAttribute("msg", "Record Inserted Successfully");
				redir.addFlashAttribute("cssMsg", "success");
			}
			if(objCylindermasterBean.getId() == 0 && cylindermasterBean != null)
			{
				redir.addFlashAttribute("msg", "Already Record Exist");
				redir.addFlashAttribute("cssMsg", "danger");
			}
			cylindermasterDao.updateCylinderIds();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "redirect:CylinderHome";
	}	
		


	@RequestMapping(value = "/deleteCylinder")
	public @ResponseBody String deleteEducation(CylindermasterBean objCylindermasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session, BindingResult objBindingResult) {
		System.out.println("deleteCylinder page...");
		List<CylindermasterBean> listOrderBeans = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		boolean delete = false;
		try {
			if (objCylindermasterBean.getId() != 0 && objCylindermasterBean.getStatus() !="") {
				delete = cylindermasterDao.deleteCylinder(objCylindermasterBean.getId(),objCylindermasterBean.getStatus());
				if (delete) {
					jsonObj.put("message", "deleted");
				} else {
					jsonObj.put("message", "delete fail");
				}
			}

			listOrderBeans = cylindermasterDao.getCylinders("1");
			objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {

				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal("error in EducationController class deleteEducation method  ");
			jsonObj.put("message", "excetption" + e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}
	
	@RequestMapping("/getCylinderCapacity")
	public  @ResponseBody  String cylinderTypes(HttpServletRequest request, HttpSession session)
	{  
		List<LpoitemsBean> retlist=null;
		//String retlist=null;
		JSONObject obj = new JSONObject();
		try {
			System.out.println("-----id-------"+request.getParameter("cid"));
			if(null != request.getParameter("cid")){
				retlist=cylindermasterDao.getCylinderCapacityByID(request.getParameter("cid"));
				if(retlist != null){
//					obj = new JSONObject(retlist);
				System.out.println("-result------"+retlist);
				obj.put("list",retlist);
				}else{
					obj.put("list", "");
				}
			}
				 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return String.valueOf(obj);
	}
	
	@ModelAttribute("cylinderTypes")
	public Map<Integer, String> populateUsers() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			List<CylinderTypesBean> list= cylindermasterDao.getCylinderstypes();
			for(CylinderTypesBean bean: list){
				statesMap.put(bean.getId(), bean.getName());
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}

	

	

	@ModelAttribute("LPONumbers")
	public Map<Integer, String> populateLPONumbers() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id,lponumber from lpomaster where item='1' and status='1'";
			List<LpomasterBean> list = cylindermasterDao.populate(sSql);
			for (LpomasterBean bean : list) {
				statesMap.put(bean.getId(), bean.getLponumber());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	@ModelAttribute("stores")
	public Map<Integer, String> populatestores() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id ,storename from storesmaster where status='1'";
			List<StoresmasterBean> list = storesmasterDao.populate(sSql);
			for (StoresmasterBean bean : list) {
				statesMap.put(bean.getId(), bean.getStorename());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	@ModelAttribute("companys")
	public Map<Integer, String> populatecompanys() {
		Map<Integer, String> statesMap = new LinkedHashMap<Integer, String>();
		try {
			String sSql = "select id ,companyname from companymaster where status='1'";
			List<CompanymasterBean> list = companymasterDao.populate(sSql);
			for (CompanymasterBean bean : list) {
				statesMap.put(bean.getId(), bean.getCompanyname());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return statesMap;
	}
	
	@RequestMapping("/getMadeByAndExparidate")
	public  @ResponseBody  String getMadeByAndExparidate(@ModelAttribute LpoitemsBean lpoitemsBean, HttpServletRequest request, HttpSession session)
	{  
		List<LpoitemsBean> retlist=null;
		//String retlist=null;
		JSONObject obj = new JSONObject();
		try {
			System.out.println("-----lponumber-------"+lpoitemsBean.getLponumber()+"-------item------"+lpoitemsBean.getItemid());
			if(null != request.getParameter("cid")){
				retlist=cylindermasterDao.getMadeByAndExparidate(lpoitemsBean.getItemid(),lpoitemsBean.getLponumber());
				if(retlist != null){
//					obj = new JSONObject(retlist);
				System.out.println("-result------"+retlist.size());
				obj.put("list",retlist);
				}else{
					obj.put("list", "");
				}
			}
				 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return String.valueOf(obj);
	}
	
	@RequestMapping(value = "/inActiveCylinder")
	public @ResponseBody String inActiveCylinder(CylindermasterBean objCylindermasterBean, ModelMap model,
			HttpServletRequest request, HttpSession session, BindingResult objBindingResult) {
		List<CylindermasterBean> listOrderBeans = null;
		JSONObject jsonObj = new JSONObject();
		ObjectMapper objectMapper = null;
		String sJson = null;
		boolean delete = false;
		try {

			listOrderBeans = cylindermasterDao.getCylinders(objCylindermasterBean.getStatus());
			objectMapper = new ObjectMapper();
			if (listOrderBeans != null && listOrderBeans.size() > 0) {

				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				jsonObj.put("allOrders1", listOrderBeans);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
				jsonObj.put("allOrders1", listOrderBeans);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal("error in EducationController class deleteEducation method  ");
			jsonObj.put("message", "excetption" + e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}
	@RequestMapping(value = "/getTruckInCylinderCount")
	public @ResponseBody String getTruckInCylinderCount(  ModelMap model,
			HttpServletRequest request, HttpSession session) {
		JSONObject jsonObj = new JSONObject();
		 String myarray=request.getParameter("myarray");
		 String myarray1=request.getParameter("myarray1");
		 String truckId=request.getParameter("truckId");
		 String[] items=myarray.split(",");
		 String[] quantity=myarray1.split(",");
		
		try {
			for(int i=0;i<items.length;i++){
				
			int count=	cylindermasterDao.truckincylinderscount(truckId,items[i]);
			if(count<Integer.parseInt(quantity[i])){
			 ItemsBean objItemsBean =  objItemsDao.getById(Integer.parseInt(items[i]));
			 jsonObj.put("msg", objItemsBean.getName()+" available in Truck :" +count);
			 objItemsBean.getName();
			}else{
				 jsonObj.put("msg", "ok");
			}
			
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal("error in cylinder master controller class ");
			jsonObj.put("message", "excetption" + e);
			return String.valueOf(jsonObj);

		}
		return String.valueOf(jsonObj);
	}

	@RequestMapping(value = "/cylinderAutoGenHome")
	public String cylinderAutoGenHome(@Valid @ModelAttribute("cylinderForm") CylindermasterBean objCylindermasterBean,
			ModelMap model, HttpServletRequest request, HttpSession session) {

		ObjectMapper objectMapper = null;
		String sJson = null;
		List<CylindermasterBean> listOrderBeans = null;
		try {
			listOrderBeans = cylindermasterDao.getCylinders("1");
			if (listOrderBeans != null && listOrderBeans.size() > 0) {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", sJson);
				// System.out.println(sJson);
			} else {
				objectMapper = new ObjectMapper();
				sJson = objectMapper.writeValueAsString(listOrderBeans);
				request.setAttribute("allOrders1", "''");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "cylinderAutoGenHome";
	}
	@RequestMapping(value = "/autoGenaddingcylinder", method = RequestMethod.POST)
	public String autoGenaddingcylinder( @ModelAttribute("cylinderForm") CylindermasterBean objCylindermasterBean,
			BindingResult bindingresults, Model model,RedirectAttributes redir) {
		
		//List<CylindermasterBean> cylinderMaster=null;
		
		int id = 0;
		String size=null; 
		
		try
		{
			if(objCylindermasterBean.getSize().equals("1")){
				objCylindermasterBean.setCapacity("11");
			}
			if(objCylindermasterBean.getSize().equals("2")){
				objCylindermasterBean.setCapacity("22");
			}
			if(objCylindermasterBean.getSize().equals("3")){
				objCylindermasterBean.setCapacity("44");
			}
			
			if(StringUtils.isNotBlank(objCylindermasterBean.getExpirtdate1())){
				Date date=  KhaibarGasUtil.dateFormate(objCylindermasterBean.getExpirtdate1());
				objCylindermasterBean.setExpirydate(date);
			}
			objCylindermasterBean.setStatus("1");
			CylindermasterBean cylindermasterBean = cylindermasterDao.getByCylinderId(objCylindermasterBean);
			if(objCylindermasterBean.getId() == 0 && cylindermasterBean == null)
			{
				int noOfCylinders=Integer.parseInt(objCylindermasterBean.getNoOfCylinders());
				for(int i=1;i<=noOfCylinders;i++){
					objCylindermasterBean.setId(0);
					objCylindermasterBean.setCylinderstatus("1");
					System.out.println("....save...."+i);
					cylindermasterDao.save(objCylindermasterBean);
				}
				redir.addFlashAttribute("msg", "Record Inserted Successfully");
				redir.addFlashAttribute("cssMsg", "success");
			}
			
			cylindermasterDao.updateCylinderIds();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);

		}
		return "redirect:cylinderAutoGenHome";
	}
	
}
*/