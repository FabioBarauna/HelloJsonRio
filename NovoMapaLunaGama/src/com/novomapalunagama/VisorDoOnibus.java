package com.novomapalunagama;

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

public class VisorDoOnibus {
	static InputStream is = null;
	static JSONArray ja = null;
	static JSONObject jo = null;
	static String jsonString = "";
	//construtor
	public VisorDoOnibus(){}
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
			jo = new JSONObject(jsonString);
		}
		catch(Exception be)
		{Log.e("Buffer Error", "Erro de Conversao" + be.toString());}
		return jo;
		}
	
}
