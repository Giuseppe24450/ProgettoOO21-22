package ImplementazioniPostgresDAO;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import ConnessioneDatabase.Connessione;

import DAO.PreferitiDAO;
import Model.Album;
import Model.Artista;
import Model.Cover;
import Model.Remastering;
import Model.Traccia;
import Model.TracciaOriginale;


// TODO: Auto-generated Javadoc
/**
 * The Class PreferitiImplementazionePostgresDAO.
 */
public class PreferitiImplementazionePostgresDAO implements PreferitiDAO {
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new preferiti implementazione postgres DAO.
	 */
	public PreferitiImplementazionePostgresDAO(){
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/**
 * Lista cover preferite dall utente nel DB.
 *
 * @param NickName the nick name
 * @return the array list<Cover>
 */
public ArrayList<Cover> listapreferitiCoverDB(String NickName){
	PreparedStatement leggipreferitiPS;
	ArrayList<Cover> l=new ArrayList<Cover>();
	
	try {
		connection =Connessione.getInstance().getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		leggipreferitiPS = connection.prepareStatement(
				"SELECT p.idtraccia,titolo,versione,tr.durata,ar.nome as nomeartista,cognome,CodiceFiscale,al.nome as nomealbum FROM listapreferiti as p,traccia as tr,artista as ar,album as al where p.idtraccia=tr.idtraccia and tr.artista=ar.CodiceFiscale and p.nickname='"+NickName+"' and tipo='Cover' and tr.album=al.idalbum");
	ResultSet rs =leggipreferitiPS.executeQuery();
	while (rs.next()) {
		Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
		Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
	Cover s = new Cover(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al);
		l.add(s);
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
 * Lista remastering preferiti dall utente nel DB.
 *
 * @param NickName the nick name
 * @return the array list<Remastering>
 */
public ArrayList<Remastering> listapreferitiRemasteringDB(String NickName){
	PreparedStatement leggipreferitiPS;
	ArrayList<Remastering> l=new ArrayList<Remastering>();
	
	try {
		connection =Connessione.getInstance().getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		leggipreferitiPS = connection.prepareStatement(
				"SELECT p.idtraccia,titolo,versione,tr.durata,ar.nome as nomeartista,cognome,CodiceFiscale,al.nome as nomealbum FROM listapreferiti as p,traccia as tr,artista as ar,album as al where p.idtraccia=tr.idtraccia and tr.artista=ar.CodiceFiscale and p.nickname='"+NickName+"' and tipo='Remastering' and al.idalbum=tr.album");
	ResultSet rs =leggipreferitiPS.executeQuery();
	while (rs.next()) {
		Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
		Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
	Remastering s = new Remastering(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al);
		l.add(s);
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
 * Lista tracce originali preferite dall utente nel DB.
 *
 * @param NickName the nick name
 * @return the array list<TracciaOriginale>
 */
public ArrayList<TracciaOriginale> listapreferitiTracciaOriginaleDB(String NickName){
	PreparedStatement leggipreferitiPS;
	ArrayList<TracciaOriginale> l=new ArrayList<TracciaOriginale>();

	try {
		connection =Connessione.getInstance().getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		leggipreferitiPS = connection.prepareStatement(
				"SELECT p.idtraccia,titolo,versione,tr.durata,ar.nome as nomeartista,cognome,ar.CodiceFiscale,al.nome as nomealbum FROM listapreferiti as p,traccia as tr,artista as ar,album as al where p.idtraccia=tr.idtraccia and tr.artista=ar.CodiceFiscale and p.nickname='"+NickName+"' and tipo='TracciaOriginale' and al.idalbum=tr.album");
	ResultSet rs =leggipreferitiPS.executeQuery();
	while (rs.next()) {
		Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
		Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
	TracciaOriginale s = new TracciaOriginale(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),  art,al);
		l.add(s);
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
 * Lista tracce preferiti dall utente dal DB,indipendentemente dalla tipologia.
 *
 * @param NickName the nick name
 * @return the array list<Traccia>
 */
public ArrayList<Traccia> listapreferitiTracceDB(String NickName){
	PreparedStatement leggipreferitiPS;
	ArrayList<Traccia> l=new ArrayList<Traccia>();
	try {
		connection =Connessione.getInstance().getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		leggipreferitiPS = connection.prepareStatement(
				"SELECT p.idtraccia,titolo,versione,tr.durata,ar.nome as nomeartista,cognome,ar.CodiceFiscale,al.nome as nomealbum FROM listapreferiti as p,traccia as tr,artista as ar,album as al where p.idtraccia=tr.idtraccia and tr.artista=ar.CodiceFiscale and p.nickname='"+NickName+"' and al.idalbum=tr.album");
	ResultSet rs =leggipreferitiPS.executeQuery();
	while (rs.next()) {
		Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
		Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
	Traccia s = new Traccia(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al);
		l.add(s);
	connection.close();
		
	}
	rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return l;}
/**
 * Inserisci traccia preferita nel DB.
 *
 * @param NickName the nick name
 * @param versione the versione
 * @param Titolo the titolo
 * @param Durata the durata
 * @param ar the artista
 */
public void inserisciDB(String NickName, Date versione,String Titolo,Time Durata,Artista ar,String nomealbum) {
	PreparedStatement inserisciPS;
	try {
		connection =Connessione.getInstance().getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		inserisciPS = connection.prepareStatement(
				"INSERT INTO listapreferiti(idtraccia,nickname) (select tr.idtraccia,'"+NickName+"' FROM traccia as tr"
						+" where tr.titolo='"+Titolo+"' and tr.versione='"+versione+"' and tr.durata='"+Durata+"'"
						+ "and tr.artista='"+ar.getcodicefiscale()+"' and tr.album in(select idalbum from album where nome='"+nomealbum+"'))");
	inserisciPS.executeUpdate();
	connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * Elimina la traccia presente nella lista preferiti dal DB.
 *
 * @param NickName the nick name
 * @param t the traccia
 */
public void deleteDB(String NickName, Traccia t) {
	PreparedStatement deletePS;
	try {
		connection =Connessione.getInstance().getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		deletePS = connection.prepareStatement(
				"delete from listapreferiti where idtraccia=(select idtraccia from traccia "
				+ "where titolo='"+t.getTitolo()+"' and versione='"+t.getversione()+"' and durata='"+t.getDurata()+"' and artista='"+t.getArtista().getcodicefiscale()+"') and nickname='"+NickName+"'");
				
		deletePS.executeUpdate();		
	connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
