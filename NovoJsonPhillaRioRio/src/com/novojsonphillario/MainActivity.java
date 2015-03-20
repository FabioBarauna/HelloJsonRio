package com.novojsonphillario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import com.novojsonphilla.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static String url = "http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes";
	TextView tv1,tv2;
	//private static String LATITUDE = "lat";
	//private static String LONGITUDE = "lon";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new Paralelo().execute();
	}

	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		//return true;
	//}

	//@Override
	//public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//if (id == R.id.action_settings) {
		//	return true;
	//	}
		//return super.onOptionsItemSelected(item);
//	}
	public class Paralelo extends AsyncTask<String, Void, JSONObject> {
		
		@Override
		protected JSONObject doInBackground(String... params) {
			LeitorJson leitorJson = new LeitorJson();
			JSONObject jo = leitorJson.getJSONJsonFromUrl(url);
			return jo;
			
		}
	protected void onPostExecute(JSONObject jo)
	{
		String latitude = LeitorJson.job.toString();
		String longitude = LeitorJson.joc.toString();
		
		tv1 = (TextView)findViewById(R.id.tv1);
		tv1.setText(latitude);
		tv2 = (TextView)findViewById(R.id.tv2);
		tv2.setText(longitude);
	}
	}
}
