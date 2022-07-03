package org.foi.nwtis.ihuzjak.zadaca_2.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.foi.nwtis.ihuzjak.zadaca_2.podaci.AerodromProblem;
import org.foi.nwtis.podaci.Airport;
import org.foi.nwtis.rest.podaci.AvionLeti;

import com.google.gson.Gson;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class AerodromiKlijent {
	public List<Airport> dajSveAerodrome(String stranica, String broj) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi")
				.queryParam("stranica",stranica).queryParam("broj",broj);
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<Airport> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, Airport[].class)));
		}
		return aerodromi;
	}
	public int dajBrojZapisa() {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi/broj");
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		int broj = 0;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			broj = gson.fromJson(odgovor, int.class);
		}
		return broj;
	}
	
	public List<Airport> dajSveAerodromeUNOS() {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi/dohvat");
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<Airport> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, Airport[].class)));
		}
		return aerodromi;
	}
	
	public List<Airport> dajAerodrome(String icao) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi").path(icao);
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<Airport> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, Airport[].class)));
		}
		return aerodromi;
	}
	
	public List<Airport> dajPraceneAerodrome(String stranica, String broj) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi?preuzimanje").queryParam("stranica", stranica)
				.queryParam("broj", broj);
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<Airport> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, Airport[].class)));
		}
		return aerodromi;
	}
	
	public List<Airport> dajPraceneAerodrome() {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi/praceni");
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<Airport> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, Airport[].class)));
		}
		return aerodromi;
	}
	
	public int dajBrojPracenihZapisa() {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi/praceni/broj");
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		int broj = 0;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			broj = gson.fromJson(odgovor, int.class);
		}
		return broj;
	}
	
	public List<AvionLeti> dajAerodromDolasci(String stranica, String broj, String icao) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi").path(icao).path("dolasci")
				.queryParam("stranica", stranica).queryParam("broj", broj);
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<AvionLeti> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, AvionLeti[].class)));
		}
		return aerodromi;
	}
	
	public int dajBrojAerodromDolasci(String icao) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi").path(icao).path("dolasci").path("broj");
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		int broj = 0;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			broj = gson.fromJson(odgovor, int.class);
		}
		return broj;
	}
	
	public List<AvionLeti> dajAerodromPolasci(String stranica, String broj, String icao) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi").path(icao).path("polasci")
				.queryParam("stranica", stranica).queryParam("broj", broj);
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<AvionLeti> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, AvionLeti[].class)));
		}
		return aerodromi;
	}
	
	public int dajBrojAerodromPolasci(String icao) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi").path(icao).path("polasci").path("broj");
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		int broj = 0;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			broj = gson.fromJson(odgovor, int.class);
		}
		return broj;
	}
	
	public List<AerodromProblem> dajSveProbleme(String stranica, String broj) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/problemi").
				queryParam("stranica", stranica).queryParam("broj", broj);
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<AerodromProblem> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, AerodromProblem[].class)));
		}
		return aerodromi;
	}
	
	public int dajBrojSvihProblema() {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/problemi/broj");
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		int broj = 0;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			broj = gson.fromJson(odgovor, int.class);
		}
		return broj;
	}
	
	public List<AerodromProblem> dajProblemeAerodroma(String stranica, String broj, String icao) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/problemi")
				.path(icao).queryParam("stranica",stranica).queryParam("broj",broj);
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<AerodromProblem> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, AerodromProblem[].class)));
		}
		return aerodromi;
	}
	
	public int dajBrojProblemaAerodroma(String icao) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/problemi").path(icao).path("broj");
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		int broj = 0;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			broj = gson.fromJson(odgovor, int.class);
		}
		return broj;
	}
	
	public List<Airport> dajAerodrom(String icao) {
		Client client = ClientBuilder.newClient();
		// TODO preuzeti adresu iz postavki
		WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi").path(icao);
		Response restOdgovor = webResource.request()
				.header("Accept", "application/json")
				.get();
		List<Airport> aerodromi = null;
		if (restOdgovor.getStatus() == 200) {
			String odgovor = restOdgovor.readEntity(String.class);
			Gson gson = new Gson();
			aerodromi = new ArrayList<>();
			aerodromi.addAll(Arrays.asList(gson.fromJson(odgovor, Airport[].class)));
		}
		return aerodromi;
	}
	
	public void dodajAerodrom(String icao) {
        Client client = ClientBuilder.newClient();
        WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/aerodromi");
        webResource.request(MediaType.APPLICATION_JSON)
        	.accept(MediaType.TEXT_PLAIN_TYPE)
            .post(Entity.json(icao), String.class);
    }
	
	public void brisiProblemeAerodroma(String icao) {
        Client client = ClientBuilder.newClient();
        WebTarget webResource = client.target("http://localhost:8080/ihuzjak-zadaca_2_wa_1/api/problemi/" + icao);
        webResource.request(MediaType.APPLICATION_JSON)
        	.accept(MediaType.TEXT_PLAIN_TYPE)
        	.delete(String.class);
    }

}
