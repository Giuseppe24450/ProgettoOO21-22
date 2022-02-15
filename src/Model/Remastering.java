package Model;

import java.sql.Date;
import java.sql.Time;



// TODO: Auto-generated Javadoc
/**
 * The Class Remastering.
 */
public class Remastering extends Traccia {

/** The tracciaOriginale. */
private TracciaOriginale tracciaoriginale;
	
	/**
	 * Instantiates a new remastering.
	 *
	 * @param v the versione
	 * @param t the titolo
	 * @param d the durata
	 * @param ar the artista
	 * @param a the album
	 */
	public Remastering(Date v, String t,Time d, Artista ar,Album a) {
		super(v, t,d, ar,a);
		// TODO Auto-generated constructor stub
	}
/**
 * Ottenere la tracciaOriginale.
 *
 * @return the tracciaoriginale
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
