package rs.ac.bg.fon.ai.dodatna.momcilovic.uros.comm;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import rs.ac.bg.fon.ai.dodatna.momcilovic.uros.domain.Konverzija;
import rs.ac.bg.fon.ai.dodatna.momcilovic.uros.domain.Zemlja;

public class MenjacnicaCommunication {
	 
	public static LinkedList<Zemlja> vratiZemlje(){
		
		String url = "http://free.currencyconverterapi.com/api/v3/countries";
		
		try {
			String output = sendGet(url);
			Gson gson = new GsonBuilder().create();
			
			JsonObject jsonResult = gson.fromJson(output, JsonObject.class);
			JsonObject result = (JsonObject) jsonResult.get("results");

			Set<Entry<String, JsonElement>> set = result.entrySet();
			LinkedList<Zemlja> lista = new LinkedList<Zemlja>();

			for (Entry<String, JsonElement> entry : set) {
				JsonObject o = (JsonObject) entry.getValue();
				Zemlja z = new Zemlja(o.get("name").getAsString(), o.get("currencyId").getAsString());
				lista.add(z);
			}
			return lista;
			
		} catch (IOException e) {
			return null;
		}
		
	}
	
	
	private static String sendGet(String url) throws IOException {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();

		conn.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		boolean endReading = false;
		String response = "";

		while (!endReading) {
			String s = in.readLine();

			if (s != null) {
				response += s;
			} else {
				endReading = true;
			}
		}
		in.close();

		return response.toString();

	}
	
	public static double vratiOdnosDveValute(String from, String to){
		String url = "http://free.currencyconverterapi.com/api/v3/convert?q=" + from + "_" + to;
		
		try {
			String output = sendGet(url);
			Gson gson = new GsonBuilder().create();
			
			JsonObject j = gson.fromJson(output, JsonObject.class);
			int count = (((JsonObject) j.get("query")).get("count")).getAsInt();
			
			if(count == 0){
				return 0;
			}
			
			JsonObject kursJson = (JsonObject) (((JsonObject) j.get("results")).get(from + "_" + to));
			double kurs =  kursJson.get("val").getAsDouble();
			
			return kurs;
		} catch (IOException e) {
			return 0;
		}
		
		
		
	}
	
	public static void upisiKonverzije(LinkedList<Konverzija> konverzije) throws IOException{
		
		JsonArray konverzijeJson = pretvoriUJson(konverzije);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/log.json")));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String konverzijeString = gson.toJson(konverzijeJson);

		out.println(konverzijeString);
		out.close();
	}
	
	
	
	private static JsonArray pretvoriUJson(LinkedList<Konverzija> konverzije){
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		JsonArray niz = ucitajIzFajla();
		if(niz == null){
			niz = new JsonArray();
		}
		
		for (Konverzija k : konverzije) {
			JsonObject konverzijaJson = new JsonObject();
			konverzijaJson.addProperty("datumVreme", formater.format(k.getDatumVreme()));
			konverzijaJson.addProperty("izValuta", k.getIzValuta());
			konverzijaJson.addProperty("uValuta", k.getuValuta());
			konverzijaJson.addProperty("kurs", k.getKurs());
			
			niz.add(konverzijaJson);
		}
		
		return niz;
	}
	
	private static JsonArray ucitajIzFajla() {
		JsonArray jsonNiz;
		String output = "";
		Gson gson = new GsonBuilder().create();

		try {
			BufferedReader br = new BufferedReader(new FileReader("data/log.json"));
			
			while (true) {
				String a = br.readLine();
				if (a == null) {
					break;
				}
				output += a;
			}
			
			br.close();
			jsonNiz = gson.fromJson(output, JsonArray.class);
			
			return jsonNiz;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
