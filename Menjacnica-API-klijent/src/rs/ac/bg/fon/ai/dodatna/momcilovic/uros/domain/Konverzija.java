package rs.ac.bg.fon.ai.dodatna.momcilovic.uros.domain;


import java.util.Date;

public class Konverzija {
	
	Date datumVreme;
	String izValuta;
	String uValuta;
	double kurs;
	
	
	
	public Konverzija(String izValuta, String uValuta, double kurs) {
		super();
		this.datumVreme = new Date();
		this.izValuta = izValuta;
		this.uValuta = uValuta;
		this.kurs = kurs;
	}
	public Date getDatumVreme() {
		return datumVreme;
	}
	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
	}
	public String getIzValuta() {
		return izValuta;
	}
	public void setIzValuta(String izValuta) {
		this.izValuta = izValuta;
	}
	public String getuValuta() {
		return uValuta;
	}
	public void setuValuta(String uValuta) {
		this.uValuta = uValuta;
	}
	public double getKurs() {
		return kurs;
	}
	public void setKurs(double kurs) {
		this.kurs = kurs;
	}
	
	
	
}
