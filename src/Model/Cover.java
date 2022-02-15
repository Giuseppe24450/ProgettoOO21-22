package Model;

import java.sql.Date;
import java.sql.Time;

// TODO: Auto-generated Javadoc
/**
 * The Class Cover.
 */
public class Cover extends Traccia {
	
	/** The tracciaoriginale. */
	private TracciaOriginale tracciaoriginale;
	
	/**
	 * Instantiates a new cover.
	 *
	 * @param v the versione
	 * @param t the titolo
	 * @param d the durata
	 * @param ar the artista
	 *  @param a the album
	 */
	public Cover(Date v, String t,Time d, Artista ar,Album a) {
		super(v, t,d, ar,a);
		// TODO Auto-generated constructor stub
	}

/**
 * Ottenere la tracciaOriginale.
 *
 * @return the tracciaOriginale
 */
public TracciaOriginale getTracciaoriginale() {
	return tracciaoriginale;
}

/**
 * Settare la tracciaOriginale.
 *
 * @param tr the TracciaOriginale
 */
public void setTracciaoriginale(TracciaOriginale tr) {
	this.tracciaoriginale = tr;
}
	




}
