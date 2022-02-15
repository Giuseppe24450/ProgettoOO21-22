package Model;
import java.sql.Date;
import java.sql.Time;

// TODO: Auto-generated Javadoc

/**
 * The Class Traccia.
 */
public class Traccia {

/** The versione(data di uscita). */
protected Date versione;

/** The Titolo. */
protected String Titolo;

/** The durata. */
protected Time durata;

/** The art. */
protected Artista artista;

/** The album */
public Album album;
/**
 * Instantiates a new traccia.
 *
 * @param v the versione
 * @param t the titolo
 * @param d the durata
 * @param ar the artista
 *  @param a the album
 * 
 */
public Traccia(Date v,String t,Time d,Artista ar,Album a){
	versione=v;
	Titolo=t;
	artista=ar;
	durata=d;
	album=a;
}
/**
 * Ottenere l'album.
 *
 * @return the album
 */
public Album getAlbum() {
	return album;
}
/**
 * Ottenere la versione.
 *
 * @return the versione
 */
public Date getversione() {
	return versione;
}

/**
 * Ottenere il titolo.
 *
 * @return the titolo
 */
public String getTitolo() {
	return Titolo;
}

/**
 * Ottenere la durata.
 *
 * @return the durata
 */
public Time getDurata() {
	return durata;
}

/**
 * Ottenere l' artista.
 *
 * @return the artista
 */
public Artista getArtista() {
	return artista;
}

}
