package org.foi.nwtis.ihuzjak.zadaca_2_lib_03_1.konfiguracije;

/**
 * Iznimka za slučaj kada je neispravna konfiguracija s postavmaka
 */
public class NeispravnaKonfiguracija extends Exception {

    /**
	 * Serijski id verzije
	 */
	private static final long serialVersionUID = 8075964301691709607L;

	/**
     * Kreira instancu <code>NeispravnaKonfiguracija</code> bez
     * detalja poruke.
     */
    public NeispravnaKonfiguracija() {
    }

    /**
     * Kreira instancu <code>NeispravnaKonfiguracija</code> s
     * pridruženim tekstom poruke.
     *
     * @param msg razloga nastanka iznimke
     */
    public NeispravnaKonfiguracija(String msg) {
        super(msg);
    }
}
