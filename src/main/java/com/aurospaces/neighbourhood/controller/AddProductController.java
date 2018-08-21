package com.aurospaces.neighbourhood.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.castor.util.Base64Decoder;
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
			if(null!=addproduct.getImage())
			{
				String imgpath=imgdecoder(addproduct.getImage(),request);
				addproduct.setImage(imgpath);
			}
			addProductDao.save(addproduct);
			objJSON.put("status", "success");
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
	 private String  imgdecoder(String imgData, HttpServletRequest request) {
	    	
	    	String filepath = null;
	    	
	    	FileOutputStream osf;
	    	
	    	KhaibarGasUtil utils=new KhaibarGasUtil();
	    	
	    	String id =utils.randNum(4);
	    	
				Base64Decoder decoder = new Base64Decoder(); 
				//byte[] imgBytes = decoder.decode(imgData.split(",")[1]);
				
				byte[] imgBytes = decoder.decode(imgData);
				/*name=name.substring(n + 1);
				name=name+".png";*/
				
				long millis = System.currentTimeMillis() % 1000;
				filepath= id+millis+".png";

			       // String rootPath = request.getSession().getServletContext().getRealPath("/");
				
				String rootPath = System.getProperty("catalina.base");
			       // File dir = new File(rootPath + File.separator + "img");
				
				File dir = new File(rootPath + File.separator + "webapps"+ File.separator + "img");
			        if (!dir.exists()) {
			            dir.mkdirs();
			        }
			        
			        System.out.println(dir);
			        
			        try {
			        	osf = new FileOutputStream(new File(dir.getAbsolutePath() + File.separator + filepath));
			        	osf.write(imgBytes);
						 osf.flush();
			        } catch (IOException e) {
			            System.out.println("error : " + e);
			        }


	              filepath= "img/"+filepath;
	    	
	    	return  filepath;
	    	
			
		}
	 
	 
	
		public void printRandomnumber () throws Exception {
		 
		 
		 String record ="1808090002";
		 
		 DateFormat dateFormat = new SimpleDateFormat("YY MM dd HH mm ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date).replaceAll(" ","")); //2016/11/16 12:08:43
			
			String date2 =dateFormat.format(date).replaceAll(" ","").substring(0,6 );
			
			//String lastRecord = "180809";
			//String lastRecord2 = "0001";
			String lastRecord = record.substring(0, 6);
			String lastRecord2 = record.substring(6,10 );
			
			String finalString ="";
			
			
			if(!date2.equals(lastRecord))
			{
				//
				finalString =finalString+"0001";
				
				
			}
			else
			{
				Integer lastRecored2Int  = Integer.parseInt(lastRecord2);
				lastRecored2Int =lastRecored2Int+1;
				
				String  part2 = String.format("%04d", lastRecored2Int);
				finalString =lastRecord+part2;	
				
			}
			
			
			System.out.println(finalString);
			
			
			
	 }
		
		
		@RequestMapping(value = "/rest/productsCatalog")
		public @ResponseBody String listOfProductCatalogs(  @RequestBody AddProduct addproduct,HttpServletRequest request) throws Exception {
			JSONObject objJSON = new JSONObject();
			try{
				List<Map<String, Object>> products =addProductDao.listOfProductCatalogs(addproduct);
				objJSON.put("productcatalog", products);
			}catch(Exception e){
				e.printStackTrace();
			}
			return String.valueOf(objJSON);
		}

}
