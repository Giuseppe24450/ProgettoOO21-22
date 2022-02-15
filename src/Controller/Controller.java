package Controller;


import java.util.ArrayList;


import DAO.AdminDAO;
import DAO.AlbumDAO;
import DAO.ArtistaDAO;
import DAO.AscoltoDAO;
import DAO.PreferitiDAO;
import DAO.TracciaDAO;

import DAO.UtenteDAO;
import Model.Album;
import Model.Artista;
import Model.Ascolto;
import Model.Cover;
import Model.Remastering;
import Model.Traccia;
import Model.TracciaOriginale;
import Model.Utente;
import ImplementazioniPostgresDAO.AdminImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.AlbumImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.ArtistaImplementazionePostgresDAO;
import ImplementazioniPostgresDAO.AscoltoImplementazionePostgresDAO;

import ImplementazioniPostgresDAO.PreferitiImplementazionePostgresDAO;

import ImplementazioniPostgresDAO.TracciaImplementazionePostgresDAO;

import ImplementazioniPostgresDAO.UtenteImplementazionePostgresDAO;
// TODO: Auto-generated Javadoc

/**
 * The Class Controller.
 */
public class Controller{

/** The utente. */
Utente utente;

/**
 * Accesso.
 *
 * @param NickName the nick name
 * @param Password the password
 * @return the string
 */
public String validit‡NickNamePassword(String NickName,String Password) {
	String errore=new String("OK");
	if(NickName.length()<3)
		return "Il Nickname non ha lunghezza valida(almeno 3 caratteri).";
	if(((!NickName.matches("(([a-zA-Z].*([0-9]|[a-zA-Z]))|([0-9].*([a-zA-Z]).*([0-9]|[a-zA-Z])))"))&&NickName.matches("[0-9]+"))||NickName.contains(" ")||!NickName.matches(".*[a-zA-Z].*"))
		return "Nickname non valido!";
			if(Password.length()<=4)
			return "La password non ha lunghezza valida";
			if(Password.contains(" "))
				return "password non valida";

			
	return errore;
}

/**
 * Consentire accesso admin.
 *
 * @param Codice the codice
 * @param password the password
 * @return the string
 */
public String consentireaccessoAdmin(String Codice,String password) {
	String messaggio= new String("");
	AdminDAO l=new AdminImplementazionePostgresDAO();
	messaggio=l.controllovalidit‡AccessoDB(Codice,password);
		return messaggio;		
}

/**
 * Accesso utente.
 *
 * @param NickName the nick name
 * @param Password the password
 * @return the string
 */
public String accessoutente(String NickName,String Password) {
	String messaggio= new String("");
	UtenteDAO l=new UtenteImplementazionePostgresDAO();
	ArrayList<Utente> ut=l.listautentiDB();
	messaggio=l.controllovalidit‡AccessoDB(NickName,Password);
	if(messaggio.contentEquals("accesso consentito")) {
		for(Utente t:ut) {
			if(t.getnickname().contentEquals(NickName)) {
		utente=new Utente(t.getNome(),t.getCognome(),NickName);
		ArrayList<Ascolto> ascoltiDB=new ArrayList<Ascolto>();
		ascoltiDB=getascoltiDB();
		
		for(Ascolto a:ascoltiDB)
			setascoltiutente(a);
		ArrayList<Traccia>T=getpreferitiTracce();
		for(Traccia tr:T)
			utente.AggiungerePreferiti(tr);
		
		return "OK";}
			}
		}
	
	if(messaggio.contentEquals("utente non presente"))
	return "l utente "+NickName+" non Ë registrato";
	
	if(messaggio.contentEquals("password errata"))
		return "password errata";	
	return messaggio;
	
}
/**
 * Setta inizialmente gli ascolti di un utente
 *@param asc the ascolto
 */
public void  setascoltiutente(Ascolto asc) {
	getascolto().add(asc);
}

/**
 * Lista tracce originali dal DB,settaggio dei remastering/cover di una traccia originale e viceversa.
 *
 * @return the array list<TracciaOriginale>
 */
public ArrayList<TracciaOriginale> listaTracciaOriginale(){
	TracciaDAO l=new TracciaImplementazionePostgresDAO();
	ArrayList<TracciaOriginale> tracce=new ArrayList<TracciaOriginale>();
	for(TracciaOriginale t:l.getTracciaOriginale()) {
	
		ArrayList<Remastering>r=l.recuperaRemasteringdellaTracciaOriginale(t);
		ArrayList<Cover> c=l.recuperaCoverdellaTracciaOriginale(t);
		
		for(Remastering rem:r) {
			rem.setTracciaoriginale(t);
			t.addRemastering(rem);
		}
		
		for(Cover cov:c) {
			cov.setTracciaoriginale(t);
			t.addCover(cov);
		}
		tracce.add(t);
	}
	return tracce;}


/**
 * Setta le tracce degli album.
 *
 * @param a the Album
 * @param codice the codice dell album
 */
public void  setlistatracceAlbum(Album a,String codice){
	
	AlbumDAO l=new AlbumImplementazionePostgresDAO();
	for(Traccia t:l.getTracceAlbum(codice,a.getNome())) {
		a.addTracce(t);
		}
	
	}

/**
 * Lista identificativi-album(codici) dal DB.
 * @return the array list<String>
 */
public ArrayList<String> listaidentificativialbum(){
	AlbumDAO a=new AlbumImplementazionePostgresDAO ();
	return a.listaidentificativialbumDB();
}
/**
 * Lista album.
 * @return the array list<Album>
 */
public ArrayList<Album> listalbum(){
	AlbumDAO a=new AlbumImplementazionePostgresDAO ();
	return a.listalbumDB();
}

/**
 * Lista artisti presenti nel DB.
 *
 * @return the array list<Artista>
 */
public ArrayList<Artista> listartista(){
	ArtistaDAO a=new ArtistaImplementazionePostgresDAO ();
	return a.listartistiDB();
}

/**
 * Ottenere la lista delle cover preferite dell utente dal db.
 *
 * @return the array list<Cover>
 */
public ArrayList<Cover> getpreferitiCover(){
	PreferitiDAO l=new PreferitiImplementazionePostgresDAO();
	ArrayList<Cover> l2=l.listapreferitiCoverDB(utente.getnickname());
	return l2;
}

/**
 * Ottenere la lista dei remastering preferiti dell utente dal DB.
 *
 * @return the array list<Remastering>
 */
public ArrayList<Remastering> getpreferitiRemastering(){
	PreferitiDAO l=new PreferitiImplementazionePostgresDAO();
	ArrayList<Remastering> l2=l.listapreferitiRemasteringDB(utente.getnickname());
	
	return l2;
}

/**
 * Ottenere la lista delle tracce originali preferite dell utente dal DB.
 *
 * @return the array list<TracciaOriginale>
 */
public ArrayList<TracciaOriginale> getpreferitiTracciaOriginale(){
	PreferitiDAO l=new PreferitiImplementazionePostgresDAO();
	ArrayList<TracciaOriginale> l2=l.listapreferitiTracciaOriginaleDB(utente.getnickname());
	return l2;
}
/**
 * Ottenere la lista delle tracce preferite dell utente dal DB indipendentemente dalla tipologia di quest'ultime.
 *
 * @return the array list<Traccia>
 */
public ArrayList<Traccia> getpreferitiTracce(){
	PreferitiDAO l=new PreferitiImplementazionePostgresDAO();
	ArrayList<Traccia> l2=l.listapreferitiTracceDB(utente.getnickname());
	return l2;
}
/**
 * Ottenere la lista dei preferiti dell utente.
 *
 * @return the array list<Traccia>
 */
public ArrayList<Traccia> getPreferiti(){
	return utente.getpreferiti();
}

/**
 * Aggiungi preferiti alla lista-preferiti dell utente ed aggiorna anche la lista-preferiti del DB.
 *
 * @param tr the Traccia
 */
public void aggiungipreferiti(Traccia tr) {
	PreferitiDAO l=new PreferitiImplementazionePostgresDAO();
	int trovato=0;
	
	for(Traccia s:getPreferiti()) {
		if(s.getTitolo().contentEquals(tr.getTitolo())&&(s.getversione().equals(tr.getversione()))&&s.getAlbum().getNome().contentEquals(tr.getAlbum().getNome())&&(s.getDurata().equals(tr.getDurata()))&&(s.getArtista().getcodicefiscale().contentEquals(tr.getArtista().getcodicefiscale()))){
			trovato=1;
		}}
	if(trovato==0) {
	utente.AggiungerePreferiti(tr);
	l.inserisciDB(utente.getnickname(),tr.getversione(),tr.getTitolo(),tr.getDurata(),tr.getArtista(),tr.getAlbum().getNome());
	}}

/**
 * Controllo registrazione dell utente.
 *
 * @param Nome the nome
 * @param Cognome the cognome
 * @param NickName the nick name
 * @param Password the password
 * @return the string
 */
public String controlloregistrazione(String Nome, String Cognome, String NickName, String Password) {
	String errore= new String("OK");
	UtenteDAO l=new UtenteImplementazionePostgresDAO();
	ArrayList<Utente> ut=l.listautentiDB();
	for(Utente t:ut) {
		if(t.getnickname().contentEquals(NickName)) {
			return "NickName gi‡ presente";
		}}
	if(Nome.contentEquals(""))
		return "Inserire il nome";
	if(Nome.contains(" "))
		return "Il nome non puÚ contenere spazi";
	if(Nome.length()<2)
		return "Nome di lunghezza errata(almeno lunghezza 2);";
	if(!Nome.matches("[a-zA-Z]+")&&(Nome.matches(".*[0-9].*")||(!Nome.contains("Ë")&&!Nome.contains("È")&&
			!Nome.contains("Ú")&&!Nome.contains("˘")&&!Nome.contains("‡")&&!Nome.contains("Á")&&!Nome.contains("Ï"))
			)) {
return "Nome non valido";
	}
	if(Cognome.contentEquals(""))
		return "Inserire il cognome";
	if(Cognome.contains(" "))
		return "Il cognome non puÚ contenere spazi";
	if(Cognome.length()<3)
		return "Cognome di lunghezza errata(almeno lunghezza 3);";
	if(!Cognome.matches("[a-zA-Z]+")&&(Cognome.matches(".*[0-9].*")||(!Cognome.contains("Ë")&&!Cognome.contains("È")&&
			!Cognome.contains("Ú")&&!Cognome.contains("˘")&&!Cognome.contains("‡")&&!Cognome.contains("Á")&&!Cognome.contains("Ï"))
			)) {
return "Cognome non valido";
	}
	
	String messaggio=validit‡NickNamePassword(NickName,Password);
	if(messaggio.contentEquals("OK")) {
	utente=new Utente(Nome,Cognome,NickName);
	l.inserisciDB(NickName, Nome, Cognome, Password);}
	else {
	return messaggio;}
	return errore;

}

/**
 * Ottenere l' utente.
 *
 * @return the utente
 */
public Utente getUtente() {
	return utente;}

/**
 * Ottenere la lista di utenti presenti nel DB
 *
 * @return the array list<Utente>
 */
public ArrayList<Utente> getListaUtentiDB(){
	UtenteDAO l=new UtenteImplementazionePostgresDAO();
return 	l.listautentiDB();
}

/**
 * Setta la lista delle tracce di un artista
 *
 * @param a the Artista
 */
public void setlistaTracceArtista(Artista a) {
	ArtistaDAO art=new ArtistaImplementazionePostgresDAO();
	ArrayList<Traccia>t=art.listaTracceArtistiDB(a.getcodicefiscale());
	for(Traccia r:t)
		a.addTracce(r);
}
/**
 * Ottenere le cover di una traccia originale;
 *
 * @param tr the TracciaOriginale
 * @return the array list<Cover>
 */
public ArrayList<Cover> getCoverdellaTracciaOriginale(TracciaOriginale tr){
	return tr.getCover();
}
/**
 * Ottenere i remastering di una traccia originale;
 *
 * @param tr the TracciaOriginale
 * @return the array list<Remastering>
 */
public ArrayList<Remastering> getRemasteringdellaTracciaOriginale(TracciaOriginale tr){
	return tr.getRemastering();
}

/**
 * Aggiungi ascolto agli ascolti di un utente,aggiornando anche il DB.
 *
 * @param a the Ascolto
 */
public void aggiungiascolto(Ascolto a) {
	getascolto().add(a);
	AscoltoDAO ad=new AscoltoImplementazionePostgresDAO();
	ad.inserisciascoltoDB(a);
}
/**
 * Ottenere gli ascolti di un utente.
 *
 *
 * @return the array list<Ascolto>
 */
public ArrayList<Ascolto> getascolto(){
	return utente.getascolti();
}

/**
 * Lista ascolti degli utenti presenti nel  DB.
 *
 * @return the array list<Ascolto>
 */
public ArrayList<Ascolto> listascoltiDB(){
	AscoltoDAO a= new AscoltoImplementazionePostgresDAO();
	return a.listascoltiutentiDB();
}

/**
 * Ottenere gli ascolti,realizzati dall utente, presenti all interno del DB.
 *
 * @return the array list<Ascolto>
 */
public ArrayList<Ascolto> getascoltiDB() {
	AscoltoDAO a= new AscoltoImplementazionePostgresDAO();
	return a.listascoltoDB(utente.getNome(),utente.getCognome(),utente.getnickname());
	
}
/**
 * Ottenere le tracce di un album.
 *
 *@param a the Album
 * @return the array list<Album>
 */
public ArrayList<Traccia> getTracceAlbum(Album a){
	return a.getTracce();
}

/**
 * Elimina la traccia dalla lista preferiti,aggiornando anche il DB .
 *
 * @param trac the Traccia
 */
public void deletepreferiti(Traccia trac) {
	PreferitiDAO l=new PreferitiImplementazionePostgresDAO();
	l.deleteDB(getUtente().getnickname(),trac);
	utente.RimuoverePreferiti(trac);
	
}

/**
 * Ottenere gli album degli artisti.
 *
 * @param codiceFiscale the codice fiscale
 * @param codici the array list<String>
 * @return array list<Album>
 */
public ArrayList<Album> albumArtisti(ArrayList<String> codici,String CodiceFiscale){
	ArrayList<Album> al=new ArrayList<Album>();
	AlbumDAO l=new AlbumImplementazionePostgresDAO();
	al=l.getAlbumdiArtisti(codici,CodiceFiscale);
	return al;
	
}
/**
 *  Ottenere l album
 * @param codice the identificativo dell album
 * @return the Album 
 */
public Album getAlbum(String codice) {
	AlbumImplementazionePostgresDAO al=new AlbumImplementazionePostgresDAO();
	return al.getAlbum(codice);
}
/**
 *  Ottenere l artista
 * @param codicefiscale the codicefiscale
 * @return the Artista
 */
public Artista getArtista(String CodiceFiscale) {
	ArtistaImplementazionePostgresDAO a=new ArtistaImplementazionePostgresDAO();
	return a.getArtista(CodiceFiscale);
}
}
