package org.foi.nwtis.ihuzjak.zadaca_2.slusaci;

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
	Konfiguracija konfig = null;

	String vrijemePocetka;
	String vrijemeKraja;
	
	String imeDnevnika;
	
    public SlusacAplikacije(){}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ServletContext context = sce.getServletContext();
		
		String nazivDatoteke = context.getInitParameter("konfiguracija");
		
		String putanja = context.getRealPath("/WEB-INF") + java.io.File.separator; 
		
		try {
			konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(putanja + nazivDatoteke);
			konfig.ucitajKonfiguraciju();
			ucitajKonfiguracijskePodatke(konfig);
			context.setAttribute("Postavke", konfig);
						
		} catch (NeispravnaKonfiguracija e1) {
			e1.printStackTrace();
			return;
		}
		ServletContextListener.super.contextInitialized(sce);
	}
	
	private void ucitajKonfiguracijskePodatke(Konfiguracija konfig) {
		imeDnevnika = konfig.dajPostavku("stranica.brojRedova");
	}
	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		context.removeAttribute("Postavke");
		ServletContextListener.super.contextDestroyed(sce);
	}

}
