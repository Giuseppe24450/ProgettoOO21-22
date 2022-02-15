package DAO;

import java.util.ArrayList;

import Model.Cover;
import Model.Remastering;

import Model.TracciaOriginale;

// TODO: Auto-generated Javadoc
/**
 * The Interface TracciaDAO.
 */
public interface TracciaDAO {

	/**
	 * Ottenere i remastering.
	 *
	 * @return the array list<Remastering>
	 */
	public ArrayList<Remastering> getRemastering();
	
	/**
	 * Ottenere le cover.
	 *
	 * @return the array list<Cover>
	 */
	public ArrayList<Cover> getCover();
	
	/**
	 * Ottenere le tracce originali.
	 *
	 * @return the array list<TracciaOriginale>
	 */
	public ArrayList<TracciaOriginale> getTracciaOriginale();
	
	/**
	 * Recupera remastering della traccia originale.
	 *
	 * @param t the TracciaOriginale
	 * @return the array list<Remastering>
	 */
	public ArrayList<Remastering> recuperaRemasteringdellaTracciaOriginale(TracciaOriginale t);
	
	/**
	 * Recupera cover della traccia originale.
	 *
	 * @param t the tracciaOriginale
	 * @return the array list<Cover>
	 */
	public ArrayList<Cover> recuperaCoverdellaTracciaOriginale(TracciaOriginale t);
}
