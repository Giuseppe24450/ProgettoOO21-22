package Model;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Utente.
 */
public class Utente {

/** The Nick name. */
private String NickName;

/** The nome. */
private String nome;

/** The cognome. */
private String cognome;

/** The preferiti. */
private  ArrayList<Traccia> preferiti;

/** The ascolti. */
private ArrayList<Ascolto>ascolti;

/**
 * Instantiates a new utente.
 *
 * @param name the nome
 * @param cogn the cognome
 * @param nick the nick name
 */
public Utente(String name,String cogn,String nick){
	NickName=nick;
	nome=name;
	cognome=cogn;
	preferiti=new ArrayList<Traccia>();
	ascolti=new ArrayList<Ascolto>();
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
 * Ottenere il nickname.
 *
 * @return the nickname
 */
public String getnickname() {
	return NickName;
}

/**
 * Aggiungere preferiti.
 *
 * @param t the traccia
 */
public void AggiungerePreferiti(Traccia t) {
	preferiti.add(t);
}

/**
 * Rimuovere preferiti.
 *
 * @param t the Traccia
 */
public void RimuoverePreferiti(Traccia t) {
	Traccia tr;
	for(int i=(preferiti.size()-1);i>=0;i--) {
		 tr=preferiti.get(i);
		 if(tr.getversione().equals(t.getversione())&&tr.getTitolo().contentEquals(t.getTitolo())&&t.getDurata().equals(tr.getDurata())&&tr.getArtista().getcodicefiscale().contentEquals(t.getArtista().getcodicefiscale())){
			 preferiti.remove(i);
			 break;}
		 }
}

/**
 * Ottenere gli ascolti.
 *
 * @return the array list<Ascolto>
 */
public ArrayList<Ascolto> getascolti(){
	return ascolti;
}
/**
 * Ottenere i preferiti dell'utente
 * * @return the array list<Traccia>
 */
public ArrayList<Traccia> getpreferiti() {
	return preferiti;
}
}
