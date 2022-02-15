package DAO;

import java.util.ArrayList;

import Model.Artista;
import Model.Traccia;

// TODO: Auto-generated Javadoc
/**
 * The Interface ArtistaDAO.
 */
public interface ArtistaDAO {
	
	/**
	 * Lista artisti DB.
	 *
	 * @return the array list<Artista>
	 */
	public ArrayList<Artista>listartistiDB();
	
	/**
	 * Lista tracce artisti DB.
	 *
	 * @param CodiceFiscale the codice fiscale
	 * @return the array list<Traccia>
	 */
	public ArrayList<Traccia>listaTracceArtistiDB(String CodiceFiscale);
	/**
	 * Ottenere l artista presente nel DB.
	 *
	 * @param CodiceFiscale the codice fiscale dell artista
	 * @return the Artista
	 */
	public Artista getArtista(String CodiceFiscale);
}
