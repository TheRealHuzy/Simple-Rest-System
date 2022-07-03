package org.foi.nwtis.ihuzjak.zadaca_2.podaci;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije.Konfiguracija;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

public class AerodromiProblemiDAO {
	
	@Inject
	ServletContext context;
	
	String url;
	Connection con;
	PreparedStatement pstmt;
	
	Konfiguracija konfig;
	
	public AerodromiProblemiDAO(Konfiguracija konfig){
		this.konfig = konfig;
		url = konfig.dajPostavku("server.database")+konfig.dajPostavku("user.database");
	}
	
	public List<AerodromProblem> dajSveProbleme(String stranica, String broj){

        int pomak = izracunajPomak(stranica, broj);
		String upit = "SELECT * FROM AERODROMI_PROBLEMI LIMIT " + broj + " OFFSET " + pomak +";";
		
		List<AerodromProblem> aerodromi = saljiNaBazu(upit);
        return aerodromi;
	}

	private List<AerodromProblem> saljiNaBazu(String upit) {
		List<AerodromProblem> aerodromi = new ArrayList<>();
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
            	AerodromProblem a = new AerodromProblem("","","");
                a.setIdent(rs.getString("ident"));
                a.setDescription(rs.getString("description"));
                aerodromi.add(a);
            }
            pstmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            AerodromProblem a = new AerodromProblem("","","");
            a.setIdent("SQLException: " + ex.getMessage());
            aerodromi.add(a);
            ex.printStackTrace();
        }
        return aerodromi;
	}
	
	private int izracunajPomak(String stranica, String broj) {
		return (Integer.parseInt(stranica) * Integer.parseInt(broj)) - Integer.parseInt(broj);
	}
	
	public int dajBrojSvihProblemZapisa() {

		String upit = "SELECT COUNT(*) FROM AERODROMI_PROBLEMI;";
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
	
	public List<AerodromProblem> dajProblemeAerodroma(String stranica, String broj, String icao){

        int pomak = izracunajPomak(stranica, broj);
		String upit = "SELECT * FROM AERODROMI_PROBLEMI WHERE ident='" + icao + "' LIMIT "
				+ broj + " OFFSET " + pomak +";";
		
		List<AerodromProblem> aerodromi = saljiNaBazu(upit);
        return aerodromi;
	}
	
	public int dajBrojProblemaAerodroma(String icao) {

		String upit = "SELECT COUNT(*) FROM AERODROMI_PROBLEMI WHERE ident='" + icao + "';";
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
	
	public void brisiProblemeAerodroma(String icao){
		String upit = "DELETE FROM AERODROMI_PROBLEMI WHERE ident='" + icao +"';";
		izbrisiIzBaze(upit);
	}

	private void izbrisiIzBaze(String upit) {
		try {
		     Class.forName("org.hsqldb.jdbc.JDBCDriver");
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
        try
        {   
        	con = DriverManager.getConnection(url, konfig.dajPostavku("user.username"),
        			konfig.dajPostavku("user.password"));
            pstmt = con.prepareStatement(upit);
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
	}
}