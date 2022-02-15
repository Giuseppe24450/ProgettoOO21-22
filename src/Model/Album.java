package Model;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
// TODO: Auto-generated Javadoc

/**
 * The Class Album.
 */
public class Album {
	
	/** The tracce. */
	private ArrayList<Traccia> tracce;
	
	/** The nome. */
	private String nome;
	
	/**
	 * Instantiates a new album.
	 *
	 * @param name the nome dell album
	 * @param v the versione della traccia
	 * @param t the titolo della traccia
	 * @param d the durata della traccia
	 * @param ar the artista della traccia
	 */
	public Album (String name,Date v,String t,Time d,Artista ar){
		this.nome=name;
		inserisci(this,v,t,d,ar);
		
	}
	/**
	 *Un album deve contenere almeno una traccia.
	 * @param a the Album
	 * @param v the versione della traccia
	 * @param t the titolo della traccia
	 * @param d the durata della traccia
	 * @param ar the artista della traccia
	 */
	public void inserisci(Album a,Date v,String t,Time d,Artista ar) {
		tracce=new ArrayList<Traccia>();
		Traccia trac=new Traccia(v,t,d,ar,a);
		addTracce(trac);
	}
	
	/**
	 * Aggiungere  tracce.
	 *
	 * @param t the Traccia
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
	 * Ottenere il nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Ottenere le tracce.
	 *
	 * @return the array list<Traccia>
	 */
	public ArrayList<Traccia> getTracce(){
		return tracce;
	}
	
}
