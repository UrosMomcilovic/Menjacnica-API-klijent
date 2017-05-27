package rs.ac.bg.fon.ai.dodatna.momcilovic.uros.util;

import java.util.LinkedList;

import rs.ac.bg.fon.ai.dodatna.momcilovic.uros.comm.MenjacnicaCommunication;
import rs.ac.bg.fon.ai.dodatna.momcilovic.uros.domain.Zemlja;

public class Sistem {
	
	private static LinkedList<Zemlja> zemlje = new LinkedList<Zemlja>();
	
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
}
