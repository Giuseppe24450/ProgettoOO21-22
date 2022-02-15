package ImplementazioniPostgresDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnessioneDatabase.Connessione;
import DAO.ArtistaDAO;
import Model.Album;
import Model.Artista;
import Model.Traccia;

// TODO: Auto-generated Javadoc
/**
 * The Class ArtistaImplementazionePostgresDAO.
 */
public class ArtistaImplementazionePostgresDAO implements ArtistaDAO {
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new artista implementazione postgres DAO.
	 */
	public ArtistaImplementazionePostgresDAO (){
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista artisti nel  DB.
	 *
	 * @return the array list<Artista>
	 */
	public ArrayList<Artista>listartistiDB(){
		PreparedStatement leggiartistaPS;
		ArrayList<Artista> l=new ArrayList<Artista>();
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggiartistaPS = connection.prepareStatement(
					"SELECT nome,cognome,CodiceFiscale FROM artista");
		ResultSet rs =leggiartistaPS.executeQuery();
		while (rs.next()) {
		Artista a = getArtista(rs.getString("CodiceFiscale"));
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
	 * Lista tracce degli artisti nel DB.
	 *
	 * @param CodiceFiscale the codice fiscale
	 * @return the array list<Artista>
	 */
	public ArrayList<Traccia>listaTracceArtistiDB(String CodiceFiscale){
		PreparedStatement leggiTraccePS;
		AlbumImplementazionePostgresDAO alb=new AlbumImplementazionePostgresDAO();
		ArrayList<Traccia> l=new ArrayList<Traccia>();
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			leggiTraccePS = connection.prepareStatement(
					"SELECT idalbum,titolo,durata,album,versione,ar.nome as nomeartista,cognome,artista,a.nome as nomealbum FROM traccia,artista as ar,album as a where artista=ar.CodiceFiscale and artista='"+CodiceFiscale+"' and a.idalbum=album");
		ResultSet rs =leggiTraccePS.executeQuery();
		
		while (rs.next()) {
			Album al=alb.getAlbum(rs.getString("idalbum"));
			Traccia a =new Traccia(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),new  Artista(rs.getString("nomeartista"),rs.getString("cognome"),rs.getString("artista"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),al.getNome()),al);
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
	 * Ottenere l artista dal DB.
	 *
	 * @param CodiceFiscale the codice fiscale
	 * @return the Artista
	 */

public Artista getArtista(String CodiceFiscale){
	PreparedStatement leggiTraccePS;
	Artista art=null;
	try {
		connection =Connessione.getInstance().getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		
		leggiTraccePS = connection.prepareStatement(
				"SELECT a.nome as nomeartista,cognome,versione,codicefiscale,titolo,durata,al.nome as nomealbum  FROM artista as a,traccia as tr,album as al where a.codicefiscale='"+CodiceFiscale+"' and tr.artista=a.codicefiscale and al.idalbum=tr.album");
	ResultSet rs =leggiTraccePS.executeQuery();
	while (rs.next()) {
	art=new Artista(rs.getString("nomeartista"),rs.getString("cognome"),CodiceFiscale,rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
		connection.close();
	}
	rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return art;
}
}
