package com.aurospaces.neighbourhood.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.castor.util.Base64Decoder;
import org.castor.util.Base64Encoder;
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
				
				String local ="D:\\agmarksimg";
				
				String server ="/var/www/html/agmarks/static/images/plant_clinic_images";
				
				//String server ="/home/agmarksco/public_html/plant_clinic_images";
				System.out.println("folder address in server"+server);
				
				//File dir = new File(rootPath + File.separator + "webapps"+ File.separator + "img");
				File dir = new File(server);
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
	 
	 
	 
	 
	 
	 public String  imgEncoder(String imgname) {
			
	    	String encodedfile = null;
			Base64Encoder encoder =new Base64Encoder();
	         //String rootPath = request.getSession().getServletContext().getRealPath("/");
			
			String rootPath = System.getProperty("catalina.base");
			
			
			
			String server ="/var/www/html/agmarks/static/images/plant_clinic_images";
			
			//String server ="/home/agmarksco/public_html/plant_clinic_images";
			
			System.out.println("folder address in server"+server);
			
			//File file = new File(rootPath + File.separator + "webapps"+ File.separator +"img"+File.separator+imgname);
			        
			        File file = new File(server + File.separator + imgname);
			
			//File dir = new File(server);
			        
			        System.out.println(file);
			        
			        try {
		                @SuppressWarnings("resource")
						FileInputStream fileInputStreamReader = new FileInputStream(file);
		                byte[] bytes = new byte[(int)file.length()];
		                fileInputStreamReader.read(bytes);
		                encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
		            } catch (FileNotFoundException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            } catch (IOException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }


	    	
	    	return  encodedfile;
	    	
			
		}
	    	
	 
	 
	 
	 
	 
	 
	 
	 

}
