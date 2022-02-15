package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * The Class TracciaOriginale.
 */
public class TracciaOriginale extends Traccia {

/** The cover. */
private ArrayList<Cover> cover;

/** The remastering. */
private ArrayList<Remastering> remastering;
	
	/**
	 * Instantiates a new traccia originale.
	 *
	 * @param v the versione
	 * @param t the titolo
	 * @param d the durata
	 * @param ar the artista
	 * @param a the album
	 * 
	 */
	public TracciaOriginale(Date v, String t,Time d, Artista ar,Album a) {
		super(v, t,d,ar,a);
		cover=new ArrayList<Cover>();
		remastering=new ArrayList<Remastering>();
	}

/**
 * Aggiungere  la cover.
 *
 * @param c the cover
 */
public void addCover(Cover c) {
	cover.add(c);
}

/**
 * Aggiungere il remastering.
 *
 * @param r the remastering
 */
public void addRemastering(Remastering r) {
	remastering.add(r);
}

/**
 * Ottenere le cover.
 *
 * @return the array list<Cover>
 */
public ArrayList<Cover> getCover(){
	return cover;
}

/**
 * Ottenere  il remastering.
 *
 * @return the array list<Remastering>
 */
public ArrayList<Remastering> getRemastering(){
	return remastering;
}

}
