package rs.ac.bg.fon.ai.dodatna.momcilovic.uros.util;

import java.io.IOException;
import java.util.LinkedList;

import rs.ac.bg.fon.ai.dodatna.momcilovic.uros.comm.MenjacnicaCommunication;
import rs.ac.bg.fon.ai.dodatna.momcilovic.uros.domain.Konverzija;
import rs.ac.bg.fon.ai.dodatna.momcilovic.uros.domain.Zemlja;

public class Sistem {
	
	private static LinkedList<Zemlja> zemlje = new LinkedList<Zemlja>();
	private static LinkedList<Konverzija> konverzije = new LinkedList<>();
	
	public static String[] izvuciImenaZemalja() {
		String[] imena = new String[zemlje.size()];

		for (int i = 0; i < imena.length; i++) {
			imena[i] = zemlje.get(i).getName();
		}

		return imena;
	}
	
	public static void ucitajUlistu(){
		zemlje = MenjacnicaCommunication.vratiZemlje();
	}
	
	public static double kurs(String from, String to){
		String idFrom = "";
		String idTo = "";
		
		for(Zemlja zemlja : zemlje){
			if(zemlja.getName().equals(from)){
				idFrom = zemlja.getId();
			}
			if(zemlja.getName().equals(to)){
				idTo = zemlja.getId();
			}
		}
		
		return MenjacnicaCommunication.vratiOdnosDveValute(idFrom, idTo);
	}
	
	public static void konverzija(String from, String to, double kurs){
		String idFrom = "";
		String idTo = "";
		
		for(Zemlja zemlja : zemlje){
			if(zemlja.getName().equals(from)){
				idFrom = zemlja.getId();
			}
			if(zemlja.getName().equals(to)){
				idTo = zemlja.getId();
			}
		}
		
		Konverzija k = new Konverzija(idFrom, idTo, kurs);
		
		konverzije.add(k);
	}
	
	public static void upisiKonUFajl(){
		try {
			if(konverzije.size() > 0){
				MenjacnicaCommunication.upisiKonverzije(konverzije);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
