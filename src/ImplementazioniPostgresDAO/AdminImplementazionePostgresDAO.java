package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnessioneDatabase.Connessione;
import DAO.AdminDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminImplementazionePostgresDAO.
 */
public class AdminImplementazionePostgresDAO implements AdminDAO{
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new admin implementazione postgres DAO.
	 */
	public AdminImplementazionePostgresDAO(){
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Controllo validit‡ accesso DB.
	 *
	 * @param Codice the codice fiscale
	 * @param p the password
	 * @return the string
	 */
	public String controllovalidit‡AccessoDB(String Codice,String p) {
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
					"SELECT codicefiscale,passwordadmin FROM admin");
		ResultSet rs =leggipasswordPS.executeQuery();
		while (rs.next()) {
		if((rs.getString("codicefiscale").contentEquals(Codice))&&(rs.getString("passwordadmin").contentEquals(p)))
			trovato=1;
		if((rs.getString("codicefiscale").contentEquals(Codice))&&!(rs.getString("passwordadmin").contentEquals(p)))
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
		return"admin non presente";
	}
}

