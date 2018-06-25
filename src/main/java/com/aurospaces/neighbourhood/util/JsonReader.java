package com.aurospaces.neighbourhood.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl2(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
  
  
  
  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	  URL obj = new URL(url);
	  
	  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	  
	  con.setRequestMethod("GET");
	    //add request header
	    con.setRequestProperty("User-Agent", "Mozilla/5.0");
	    
	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    
	    String jsonText = readAll(in);
	      JSONObject json = new JSONObject(jsonText);
	    
		return json;
	    
  }

 
}