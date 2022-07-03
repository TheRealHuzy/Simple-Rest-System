package org.foi.nwtis.ihuzjak.zadaca_2.podaci;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.Konfiguracija;
import org.foi.nwtis.podaci.Airport;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

public class AirportDAO {
	
	@Inject
	ServletContext context;
	
	String url;
	Connection con;
	PreparedStatement pstmt;
	
	Konfiguracija konfig;
	
	public AirportDAO(Konfiguracija konfig){
		this.konfig = konfig;
		url = konfig.dajPostavku("server.database")+konfig.dajPostavku("user.database");
	}
	
	public List<Airport> dajSveAerodrome(String stranica, String broj){
		
        int pomak = izracunajPomak(stranica, broj);
		String upit = "SELECT * FROM AIRPORTS LIMIT " + broj + " OFFSET " + pomak +";";
		
		List<Airport> aerodromi = saljiNaBazu(upit);
        return aerodromi;
	}
	
	public List<Airport> dajSveAerodromeUNOS(){
		String upit = "SELECT * FROM AIRPORTS;";
		List<Airport> aerodromi = saljiNaBazu(upit);
        return aerodromi;
	}

	private List<Airport> saljiNaBazu(String upit) {
		List<Airport> aerodromi = new ArrayList<>();
		try {
		     Class.forName("org.hsqldb.jdbc.JDBCDriver");
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
        try
        {   con = DriverManager.getConnection(url, konfig.dajPostavku("user.username"),
        		konfig.dajPostavku("user.password"));
            pstmt = con.prepareStatement(upit);
            ResultSet rs = pstmt.executeQuery();
                
            while (rs.next()) {
                Airport a = new Airport();
                a.setIdent(rs.getString("ident"));
                a.setName(rs.getString("name"));
                a.setMunicipality(rs.getString("municipality"));
                a.setContinent(rs.getString("continent"));
                aerodromi.add(a);
            }
            pstmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            Airport a = new Airport();
            a.setIdent("SQLException: " + ex.getMessage());
            aerodromi.add(a);
            ex.printStackTrace();
        }
        return aerodromi;
	}
	
	private int izracunajPomak(String stranica, String broj) {
		return (Integer.parseInt(stranica) * Integer.parseInt(broj)) - Integer.parseInt(broj);
	}

	public int dajBrojZapisa() {

		String upit = "SELECT COUNT(*) FROM AIRPORTS;";
		int broj = 1;
		try {
		     Class.forName("org.hsqldb.jdbc.JDBCDriver");
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
       try
       {   con = DriverManager.getConnection(url, konfig.dajPostavku("user.username"),
    		   konfig.dajPostavku("user.password"));
           pstmt = con.prepareStatement(upit);
           ResultSet rs = pstmt.executeQuery();
           while (rs.next()) {
        	   broj = rs.getInt(1);
           }
           pstmt.close();
           con.close();
       } catch(SQLException ex) {
           System.err.println("SQLException: " + ex.getMessage());
           ex.printStackTrace();
       }
		return broj;
	}
	
	public List<Airport> dajAerodrom(String icao){
		String upit = "SELECT * FROM AIRPORTS WHERE ident='" + icao + "';";
		List<Airport> aerodromi = saljiNaBazu(upit);
        return aerodromi;
	}
}
