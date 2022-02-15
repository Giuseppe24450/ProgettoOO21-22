package DAO;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;


import Model.Artista;
import Model.Cover;
import Model.Remastering;
import Model.Traccia;
import Model.TracciaOriginale;

// TODO: Auto-generated Javadoc
/**
 * The Interface ListaPreferitiDAO.
 */
public interface PreferitiDAO {
	
	/**
	 * Lista preferiti cover dall utente nel DB.
	 *
	 * @param NickName the nick name
	 * @return the array list<Cover>
	 */
	public ArrayList<Cover> listapreferitiCoverDB(String NickName);
	
	/**
	 * Lista preferiti remastering dall utente nel DB.
	 *
	 * @param NickName the nick name
	 * @return the array list<Remastering>
	 */
	public ArrayList<Remastering> listapreferitiRemasteringDB(String NickName);
	
	/**
	 * Lista preferiti traccia originale dall utente nel DB.
	 *
	 * @param NickName the nick name
	 * @return the array list<TracciaOriginale>
	 */
	public ArrayList<TracciaOriginale> listapreferitiTracciaOriginaleDB(String NickName);
	/**
	 * Lista tracce preferiti dal DB.
	 *
	 * @param NickName the nick name
	 * @return the array list<Traccia>
	 */
	public ArrayList<Traccia> listapreferitiTracceDB(String NickName);
	/**
	 * Elimina traccia dalla lista preferiti nel  DB.
	 *
	 * @param NickName the nick name
	 * @param t the traccia
	 */
	public void deleteDB(String NickName, Traccia t);
	
	/**
	 * Inserisci traccia nella lista preferiti nel DB.
	 *
	 * @param nickName the nick name
	 * @param versione the versione
	 * @param titolo the titolo
	 * @param durata the durata
	 * @param artista the artista
	 */
	public void inserisciDB(String nickName, Date versione, String titolo, Time durata, Artista artista,String nomeAlbum);
}
