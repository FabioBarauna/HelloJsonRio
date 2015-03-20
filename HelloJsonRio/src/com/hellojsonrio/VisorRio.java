package com.hellojsonrio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class VisorRio {
	static InputStream is = null;
	BufferedReader br = null;
	StringBuilder sb = null;
	String line = null;
	static JSONArray ja = null;
	static JSONObject jo = null;
	static JSONObject jao = null;
	static final String URL = "http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes";
	static String jsonString = "";
	static String jaString = null;
	//construtor
	public VisorRio(){}
	//conexao
	public String getJSONJsonFromUrl (){
		try
		{
			/*URL url = new URL ("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.connect();
			*/
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(URL);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			br = new BufferedReader(br, is.read());
			sb = new StringBuilder();
			while ((line = br.readLine()) != null)
			{
			sb.append(line +"/n" );
			}
			jsonString = sb.toString();
			
			
		
		
		
		
		
			
		
		
		
		
		
		
		}
		catch (MalformedURLException e)
		{e.printStackTrace();}
		catch (IOException e )
		{e.printStackTrace();}
		
		/*
		try 
		{
			br = new BufferedReader ( new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			
			
			is.close();
			
		
		}
		catch(Exception e)
		{Log.e("Buffer Error", "Erro de Conversao" + e.toString());}
		*/
		
		
		//Convertendo Para JSON
		try
		{
		jo = new JSONObject(jsonString);
		ja = jo.getJSONArray("COLUMNS");
		}
		catch(JSONException e)
		{Log.e("LeitorJson", "Erro de Conversão jo  " + e.toString()); }
		
		
		
		jaString = ja.toString();
	
		
		return jaString;
		   
	}
	
}