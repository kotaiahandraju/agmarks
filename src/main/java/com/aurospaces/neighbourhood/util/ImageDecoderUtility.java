package com.aurospaces.neighbourhood.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.castor.util.Base64Decoder;
import org.springframework.stereotype.Component;

@Component
public class ImageDecoderUtility {
	
	
	
	 public String  imgDecoder(String imgData, HttpServletRequest request) {
	    	
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


	             // filepath= filepath;
	    	
	    	return  filepath;
	    	
			
		}

}
