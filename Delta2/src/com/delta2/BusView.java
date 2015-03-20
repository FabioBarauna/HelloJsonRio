package com.delta2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class BusView {
 static InputStream is = null;
 static JSONObject jo = null;
 static String jsonString = null; 
 //construtor
 
 	public BusView(){}
 
// conexão
 	
 	 private static String readAll(Reader rd) throws IOException {
 	    StringBuilder sb = new StringBuilder();
 	    int cp;
 	    while ((cp = rd.read()) != -1) {
 	      sb.append((char) cp);
 	    }
 	    return sb.toString();
 	  }

 	  public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
 	    is = new URL(url).openStream();
 	    try {
 	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
 	      jsonString = readAll(rd);
 	      jo = new JSONObject(jsonString);
 	      return jo;
 	    } finally {
 	      is.close();
 	    }
 
}
}