package org.foi.nwtis.ihuzjak.zadaca_2.rest;

import java.util.ArrayList;
import java.util.List;

import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AerodromPracen;
import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AerodromiDolasciDAO;
import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AerodromiPolasciDAO;
import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AerodromiPraceniDAO;
import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AirportDAO;
import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.Konfiguracija;
import org.foi.nwtis.podaci.Airport;
import org.foi.nwtis.rest.podaci.AvionLeti;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("aerodromi")
public class RestAerodromi {
	
	@Inject
    ServletContext context;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response dajSveAerodrome(@QueryParam("stranica") String stranica, @QueryParam("broj") String broj) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AirportDAO aDAO = new AirportDAO(konfig);
		List<Airport> aerodromi = aDAO.dajSveAerodrome(stranica, broj);
		
		if(!aerodromi.isEmpty()) {
			odgovor = Response.status(Response.Status.OK).entity(aerodromi).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema aerodroma.").build();
		}
		return odgovor;
	}
	
	@GET
	@Path("broj")
	@Produces({MediaType.APPLICATION_JSON})
	public Response dajBrojZapisa() {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AirportDAO aDAO = new AirportDAO(konfig);
		int broj = aDAO.dajBrojZapisa();
		
		if(broj != 0) {
			odgovor = Response.status(Response.Status.OK).entity(broj).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema podatka.").build();
		}
		return odgovor;
	}
	
	@GET
	@Path("dohvat")
	@Produces({MediaType.APPLICATION_JSON})
	public Response dajSveAerodromeUNOS() {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AirportDAO aDAO = new AirportDAO(konfig);
		List<Airport> aerodromi = aDAO.dajSveAerodromeUNOS();
		
		if(!aerodromi.isEmpty()) {
			odgovor = Response.status(Response.Status.OK).entity(aerodromi).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema aerodroma.").build();
		}
		return odgovor;
	}
	
	@POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void dodajAerodrom(String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		AerodromiPraceniDAO apDAO = new AerodromiPraceniDAO(konfig);
		apDAO.posaljiAerodrom(icao);
	}
	
	@GET
	@Path("praceni")
	@Produces({MediaType.APPLICATION_JSON})	
	public Response dajPraceneAerodrome() {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiPraceniDAO apDAO = new AerodromiPraceniDAO(konfig);
		List<AerodromPracen> aerodromi = apDAO.dajSvePraceneAerodrome();
		
		if(!aerodromi.isEmpty()) {
			odgovor = Response.status(Response.Status.OK).entity(aerodromi).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema aerodroma.").build();
		}
		return odgovor;
	}
	
	@GET
	@Path("?preuzimanje")
	@Produces({MediaType.APPLICATION_JSON})	
	public Response dajAerodromeZaPratiti(@QueryParam("stranica") String stranica, @QueryParam("broj") String broj) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiPraceniDAO apDAO = new AerodromiPraceniDAO(konfig);
		List<AerodromPracen> aerodromi = apDAO.dajSvePraceneAerodrome(stranica, broj);
		
		if(!aerodromi.isEmpty()) {
			odgovor = Response.status(Response.Status.OK).entity(aerodromi).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema aerodroma.").build();
		}
		return odgovor;
	}
	
	@GET
	@Path("praceni/broj")
	@Produces({MediaType.APPLICATION_JSON})
	public Response dajBrojPracenihZapisa() {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiPraceniDAO apDAO = new AerodromiPraceniDAO(konfig);
		int broj = apDAO.dajBrojPracenihZapisa();
		
		if(broj != 0) {
			odgovor = Response.status(Response.Status.OK).entity(broj).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema podatka.").build();
		}
		return odgovor;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{icao}")
	public Response dajAerodrom(@PathParam("icao") String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AirportDAO aDAO = new AirportDAO(konfig);
		List<Airport> aerodromi = aDAO.dajAerodrom(icao);
		
		if(!aerodromi.isEmpty()) {
			odgovor = Response.status(Response.Status.OK).entity(aerodromi).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema aerodroma.").build();
		}
		return odgovor;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{icao}/dolasci")
	public Response dajDolaskeAerodoma(@QueryParam("stranica") String stranica, @QueryParam("broj") String broj,
			@PathParam("icao") String icao) 	{
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiDolasciDAO adDAO = new AerodromiDolasciDAO(konfig);
		List<AvionLeti> aerodromi = new ArrayList<>();
		if (stranica == null) {
			aerodromi = adDAO.dajAerodromeDolaske("1", broj, icao);
		} else {
			aerodromi = adDAO.dajAerodromeDolaske(stranica, broj, icao);
		}
		
		if(!aerodromi.isEmpty()) {
			odgovor = Response.status(Response.Status.OK).entity(aerodromi).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema aerodroma.").build();
		}
		return odgovor;
	}
	
	@GET
	@Path("{icao}/dolasci/broj")
	@Produces({MediaType.APPLICATION_JSON})
	public Response dajBrojZapisaDolasci(@PathParam("icao") String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiDolasciDAO adDAO = new AerodromiDolasciDAO(konfig);
		int broj = adDAO.dajBrojZapisaDolasci(icao);
		
		if(broj != 0) {
			odgovor = Response.status(Response.Status.OK).entity(broj).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema podatka.").build();
		}
		return odgovor;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("{icao}/polasci")
	public Response dajPolaskeAerodoma(@QueryParam("stranica") String stranica, @QueryParam("broj") String broj,
			@PathParam("icao") String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiPolasciDAO apDAO = new AerodromiPolasciDAO(konfig);
		List<AvionLeti> aerodromi = new ArrayList<>();
		if (stranica == null) {
			aerodromi = apDAO.dajAerodromePolaske("1", broj, icao);
		} else {
			aerodromi = apDAO.dajAerodromePolaske(stranica, broj, icao);
		}
		
		if(!aerodromi.isEmpty()) {
			odgovor = Response.status(Response.Status.OK).entity(aerodromi).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema aerodroma.").build();
		}
		return odgovor;
	}
	
	@GET
	@Path("{icao}/polasci/broj")
	@Produces({MediaType.APPLICATION_JSON})
	public Response dajBrojZapisaPolasci(@PathParam("icao") String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiPolasciDAO apDAO = new AerodromiPolasciDAO(konfig);
		int broj = apDAO.dajBrojZapisaPolasci(icao);
		
		if(broj != 0) {
			odgovor = Response.status(Response.Status.OK).entity(broj).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema podatka.").build();
		}
		return odgovor;
	}
}
