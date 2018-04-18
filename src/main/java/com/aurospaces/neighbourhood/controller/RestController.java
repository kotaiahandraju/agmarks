/**
 * 
 */
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

import com.aurospaces.neighbourhood.bean.CommPrices;
import com.aurospaces.neighbourhood.bean.Pincodedata;
import com.aurospaces.neighbourhood.bean.VegPrices;
import com.aurospaces.neighbourhood.db.dao.CommPricesDao;
import com.aurospaces.neighbourhood.db.dao.PincodedataDao;
import com.aurospaces.neighbourhood.db.dao.UsersDao;
import com.aurospaces.neighbourhood.db.dao.VegPricesDao;

/**
 * @author YOGI
 */
@Controller
public class RestController {
	@Autowired CommPricesDao commPricesDao;
	@Autowired UsersDao usersDao;
	@Autowired VegPricesDao vegPricesDao;
	@Autowired PincodedataDao pincodedataDao;
	@RequestMapping(value = "/rest/getCommoditiesList")
	public @ResponseBody String getCommodities(@RequestBody CommPrices commPrices,  HttpServletRequest request) throws Exception {
		List<Map<String,Object>> list=null;
		JSONObject objJSON = new JSONObject();
		try{
			list = commPricesDao.getCommpricesList(commPrices);
			if(list != null){
				objJSON.put("commodities", list);
				
			}else{
				objJSON.put("commodities", "");
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/getcounts")
	public @ResponseBody String getcounts(  HttpServletRequest request) throws Exception {
		int count=0;
		JSONObject objJSON = new JSONObject();
		try{
			count = usersDao.getcounts("Farmer");
				objJSON.put("Farmer", count);
				
				count=0;
			
			count = usersDao.getcounts("Processor");
				objJSON.put("Processor", count);
				count=0;
			
			count = usersDao.getcounts("Warehouse");
				objJSON.put("Warehouse", count);
				count=0;
			
			count = usersDao.getcounts("Trader");
				objJSON.put("Trader", count);
				
				count=0;
			
			count = usersDao.getcounts("Supplier");
				objJSON.put("Supplier", count);
				
				count=0;
			
			count = usersDao.getcounts("Logistics");
				objJSON.put("Logistics", count);
				
				count=0;
			
			count = usersDao.getcounts("Vendor");
				objJSON.put("Vendor", count);
				
			
			

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/getvegpriceList")
	public @ResponseBody String getvegpriceList(@RequestBody VegPrices vegPrice,  HttpServletRequest request) throws Exception {
		List<Map<String,Object>> list=null;
		JSONObject objJSON = new JSONObject();
		try{
			list = vegPricesDao.getVegPriceList(vegPrice);
			if(list != null){
				objJSON.put("vegpriceList", list);
				
			}else{
				objJSON.put("vegpriceList", "");
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/getPincodeData")
	public @ResponseBody String getPincodeData(@RequestBody Pincodedata pincodedata,  HttpServletRequest request) throws Exception {
		List<Map<String,Object>> list=null;
		JSONObject objJSON = new JSONObject();
		try{
			list = pincodedataDao.getPincodeData(pincodedata);
			if(list != null){
				objJSON.put("pincodedata", list);
				
			}else{
				objJSON.put("pincodedata", "");
			}

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
}

	/*// for getting services..
	@RequestMapping(value = "/rest/getServices")
	public @ResponseBody RestResponse getServices(HttpServletRequest request) throws Exception {
		System.out.println("testing");
		int locationId = 24;
		System.out.println(request.getParameter("locationId"));
		if (StringUtils.isNotBlank(request.getParameter("locationId"))) {
			locationId = Integer.parseInt(request.getParameter("locationId"));
		}
		String baseUrl = MiscUtils.getBaseUrl(request);
		Collection<RestService1> mobileCategories = category1Dao.getServicesForMobile(baseUrl, locationId);

		if (mobileCategories != null && mobileCategories.size() > 0) {
			@SuppressWarnings("rawtypes")
			HashMap retMap = new HashMap();
			// retMap.put("service_list", mobileCategories);
			RestResponse rr = new RestResponse("200", mobileCategories);
			return rr;

		} else {
			RestResponse rr = new RestResponse("500", "Service not available");
			return rr;
		}
	}

	@RequestMapping(value = "/rest/getDoctorType")
	public @ResponseBody RestResponse getDoctorType() {
		RestResponse rr = null;
		System.out.println("get Doctor type start...");
		List<Map<String, String>> list = doctorType1Dao.getAllDoctors();
		// System.out.println("order data end.."+list.get(1).toString());
		if (list != null && list.size() > 0) {
			rr = new RestResponse("200", list);
		} else {
			rr = new RestResponse("500", "Doctor not available");
		}
		return rr;
	}

	@RequestMapping(value = "/rest/getPackageLists")
	public @ResponseBody RestResponse getPackageLists(@RequestParam("service_id") int service_id,
			HttpServletRequest request

	) {
		RestResponse rr = null;
		int locationId = 24;
		if (StringUtils.isNotBlank(request.getParameter("locationId"))) {
			locationId = Integer.parseInt(request.getParameter("locationId"));
		}
		String baseUrl = MiscUtils.getBaseUrl(request);
		List<Map<String, String>> list = serviceUnitDao.getAllTests(service_id, baseUrl, locationId);
		if (list != null && list.size() > 0) {
			rr = new RestResponse("200", list);
		} else {
			rr = new RestResponse("500", "Packages not available");
		}
		return rr;
	}

	@RequestMapping(value = "/rest/getOrderData")
	public @ResponseBody RestResponse insertOrderData(@RequestBody OrderData objOrder)
			throws AddressException, MessagingException, IOException {
		System.out.println("order data start...");
		Gson gson = new Gson();
		System.out.println("BEGIN ORDER DATA ");
		System.out.println(gson.toJson(objOrder));
		System.out.println("END ORDER DATA ");
		System.out.println("Address of customer...");
		try {
			OrderInfo1 objOrder1 = new OrderInfo1();
			Service objService = objOrder.getService();
			Customer1 other_booking = objOrder.getOther_booking();
			RestLocation current_location = objOrder.getCurrent_location();

			NeighbourhoodUtil objNeighbourhoodUtil = new NeighbourhoodUtil();
			String generatedOrderId = objNeighbourhoodUtil.randNum();
			objOrder1.setGeneratedId(generatedOrderId);
			objOrder1.setAddress(other_booking.getCust_address());
			objOrder1.setAppointmentSlotOldId(other_booking.getSelected_time_slot());
			objOrder1.setContactNumber(other_booking.getCust_mobile_no());
			objOrder1.setContactEmail(other_booking.getCust_email_id());
			objOrder1.setServiceName(objService.getService_name());
			objOrder1.setStatusId(AuroConstants.ordered);
			objOrder1.setDescription(other_booking.getProblem_desc());
			objOrder1.setServiceId(objService.getService_id());
			// objOrder1.setVendorId();
			// objOrder1.getSubCategory();
			objOrder1.setLocationId(current_location.getLocation_id());
			objOrder1.setAppointTimeName(other_booking.getSelected_time_slot());
			orderInfo1Dao.save(objOrder1);

			EmailSendingUtil objEmailSendingUtil = new EmailSendingUtil();
			String sCustomerMail = objEmailSendingUtil.sendEmail(objOrder1, objContext, "customer");
			System.out.println("customer mail goes--" + sCustomerMail);
			String sAdminMail = objEmailSendingUtil.sendEmail(objOrder1, objContext, "admin");
			System.out.println("admin mail goes--" + sAdminMail);
			SMSUtil objSmsUtil = new SMSUtil();
			objSmsUtil.sendSms(objContext, objOrder1, "customer");
			objSmsUtil.sendSms(objContext, objOrder1, "order_admin");

			objOrder.setOrder_id(objOrder1.getGeneratedId());
			objOrder.setAmount("0");
			objOrder.setSuccess_url("success");
			objOrder.setFailure_url("failure");
			objOrder.setSalt_key("Salt key");
			objOrder.setTxn_id("QWETQWERTYUIO");
			objOrder.setMerchant_key("Merchant Key");

			System.out.println("BEGIN ORDER RESPONSE ");
			System.out.println(gson.toJson(objOrder));
			System.out.println("END ORDER RESPONSE ");

			return new RestResponse("200", objOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return new RestResponse("500", "Invalid Server Response..");
		}
	}

	@RequestMapping(value = "/rest/getBooking")
	public @ResponseBody RestResponse insertOrder(@RequestBody RestBean objRestBean, HttpSession objSession,
			HttpServletRequest request) {
		RestResponse rr = null;
		Gson gson = new Gson();
		System.out.println("BEGIN PATHOLOGY DATA ");
		System.out.println(gson.toJson(objRestBean));
		System.out.println("END PATHOLOGY DATA ");
		try {

			String baseUrl = MiscUtils.getBaseUrl(request);
			System.out.println(baseUrl);
			OrderInfo1 objOrder1 = new OrderInfo1();

			NeighbourhoodUtil objNeighbourhoodUtil = new NeighbourhoodUtil();
			String generatedOrderId = objNeighbourhoodUtil.randNum();
			objOrder1.setGeneratedId(generatedOrderId);
			objOrder1.setAddress(objRestBean.getAddress());
			// objOrder1.setAppointmentDate(dateFormat.format());
			objOrder1.setCouponCode(objRestBean.getCoupon_code());
			System.out.println("objRestBean.getCoupon_code()---" + objRestBean.getCoupon_code());
			objOrder1.setAppointmentDate(new Date(objRestBean.getDay_slot()));
			// objOrder1.setAppointTimeName("");

			objOrder1.setAppointmentSlotId(objRestBean.getTime_slot());
			objOrder1.setContactNumber(objRestBean.getMobile());
			objOrder1.setContactEmail(objRestBean.getEmail());
			objOrder1.setStatusId(AuroConstants.ordered);
			objOrder1.setServiceId(objRestBean.getService_id());
			objOrder1.setGender(objRestBean.getGender());
			objOrder1.setCustomerName(objRestBean.getName());
			int count = orderInfo1Dao.getCustomerInfo(objOrder1.getContactNumber());
			// objOrder1.setLocationId((int)objSession.getAttribute("locationId"));
			// objOrder1.setLatitude((double)objSession.getAttribute("latitude"));
			// objOrder1.setLongitude((double)objSession.getAttribute("longitude"));
			objOrder1.setLocationId(24);
			objOrder1.setUserId(3);
			GeoMain gm = new GeoMain();
			GeoTag latlong = gm.getAddr(objOrder1.getAddress());
			if (latlong != null) {
				objOrder1.setLatitude(latlong.x);
				objOrder1.setLongitude(latlong.y);
			}
			// objOrder1.setLatitude(new Double(76.9766));
			// objOrder1.setLongitude(new Double(76.9766));
			// objOrder1.setServiceId(objService.getService_id());
			 objOrder1.setAppointDate(objRestBean.getDay_slot()); 
			// objOrder1.setVendorId();
			// objOrder1.getSubCategory();

			BigDecimal totalNetAmt = null;
			BigDecimal totalDiscount = null;
			BigDecimal finalTotalNetAmount = null;
			Packages packagesData[] = objRestBean.getPackages();

			for (Packages local : packagesData) {
				objOrder1.setVendorId(local.getVendor_id());
				System.out.println(objOrder1.getVendorId());
				System.out.println("getBase_price" + local.getBase_price());
				System.out.println("getDiscount_price" + local.getDiscount_price());
				System.out.println("getFinal_price" + objRestBean.getFinal_price());
				System.out.println("getBase_price" + local.getBase_price());

				if (local.getBase_price() != null) {
					objOrder1.setTotalPrice(local.getBase_price());
				}
				if (local.getDiscount_price() != null) {
					objOrder1.setTotalDiscount(local.getDiscount_price());
				}
				objOrder1.setNetAmount(new BigDecimal(objRestBean.getFinal_price()));

				
				 * if(finalTotalNetAmount != null) { finalTotalNetAmount
				 * =finalTotalNetAmount.add(NeighbourhoodUtil.getDiscount(local.
				 * getTotal_price(), local.getDiscount_percent())) ; totalNetAmt
				 * =totalNetAmt.add(local.getTotal_price()); }else{
				 * finalTotalNetAmount =
				 * NeighbourhoodUtil.getDiscount(local.getTotal_price(),
				 * local.getDiscount_price()); totalNetAmt =
				 * local.getTotal_price(); } totalDiscount =
				 * local.getDiscount_percent();
				 * System.out.println(finalTotalNetAmount);
				 

				
				 * if(totalNetAmt != null){ totalNetAmt =
				 * totalNetAmt.add(local.getTotal_price()); }else{ totalNetAmt =
				 * local.getTotal_price(); } if(totalDiscount != null){
				 * totalDiscount =
				 * totalDiscount.add(local.getDiscount_percent()); }else{
				 * totalDiscount =local.getDiscount_percent(); }
				 * if(finalTotalNetAmount != null){ finalTotalNetAmount
				 * =finalTotalNetAmount.add(
				 * totalNetAmt.subtract(totalNetAmt.multiply(totalDiscount).
				 * divide(new BigDecimal(100)))); }else{ finalTotalNetAmount =
				 * totalNetAmt.subtract(totalNetAmt.multiply(totalDiscount).
				 * divide(new BigDecimal(100))); }
				 
			}

			
			 * objOrder1.setNetAmount(finalTotalNetAmount);
			 * 
			 * objOrder1.setTotalPrice(totalNetAmt); //totalDiscount
			 * =NeighbourhoodUtil.totalDisc(totalNetAmt,finalTotalNetAmount);
			 * System.out.println("totalDiscount---"+totalDiscount);
			 * if(totalDiscount == null ){ objOrder1.setTotalDiscount(new
			 * BigDecimal(0.0)); }else{
			 * objOrder1.setTotalDiscount(totalDiscount); }
			 

			orderInfo1Dao.save(objOrder1);

			for (Packages local : packagesData) {
				OrderServiceUnit1 os1 = new OrderServiceUnit1();
				os1.setOrderId(objOrder1.getId());
				os1.setServiceUnitId(local.getId());
				orderServiceUnit1Dao.save(os1);
			}

			// for discount related....
			System.out.println("count----" + count);
			if (count == 0) {
				CustomerDiscount1 customerDiscount1 = new CustomerDiscount1();
				customerDiscount1.setMobileNumber(objOrder1.getContactNumber());
				customerDiscount1.setOrderId(objOrder1.getId());
				String sPropFilePath = objContext.getRealPath("Resources" + File.separator + "DataBase.properties");
				if (StringUtils.isNotBlank(sPropFilePath)) {
					Properties objProperties = new Properties();
					InputStream objStream = new FileInputStream(sPropFilePath);
					objProperties.load(objStream);
					String sCustomerDiscount = objProperties.getProperty("CustomerDiscount");
					customerDiscount1.setDiscount(new BigDecimal(sCustomerDiscount));
					cusotmerDiscount1Dao.save(customerDiscount1);
				}
			}

			if (objOrder1.getId() == 0) {
				return new RestResponse("500", "Invalid Server Response..");
			} else {
				TimeSlots objTimeSlot1 = timeSlotsDao.getById(objOrder1.getAppointmentSlotId());
				String timeName = null;
				if (objTimeSlot1 != null) {
					timeName = objTimeSlot1.getLabel();
					if (StringUtils.isNotBlank(timeName)) {
						objOrder1.setAppointTimeName(timeName);
					}
				}

				Service1 objService1 = service1Dao.getById(objOrder1.getServiceId());
				String serviceName = null;
				if (objService1 != null) {
					serviceName = objService1.getName();
					if (StringUtils.isNotBlank(serviceName)) {
						objOrder1.setServiceName(serviceName);
					}
				}

				EmailSendingUtil objEmailSendingUtil = new EmailSendingUtil();
				objOrder1.setNetAmount(new BigDecimal(objRestBean.getFinal_price()));
				String sCustomerMail = objEmailSendingUtil.sendEmail(objOrder1, objContext, "customer");
				System.out.println("customer maili goes--" + sCustomerMail);
				String sAdminMail = objEmailSendingUtil.sendEmail(objOrder1, objContext, "admin");
				System.out.println("admin mail goes--" + sAdminMail);
				SMSUtil objSmsUtil = new SMSUtil();
				objSmsUtil.sendSms(objContext, objOrder1, "customer");
				// admin number to calll
				objSmsUtil.sendSms(objContext, objOrder1, "order_admin");
				RestResponseBean objRestResponse = new RestResponseBean();
				objRestResponse.setOrder_id(generatedOrderId);
				objRestResponse.setAmount(new BigDecimal(objRestBean.getFinal_price()));
				objRestResponse.setSuccess_url(baseUrl + "/Sucess.html");
				objRestResponse.setFailure_url(baseUrl + "/Fail.html");
				objRestResponse.setSalt_key(AuroConstants.saltKey);
				objRestResponse.setMerchant_key(AuroConstants.merchantKey);
				objRestResponse.setTxn_id("QWETQWERTYUIO");
				objRestResponse.setServer_url(AuroConstants.server_url);
				return new RestResponse("200", objRestResponse);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new RestResponse("500", "Invalid Server Response..");
		}
	}

	@RequestMapping(value = "/rest/getDoctorData")
	public @ResponseBody RestResponse getDoctorData(@RequestBody OrderData objOrder) {
		RestResponse rr = null;

		Gson gson = new Gson();
		System.out.println("BEGIN DOCTOR DATA ");
		System.out.println(gson.toJson(objOrder));
		System.out.println("END DOCTOR DATA ");

		try {
			OrderInfo1 objOrder1 = new OrderInfo1();
			Service objService = objOrder.getService();
			DoctorBooking objDoctorBooking = objOrder.getDoctor_booking();
			RestLocation current_location = objOrder.getCurrent_location();

			NeighbourhoodUtil objNeighbourhoodUtil = new NeighbourhoodUtil();
			String generatedOrderId = objNeighbourhoodUtil.randNum();
			objOrder1.setGeneratedId(generatedOrderId);
			objOrder1.setAddress(objDoctorBooking.getCust_address());
			objOrder1.setContactNumber(objDoctorBooking.getCust_mobile_no());
			objOrder1.setContactEmail(objDoctorBooking.getCust_email_id());
			objOrder1.setStatusId(AuroConstants.ordered);

			// objOrder1.setDescription(current_location.getDescription());

			// objOrder1.setServiceId(objService.getService_id());
			// objOrder1.setServiceName(objService.getService_name());

			objOrder1.setServiceId(3);
			objOrder1.setServiceName("Doctor");

			objOrder1.setAppointDate(objDoctorBooking.getSelected_day_slot());

			// objOrder1.setVendorId();
			// objOrder1.getSubCategory();
			String[] symptomIdArray = objDoctorBooking.getSymptoms();
			for (String s : symptomIdArray) {
				System.out.println("symptoms...." + s);
			}

			// objOrder1.setLocationId(current_location.getId());
			objOrder1.setLocationId(current_location.getLocation_id());
			objOrder1.setAppointTimeName(objDoctorBooking.getSelected_time_slot());
			orderInfo1Dao.save(objOrder1);
			DoctorType doctorData = objDoctorBooking.getDoctor_type();

			System.out.println(new BigDecimal(doctorData.getDoctor_base_price()));
			System.out.println(new BigDecimal(doctorData.getDoctor_discount()));
			System.out.println(new BigDecimal(doctorData.getDoctor_total_price()));

			objOrder1.setTotalPrice(new BigDecimal(doctorData.getDoctor_base_price()));
			objOrder1.setTotalDiscount(new BigDecimal(doctorData.getDoctor_discount()));
			objOrder1.setNetAmount(new BigDecimal(doctorData.getDoctor_total_price()));

			orderInfo1Dao.save(objOrder1);

			EmailSendingUtil objEmailSendingUtil = new EmailSendingUtil();
			String sCustomerMail = objEmailSendingUtil.sendEmail(objOrder1, objContext, "customer");
			System.out.println("customer maili goes--" + sCustomerMail);
			String sAdminMail = objEmailSendingUtil.sendEmail(objOrder1, objContext, "admin");
			System.out.println("admin mail goes--" + sAdminMail);
			SMSUtil objSmsUtil = new SMSUtil();
			objSmsUtil.sendSms(objContext, objOrder1, "customer");
			objSmsUtil.sendSms(objContext, objOrder1, "order_admin");

			objOrder.setOrder_id(objOrder1.getGeneratedId());
			objOrder.setAmount("0");
			objOrder.setSuccess_url("/Sucess.html");
			objOrder.setFailure_url("failure");
			objOrder.setSalt_key("Salt key");
			objOrder.setTxn_id("QWETQWERTYUIO");
			objOrder.setMerchant_key("Merchant Key");

			System.out.println("BEGIN DOCTOR RESPONSE ");
			System.out.println(gson.toJson(objOrder));
			System.out.println("END DOCTOR RESPONSE");

			return new RestResponse("200", objOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return new RestResponse("500", "Invalid Server Response..");
		}
	}

	@RequestMapping(value = "/rest/sendFeedback")
	public @ResponseBody RestResponse sendFeedback(@RequestBody FeedbackAPI objBean)
			throws AddressException, MessagingException, IOException {
		RestResponse rr = null;
		// System.out.println("send Feedback ...");
		OrderInfo1 objOrderBean = new OrderInfo1();
		System.out.println(objBean.getEmail_id_or_mobile_no());

		String mobileNumber = objBean.getEmail_id_or_mobile_no();
		if (!mobileNumber.equals("none")) {
			OrderInfo1 orderInfo = orderInfo1Dao.getLastOrder(mobileNumber);
			if (orderInfo != null) {
				orderInfo1Dao.addFeedback(orderInfo, objBean.happy_rating, objBean.happy_comments);
			}
		}
		objOrderBean.setContactEmail(objBean.getEmail_id_or_mobile_no());
		objOrderBean.setDescription(objBean.getHappy_comments());
		EmailSendingUtil objEmailSendingUtil = new EmailSendingUtil();
		objEmailSendingUtil.sendEmail(objOrderBean, objContext, "feedback");
		rr = new RestResponse("200", "success");
		return rr;
	}

	@RequestMapping(value = "/rest/contactUs")
	public @ResponseBody RestResponse contactUs(@RequestBody ContactUs objBean)
			throws AddressException, MessagingException, IOException {
		RestResponse rr = null;
		System.out.println("rest Contact Us...");
		OrderInfo1 orderBean = new OrderInfo1();
		if (StringUtils.isNotBlank(objBean.getEmail_id_or_mobile_no())) {
			orderBean.setContactEmail(objBean.getEmail_id_or_mobile_no());
		}
		if (StringUtils.isNotBlank(objBean.getLeave_message())) {
			orderBean.setDescription(objBean.getLeave_message());
		}
		EmailSendingUtil objEmailSendingUtil = new EmailSendingUtil();
		objEmailSendingUtil.sendEmail(orderBean, objContext, "feedback");
		System.out.println(objBean.getEmail_id_or_mobile_no());
		System.out.println("get Doctor type end...");
		rr = new RestResponse("200", "success");
		return rr;
	}

	@RequestMapping(value = "/rest/getCustomerLocations")
	public @ResponseBody RestResponse getCustomerLocation(@RequestParam("phone") String phone,
			HttpServletResponse response, HttpServletRequest request, HttpSession objSession, Model mod) {
		// GeoLocations g = sContext.getObjGeoLocation("dummy");
		List<String> orderIds = orderInfo1Dao.getOrderIdsByPhone(phone);

		List<GeoLocations> gl = new ArrayList<GeoLocations>();
		for (String orderId : orderIds) {
			GeoLocations lgl = sContext.getObjGeoLocation(orderId);
			if (lgl != null) {
				gl.add(sContext.getObjGeoLocation(orderId));
			}
		}
		return new RestResponse("200", gl);
	}

	@RequestMapping(value = "/rest/getVendorLocations")
	public @ResponseBody RestResponse getVendorLocations(@RequestBody GeoLocations objGLocations,
			HttpServletResponse response, HttpServletRequest request, HttpSession objSession, Model mod) {
		sContext.setObjGeoLocation(objGLocations);
		return new RestResponse("200", "success");

	}

	@RequestMapping(value = "/rest/updateVendorLocations")
	public @ResponseBody RestResponse updateVendorLocations(@RequestBody GeoLocations objGLocations,
			HttpServletResponse response, HttpServletRequest request, HttpSession objSession, Model mod) {
		sContext.setObjGeoLocation(objGLocations);
		String orderId = objGLocations.getOrder_id();
		OrderInfo1 orderInfo1 = orderInfo1Dao.getByGeneratedId(orderId);
		if (orderInfo1 != null) {
			orderInfo1Dao.addStatus(orderId, AuroConstants.enRoute);
		}
		return new RestResponse("200", "success");

	}

	@RequestMapping(value = "/rest/getLocations")
	public @ResponseBody RestResponse getLocations(HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession, Model mod) {
		List<Map<String, String>> list = locationDao.getAllLocations("asc");
		if (list != null && list.size() > 0) {
			return new RestResponse("200", list);
		} else {
			return new RestResponse("500", "Location not available");
		}

	}

	@RequestMapping(value = "/rest/getMapLocations1")
	public @ResponseBody RestResponse getMapLocations1(HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession, Model mod) {

		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		String baseUrl = MiscUtils.getBaseUrl(request);
		objSession.setAttribute("latitude", latitude);
		objSession.setAttribute("longitude", longitude);
		int locId = clusterService.getLocationId(latitude, longitude);
		objSession.setAttribute("locationId", locId);

		geotag gt = new geotag(latitude, longitude);
		ArrayList<geotag> nearbyLocs = clusterService.getRandomGeotags(gt, 60);

		List<Map<String, String>> list = vendor1Dao.getMapLocations(baseUrl);
		System.out.println("Size of list " + list.size());
		System.out.println("Size of nearby " + nearbyLocs.size());
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("latitude", nearbyLocs.get(i).x + "");
			list.get(i).put("longitude", nearbyLocs.get(i).y + "");
		}
		if (list != null && list.size() > 0) {
			return new RestResponse("200", list);
		} else {
			return new RestResponse("500", "Location not available");
		}

	}

	 latlong now has the phone number as well 
	@RequestMapping(value = "/rest/getMapLocations")
	public @ResponseBody RestResponse getMapLocations(@RequestBody LatLongMobile latlong, HttpServletResponse response,
			HttpServletRequest request, HttpSession objSession, Model mod) {

		String baseUrl = MiscUtils.getBaseUrl(request);
		objSession.setAttribute("latitude", latlong.latitude);
		objSession.setAttribute("longitude", latlong.longitude);
		int locId = clusterService.getLocationId(latlong.latitude, latlong.longitude);
		objSession.setAttribute("locationId", locId);

		geotag gt = new geotag(latlong.latitude, latlong.longitude);
		ArrayList<geotag> nearbyLocs = clusterService.getRandomGeotags(gt, 60);

		List<Map<String, String>> list = vendor1Dao.getMapLocations(baseUrl);
		int locsize = nearbyLocs.size();
		for (int i = 0; i < list.size(); i++) {
			int j = i % locsize;
			list.get(i).put("latitude", nearbyLocs.get(j).x + "");
			list.get(i).put("longitude", nearbyLocs.get(j).y + "");
		}

		String order_icon = baseUrl + AuroConstants.order_icon;

		List<Map<String, String>> list2 = vendor1Dao.getOrderVendors(order_icon, latlong.mobile);

		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put("vendorList", list);
		returnObj.put("orderList", list2);

		if (list != null && list.size() > 0) {
			return new RestResponse("200", returnObj);
		} else {
			return new RestResponse("500", "Location not available");
		}

	}

	// subclassing ExceptionHandlerExceptionResolver (see below).
	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest req, Exception exception) {
		// logger.error("Request: " + req.getRequestURL() + " raised " +
		// exception);
		exception.printStackTrace();
		// ModelAndView mav = new ModelAndView();
		// mav.addObject("exception", exception);
		// mav.addObject("url", req.getRequestURL());
		// mav.setViewName("error");
		return "junk";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/rest/createOtp")
	public @ResponseBody RestResponse createOtp(@RequestParam("phoneNo") String phoneNo, HttpServletResponse response,
			HttpServletRequest request, HttpSession objSession, Model mod) throws IOException {
		String otp = null;
		ServiceUnitVendor objCoupon1 = null;
		int serviceId1 = 0;
		int vendorId1 = 0;
		int packageId1 = 0;
		String serviceTax = null;
		Properties objProperties = null;
		String couponCode = request.getParameter("couponCode");
		String serviceId = request.getParameter("serviceId");
		String venorId = request.getParameter("vendorId");
		String packageId = request.getParameter("packageId");
		if (StringUtils.isNotBlank(serviceId)) {
			serviceId1 = Integer.parseInt(serviceId);
		}
		if (StringUtils.isNotBlank(venorId)) {
			vendorId1 = Integer.parseInt(venorId);
		}
		if (StringUtils.isNotBlank(packageId)) {
			packageId1 = Integer.parseInt(packageId);
		}
		if (StringUtils.isNotBlank(couponCode)) {
			objCoupon1 = coupon1Dao.getCouponcodePercentage(couponCode, serviceId1, vendorId1, packageId1);

		}
		
		 * List<CouponCode> listCode = orderInfo1Dao.validateCoupon(couponCode);
		 * if(listCode != null && listCode.size() > 0){ otp = getOtp(phoneNo,
		 * objSession, mod); return new RestResponse("200", otp); }else{ return
		 * new RestResponse("500", "invalid coupon"); }
		 
		String sPropFilePath = objContext.getRealPath("Resources" + File.separator + "DataBase.properties");
		if (StringUtils.isNotBlank(sPropFilePath)) {
			objProperties = new Properties();
			InputStream objStream = new FileInputStream(sPropFilePath);
			objProperties.load(objStream);

		}

		otp = getOtp(phoneNo, objSession, mod);
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put("otp", otp);
		if (objCoupon1 != null) {
			returnObj.put("couponcode", objCoupon1.getCouponDiscount());
		} else {
			returnObj.put("couponcode", "0");
		}
		if (objProperties.getProperty("serviceTax") != null) {
			returnObj.put("serviceTax", objProperties.getProperty("serviceTax"));
		} else {
			returnObj.put("serviceTax", "0");
		}
		if (objProperties.getProperty("sbcease") != null) {
			returnObj.put("sbcease", objProperties.getProperty("sbkktax"));
		} else {
			returnObj.put("sbcease", "0");
		}
		return new RestResponse("200", returnObj);
	}

	public String getOtp(String phoneNo, HttpSession objSession, Model mod) {
		UserOtp objUserOtp = null;
		SMSUtil objSmsUtil = null;
		System.out.println("phoneNo..." + phoneNo);
		Map otpList = (Map) objSession.getAttribute("otplist");
		if (otpList == null)
			otpList = new HashMap<String, UserOtp>();
		objSession.setAttribute("otplist", otpList);
		String otp = MiscUtils.genOtp();
		System.out.println(otp);
		otpList.put(phoneNo, otp);
		mod.addAttribute("message", "The otp has been added for phoneNo " + phoneNo + " and otp is " + otp);

		objUserOtp = new UserOtp();
		System.out.println("phoneNo...." + phoneNo);
		objUserOtp.setPhoneNo(phoneNo);
		objUserOtp.setOtp(otp);
		objUserOtp.setStatus("initiated");
		userOtpDao.save(objUserOtp);

		OrderInfo1 objSmsBean = new OrderInfo1();
		objSmsBean.setOtp(objUserOtp.getOtp());
		objSmsBean.setContactNumber(phoneNo);
		objSmsUtil = new SMSUtil();
		objSmsUtil.sendSms(objContext, objSmsBean, "user_otp");
		return otp;
	}

	@RequestMapping(value = "/generateCustomerLocations")
	public @ResponseBody String generateCustomerLocations(HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession) {
		clusterService.generateGeoCoordinates();
		return "success";
	}

	@RequestMapping(value = "/createClusters")
	public @ResponseBody String createClusters(HttpServletResponse response, HttpServletRequest request) {
		List<Vendor1> vlist = vendor1Dao.getAll();
		clusterService.createClusters(vlist, 5, 5);
		return "success";
	}

	@RequestMapping(value = "/getNearby")
	public @ResponseBody List<VendorLocation> getNearByVendors(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {

		geotag gt = new geotag();
		gt.x = latitude;
		gt.y = longitude;

		List<VendorLocation> fullList = vendor1Dao.getAllVendorLocations();
		return clusterService.getNearByVendors(fullList, gt);
	}

	@RequestMapping(value = "/rest/customerTrackingData")
	public @ResponseBody RestResponse customerData(@RequestBody TrackingBean trackingBean, HttpServletResponse response,
			HttpServletRequest request, HttpSession objSession, Model mod) {
		if (trackingBean != null) {
			sContext.serTrackBean(trackingBean);
			SMSUtil objSmsUtil = new SMSUtil();

			List<OrderInfo1> list = orderInfo1Dao.getOrderDataByPhone(trackingBean.getPhone());
			for (OrderInfo1 local : list) {
				OrderInfo1 objOrder1 = new OrderInfo1();
				objOrder1.setGeneratedId(local.getGeneratedId());
				objOrder1.setContactNumber(local.getVendorMobile());
				objOrder1.setVendorMobile(trackingBean.getPhone());
				objSmsUtil.sendSms(objContext, objOrder1, "customer_alert");
			}
			return new RestResponse("200", "data received successfully");
		} else {
			return new RestResponse("500", "Code and message not recieved");
		}
	}

	@RequestMapping(value = "/rest/vendorTrackingData")
	public @ResponseBody RestResponse vendorData(HttpServletResponse response, HttpServletRequest request,
			HttpSession objSession, Model mod) {
		TrackingBean customerData = sContext.getTrackBean();
		if (customerData != null) {
			return new RestResponse("200", customerData);
		} else {
			return new RestResponse("500", "Invalid server response");
		}
	}

	@RequestMapping(value = "/rest/isAccept")
	public @ResponseBody RestResponse isAccept(@RequestBody AcceptReject acceptReject)
			throws AddressException, MessagingException, IOException {
		String message = "success..";
		try {
			if (acceptReject != null && acceptReject.isIs_accept()) {
				OrderInfo1 orderInfo = orderInfo1Dao.getByGeneratedId(acceptReject.getOrder_id());
				OrderStatusLog1 osl1 = new OrderStatusLog1();
				osl1.setOrderId(orderInfo.getId());
				osl1.setStatusId(AuroConstants.confirmed);
				orderInfo.setStatusId(osl1.getStatusId());
				orderStatusLog1Dao.save(osl1);
				orderInfo.setVendorId(Integer.parseInt(acceptReject.getVendor_id()));
				orderInfo1Dao.save(orderInfo);
				// confirmation sms and email to customer and vendor
				Vendor1 vendor = vendor1Dao.getById(orderInfo.getVendorId());
				if (osl1.getStatusId() == AuroConstants.confirmed) {
					TimeSlots timeSlots = timeSlotsDao.getById(orderInfo.getAppointmentSlotId());
					orderInfo.setAppointTimeName(timeSlots.getLabel());
					orderInfo.setContactNumber(vendor.getMobileNumber());
					if (vendor.getLastName() == null) {
						vendor.setLastName("");
					}
					orderInfo.setVendorName(vendor.getFirstName() + " " + vendor.getLastName());
					orderInfo.setVendorMobile(vendor.getMobileNumber());
					EmailSendingUtil objEmailSendingUtil = new EmailSendingUtil();
					objEmailSendingUtil.sendEmail(orderInfo, objContext, "customer_vendor");
					orderInfo.setContactNumber(orderInfo.getContactNumber());
					SMSUtil objSms = new SMSUtil();
					if (orderInfo.getNetAmount() == null && orderInfo.getTotalPrice() == null) {
						objSms.sendSms(objContext, orderInfo, "sms_vendor_message");
					} else if (orderInfo.getNetAmount() != null && orderInfo.getTotalPrice() != null) {
						objSms.sendSms(objContext, orderInfo, "sms_cusotmer_orderpriceconfirm");
					} else {
						message = "sms_fail";
					}
					System.out.println("sms status --" + message);
					orderInfo.setContactNumber(vendor.getMobileNumber());
					objSms.sendSms(objContext, orderInfo, "sms_vendor_confirmation");
					objSms.sendSms(objContext, orderInfo, "vendor_customer_details");
				}
				return new RestResponse("200", "Order confirmation successful.");
			} else {
				return new RestResponse("500", "Invalid server response");
			}
		} catch (Exception e) {
			return new RestResponse("500", "Invalid server response");
		}

	}

	@RequestMapping(value = "/rest/updateStatus")
	public @ResponseBody RestResponse updateStatus(@RequestBody AcceptReject acceptReject)
			throws AddressException, MessagingException, IOException {
		String message = "success..";
		try {
			OrderInfo1 orderInfo = orderInfo1Dao.getByGeneratedId(acceptReject.getOrder_id());
			int statusId = orderInfo.getStatusId();
			orderInfo.setStatusId(acceptReject.getStatus_id());
			orderInfo1Dao.addStatus(acceptReject.getOrder_id(), acceptReject.getStatus_id());
			SMSUtil objSms = new SMSUtil();
			if (statusId != acceptReject.getStatus_id()) {
				Vendor1 vendor = vendor1Dao.getById(Integer.parseInt(acceptReject.getVendor_id()));
				if (acceptReject.getStatus_id() == AuroConstants.cancel) {
					objSms.sendSms(objContext, orderInfo, "sms_cancel_customer");
					if (orderInfo.getVendorId() != 0) {
						orderInfo.setContactNumber(vendor.getMobileNumber());
						objSms.sendSms(objContext, orderInfo, "sms_cancel_vendor");
					}
				} else if (acceptReject.getStatus_id() == AuroConstants.paid) {
					objSms.sendSms(objContext, orderInfo, "sms_paid_customer");
					objSms.sendSms(objContext, orderInfo, "sms_customer_feedback");
					orderInfo.setContactNumber(vendor.getMobileNumber());
					objSms.sendSms(objContext, orderInfo, "sms_vendor_feedback");
					new EmailSendingUtil().sendEmail(orderInfo, objContext, "customer_feedback");
				} else if (acceptReject.getStatus_id() == AuroConstants.sampleCollected) {
					objSms.sendSms(objContext, orderInfo, "Customer_Sample_Collected");
				} else if (acceptReject.getStatus_id() == AuroConstants.reportsUploaded) {
					message = objSms.sendSms(objContext, orderInfo, "Customer_reports_upload");
				} else if (acceptReject.getStatus_id() == AuroConstants.priceOnInspection) {
					message = objSms.sendSms(objContext, orderInfo, "sms_customer_oninspection");
				} else {
					message = "sms_fail";
				}
				System.out.println("sms sending.." + message);
				return new RestResponse("200", "Order status updated.");
			} else {

				return new RestResponse("500", "status not changed as no change with previous change");
			}

		} catch (Exception e) {
			return new RestResponse("500", "Invalid server response");
		}
	}
	
	 * @RequestMapping(value = "/rest/getVendorProfile") public @ResponseBody
	 * RestResponse getVendorProfile(@RequestBody String phone,
	 * HttpServletResponse response, HttpServletRequest request, HttpSession
	 * objSession, Model mod) { //vendor profile should come based on the phone
	 * no... TrackingBean customerData = sContext.getTrackBean();
	 * if(customerData != null){ return new RestResponse("200", customerData);
	 * }else{ return new RestResponse("500", "Invalid server response"); } }
	 

	@RequestMapping(value = "/rest/loginVendor")
	public @ResponseBody RestResponse loginVendor(HttpServletResponse response, HttpServletRequest request,
			@RequestBody VendorLoginInput vendorData) throws Exception {
		// Vendor1 vendor1 = vendor1Dao.getById(vendorId);
		List<Object> vendorList = vendor1Dao.getVendorOrders(vendorData);
		Map<String, Object> orders = new HashMap<String, Object>();
		orders.put("orders", vendorList);
		RestResponse rr = new RestResponse("200", orders);
		return rr;
	}

	@RequestMapping(value = "/rest/getVendorOrderList")
	public @ResponseBody RestResponse getVendorOrderList(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("vendor_id") String vendorId) throws Exception {
		List<VendorService1> vendorServices = vendorService1Dao.getServicesForVendor(vendorId);

		Map<String, OrderSequence> orderSeqMap = new HashMap<String, OrderSequence>();

		for (VendorService1 vs : vendorServices) {
			OrderSequence seq = orderSequenceDao.getByServiceId(vs.getServiceId() + "");
			// List<OrderStatus> statList = seq.getSequence();
			orderSeqMap.put(vs.getServiceId() + "", seq);
		}
		List<Object> vendorList = vendor1Dao.getVendorOrderDetail(vendorId, orderSeqMap);
		Map<String, Object> orders = new HashMap<String, Object>();
		orders.put("orders", vendorList);
		RestResponse rr = new RestResponse("200", orders);
		return rr;
	}

	@RequestMapping(value = "/rest/getVendorDetailsOfPackage")
	public @ResponseBody RestResponse getVendorDetailsOfPackage(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("package_id") int package_id) throws Exception {
		String baseUrl = MiscUtils.getBaseUrl(request);
		Collection<ServiceUnitReviews> list = serviceUnitDao.getVendorPackageDate(package_id, baseUrl);
		RestResponse rr = new RestResponse("200", list);
		return rr;
	}

	@RequestMapping(value = "/rest/getCustomerOrderList")
	public @ResponseBody RestResponse getCustomerOrderList(HttpServletResponse response, HttpServletRequest request,
			@RequestParam("phone") String phone) throws Exception {
		List<OrderInfo1> vendorServices = orderInfo1Dao.getOrders(phone);

		Map<String, OrderSequence> orderSeqMap = new HashMap<String, OrderSequence>();

		for (OrderInfo1 vs : vendorServices) {
			OrderSequence seq = orderSequenceDao.getByServiceId(vs.getServiceId() + "");
			// List<OrderStatus> statList = seq.getSequence();
			orderSeqMap.put(vs.getServiceId() + "", seq);
		}
		String baseUrl = MiscUtils.getBaseUrl(request);
		List<Object> vendorList = orderInfo1Dao.getCustomerOrderDetail(phone, orderSeqMap, baseUrl);
		Map<String, Object> orders = new HashMap<String, Object>();
		orders.put("orders", vendorList);
		RestResponse rr = new RestResponse("200", orders);
		return rr;
	}

	@RequestMapping(value = "/rest/vendorRegistration")
	public @ResponseBody RestResponse vendorRegistration(@RequestBody String val, HttpServletResponse response, HttpServletRequest request
			,HttpSession objSession, Model mod){
		String vendorName = null;
		String mobile_no = null;
		String password = null;
		String locality = null;
		String services = null;
		String address = null;
		Vendor1 vendorObj = null;
		int vendorserviceId=0;
		int id=0;
		String msg= null;
		 String[] array = null;
		 RestResponse rr = null;
		 String otp = null;
		 Map<String, String> returnObj = new HashMap<String, String>();
		try {
			JSONObject obj = new JSONObject(val);
			System.out.println(obj.get("name"));
			vendorName=	(String) obj.get("name");
			mobile_no=	(String) obj.get("mobile_no");
			password=	(String) obj.get("password");
			locality=	(String) obj.get("locality");
			services=	(String) obj.get("services");
			address=	(String) obj.get("address");
			vendorObj = new Vendor1();
			if(StringUtils.isNotBlank(vendorName)){
				vendorObj.setFirstName(vendorName);
			}
			if(StringUtils.isNotBlank(mobile_no)){
				vendorObj.setMobileNumber(mobile_no);
			}
			if(StringUtils.isNotBlank(password)){
				vendorObj.setPassword(password);
			}
			if(StringUtils.isNotBlank(locality)){
				int locationId = location1Dao.getLocationName(locality);
				vendorObj.setServingCity(locationId);
			}
			if(StringUtils.isNotBlank(services)){
				int serviceId = service1Dao.getServiceId(services);
				services =String.valueOf(serviceId);
				vendorObj.setSubCategory1(services);
			}
			if(StringUtils.isNotBlank(address)){
				vendorObj.setAddress(address);
			}
			objSession.setAttribute(mobile_no, vendorObj);
			Vendor1 obj1 = (Vendor1)(objSession.getAttribute(mobile_no));
			System.out.println(obj1.getMobileNumber());
			vendor1Dao.save(vendorObj);
			VendorService1 objvendorService1=new VendorService1();
			
			 String Lid=services;
			 array = Lid.split(",");
			 for(int i =0;i<array.length;i++){
				 int serviceId = service1Dao.getServiceId(array[i]);
				 objvendorService1.setVendorId(vendorObj.getId());
				 objvendorService1.setServiceId(serviceId);
				 objvendorService1.setId(id);
				 objVendorService1Dao.save(objvendorService1);
			 }
			int vendor = 0;
			vendor = vendor1Dao.getNoOfVendors(mobile_no);
			if(vendor == 0){
				
			msg = "success";
			otp = getOtp(mobile_no, objSession, mod);
			returnObj.put("otp_code", otp);
			
			}else{
				msg="fail";
				returnObj.put("otp_code", "null");
			}
			returnObj.put("msg", msg);
		} catch (Exception e) {
			e.printStackTrace();
			 rr = new RestResponse("500", "Invalid server response");
		}

		 rr = new RestResponse("200", returnObj);
		return rr;
	}
	


	@RequestMapping(value = "/rest/getVendorOrderHistory")
	public @ResponseBody RestResponse getVendorOrderHistory(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("mobile_number") String mobile_number) throws Exception {
		
		List<Map<String, String>> list = orderInfo1Dao.getVendorOrderHistory(mobile_number);
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put("vendorList", list);
		RestResponse rr = new RestResponse("200", list);
		return rr;
	}
	
	
	@RequestMapping(value = "/rest/isAcceptOrder")
	public @ResponseBody RestResponse isAcceptOrder(@RequestBody String val)
			throws AddressException, MessagingException, IOException {
		String message = "success..";
		String order_id =null;
		String isAccept = null;
		try {
			
			JSONObject obj = new JSONObject(val);
			System.out.println(obj.get("order_id"));
			order_id=	(String) obj.get("order_id");
			isAccept=	(String) obj.get("isAccept");
			OrderInfo1 orderInfo = orderInfo1Dao.getByGeneratedId(order_id);
			if(isAccept.equals("true")){
				orderInfo.setStatusId(1);
				orderInfo1Dao.save(orderInfo);
				
				
				Vendor1 vendor = vendor1Dao.getById(orderInfo.getVendorId());
					TimeSlots timeSlots = timeSlotsDao.getById(orderInfo.getAppointmentSlotId());
					orderInfo.setAppointTimeName(timeSlots.getLabel());
					//orderInfo.setContactNumber(vendor.getMobileNumber());
					if (vendor.getLastName() == null) {
						vendor.setLastName("");
					}
					orderInfo.setVendorName(vendor.getFirstName() + " " + vendor.getLastName());
					orderInfo.setVendorMobile(vendor.getMobileNumber());
					EmailSendingUtil objEmailSendingUtil = new EmailSendingUtil();
					objEmailSendingUtil.sendEmail(orderInfo, objContext, "customer_vendor");
					//orderInfo.setContactNumber(orderInfo.getContactNumber());
					System.out.println(orderInfo.getNetAmount());
					System.out.println(orderInfo.getTotalPrice());
					SMSUtil objSms = new SMSUtil();
					if (orderInfo.getNetAmount().toString().equals("0.00")) {
						objSms.sendSms(objContext, orderInfo, "sms_vendor_message");
					} else if (orderInfo.getNetAmount() != new BigDecimal(0.00) ) {
						objSms.sendSms(objContext, orderInfo, "sms_cusotmer_orderpriceconfirm");
					} else {
						message = "sms_fail";
					}
					System.out.println("sms status --" + message);
					orderInfo.setContactNumber(vendor.getMobileNumber());
					objSms.sendSms(objContext, orderInfo, "sms_vendor_confirmation");
					objSms.sendSms(objContext, orderInfo, "vendor_customer_details");
				
			} else {
				orderInfo.setStatusId(6);
				orderInfo1Dao.save(orderInfo);
			}
				
				
				
				
			
			if (acceptReject != null && acceptReject.isIs_accept()) {
				OrderInfo1 orderInfo = orderInfo1Dao.getByGeneratedId(acceptReject.getOrder_id());
				OrderStatusLog1 osl1 = new OrderStatusLog1();
				osl1.setOrderId(orderInfo.getId());
				osl1.setStatusId(AuroConstants.confirmed);
				orderInfo.setStatusId(osl1.getStatusId());
				orderStatusLog1Dao.save(osl1);
				orderInfo.setVendorId(Integer.parseInt(acceptReject.getVendor_id()));
				orderInfo1Dao.save(orderInfo);
				// confirmation sms and email to customer and vendor
				Vendor1 vendor = vendor1Dao.getById(orderInfo.getVendorId());
				if (osl1.getStatusId() == AuroConstants.confirmed) {
					TimeSlots timeSlots = timeSlotsDao.getById(orderInfo.getAppointmentSlotId());
					orderInfo.setAppointTimeName(timeSlots.getLabel());
					orderInfo.setContactNumber(vendor.getMobileNumber());
					if (vendor.getLastName() == null) {
						vendor.setLastName("");
					}
					orderInfo.setVendorName(vendor.getFirstName() + " " + vendor.getLastName());
					orderInfo.setVendorMobile(vendor.getMobileNumber());
					EmailSendingUtil objEmailSendingUtil = new EmailSendingUtil();
					objEmailSendingUtil.sendEmail(orderInfo, objContext, "customer_vendor");
					orderInfo.setContactNumber(orderInfo.getContactNumber());
					SMSUtil objSms = new SMSUtil();
					if (orderInfo.getNetAmount() == null && orderInfo.getTotalPrice() == null) {
						objSms.sendSms(objContext, orderInfo, "sms_vendor_message");
					} else if (orderInfo.getNetAmount() != null && orderInfo.getTotalPrice() != null) {
						objSms.sendSms(objContext, orderInfo, "sms_cusotmer_orderpriceconfirm");
					} else {
						message = "sms_fail";
					}
					System.out.println("sms status --" + message);
					orderInfo.setContactNumber(vendor.getMobileNumber());
					objSms.sendSms(objContext, orderInfo, "sms_vendor_confirmation");
					objSms.sendSms(objContext, orderInfo, "vendor_customer_details");
				}
				return new RestResponse("200", "Order confirmation successful.");
			} else {
				return new RestResponse("500", "Invalid server response");
			}
		} catch (Exception e) {
			return new RestResponse("500", "Invalid server response");
		}
		return new RestResponse("200", "Order confirmation successful.");

	}


	@RequestMapping(value = "/rest/getVendorOrderListMobile")
	public @ResponseBody RestResponse getVendorOrderListMobile(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("mobile_number") String mobile_number) throws Exception {
		
		List<Map<String, String>> list = orderInfo1Dao.getVendorOrderListMobile(mobile_number);
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put("vendorList", list);
		RestResponse rr = new RestResponse("200", list);
		return rr;
	}
	@RequestMapping(value = "/rest/getVendorOrderDetailsMobile")
	public @ResponseBody RestResponse getVendorOrderDetailsMobile(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("order_id") String order_id) throws Exception {
		
		List<Map<String, String>> list = orderInfo1Dao.getVendorOrderDetailsMobile(order_id);
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put("vendorList", list);
		RestResponse rr = new RestResponse("200", list);
		return rr;
	}
	@RequestMapping(value = "/rest/gertVendorStatusUpdate")
	public @ResponseBody RestResponse gertVendorStatusUpdate(@RequestBody String val)
			 {
		String message = "success..";
		String order_id =null;
		String start_status = null;
		AutoPriceMeater autoPriceMeater = null;
		try {
			autoPriceMeater = new AutoPriceMeater();
			JSONObject obj = new JSONObject(val);
			System.out.println(obj.get("order_id"));
			order_id=	(String) obj.get("order_id");
			start_status=	(String) obj.get("start_status");
			OrderInfo1 orderInfo = orderInfo1Dao.getByGeneratedId(order_id);
			if(start_status.equals("true")){
				orderInfo.setStatusId(16);
				orderInfo1Dao.save(orderInfo);
			}
			Date date = new Date();
			String time=date.getHours()+".";
			if(date.getMinutes() < 10){
				time = time+"0"+date.getMinutes();
			}else{
				time = time+date.getMinutes();
			}
			double time2= Double.parseDouble(time);
			autoPriceMeater.setGeneratedId(order_id);
			autoPriceMeater.setStartTime(time2);
			if(orderInfo.getVendorId() != 0){
			autoPriceMeater.setVendor_id(orderInfo.getVendorId());
			}
			autoPriceMeaterDao.save(autoPriceMeater);
		System.out.println(time2);
		}catch(Exception e){
			
		}
		return new RestResponse("200", "sucess");
	}
	@RequestMapping(value = "/rest/gertVendorStatusUpdateComplete")
	public @ResponseBody RestResponse gertVendorStatusUpdateComplete(@RequestBody String val)
			 {
		String message = "success..";
		String order_id =null;
		String start_status = null;
		String remarks = null;
		String workOrderFormImage =null;
		AutoPriceMeater autoPriceMeater = null;
		try {
			
			JSONObject obj = new JSONObject(val);
			System.out.println(obj.get("order_id"));
			order_id=	(String) obj.get("order_id");
			//start_status=	(String) obj.get("complete_status");
			remarks=	(String) obj.get("remarks");
			//workOrderFormImage=	(String) obj.get("workOrderFormImage");
			//System.out.println(obj.get("workOrderFormImage"));
			
			OrderInfo1 orderInfo = orderInfo1Dao.getByGeneratedId(order_id);
			orderInfo.setStatusId(18);
			if(StringUtils.isNotEmpty(remarks)){
				orderInfo.setAuroLog(remarks);
				AurospacesOrderLog objAurospaceLog =new AurospacesOrderLog();
				objAurospaceLog.setOrderId(orderInfo.getId());
				objAurospaceLog.setUserId(orderInfo.getUserId());
				objAurospaceLog.setAurospacesLog(remarks);
				auroOrderLog.save(objAurospaceLog);
				}
			orderInfo.setIsPause(0);
			orderInfo1Dao.save(orderInfo);
			 autoPriceMeater =  autoPriceMeaterDao.getByGendrtatedId(order_id);
		      Date date = new Date();
				String time=date.getHours()+".";
				if(date.getMinutes() < 10){
					time = time+"0"+date.getMinutes();
				}else{
					time = time+date.getMinutes();
				}
				
				double time2= Double.parseDouble(time);
				autoPriceMeater.setEndTime(time2);
				autoPriceMeaterDao.save(autoPriceMeater);
				
		
		}catch(Exception e){
			return new RestResponse("500", "Order is completed Exception");
		
		}
		return new RestResponse("200", "Order is completed success");
	}
	
	@RequestMapping(value = "/rest/loginVendorMobile")
	public @ResponseBody RestResponse loginVendorMobile(@RequestBody String val) throws Exception {
		String responce =null;
		String mobile_number= null;
		String password= null;
		try{
			JSONObject obj = new JSONObject(val);
			System.out.println(obj.get("mobile_number"));
			mobile_number=	(String) obj.get("mobile_number");
			password=	(String) obj.get("password");
			responce = vendor1Dao.vendorLogin(mobile_number, password);
		}catch(Exception e){
			e.printStackTrace();
			new RestResponse("500", "invalid mobileNumber and password");
		}
		return new RestResponse("200", responce);
	}
	@RequestMapping(value = "/rest/getVendorCompletedOrders")
	public @ResponseBody RestResponse getVendorCompletedOrders(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("mobile_number") String mobile_number) throws Exception {
		
		List<Map<String, String>> list = orderInfo1Dao.getVendorCompletedOrders(mobile_number);
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put("vendorList", list);
		RestResponse rr = new RestResponse("200", list);
		return rr;
	}
	@RequestMapping(value = "/rest/getVendorCancelOrders")
	public @ResponseBody RestResponse getVendorCancelOrders(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("mobile_number") String mobile_number) throws Exception {
		
		List<Map<String, String>> list = orderInfo1Dao.getVendorCancelOrders(mobile_number);
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put("vendorList", list);
		RestResponse rr = new RestResponse("200", list);
		return rr;
	}
	@RequestMapping(value = "/rest/otpValidateVendorRegistration")
	public  String otpValidateVendorRegistration(HttpServletResponse response,
			HttpServletRequest request, @RequestBody String val) throws Exception {
		String vendorName = null;
		String mobile_no = null;
		String password = null;
		String locality = null;
		String services = null;
		String address = null;
		String email_id = null;
		Vendor1 vendorObj = null;
		int vendorserviceId=0;
		int id=0;
		 String[] array = null;
		 RestResponse rr = null;
		 String otp = null;
		 JSONObject obj = new JSONObject(val);
		vendorName=	(String) obj.get("name");
		mobile_no=	(String) obj.get("mobile_no");
		password=	(String) obj.get("password");
		locality=	(String) obj.get("locality");
		services=	(String) obj.get("services");
		address=	(String) obj.get("address");
		email_id=	(String) obj.get("email");
		vendorObj = new Vendor1();
		if(StringUtils.isNotBlank(vendorName)){
			vendorObj.setFirstName(vendorName);
		}
		if(StringUtils.isNotBlank(mobile_no)){
			vendorObj.setMobileNumber(mobile_no);
		}
		if(StringUtils.isNotBlank(password)){
			vendorObj.setPassword(password);
		}
		if(StringUtils.isNotBlank(email_id)){
			vendorObj.setEmail(email_id);
		}
		if(StringUtils.isNotBlank(locality)){
			int locationId = location1Dao.getLocationName(locality);
			vendorObj.setServingCity(locationId);
		}
		if(StringUtils.isNotBlank(services)){
			int serviceId = service1Dao.getServiceId(services);
			services =String.valueOf(serviceId);
			vendorObj.setSubCategory1(services);
		}
		if(StringUtils.isNotBlank(address)){
			vendorObj.setAddress(address);
		}
		
		vendor1Dao.save(vendorObj);
		VendorService1 objvendorService1=new VendorService1();
		
		 String Lid=services;
		 array = Lid.split(",");
		 for(int i =0;i<array.length;i++){
			 int serviceId = service1Dao.getServiceId(array[i]);
			 objvendorService1.setVendorId(vendorObj.getId());
			 objvendorService1.setServiceId(serviceId);
			 objvendorService1.setId(id);
			 objVendorService1Dao.save(objvendorService1);
		 }

		return "null";
	}
	@RequestMapping(value = "/rest/getVendorProfile")
	public @ResponseBody RestResponse getVendorProfile(HttpServletResponse response,
			HttpServletRequest request, @RequestParam("mobile_number") String mobile_number) throws Exception {
		
		List<Map<String, String>> list = vendor1Dao.getVendorProfile(mobile_number);
		Map<String, Object> returnObj = new HashMap<String, Object>();
		
		RestResponse rr = new RestResponse("200", list);
		System.out.println("response:"+list);
		return rr;
	}
	
	@RequestMapping(value = "/rest/adminLoginMobile")
	public @ResponseBody RestResponse adminLoginMobile(HttpServletResponse response,
			HttpServletRequest request,HttpSession objSession , @RequestBody String val) throws Exception {
		
		LoginBean objLoginBean = null;
		List<Map<String, String>> listOrderBeans = null;
		RestResponse rr = null;
		try{
			 JSONObject obj = new JSONObject(val);
		String sUserName = (String) obj.get("userName");
		String sPassword = (String) obj.get("password");
		System.out.println(sUserName + "" + sPassword);
		if (sUserName != null && sPassword != null) {
			objLoginBean = new LoginBean();
			objLoginBean.setUserName(sUserName);
			objLoginBean.setPassword(sPassword);
			LoginBean localBean = objLoginService.getUserDetails(objLoginBean);
			if (localBean != null) {
				objSession.setAttribute("cacheUserBean", localBean);
			    rr = new RestResponse("200", "success");
				
			}else{
				rr = new RestResponse("200", "faile");
			}
			//listOrderBeans = orderInfo1Dao.getOrdersByParams(null,null,null,null,null,null,null,null,null,null,null);
			
		}
		}catch (Exception e) {
			// TODO: handle exception
		}

		
		
		return rr;
	}
	@RequestMapping(value = "/rest/adminAllOrdersMobile")
	public @ResponseBody RestResponse adminAllOrdersMobile(HttpServletResponse response,
			HttpServletRequest request,HttpSession objSession) throws Exception {
		
		LoginBean objLoginBean = null;
		List<Map<String, String>> listOrderBeans = null;
		RestResponse rr = null;
		try{
		String sUserName = request.getParameter("userName");
		String sPassword = request.getParameter("password");
		System.out.println(sUserName + "" + sPassword);
		if (sUserName != null && sPassword != null) {
			objLoginBean = new LoginBean();
			objLoginBean.setUserName(sUserName);
			objLoginBean.setPassword(sPassword);
			LoginBean localBean = objLoginService.getUserDetails(objLoginBean);
			if (localBean != null) {
				objSession.setAttribute("cacheUserBean", localBean);
			    rr = new RestResponse("200", "success");
				
			}else{
				rr = new RestResponse("200", "faile");
			}
			
			
		}
			listOrderBeans = orderInfo1Dao.getOrdersByParams(null,null,null,null,null,null,null,null,null,null,null);
			rr = new RestResponse("200", listOrderBeans);
		}catch (Exception e) {
			// TODO: handle exception
		}

		
		
		return rr;
	}
	
	@RequestMapping(value = "/rest/resendOtp")
	public @ResponseBody RestResponse resendOtp(HttpServletResponse response,HttpSession objSession,Model  mod,
			HttpServletRequest request, @RequestParam("mobile_number") String mobile_number) throws Exception {
		String otp = null;
		 Map<String, String> returnObj = new HashMap<String, String>();
		 otp = getOtp(mobile_number, objSession, mod);
			returnObj.put("otp_code", otp);
			RestResponse rr = new RestResponse("200", returnObj);
		return rr;
	}
	@RequestMapping(value = "/rest/autoPriceMetarPauses")
	public @ResponseBody RestResponse autoPriceMetarPauses(HttpServletResponse response,HttpSession objSession,Model  mod,
			HttpServletRequest request, @RequestBody String val) throws Exception {
		String order_id = null;
		String message =null;
		AutoPriceMeater autoPriceMeater = null;
		JSONObject obj = new JSONObject(val);
		      order_id =(String) obj.get("order_id");
		      message =(String) obj.get("message");
		      autoPriceMeater =  autoPriceMeaterDao.getByGendrtatedId(order_id);
		      Date date = new Date();
				String time=date.getHours()+".";
				if(date.getMinutes() < 10){
					time = time+"0"+date.getMinutes();
				}else{
					time = time+date.getMinutes();
				}
				
				double time2= Double.parseDouble(time);
				autoPriceMeater.setEndTime(time2);
				autoPriceMeater.setMessage(message);
				autoPriceMeaterDao.save(autoPriceMeater);
				
				OrderInfo1 orderInfo = orderInfo1Dao.getByGeneratedId(order_id);
				orderInfo.setIsPause(1);
				orderInfo1Dao.save(orderInfo);
		
			RestResponse rr = new RestResponse("200", "");
		return rr;
	}
	@RequestMapping(value = "/rest/autoPriceRestart")
	public @ResponseBody RestResponse autoPriceRestart(HttpServletResponse response,HttpSession objSession,Model  mod,
			HttpServletRequest request, @RequestBody String val) throws Exception {
		String order_id = null;
		AutoPriceMeater autoPriceMeater = null;
		RestResponse rr = null;
		try{
			autoPriceMeater = new AutoPriceMeater();
		JSONObject obj = new JSONObject(val);
		      order_id = (String)obj.get("order_id");
		      OrderInfo1 orderInfo = orderInfo1Dao.getByGeneratedId(order_id);
		      Date date = new Date();
				String time=date.getHours()+".";
				if(date.getMinutes() < 10){
					time = time+"0"+date.getMinutes();
				}else{
					time = time+date.getMinutes();
				}
				double time2= Double.parseDouble(time);
				autoPriceMeater.setGeneratedId(order_id);
				autoPriceMeater.setStartTime(time2);
				if(orderInfo.getVendorId() != 0){
				autoPriceMeater.setVendor_id(orderInfo.getVendorId());
				}
				autoPriceMeaterDao.save(autoPriceMeater);
				orderInfo.setIsPause(0);
				orderInfo1Dao.save(orderInfo);
			   rr = new RestResponse("200", "");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return rr;
	}
	
	@RequestMapping(value = "/rest/serviceListTags")
	public @ResponseBody RestResponse serviceListTags(HttpServletResponse response,HttpSession objSession,Model  mod,
			HttpServletRequest request,@RequestBody String val) throws Exception {
		String baseUrl = MiscUtils.getBaseUrl(request);
		 Map<String, String> returnObj = new HashMap<String, String>();
		 List<Map<String, String>>  list = null;
		 list = serviceUnitDao.gettagsPrice(baseUrl);
			RestResponse rr = new RestResponse("200", list);
		return rr;
	}
	@RequestMapping(value = "/rest/a1Booking")
	public @ResponseBody RestResponse a1Booking(HttpServletResponse response,HttpSession objSession,Model  mod,
			HttpServletRequest request,@RequestBody String val) throws Exception {
		String baseUrl = MiscUtils.getBaseUrl(request);
		String service_name = null; 
		String cust_email_id = null;
		String cust_mobile_no = null;
		String cust_servicing_address = null;
		String description = null;
		String date = null;
		String time = null;
		String Price =null;
		String service_image = null;
		JSONObject obj = new JSONObject(val);
	      
		try{
			service_name = (String)obj.get("service_name");
			cust_email_id = (String)obj.get("cust_email_id");
			cust_mobile_no = (String)obj.get("cust_mobile_no");
			cust_servicing_address = (String)obj.get("cust_servicing_address");
			date = (String)obj.get("date");
			time = (String)obj.get("time");
			Price = (String)obj.get("Price");
			service_image = (String)obj.get("service_image");
			
			
		}catch(Exception e){
			
		}
			RestResponse rr = new RestResponse("200", "save sucess ");
		return rr;
	}
	@RequestMapping(value = "/rest/test1")
	public @ResponseBody RestResponse test(HttpServletResponse response, HttpServletRequest request, @RequestBody ScalarBean requestData
			) throws Exception {
		System.out.println("---------"+requestData.getDescription());
		JSONObject obj =new JSONObject(val);
		String description =(String)obj.get("description");
		System.out.println(description);
		// Vendor1 vendor1 = vendor1Dao.getById(vendorId);
//		List<Object> vendorList = vendor1Dao.getVendorOrders(vendorData);
		RestResponse rr = null;
//		if(objscScalarBean.getId() == 0){
//			 rr = new RestResponse("500", "error");
//		}else{
//			 rr = new RestResponse("200", "success");
//		}
		Map<String, Object> orders = new HashMap<String, Object>();
//		orders.put("orders", vendorList);
		
		return rr;
	}*/
//}
