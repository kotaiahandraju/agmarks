package com.aurospaces.neighbourhood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurospaces.neighbourhood.bean.AddProduct;
import com.aurospaces.neighbourhood.db.dao.AddProductDao;
import com.aurospaces.neighbourhood.util.KhaibarGasUtil;

@Controller
public class AddProductController 
{
	@Autowired AddProductDao addProductDao;
	@RequestMapping(value = "/rest/saveAddProduct")
	public @ResponseBody String saveAddProducts( @RequestBody AddProduct addproduct, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			addProductDao.save(addproduct);
			objJSON.put("status", "insertedSuccesfully");
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/searchProduct")
	public @ResponseBody String searchProduct( @RequestBody AddProduct addproduct, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			List<AddProduct> product =addProductDao.searchProduct(addproduct);
			objJSON.put("product", product);
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
	@RequestMapping(value = "/rest/removeProduct")
	public @ResponseBody String removeProduct( @RequestBody AddProduct addproduct, HttpServletRequest request) throws Exception {
		JSONObject objJSON = new JSONObject();
		try{
			addProductDao.removeProduct(addproduct);
			objJSON.put("status", "success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return String.valueOf(objJSON);
	}
}
