package org.foi.nwtis.ihuzjak.zadaca_2.mvc;

import java.util.List;

import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.Konfiguracija;
import org.foi.nwtis.podaci.Airport;
import org.foi.nwtis.rest.podaci.AvionLeti;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Controller
@Path("aerodromi")
@RequestScoped
public class PregledAerodroma {

	@Inject
	private Models model;
	
	@Inject
	ServletContext context;
	
	String brojZapisa;

	@GET
	@Path("pocetak")
	@View("index.jsp")
	public void pocetak() {
		
	}

	@GET
	@View("pregledSvihAerodroma.jsp")
	public void pregledSvihAerodroma(@DefaultValue("1")@QueryParam("stranica") String stranica) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		brojZapisa = konfig.dajPostavku("stranica.brojRedova");
		
		AerodromiKlijent ak = new AerodromiKlijent();

		int zadnjiBroj = ak.dajBrojZapisa();
		if (zadnjiBroj == 0) zadnjiBroj = 1;
		zadnjiBroj = (int) Math.ceil(zadnjiBroj/Double.parseDouble(brojZapisa));
		int trenutniBroj = Integer.parseInt(stranica);
		trenutniBroj = provjeriBrojStranice(zadnjiBroj, trenutniBroj);
		model.put("zadnjiBroj", zadnjiBroj);
		model.put("trenutniBroj", trenutniBroj);
		
		List<Airport> aerodromi = ak.dajSveAerodrome(String.valueOf(trenutniBroj), brojZapisa);
		model.put("aerodromi", aerodromi);
	}
	
	@GET
	@Path("{icao}")
	@View("pregledAerodroma.jsp")
	public void pregledAerodroma(@PathParam("icao") String icao) {
		AerodromiKlijent ak = new AerodromiKlijent();
		List <Airport> aerodromi = ak.dajAerodrom(icao);
		model.put("aerodromiAE", aerodromi);
		model.put("icao", icao);
	}
	
	private int provjeriBrojStranice(int zadnjiBroj, int trenutniBroj) {
		if (trenutniBroj > zadnjiBroj) {
			trenutniBroj = zadnjiBroj;
		} else if (trenutniBroj < 1) {
			trenutniBroj = 1;
		}
		return trenutniBroj;
	}
	
	@GET
	@Path("unosAerodroma")
	@View("unosAerodroma.jsp")
	public void pregledSvihAerodromaUNOS() {
		AerodromiKlijent ak = new AerodromiKlijent();
		List<Airport> aerodromi = ak.dajSveAerodromeUNOS();
		model.put("dodavanje", aerodromi);
	}
	
	@POST
	@Path("unosAerodroma")
	@View("unosAerodroma.jsp")
	public void unosAerodroma(@FormParam("slcPosaljiUpit") String slcPosaljiUpit) {
		AerodromiKlijent ak = new AerodromiKlijent();
		List<Airport> aerodromi = ak.dajSveAerodromeUNOS();
		model.put("dodavanje", aerodromi);
		ak.dodajAerodrom(slcPosaljiUpit);
	}
	
	@GET
	@Path("preuzimanje")
	@View("pregledPracenihAerodroma.jsp")
	public void pregledPracenihAerodroma(@DefaultValue("1")@QueryParam("stranica") String stranica) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		brojZapisa = konfig.dajPostavku("stranica.brojRedova");
		AerodromiKlijent ak = new AerodromiKlijent();
		
		int zadnjiBroj = ak.dajBrojPracenihZapisa();
		if (zadnjiBroj == 0) zadnjiBroj = 1;
		zadnjiBroj = (int) Math.ceil(zadnjiBroj/Double.parseDouble(brojZapisa));
		int trenutniBroj = Integer.parseInt(stranica);
		trenutniBroj = provjeriBrojStranice(zadnjiBroj, trenutniBroj);
		model.put("zadnjiBroj", zadnjiBroj);
		model.put("trenutniBroj", trenutniBroj);
		
		List<Airport> aerodromi = ak.dajPraceneAerodrome(stranica, brojZapisa);
		model.put("praceni", aerodromi);
	}
	
	@GET
	@Path("{icao}/dolasci")
	@View("pregledAerodromDolasci.jsp")
	public void pregledAerodromDolasci(@DefaultValue("1")@QueryParam("stranica") String stranica,
			@PathParam("icao") String icao,@DefaultValue("01.01.2022") @QueryParam("dan") String dan) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		brojZapisa = konfig.dajPostavku("stranica.brojRedova");
		
		AerodromiKlijent ak = new AerodromiKlijent();
		
		int zadnjiBroj = ak.dajBrojAerodromDolasci(icao);
		if (zadnjiBroj == 0) zadnjiBroj = 1;
		zadnjiBroj = (int) Math.ceil(zadnjiBroj/Double.parseDouble(brojZapisa));
		int trenutniBroj = Integer.parseInt(stranica);
		trenutniBroj = provjeriBrojStranice(zadnjiBroj, trenutniBroj);
		model.put("zadnjiBroj", zadnjiBroj);
		model.put("trenutniBroj", trenutniBroj);
		
		List<AvionLeti> aerodromi = ak.dajAerodromDolasci(stranica, brojZapisa, icao);
		model.put("dolasci", aerodromi);
		model.put("icao", icao);
	}
	
	@GET
	@Path("{icao}/polasci")
	@View("pregledAerodromPolasci.jsp")
	public void pregledAerodromPolasci(@DefaultValue("1")@QueryParam("stranica") String stranica,
			@PathParam("icao") String icao, @DefaultValue("01.01.2022") @QueryParam("dan") String dan) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		brojZapisa = konfig.dajPostavku("stranica.brojRedova");
		AerodromiKlijent ak = new AerodromiKlijent();
		
		int zadnjiBroj = ak.dajBrojAerodromPolasci(icao);
		if (zadnjiBroj == 0) zadnjiBroj = 1;
		zadnjiBroj = (int) Math.ceil(zadnjiBroj/Double.parseDouble(brojZapisa));
		int trenutniBroj = Integer.parseInt(stranica);
		trenutniBroj = provjeriBrojStranice(zadnjiBroj, trenutniBroj);
		model.put("zadnjiBroj", zadnjiBroj);
		model.put("trenutniBroj", trenutniBroj);
		
		List<AvionLeti> aerodromi = ak.dajAerodromPolasci(stranica, brojZapisa, icao);
		model.put("polasci", aerodromi);
		model.put("icao", icao);
	}
}
