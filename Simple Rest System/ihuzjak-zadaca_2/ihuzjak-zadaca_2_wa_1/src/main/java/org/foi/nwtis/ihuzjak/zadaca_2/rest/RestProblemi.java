package org.foi.nwtis.ihuzjak.zadaca_2.rest;

import java.util.List;

import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AerodromProblem;
import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AerodromiProblemiDAO;
import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.Konfiguracija;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("problemi")
public class RestProblemi {
	
	@Inject
    ServletContext context;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response dajSveProbleme(@QueryParam("stranica") String stranica, @QueryParam("broj") String broj) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiProblemiDAO apDAO = new AerodromiProblemiDAO(konfig);
		List<AerodromProblem> aerodromi = apDAO.dajSveProbleme(stranica, broj);
		
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
	public Response dajBrojProblemZapisa() {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiProblemiDAO apDAO = new AerodromiProblemiDAO(konfig);
		int broj = apDAO.dajBrojSvihProblemZapisa();
		
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
	public Response dajProblemeAerodoma(@QueryParam("stranica") String stranica, @QueryParam("broj") String broj,
			@PathParam("icao") String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiProblemiDAO apDAO = new AerodromiProblemiDAO(konfig);
		List<AerodromProblem> aerodromi = apDAO.dajProblemeAerodroma(stranica, broj, icao);
		
		if(!aerodromi.isEmpty()) {
			odgovor = Response.status(Response.Status.OK).entity(aerodromi).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema aerodroma.").build();
		}
		return odgovor;
	}
	
	@GET
	@Path("/{icao}/broj")
	@Produces({MediaType.APPLICATION_JSON})
	public Response dajBrojProblemaAerodroma(@PathParam("icao") String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		Response odgovor = null;
		
		AerodromiProblemiDAO apDAO = new AerodromiProblemiDAO(konfig);
		int broj = apDAO.dajBrojProblemaAerodroma(icao);
		
		if(broj != 0) {
			odgovor = Response.status(Response.Status.OK).entity(broj).build();
		} else {
			odgovor = Response.status(Response.Status.NOT_FOUND).entity("Nema podatka.").build();
		}
		return odgovor;
	}
	
	@DELETE
	@Path("{icao}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void handlePersonRequest(@PathParam ("icao") String icao) {
		Konfiguracija konfig = (Konfiguracija) context.getAttribute("Postavke");
		AerodromiProblemiDAO apDAO = new AerodromiProblemiDAO(konfig);
		apDAO.brisiProblemeAerodroma(icao);
	}

}
