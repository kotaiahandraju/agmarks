package com.aurospaces.neighbourhood.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.ServletContext;

public class SendSMS {
	
   //public  String  mobileNumber =null;
	
    
     //public String message=null;
    
    
    public static String sendSMS(String message, String mobileNumber,ServletContext objContext) throws IOException
	{
    	 String username = null;
    	 String password = null;
    	 String from = null ;
    	//static String from = "AARNA-MATRIMONY";
         String requestUrl = null;
         String toAddress = null;
    	InputStream input = null;
		String body = null;
		 Properties prop = new Properties();
		 String response = null;
		 try{
			 String propertiespath = objContext.getRealPath("Resources" +File.separator+"DataBase.properties");
				input = new FileInputStream(propertiespath);
				// load a properties file
				prop.load(input);
				 username = prop.getProperty("smsusername");
				 password= prop.getProperty("smspassword");
				 from = prop.getProperty("smssenderfrom");
		System.out.println("hello sms class");
		requestUrl  = "http://182.18.160.225/index.php/api/bulk-sms?username="+URLEncoder.encode(username, "UTF-8")+"&password="+ URLEncoder.encode(password, "UTF-8")+"&from="+from+"&to="+URLEncoder.encode(mobileNumber, "UTF-8")+"&message="+URLEncoder.encode(message, "UTF-8")+"&sms_type=2";
        URL url = new URL(requestUrl);
        HttpURLConnection uc = (HttpURLConnection)url.openConnection();
         response = uc.getResponseMessage();
        System.out.println("SMS response:"+response);
        System.out.println("SMS response:"+message);
        uc.disconnect();
		 }catch( Exception e){
			 e.printStackTrace();
		 }
        return response;
	}
    
    
    
    
    
    public static void sendSMSFromAgmarksCreditials(String pmessage, String pmobileNumber)
    {
    	//Your authentication key
        String authkey = "211321ALxeUaDj1h5add7963";
        //Multiple mobiles numbers separated by comma
        String mobiles = pmobileNumber;
        //Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = "AGMARK";
        //Your message to send, Add URL encoding here.
        String message = pmessage;
        //define route
        String route="4";

        //Prepare Url
        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

        //encoding message
        String encoded_message=URLEncoder.encode(message);

        //Send SMS API
        String mainUrl="http://api.msg91.com/api/sendhttp.php?";

        //Prepare parameter string
        StringBuilder sbPostData= new StringBuilder(mainUrl);
        sbPostData.append("authkey="+authkey);
        sbPostData.append("&mobiles="+mobiles);
        sbPostData.append("&message="+encoded_message);
        sbPostData.append("&route="+route);
        sbPostData.append("&sender="+senderId);

        //final string
        mainUrl = sbPostData.toString();
        try
        {URL url = new URL(mainUrl);
        HttpURLConnection uc = (HttpURLConnection)url.openConnection();
      String  respons = uc.getResponseMessage();
       System.out.println("SMS response:"+respons);
       System.out.println("SMS response:"+message);
            //prepare connection
            /*myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null)
            //print response
            System.out.println(response);
*/
            //finally close connection
            //Reader.close();
        }
        catch (IOException e)
        {
                e.printStackTrace();
        }
    }
    	
    	
    	
    	
    }



