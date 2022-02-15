package DAO;

import java.util.ArrayList;

import Model.Utente;

// TODO: Auto-generated Javadoc
/**
 * The Interface UtenteDAO.
 */
public interface UtenteDAO {

/**
 * Lista utenti DB.
 *
 * @return the array list<Utente>
 */
public ArrayList<Utente> listautentiDB();

/**
 * Inserisci utente nel DB.
 *
 * @param nickName the nick name
 * @param nome the nome
 * @param cognome the cognome
 * @param password the password
 */
public void inserisciDB(String nickName, String nome, String cognome,String password);

/**
 * Controllo validit‡ accesso DB.
 *
 * @param NickName the nick name
 * @param password the password
 * @return the string
 */
public String controllovalidit‡AccessoDB(String NickName,String password) ;
}
