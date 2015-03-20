package com.delta2;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends Activity implements LocationListener {

	private MapView osm;
	private MapController mc;
	private LocationManager lm;
	private static String url = ("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
	private static JSONObject joo;
	private static JSONArray jaa;
	private static JSONArray jab;
	double lat;
	double lng;
	//GeoPoint bus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		osm = (MapView) findViewById(R.id.mapView);
		osm.setTileSource(TileSourceFactory.MAPNIK);
		osm.setBuiltInZoomControls(true);
		osm.setMultiTouchControls(true);

		mc = (MapController) osm.getController();
		mc.setZoom(16);

		GeoPoint center = new GeoPoint(-22.9217, -43.31552);
		addMarker(center);

		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
		new Paralelo().execute();

	}

	public void addMarker(GeoPoint center) {
		Marker marker = new Marker(osm);
		marker.setPosition(center);
		marker.setIcon(getResources().getDrawable(R.drawable.ic_launcher));

		osm.getOverlays().add(marker);
		osm.invalidate();
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

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

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (lm != null) {
			lm.removeUpdates(this);
		}
	}

	public class Paralelo extends AsyncTask<String, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(String... params) {
			BusView bv = new BusView();
			try {
				JSONObject jo = bv.readJsonFromUrl(url);
				joo = jo;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return joo;
		}
	}

	protected void onPostExecute(JSONObject joo) throws JSONException
	{
		jaa = joo.getJSONArray("DATA");
		jab = jaa.getJSONArray(23);
		lat = (Double) jab.get(3);
		lng = (Double) jab.get(4);
		GeoPoint bus = new GeoPoint(lat,lng);
		addMarker(bus);
	}
	
	
}
