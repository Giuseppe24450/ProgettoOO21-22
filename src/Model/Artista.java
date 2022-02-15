package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Artista.
 */
public class Artista {

/** The nome. */
private String nome;

/** The cognome. */
private String cognome;

/** The Codice fiscale. */
private String CodiceFiscale;

/** The tracce. */
private ArrayList<Traccia>tracce;

/**
 * Instantiates a new artista.
 *
 * @param n the nome
 * @param c the cognome
 * @param Codice the codice fiscale
 */
public Artista(String n,String c,String Codice,Date v,String t,Time d,String nomealbum){
	this.nome=n;
	this.cognome=c;
	this.CodiceFiscale=Codice;
	Album a=new Album(nomealbum,v,t,d,this);
	inserisci(a,v,t,d,this);
}

/**
 * L artista deve realizzare almeno una traccia.
 *
 * @param a the album della traccia
 * @param v the versione della traccia
 * @param t the titolo della traccia
 *  @param d the durata della traccia
 *  @param ar the artista  della traccia
 */
public void inserisci(Album a,Date v,String t,Time d,Artista ar) {
	tracce=new ArrayList<Traccia>();
	Traccia trac=new Traccia(v,t,d,ar,a);
	addTracce(trac);
}
/**
 * Aggiungere  tracce.
 *
 * @param t the traccia
 */
public void addTracce(Traccia t) {
	int trovato=0;
	for(Traccia tr:getTracce()) {
		 if(tr.getversione().equals(t.getversione())&&tr.getTitolo().contentEquals(t.getTitolo())&&t.getDurata().equals(tr.getDurata())&&tr.getArtista().getcodicefiscale().contentEquals(t.getArtista().getcodicefiscale())){
			trovato=1;}
		 }
	if(trovato==0)
		tracce.add(t);
	
}

/**
 * Ottenere le tracce.
 *
 * @return the array list<Traccia>
 */
public ArrayList<Traccia> getTracce(){
	return tracce;
}

/**
 * Ottenere il nome.
 *
 * @return the nome
 */
public String getNome() {
	return nome;
}

/**
 * Ottenere il cognome.
 *
 * @return the cognome
 */
public String getCognome() {
	return cognome;
}

/**
 * Ottenere il codicefiscale.
 *
 * @return the codicefiscale
 */
public String getcodicefiscale() {
	return CodiceFiscale;
}
}
