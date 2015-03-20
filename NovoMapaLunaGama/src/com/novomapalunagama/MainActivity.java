package com.novomapalunagama;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.PathOverlay;

//import com.novomapalunagama.VisorDoOnibus.VisorDoOnibusListener;











import com.novomapalunagama.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity implements LocationListener {

	private MapView osm;
	private MapController mc;
	private LocationManager lm;
	//private static String LATITUDE = "lat";
	//private static String LONGITUDE = "lon";
	private static String url = "http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		osm = (MapView) findViewById(R.id.mapView1);
		osm.setTileSource(TileSourceFactory.MAPNIK);
		osm.setBuiltInZoomControls(true);
		osm.setMultiTouchControls(true);
		
		mc = (MapController)osm.getController();
		mc.setZoom(16);
		
		
		
		//GeoPoint gp1 = new GeoPoint(-22.752895,-43.300222);
		//GeoPoint gp2 = new GeoPoint(-22.752820,-43.300277);
		//GeoPoint gp3 = new GeoPoint(-22.752823,-43.300382);
		//GeoPoint gp4 = new GeoPoint(-22.752808,-43.300483);
		//GeoPoint gp5 = new GeoPoint(-22.752715,-43.300781);
		//GeoPoint gp6 = new GeoPoint(-22.752639,-43.301074);
		//GeoPoint gp7 = new GeoPoint(-22.751470,-43.302336);
		
		
		//PathOverlay po = new PathOverlay(Color.CYAN, this);
		//po.addPoint(gp1);
		//po.addPoint(gp2);
		//po.addPoint(gp3);
		//po.addPoint(gp4);
		//po.addPoint(gp5);
		//po.addPoint(gp6);
		//po.addPoint(gp7);
		
		//osm.getOverlays().add(po);
						
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
 		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
 		
 		
 		new Paralelo().execute();
 		
 		
 		
 		
 		
 	
 		
 		
	}
	public void addMarker(GeoPoint center){
		Marker marker = new Marker(osm);
		marker.setPosition(center);
		marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
		marker.setIcon(getResources().getDrawable(R.drawable.ic_launcher));
		
		osm.getOverlays().add(marker);
		osm.invalidate();
		
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void onLocationChanged(Location location) {
		GeoPoint center = new GeoPoint(location.getLatitude(),location.getLongitude());
		mc.animateTo(center);
		addMarker(center);
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
public class Paralelo extends AsyncTask<String, Void, JSONObject> {
		
		@Override
		protected JSONObject doInBackground(String... params) {
			VisorDoOnibus leitorJson = new VisorDoOnibus();
			JSONObject jo = leitorJson.getJSONJsonFromUrl(url);
			return jo;
			
			
		}
	protected void onPostExecute(JSONObject jo)
	{
		try {
			JSONArray ja = jo.getJSONArray("DATA");
			JSONArray jaa = ja.getJSONArray(33);
			String lat = (String) jaa.get(3);
			String lng = (String) jaa.get(4);
			
			
			Double latd = Double.parseDouble(lat);
			Double lngd = Double.parseDouble(lng);
			GeoPoint center = new GeoPoint(latd,lngd);
			mc.setCenter(center);
			addMarker(center);	
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	}
	
		
	
	
}