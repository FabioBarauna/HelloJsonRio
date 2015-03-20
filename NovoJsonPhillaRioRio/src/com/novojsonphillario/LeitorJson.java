package com.novojsonphillario;


	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;


import org.json.JSONObject;

import android.util.Log;

	public  class  LeitorJson 
	{
	static InputStream is = null;
	static JSONArray ja = null;
	static JSONArray jab = null;
	static JSONObject jo = null;
	static JSONObject job = null;
	static JSONObject joc = null;
	static String jsonString = "";
	//construtor
	public LeitorJson(){}
	//conexao
	public JSONObject getJSONJsonFromUrl (String url){
		try
		{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		is = httpEntity.getContent();
		
		}
		catch (UnsupportedEncodingException e)
		{e.printStackTrace();}
		catch (ClientProtocolException e)
		{e.printStackTrace();}
		catch (IOException e )
		{e.printStackTrace();}
		
		
		
		
		
		try 
		{
			BufferedReader br = new BufferedReader ( new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null)
			{
			sb.append(line +"/n" );
			}
			is.close();
			jsonString = sb.toString();
		}
		catch(Exception e)
		{Log.e("Buffer Error", "Erro de Conversao" + e.toString());}
		
		//Convertendo Para JSON
		try
		{
		jo = new JSONObject(jsonString);
		ja = jo.getJSONArray("COLUMNS");
		//jab = ja.getJSONArray(0);
		job = ja.getJSONObject(3);
		joc = ja.getJSONObject(4);
		
		
		}
		catch(JSONException e)
		{Log.e("LeitorJson", "Erro de Convers�o" + e.toString()); }
		return jo;
		
		
		
		
		   
	}
	
}
