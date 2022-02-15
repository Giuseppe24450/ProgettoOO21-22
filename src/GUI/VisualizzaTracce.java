package GUI;


import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.Controller;
import Model.Album;
import Model.Artista;

import Model.Cover;
import Model.Remastering;
import Model.Traccia;
import Model.TracciaOriginale;


import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.Time;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class Visualizza.
 */
public class VisualizzaTracce extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

/** The frame. */
JFrame frame;

/** The controller. */
Controller controller;

/** The btn aggiungi preferiti. */
private JButton btnAggiungiPreferiti;

/** The table. */
private JTable table;

/** The btn riproduci. */
private JButton btnRiproduci;

/** The scroll pane. */
private JScrollPane scrollPane;
/** The text. */
private JTextField textCerca;
/** The label. */
private JLabel lblNewLabel_1;
/** The label. */
private JLabel lblNewLabel;
	
	/**
	 * Instantiates a new visualizza.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 * @param Ruolo the ruolo
	 */
	public VisualizzaTracce(Controller c,JFrame frameChiamante,String Ruolo) {
		setResizable(false);
			
		frame = this;
		controller=c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 635);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
                return false; } 
		};
		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setBounds(1, 26, 450, 0);
		table.setBackground(new Color(255, 240, 245));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceFiscaleArtista", "Versione", "Titolo", "Durata", "NomeArtista", "CognomeArtista", "Tipologia","Album"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class,String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(5).setPreferredWidth(109);
		TableColumn col=table.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);
		ArrayList<Cover> cov=new ArrayList<Cover>();
		ArrayList<Remastering> r=new ArrayList<Remastering>();
		ArrayList<TracciaOriginale> tr=new ArrayList<TracciaOriginale>();
		tr=controller.listaTracciaOriginale();
		for(TracciaOriginale trac:tr) {
			for(Cover cove:trac.getCover()) {
				cov.add(cove);
			}
			for(Remastering rem:trac.getRemastering()) {
				r.add(rem);
			}
		
		}

		
		
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		for(Traccia t:cov) {
		model.addRow(new Object[] {t.getArtista().getcodicefiscale(),(t.getversione()),t.getTitolo(),t.getDurata(),t.getArtista().getNome(),t.getArtista().getCognome(),"Cover",t.getAlbum().getNome()});
		}
		
		for(Traccia t:r) {
			model.addRow(new Object[] {t.getArtista().getcodicefiscale(),(t.getversione()),t.getTitolo(),t.getDurata(),t.getArtista().getNome(),t.getArtista().getCognome(),"Remastering",t.getAlbum().getNome()});
			}
		
		for(Traccia t:tr) {
			model.addRow(new Object[] {t.getArtista().getcodicefiscale(),(t.getversione()),t.getTitolo(),t.getDurata(),t.getArtista().getNome(),t.getArtista().getCognome(),"Traccia Originale",t.getAlbum().getNome()});
			}
		
		contentPane.setLayout(null);
		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 22, 891, 248);
		getContentPane().add(scrollPane);
		JButton btnMenù = new JButton("Ritorna al menù ");
		btnMenù.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMenù.setBounds(36, 559, 193, 23);
		btnMenù.setBackground(new Color(255, 0, 255));
		btnMenù.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		if(Ruolo.contentEquals("Utente")) {
		btnAggiungiPreferiti = new JButton("Aggiungi ai Preferiti");
		btnAggiungiPreferiti.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAggiungiPreferiti.setBounds(358, 559, 193, 23);
		btnAggiungiPreferiti.setBackground(new Color(255, 0, 255));
		btnAggiungiPreferiti.setEnabled(false);
		btnAggiungiPreferiti.setToolTipText("Seleziona traccia dalla tabella");
		btnAggiungiPreferiti.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!btnAggiungiPreferiti.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Seleziona traccia dalla tabella!");
				}
				else {
				int riga=table.getSelectedRow();
				String versione=new String(table.getModel().getValueAt(riga, 1).toString());
				String titolo=new String(table.getModel().getValueAt(riga, 2).toString());
				String durata=new String(table.getModel().getValueAt(riga, 3).toString());
				String nomeAlbum=new String(table.getModel().getValueAt(riga, 7).toString());
				Time d=Time.valueOf(durata);
				Date v=Date.valueOf(versione);
				Artista ar=controller.getArtista(table.getModel().getValueAt(riga, 0).toString());
		Album alb=new Album(nomeAlbum,v, titolo, d, ar);
				Traccia tr=new Traccia(v,titolo,d,ar,alb);
					controller.aggiungipreferiti(tr);
				

			}}
		});
		contentPane.add(btnAggiungiPreferiti);
		}
		
		contentPane.add(btnMenù);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(75, 0, 130)));
		panel.setBounds(308, 407, 327, 49);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnCercaTraccia = new JButton("Cerca");
		btnCercaTraccia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCercaTraccia.setBounds(206, 11, 90, 23);
		panel.add(btnCercaTraccia);
		btnCercaTraccia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textCerca.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame,"Immetti il titolo");}
				else {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				for(int i=model.getRowCount()-1;i>=0;i--) {
					String titolo=new String(table.getModel().getValueAt(i, 2).toString());
					if(!(titolo.contentEquals(textCerca.getText())))
						model.removeRow(i);
				}
			}}
		});
		btnCercaTraccia.setBackground(new Color(255, 0, 255));
		
		
		textCerca = new JTextField();
		textCerca.setBounds(30, 12, 96, 20);
		panel.add(textCerca);
		textCerca.setToolTipText("Inserire il titolo");
		textCerca.setColumns(10);
		
		
		
		
		if(Ruolo.contentEquals("Utente")) {
		btnRiproduci = new JButton("Riproduci");
		btnRiproduci.setBounds(707, 559, 161, 23);
		btnRiproduci.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRiproduci.setBackground(new Color(255, 0, 255));
		btnRiproduci.setEnabled(false);
			btnRiproduci.setToolTipText("Seleziona traccia dalla tabella");
			
		btnRiproduci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!btnRiproduci.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Seleziona traccia dalla tabella!");
				}
				else {
				int riga=table.getSelectedRow();
				String versione=new String(table.getModel().getValueAt(riga, 1).toString());
				Date v=Date.valueOf(versione);
				String titolo=new String(table.getModel().getValueAt(riga, 2).toString());
				String durata=new String(table.getModel().getValueAt(riga, 3).toString());
				Artista ar=controller.getArtista(table.getModel().getValueAt(riga, 0).toString());
				Time t=Time.valueOf(durata);
				
				String nomeAlbum=new String(table.getModel().getValueAt(riga, 7).toString());
				int ora=t.getHours();
				int minuti=t.getMinutes();
				int secondi=t.getSeconds();
				Album alb=new Album(nomeAlbum,v, titolo, t, ar);
				Traccia tr=new Traccia(v,titolo,t,ar,alb);
				JFrame frameRiproduzione;
					frameRiproduzione = new Riproduzione(controller,frame,ora,minuti,secondi,tr);
				frame.setVisible(false);
				frameRiproduzione.setVisible(true);
			}}
		});
		contentPane.add(btnRiproduci);}
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 253, 998, 358);
		contentPane.add(lblNewLabel_1);
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/imgsound.png"));
		lblNewLabel_1.setIcon(Immagineicona);
		
		lblNewLabel = new JLabel("Tracce");
		lblNewLabel.setForeground(new Color(255, 0, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(392, 0, 118, 29);
		contentPane.add(lblNewLabel);
		if(Ruolo.contentEquals("Utente")) {
	table.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			btnAggiungiPreferiti.setEnabled(true);
			btnRiproduci.setEnabled(true);
			btnRiproduci.setToolTipText("Riproduci!");
			btnAggiungiPreferiti.setToolTipText("Aggiungi");
			
		}
	});
	
}}
	}

