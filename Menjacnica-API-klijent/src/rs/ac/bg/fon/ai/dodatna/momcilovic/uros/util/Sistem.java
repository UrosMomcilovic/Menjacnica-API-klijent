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
	
}
