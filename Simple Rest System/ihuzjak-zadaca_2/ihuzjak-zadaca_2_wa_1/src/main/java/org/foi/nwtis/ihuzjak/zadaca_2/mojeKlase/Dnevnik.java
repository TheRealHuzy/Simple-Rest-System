package org.foi.nwtis.ihuzjak.zadaca_2.mojeKlase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dnevnik {
	String nazivDnevnika;
	
	public Dnevnik(String nazivDnevnika){
		this.nazivDnevnika = nazivDnevnika;
	}
	
	public void dodajUDnevnik(String odVrijeme, String doVrijeme, String putanja) {
		String odgovor = "Rad aplikacije: " + odVrijeme + " - " + doVrijeme;
		upisiUDatoteku(odgovor, putanja);
	}
	
	private void upisiUDatoteku(String odgovor, String putanja) {
		File dnevnikDatoteka = new File(this.nazivDnevnika);
		System.out.println(putanja);
		try (FileWriter fw = new FileWriter(dnevnikDatoteka, true))
		{
			if (!dnevnikDatoteka.exists()) {
				dnevnikDatoteka.createNewFile();
			}
			fw.write(odgovor);
		} catch (IOException e) {
			System.out.println("Greška pri pisanju u datoteku dnevnika");
		}
	}
}