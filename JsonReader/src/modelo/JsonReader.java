package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void main(String[] args) throws IOException, JSONException {
		JSONObject json = readJsonFromUrl("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
		JSONArray ja = json.getJSONArray("COLUMNS");
		JSONArray jb = json.getJSONArray("DATA");
		JSONArray jba = jb.getJSONArray(1);
		jba.get(3);
		// System.out.println(jba.toString());
		// JSONArray jaa = ja.getJSONArray(2);

		double djc = (double) jba.get(3);
		// System.out.println(sjc);
		// System.out.println(djc);
		System.out.println(djc);
		System.out.println(jba.get(3));

	}

}