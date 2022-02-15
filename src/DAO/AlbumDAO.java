package DAO;

import java.util.ArrayList;

import Model.Album;

import Model.Traccia;

// TODO: Auto-generated Javadoc
/**
 * The Interface AlbumDAO.
 */
public interface AlbumDAO {

	/**
	 * Listaidentificativialbum DB.
	 *
	 * @return the array list<String>
	 */
	public ArrayList<String>listaidentificativialbumDB();
	
	/**
	 * Ottenere le tracce dell album.
	 *
	 * @param nome the nome
	 * @param codice the codicealbum
	 * @return the array list<Traccia>
	 */
	public ArrayList<Traccia>getTracceAlbum(String nome,String Codice);

	/**
	 *  Lista album.
	 *
	 * 
	 * @return the array list 
	 */
	public ArrayList<Album>listalbumDB();
	/**
	 *  Ottenere album di artisti.
	 *
	 * @param codici the codicialbum
	 * @param CodiceFiscale the codice fiscale dell artista
	 * @return the array list <Album>
	 */
	public ArrayList<Album>getAlbumdiArtisti(ArrayList<String> codici,String CodiceFiscale);
	
	/**
	 *  Ottenere l album
	 * @param codice the identificativo dell album
	 * @return the Album 
	 */
	public Album getAlbum(String codice);
}
