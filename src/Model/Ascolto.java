package Model;

import java.time.LocalDateTime;
// TODO: Auto-generated Javadoc

/**
 * The Class Ascolto.
 */
public class Ascolto {

/** The data. */
private LocalDateTime data;

/** The utente. */
private Utente utente;

/** The traccia. */
private Traccia traccia;

/**
 * Instantiates a new ascolto.
 *
 * @param l the data dell ascolto
 * @param u the utente
 * @param t the Traccia
 */
public Ascolto(LocalDateTime l,Utente u,Traccia t){
	data=l;
	utente=u;
	traccia=t;
}

/**
 * Ottenere la data.
 *
 * @return the data
 */
public LocalDateTime getdata() {
	return data;
}

/**
 * Ottenere la traccia.
 *
 * @return the traccia
 */
public Traccia getTraccia() {
	return traccia;
}

/**
 * Ottenere l' utente.
 *
 * @return the utente
 */
public Utente getUtente() {
	return utente;
}

}
