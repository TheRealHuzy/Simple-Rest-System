package org.foi.nwtis.ihuzjak.zadaca_2.slusaci;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.foi.nwtis.ihuzjak.zadaca_2.dretve.PreuzimanjeRasporedaAerodroma;
import org.foi.nwtis.ihuzjak.zadaca_2.mojeKlase.Dnevnik;
import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.Konfiguracija;
import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.NeispravnaKonfiguracija;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class SlusacAplikacije
 *
 */
@WebListener
public class SlusacAplikacije implements ServletContextListener {
	PreuzimanjeRasporedaAerodroma pra = null;
	Konfiguracija konfig = null;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	String vrijemePocetka;
	String vrijemeKraja;
	Dnevnik dnevnik;
	String putanja;
	
	String imeDnevnika;
	
    public SlusacAplikacije(){}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Timestamp tVrijemePocetka = new Timestamp(System.currentTimeMillis());
		vrijemePocetka = sdf.format(tVrijemePocetka);
		
		ServletContext context = sce.getServletContext();
		
		String nazivDatoteke = context.getInitParameter("konfiguracija");
		
		putanja = context.getRealPath("/WEB-INF") + java.io.File.separator; 
		
		try {
			konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(putanja + nazivDatoteke);
			konfig.ucitajKonfiguraciju();
			context.setAttribute("Postavke", konfig);
			ucitajKonfiguracijskePodatke(konfig);
			dnevnik = new Dnevnik(imeDnevnika);
			
						
		} catch (NeispravnaKonfiguracija e1) {
			e1.printStackTrace();
			return;
		}

		pra = new PreuzimanjeRasporedaAerodroma(konfig);
		pra.start();
		ServletContextListener.super.contextInitialized(sce);
	}
	
	private void ucitajKonfiguracijskePodatke(Konfiguracija konfig) {
		imeDnevnika = konfig.dajPostavku("dnevnik.datoteka");
	}
	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		ServletContext context = sce.getServletContext();
		
		Timestamp tVrijemeKraja = new Timestamp(System.currentTimeMillis());
		vrijemeKraja = sdf.format(tVrijemeKraja);
		dnevnik.dodajUDnevnik(vrijemePocetka, vrijemeKraja, putanja);
		context.removeAttribute("Postavke");
		
		if(pra != null) {
			pra.interrupt();
		}
		ServletContextListener.super.contextDestroyed(sce);
	}
}
