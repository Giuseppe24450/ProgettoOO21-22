package ImplementazioniPostgresDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnessioneDatabase.Connessione;
import DAO.TracciaDAO;
import Model.Album;
import Model.Artista;
import Model.Cover;
import Model.Remastering;
import Model.TracciaOriginale;

// TODO: Auto-generated Javadoc
/**
 * The Class TracciaImplementazionePostgresDAO.
 */
public class TracciaImplementazionePostgresDAO implements TracciaDAO{
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new traccia implementazione postgres DAO.
	 */
	public TracciaImplementazionePostgresDAO(){
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ottenere le tracce originali.
	 *
	 * @return the array list<TracciaOriginale>
	 */
	public ArrayList<TracciaOriginale> getTracciaOriginale(){
		PreparedStatement leggiTracciaOriginalePS;
		ArrayList<TracciaOriginale> tr=new ArrayList<TracciaOriginale>();
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggiTracciaOriginalePS = connection.prepareStatement(
					"SELECT versione,idalbum,titolo,durata,ar.nome as nomeartista,cognome,CodiceFiscale,al.nome as nomealbum FROM Traccia,Artista as ar,album as al WHERE Traccia.Artista=ar.CodiceFiscale and tipo='TracciaOriginale' and album=al.idalbum");
			ResultSet rs = leggiTracciaOriginalePS.executeQuery();
		
		
		while (rs.next()) {
			Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
			Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
			TracciaOriginale t = new TracciaOriginale(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al);
			tr.add(t);
			connection.close();
		}
		rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tr;
	}
	
	
	/**
	 * Ottenere le cover.
	 *
	 * @return the array list<Cover>
	 */
	public ArrayList<Cover> getCover(){
		PreparedStatement leggiCoverPS;
		ArrayList<Cover> l=new ArrayList<Cover>();
	
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggiCoverPS = connection.prepareStatement(
					"SELECT versione,titolo,durata,ar.nome as nomeartista,cognome,CodiceFiscale,al.nome as nomealbum FROM Traccia,Artista as ar,album as al WHERE Traccia.Artista=ar.CodiceFiscale and tipo='Cover' and al.idalbum=album");
		ResultSet rs = leggiCoverPS.executeQuery();
		while (rs.next()) {
			Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
			Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
			Cover c = new Cover(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al);
			
			l.add(c);
			
			connection.close();
		}
		rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	/**
	 * Ottenere i remastering.
	 *
	 * @return the array list<Remastering>
	 */
	public ArrayList<Remastering> getRemastering(){
		PreparedStatement leggiRemasteringPS;
		ArrayList<Remastering> l=new ArrayList<Remastering>();
	
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggiRemasteringPS = connection.prepareStatement(
					"SELECT versione,titolo,durata,ar.nome as nomeartista,cognome,CodiceFiscale,al.nome as nomealbum FROM Traccia,Artista as ar,album as al WHERE Traccia.Artista=ar.CodiceFiscale and tipo='Remastering' and al.idalbum=album");
		ResultSet rs = leggiRemasteringPS.executeQuery();
		while (rs.next()) {
			Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
			Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
			Remastering r = new Remastering(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"), art,al);
			l.add(r);
			
			connection.close();
		}
		rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	/**
	 * Recupera remastering della traccia originale.
	 *
	 * @param t the TracciaOriginale
	 * @return the array list<Remastering>
	 */
	public ArrayList<Remastering> recuperaRemasteringdellaTracciaOriginale(TracciaOriginale t) {
		PreparedStatement leggiRemasteringPS;
		ArrayList<Remastering> l=new ArrayList<Remastering>();

		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			leggiRemasteringPS = connection.prepareStatement(
					"SELECT versione,titolo,durata,ar.nome as nomeartista,cognome,codicefiscale,al.nome as nomealbum FROM traccia,artista as ar,album as al WHERE traccia.artista=ar.codicefiscale and tipo='Remastering' and remasteringdi=(select idtraccia from "
					+ "traccia where versione='"+t.getversione()+"' and titolo='"+t.getTitolo()+"' and durata='"+t.getDurata()+"' and artista='"+t.getArtista().getcodicefiscale()+"' and album in(select idalbum from album where nome='"+t.getAlbum().getNome()+"') and tipo='TracciaOriginale') and al.idalbum=album");
		ResultSet rs = leggiRemasteringPS.executeQuery();
		while (rs.next()) {
			Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("codicefiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
			Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
			Remastering r = new Remastering(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al);
			l.add(r);
			
			connection.close();
		}
		rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return l;
	}

	/**
	 * Recupera cover della traccia originale.
	 *
	 * @param t the TracciaOriginale
	 * @return the array list<Cover>
	 */
	public ArrayList<Cover> recuperaCoverdellaTracciaOriginale(TracciaOriginale t){
		PreparedStatement leggiCoverPS;
		ArrayList<Cover> l=new ArrayList<Cover>();
	
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggiCoverPS = connection.prepareStatement(
					"SELECT versione,titolo,durata,ar.nome as nomeartista,cognome,CodiceFiscale,al.nome as nomealbum FROM Traccia,Artista as ar,album as al WHERE Traccia.Artista=ar.CodiceFiscale and tipo='Cover'and coverdi=(select idtraccia from "
					+ "traccia where versione='"+t.getversione()+"' and titolo='"+t.getTitolo()+"' and durata='"+t.getDurata()+"'and artista='"+t.getArtista().getcodicefiscale()+"' and album in(select idalbum from album where nome='"+t.getAlbum().getNome()+"') and tipo='TracciaOriginale') and al.idalbum=album");
		ResultSet rs = leggiCoverPS.executeQuery();
		while (rs.next()) {
			Artista art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
			Album al=new Album(rs.getString("nomealbum"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
			Cover c = new Cover(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al);
		
			l.add(c);
			
			connection.close();
		}
		rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	

}

