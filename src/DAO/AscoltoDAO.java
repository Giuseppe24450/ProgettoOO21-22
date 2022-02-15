package DAO;

import java.util.ArrayList;

import Model.Ascolto;


// TODO: Auto-generated Javadoc
/**
 * The Interface AscoltoDAO.
 */
public interface AscoltoDAO {
	
	/**
	 * Lista ascolti dell utente nel DB.
	 *
	 * @param nome the  nome dell utente
	 * @param cognome the  cognome dell utente
	 * @param nickname the  nickname dell utente
	 * @return the array list<Ascolto>
	 */
	public ArrayList<Ascolto>listascoltoDB(String nome,String cognome,String nickname);
	
	/**
	 * Inserisci ascolto nel DB.
	 *
	 * @param a the ascolto
	 */
	public void inserisciascoltoDB(Ascolto a);
	
	/**
	 * Lista ascolti utenti DB.
	 *
	 * @return the array list<Ascolto>
	 */
	public ArrayList<Ascolto>listascoltiutentiDB();
}
