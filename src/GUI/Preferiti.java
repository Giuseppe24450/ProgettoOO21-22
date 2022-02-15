package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Artista;
import Model.Cover;
import Model.Remastering;
import Model.Traccia;
import Model.TracciaOriginale;

import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.Time;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;


// TODO: Auto-generated Javadoc
/**
 * The Class Preferiti.
 */
public class Preferiti extends JFrame {
	
	/** The controller. */
	Controller controller;
	
	/** The frame. */
	JFrame frame;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The table. */
	private JTable table;
	
	/** The btn riproduci. */
	private JButton btnRiproduci;
	
	/** The btn remove pref. */
	private JButton btnRemovePref;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	/** The label. */
	private JLabel lblNewLabel;
	/** The label. */
	private JLabel lblNewLabel_1;
	
	/**
	 * Instantiates a new preferiti.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 */
	public Preferiti(Controller c,JFrame frameChiamante){
		setResizable(false);
		controller=c;
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {                
                return false; } 
		};
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
		table.setBounds(1, 26, 450, 0);
		table.setShowGrid(false);
		table.setBackground(new Color(218, 112, 214));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceFiscale", "Versione", "Titolo", "Durata", "NomeArtista", "CognomeArtista", "Tipologia","Album"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(6).setPreferredWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(121);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		TableColumn col=table.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);
		
		ArrayList<Cover>listacover=new ArrayList<Cover>();
		listacover=controller.getpreferitiCover();
		ArrayList<Remastering>listaremastering=new ArrayList<Remastering>();
		listaremastering=controller.getpreferitiRemastering();
		ArrayList<TracciaOriginale>listatracciaoriginale=new ArrayList<TracciaOriginale>();
		listatracciaoriginale=controller.getpreferitiTracciaOriginale();
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		
		
		for(TracciaOriginale tr:listatracciaoriginale) {
			model.addRow(new Object[] {tr.getArtista().getcodicefiscale(),tr.getversione(),tr.getTitolo(),tr.getDurata(),tr.getArtista().getNome(),tr.getArtista().getCognome(),"Traccia Originale",tr.getAlbum().getNome()});
		
}
			
		for(Cover cov:listacover) {
			model.addRow(new Object[] {cov.getArtista().getcodicefiscale(),cov.getversione(),cov.getTitolo(),cov.getDurata(),cov.getArtista().getNome(),cov.getArtista().getCognome(),"Cover",cov.getAlbum().getNome()});
	
		}
		
		for(Remastering r:listaremastering) {
			model.addRow(new Object[] {r.getArtista().getcodicefiscale(),r.getversione(),r.getTitolo(),r.getDurata(),r.getArtista().getNome(),r.getArtista().getCognome(),"Remastering",r.getAlbum().getNome()});	
			}
		
		contentPane.setLayout(null);
		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 33, 666, 366);
		getContentPane().add(scrollPane);
		
		JButton btnTermina = new JButton("Ritorna al men\u00F9");
		btnTermina.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTermina.setBounds(52, 471, 196, 23);
		btnTermina.setBackground(new Color(255, 255, 255));
		btnTermina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(btnTermina);
		
		btnRiproduci = new JButton("Riproduci");
		btnRiproduci.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRiproduci.setBounds(295, 471, 118, 23);
		btnRiproduci.setBackground(new Color(255, 255, 255));
		btnRiproduci.setEnabled(false);
		btnRiproduci.setToolTipText("Seleziona traccia dalla tabella");
		
		btnRiproduci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!btnRiproduci.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Seleziona dalla tabella!");
				}
				else {
				int riga=table.getSelectedRow();
				String versione=new String(table.getModel().getValueAt(riga, 1).toString());
				Date v=Date.valueOf(versione);
				String titolo=new String(table.getModel().getValueAt(riga, 2).toString());
				String orario=new String(table.getModel().getValueAt(riga, 3).toString());
				String nomeAlbum=new String(table.getModel().getValueAt(riga, 7).toString());
				Time t=Time.valueOf(orario);
				String codfiscaleArtista=new String(table.getModel().getValueAt(riga, 0).toString());
				Artista art=controller.getArtista(codfiscaleArtista);
				Model.Album alb=new Model.Album(nomeAlbum,v, titolo, t, art);
				Traccia trac=new Traccia(v,titolo,t,art,alb);
				int ora=t.getHours();
				int minuti=t.getMinutes();
				int secondi=t.getSeconds();
				JFrame frameRiproduzione;
				
					frameRiproduzione = new Riproduzione(controller,frame,ora,minuti,secondi,trac);
					
				frame.setVisible(false);
				frameRiproduzione.setVisible(true);
			}}
		});
		contentPane.add(btnRiproduci);
		
		btnRemovePref = new JButton("Rimuovi Preferiti");
		btnRemovePref.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemovePref.setBounds(492, 471, 140, 23);
		btnRemovePref.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!btnRemovePref.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Seleziona traccia dalla tabella!");
				}
				else {
				int riga=table.getSelectedRow();
				DefaultTableModel model=(DefaultTableModel) table.getModel();

				String versione=new String(table.getModel().getValueAt(riga, 1).toString());
				Date v=Date.valueOf(versione);
				String titolo=new String(table.getModel().getValueAt(riga, 2).toString());
				String orario=new String(table.getModel().getValueAt(riga, 3).toString());
				String codfiscaleArtista=new String(table.getModel().getValueAt(riga, 0).toString());
				String nomeAlbum=new String(table.getModel().getValueAt(riga, 7).toString());
				Time t=Time.valueOf(orario);
				Artista art=controller.getArtista(codfiscaleArtista);
				Model.Album alb= new Model.Album(nomeAlbum,v, titolo, t, art);
				Traccia trac=new Traccia(v,titolo,t,art,alb);
			
				model.removeRow(riga);
				controller.deletepreferiti(trac);
				
				
			}}
		});
		btnRemovePref.setEnabled(false);
		btnRemovePref.setToolTipText("Seleziona traccia dalla tabella");
		btnRemovePref.setBackground(new Color(255, 255, 255));
		contentPane.add(btnRemovePref);
		
		lblNewLabel = new JLabel("");
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/imgascolto.png"));
		lblNewLabel.setIcon(Immagineicona);
		lblNewLabel.setBounds(0, 33, 689, 502);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Preferiti");
		lblNewLabel_1.setForeground(new Color(255, 0, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(267, 0, 172, 25);
		contentPane.add(lblNewLabel_1);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						btnRiproduci.setEnabled(true);
						btnRemovePref.setEnabled(true);
						btnRiproduci.setToolTipText("Riproduci!");
						btnRemovePref.setToolTipText("Rimuovi");
						}
		});
	
	}
}
