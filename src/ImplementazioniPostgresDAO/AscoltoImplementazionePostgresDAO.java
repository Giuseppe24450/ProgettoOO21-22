package ImplementazioniPostgresDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ConnessioneDatabase.Connessione;
import DAO.AscoltoDAO;
import Model.Album;
import Model.Artista;
import Model.Ascolto;
import Model.Traccia;
import Model.Utente;

// TODO: Auto-generated Javadoc
/**
 * The Class AscoltoImplementazionePostgresDAO.
 */
public class AscoltoImplementazionePostgresDAO implements AscoltoDAO {
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new ascolto implementazione postgres DAO.
	 */
	public AscoltoImplementazionePostgresDAO (){
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista ascolti degli utenti nel DB.
	 *
	 * @return the array list<Ascolto>
	 */
	public ArrayList<Ascolto>listascoltiutentiDB(){
		PreparedStatement leggiascoltoPS;
		ArrayList<Ascolto> l=new ArrayList<Ascolto>();
	
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggiascoltoPS = connection.prepareStatement(
					"Select al.idalbum as identificativoalbum,art.nome as nomeartista,art.cognome,art.CodiceFiscale,a.data_,t.titolo,t.durata,al.nome as nomealbum,t.versione,a.utente,ut.nome as nomeutente,ut.cognome as cognomeutente "
					+ "from ascolto as a,traccia as t,artista as art,utente as ut,album as al where a.utente=ut.nickname and t.idtraccia=a.traccia and"
							+ " t.artista=art.CodiceFiscale and al.idalbum=t.album");
		ResultSet rs =leggiascoltoPS.executeQuery();
		while (rs.next()) {
		Ascolto a;
		Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
		Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
		LocalDateTime local=rs.getTimestamp("data_").toLocalDateTime();
			a = new Ascolto(local,new Utente(rs.getString("nomeutente"),rs.getString("cognomeutente"),rs.getString("utente")),new Traccia(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al));
			l.add(a);
		connection.close();
			
		}
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}
	
	/**
	 * Lista ascolti dell utente nel DB.
	 *
	 * @param nome the nome
	 * @param cognome the cognome
	 * @param nickname the nickname
	 * @return the array list<Ascolto>
	 */
	public ArrayList<Ascolto>listascoltoDB(String nome,String cognome,String nickname){
		PreparedStatement leggiascoltoPS;
		ArrayList<Ascolto> l=new ArrayList<Ascolto>();
	
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggiascoltoPS = connection.prepareStatement(
					"Select al.idalbum as identificativoalbum,art.nome as nomeartista,art.cognome,art.CodiceFiscale,a.data_,t.titolo,t.durata,al.nome as nomealbum,t.versione,a.utente "
					+ "from ascolto as a,traccia as t,artista as art,album as al where a.utente='"+nickname+"' and t.idtraccia=a.traccia and"
							+ " t.artista=art.CodiceFiscale and al.idalbum=t.album");
		ResultSet rs =leggiascoltoPS.executeQuery();
		while (rs.next()) {
		Ascolto a;
		Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
		Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
		LocalDateTime local=rs.getTimestamp("data_").toLocalDateTime();
			a = new Ascolto(local,new Utente(nome,cognome,nickname),new Traccia(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al));
			l.add(a);
		connection.close();
			
		}
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}
	
	/**
	 * Inserisci ascolto nel  DB.
	 *
	 * @param a the ascolto
	 */
	public void inserisciascoltoDB(Ascolto a) {
		PreparedStatement inserisciascoltoPS;
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			LocalDateTime istante=LocalDateTime.now();
			inserisciascoltoPS = connection.prepareStatement(
				"INSERT INTO ascolto (Select '"+istante+"',idtraccia,nickname from utente,traccia,artista as art where nickname='"+a.getUtente().getnickname()+"' and versione='"+a.getTraccia().getversione()+"'"
						+ " and titolo='"+a.getTraccia().getTitolo()+"' and durata='"+a.getTraccia().getDurata()+"' and art.CodiceFiscale='"+a.getTraccia().getArtista().getcodicefiscale()+"')");
					
		inserisciascoltoPS.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
