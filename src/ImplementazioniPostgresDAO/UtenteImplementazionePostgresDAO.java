package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnessioneDatabase.Connessione;
import DAO.UtenteDAO;
import Model.Utente;

// TODO: Auto-generated Javadoc
/**
 * The Class UtenteImplementazionePostgresDAO.
 */
public class UtenteImplementazionePostgresDAO implements UtenteDAO {
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new utente implementazione postgres DAO.
	 */
	public UtenteImplementazionePostgresDAO(){
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista utenti nel  DB.
	 *
	 * @return the array list<Utente>
	 */
	public ArrayList<Utente> listautentiDB(){
		PreparedStatement leggiutentePS;
		ArrayList<Utente> l= new ArrayList<Utente>();
		try {
			leggiutentePS = connection.prepareStatement(
					"SELECT nome,cognome,nickname,password FROM utente");
		ResultSet rs =leggiutentePS.executeQuery();
		while (rs.next()) {
				Utente s=new Utente(rs.getString("nome"),rs.getString("cognome"),rs.getString("nickname"));
				l.add(s);
		}
		connection.close();
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	/**
	 * Inserisci utente nel DB.
	 *
	 * @param nickname the nickname
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param password the password
	 */
	public void inserisciDB(String nickname,String nome,String cognome,String password) {
		PreparedStatement inserisciutentePS;
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		inserisciutentePS = connection.prepareStatement(
					"INSERT INTO UTENTE(nickname,nome,cognome,password) VALUES('"+nickname+"','"+nome+"','"+cognome+"','"+password+"')");
		inserisciutentePS.executeUpdate();
		connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Controllo validit‡ accesso DB.
	 *
	 * @param NickName the nick name
	 * @param p the password
	 * @return the string
	 */
	public String controllovalidit‡AccessoDB(String NickName,String password) {
		PreparedStatement leggipasswordPS;
		int trovato=0;
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggipasswordPS = connection.prepareStatement(
					"SELECT nome,cognome,nickname,password FROM utente");
		ResultSet rs =leggipasswordPS.executeQuery();
		while (rs.next()) {
		if((rs.getString("nickname").contentEquals(NickName))&&(rs.getString("password").contentEquals(password)))
			trovato=1;
		if((rs.getString("nickname").contentEquals(NickName))&&!(rs.getString("password").contentEquals(password)))
			return"password errata";
		}
		connection.close();
		rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(trovato==1)
			return"accesso consentito";
		return"utente non presente";
	}
}
