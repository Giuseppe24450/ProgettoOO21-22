package ImplementazioniPostgresDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnessioneDatabase.Connessione;
import DAO.AlbumDAO;

import Model.Album;
import Model.Artista;
import Model.Traccia;

// TODO: Auto-generated Javadoc
/**
 * The Class AlbumImplementazionePostgresDAO.
 */
public class AlbumImplementazionePostgresDAO implements AlbumDAO{
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new album implementazione postgres DAO.
	 */
	public AlbumImplementazionePostgresDAO (){
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Lista identificativi degli album del DB(codici).
	 *
	 * @return the array list
	 */
	public ArrayList<String>listaidentificativialbumDB(){
		PreparedStatement leggialbumPS;
		ArrayList<String>iden=new ArrayList<String>();
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggialbumPS = connection.prepareStatement(
					"SELECT idalbum,nome FROM album");
		ResultSet rs =leggialbumPS.executeQuery();
		while (rs.next()) {
		iden.add(new String(rs.getString("idalbum")));
		
		connection.close();
			
		}
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return iden;
	}
	/**
	 * Lista album nel DB.
	 *
	 * @return the array list<Album>
	 */
	public ArrayList<Album>listalbumDB(){
		PreparedStatement leggialbumPS;
		ArrayList<Album>l=new ArrayList<Album>();
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggialbumPS = connection.prepareStatement(
					"SELECT idalbum,nome FROM album");
		ResultSet rs =leggialbumPS.executeQuery();
		while (rs.next()) {
			
			ArrayList<Traccia> tr=getTracceAlbum(rs.getString("idalbum"),rs.getString("nome"));
			Album a=new Album(rs.getString("nome"),tr.get(0).getversione(),tr.get(0).getTitolo(),tr.get(0).getDurata(),tr.get(0).getArtista());
			
			l.add(a);
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
	 *  Ottenere l album
	 * @param codice the identificativo dell album
	 * @return the Album 
	 */
	
public Album getAlbum(String codice){
		
		PreparedStatement leggitraccealbumPS;
		
		Album al = null;
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggitraccealbumPS = connection.prepareStatement(
					"SELECT nome,idalbum FROM album where idalbum='"+codice+"'");
		ResultSet rs =leggitraccealbumPS.executeQuery();
		while (rs.next()) {
			
			ArrayList<Traccia>tr=getTracceAlbum(codice,rs.getString("nome"));
			al=new Album(rs.getString("nome"),tr.get(0).getversione(),tr.get(0).getTitolo(),tr.get(0).getDurata(),tr.get(0).getArtista());	
			
		}
		connection.close();
		rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return al;
	}
	/**
	 * Ottenere gli album degli artisti.
	 *
	 * @param codici the codici dell album
	 * @param CodiceFiscale the codice Fiscale
	 * @return the array list<Album>
	 */
	
	public ArrayList<Album>getAlbumdiArtisti(ArrayList<String>codici,String CodiceFiscale){
		PreparedStatement leggialbumPS;
		ArrayList<Album> l=new ArrayList<Album>();
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggialbumPS = connection.prepareStatement(
					"select distinct(idalbum),nome as nomealbum from album  where idalbum not in(select album from traccia"
					+" where artista<>'"+CodiceFiscale+"')"
					);
		ResultSet rs =leggialbumPS.executeQuery();
		while (rs.next()) {
			codici.add(rs.getString("idalbum"));
			ArrayList<Traccia> tr=getTracceAlbum(rs.getString("idalbum"),rs.getString("nomealbum"));	
			Artista art=new Artista(tr.get(0).getArtista().getNome(),tr.get(0).getArtista().getCognome(),tr.get(0).getArtista().getcodicefiscale(),tr.get(0).getversione(),tr.get(0).getTitolo(),tr.get(0).getDurata(),tr.get(0).getAlbum().getNome());
			Album a=new Album(tr.get(0).getAlbum().getNome(),tr.get(0).getversione(),tr.get(0).getTitolo(),tr.get(0).getDurata(),art);
			l.add(a);	
			
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
	 * Ottenere le tracce dell'album.
	 *
	 * @param nome the nome dell album
	 * @param codice the codice dell album(identificativo)
	 * @return the array list<Traccia>
	 */
	public ArrayList<Traccia>getTracceAlbum(String codice,String nome){
		
		PreparedStatement leggitraccealbumPS;
		ArrayList<Traccia> l=new ArrayList<Traccia>();
	
		try {
			connection =Connessione.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			leggitraccealbumPS = connection.prepareStatement(
					"SELECT idtraccia,a.nome as nomealbum,titolo,versione,durata,ar.nome,ar.cognome,ar.CodiceFiscale FROM traccia,album as a,artista as ar where artista=ar.CodiceFiscale and album=a.idalbum and a.nome='"+nome+"' and a.idalbum='"+codice+"'");
		ResultSet rs =leggitraccealbumPS.executeQuery();
		while (rs.next()) {
			Artista art=new Artista(rs.getString("nome"),rs.getString("cognome"),rs.getString("CodiceFiscale"),rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),rs.getString("nomealbum"));
			Album al=new Album(nome,rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art);
			Traccia s = new Traccia(rs.getDate("versione"),rs.getString("titolo"),rs.getTime("durata"),art,al);
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
	
}
