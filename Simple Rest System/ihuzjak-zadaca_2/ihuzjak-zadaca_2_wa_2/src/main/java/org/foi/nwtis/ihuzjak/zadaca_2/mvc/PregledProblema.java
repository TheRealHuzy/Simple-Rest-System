package org.foi.nwtis.ihuzjak.zadaca_2.mvc;

import java.util.List;

import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AerodromProblem;
import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.Konfiguracija;
import org.foi.nwtis.podaci.Airport;

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
@Path("problemi")
@RequestScoped
public class PregledProblema {
	
	@Inject
	private Models model;
	
	@Inject
	ServletContext context;
	
	String brojZapisa;
	
	@GET
	@View("pregledSvihProblema.jsp")
	public void pregledSvihAerodroma(@DefaultValue("1")@QueryParam("stranica") String stranica) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		brojZapisa = konfig.dajPostavku("stranica.brojRedova");
		AerodromiKlijent ak = new AerodromiKlijent();

		int zadnjiBroj = ak.dajBrojSvihProblema();
		if (zadnjiBroj == 0) zadnjiBroj = 1;
		zadnjiBroj = (int) Math.ceil(zadnjiBroj/Double.parseDouble(brojZapisa));
		int trenutniBroj = Integer.parseInt(stranica);
		trenutniBroj = provjeriBrojStranice(zadnjiBroj, trenutniBroj);
		model.put("zadnjiBroj", zadnjiBroj);
		model.put("trenutniBroj", trenutniBroj);
		ak.dajSveProbleme(stranica, stranica);
		List<AerodromProblem> aerodromi = ak.dajSveProbleme(String.valueOf(trenutniBroj), brojZapisa);
		model.put("problemi", aerodromi);
	}
	
	@GET
	@Path("{icao}")
	@View("pregledProblemaAerodroma.jsp")
	public void pregledAerodromPolasci(@DefaultValue("1")@QueryParam("stranica") String stranica,
			@PathParam("icao") String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		brojZapisa = konfig.dajPostavku("stranica.brojRedova");
		
		AerodromiKlijent ak = new AerodromiKlijent();
		
		int zadnjiBroj = ak.dajBrojProblemaAerodroma(icao);
		if (zadnjiBroj == 0) zadnjiBroj = 1;

		zadnjiBroj = (int) Math.ceil(zadnjiBroj/Double.parseDouble(brojZapisa));
		int trenutniBroj = Integer.parseInt(stranica);
		trenutniBroj = provjeriBrojStranice(zadnjiBroj, trenutniBroj);
		model.put("zadnjiBroj", zadnjiBroj);
		model.put("trenutniBroj", trenutniBroj);
		
		List<AerodromProblem> aerodromi = ak.dajProblemeAerodroma(stranica, brojZapisa, icao);
		model.put("problemiAE", aerodromi);
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
	@Path("brisanjeProblemaAerodroma")
	@View("brisanjeProblemaAerodroma.jsp")
	public void brisanjeProblemaAerodroma() {
		AerodromiKlijent ak = new AerodromiKlijent();
		List<Airport> aerodromi = ak.dajPraceneAerodrome();
		model.put("brisanje", aerodromi);
	}
	
	@POST
	@Path("brisanjeProblemaAerodroma")
	@View("brisanjeProblemaAerodroma.jsp")
	public void brisanjeProblemaAerodroma(@FormParam("slcPosaljiUpit") String slcPosaljiUpit) {
		AerodromiKlijent ak = new AerodromiKlijent();
		List<Airport> aerodromi = ak.dajPraceneAerodrome();
		model.put("brisanje", aerodromi);
		ak.brisiProblemeAerodroma(slcPosaljiUpit);
	}
}
