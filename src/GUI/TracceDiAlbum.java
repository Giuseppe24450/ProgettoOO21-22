package GUI;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Album;
import Model.Artista;
import Model.Cover;
import Model.Remastering;
import Model.Traccia;
import Model.TracciaOriginale;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class TracceDiAlbum.
 */
public class TracceDiAlbum extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/** The controller. */
	Controller controller;
	
	/** The frame. */
	JFrame frame;
	
	/** The table. */
	private JTable table;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	/** The button. */
	private JButton btnNewButton_3;
	/** The text. */
	private JTextArea textArea;
	
	/**
	 * Instantiates a new tracce di album.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 * @param l the album 
	 * @param ruolo the ruolo
	 * 
	 */
	public TracceDiAlbum(Controller c,JFrame frameChiamante,Model.Album l,String Ruolo) {
		
		setResizable(false);
		int trovato=0;
		controller=c;
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 575);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
                return false; } 
		};
		table.setBounds(1, 26, 649, 0);
		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setBackground(new Color(224, 255, 255));
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 205));
		panel.setBounds(5, 494, 651, 33);
		contentPane.add(panel);
		if(Ruolo.contentEquals("Utente")) {
		JButton btnNewButton = new JButton("Riproduci");
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.setEnabled(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!btnNewButton.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Ricordati di selezionare prima");
				}
				else {
				int riga=table.getSelectedRow();
				String versione=new String(table.getModel().getValueAt(riga, 1).toString());
				Date v=Date.valueOf(versione);
				String titolo=new String(table.getModel().getValueAt(riga, 2).toString());
				String durata=new String(table.getModel().getValueAt(riga, 3).toString());
				Artista ar=controller.getArtista(table.getModel().getValueAt(riga, 0).toString());
				Time t=Time.valueOf(durata);
				int ora=t.getHours();
				int minuti=t.getMinutes();
				int secondi=t.getSeconds();
				Traccia tr=new Traccia(v,titolo,t,ar,l);
				JFrame frameRiproduzione;
				
					frameRiproduzione = new Riproduzione(controller,frame,ora,minuti,secondi,tr);
					
				
				frame.setVisible(false);
			frameRiproduzione.setVisible(true);
			}
				}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Aggiungi ai Preferiti");
		btnNewButton_1.setBackground(new Color(255, 165, 0));
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!btnNewButton.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Ricordati di selezionare prima");
				}
				else {
				int riga=table.getSelectedRow();
				
				String versione=new String(table.getModel().getValueAt(riga, 1).toString());
				Date v=Date.valueOf(versione);
				String titolo=new String(table.getModel().getValueAt(riga, 2).toString());
				String durata=new String(table.getModel().getValueAt(riga, 3).toString());
				Time d=Time.valueOf(durata);
				Artista ar=controller.getArtista(table.getModel().getValueAt(riga, 0).toString());
		Traccia tr=new Traccia(v,titolo,d,ar,l);
					controller.aggiungipreferiti(tr);
			}}
		});
		panel.add(btnNewButton_1);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnNewButton_1.setEnabled(true);
				btnNewButton.setEnabled(true);
			}
		});}
		
		JButton btnNewButton_2 = new JButton("Ok");
		btnNewButton_2.setBackground(new Color(255, 165, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		
		panel.add(btnNewButton_2);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodiceFiscale", "Versione", "Titolo", "Durata", "NomeArtista", "CognomeArtista","Tipologia"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class, String.class, String.class, String.class,String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(108);
		table.getColumnModel().getColumn(5).setPreferredWidth(113);
		table.getColumnModel().getColumn(6).setPreferredWidth(119);
		table.getColumnModel().getColumn(4).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		TableColumn col=table.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		ArrayList<TracciaOriginale>listatracciaoriginale=new ArrayList<TracciaOriginale>();
		
		listatracciaoriginale=controller.listaTracciaOriginale();
		
		for(Traccia t:controller.getTracceAlbum(l)) {
			for(TracciaOriginale tr:listatracciaoriginale) {
				if(tr.getversione().equals(t.getversione())&&tr.getTitolo().contentEquals(t.getTitolo())&&tr.getDurata().equals(t.getDurata())&&
						tr.getArtista().getNome().contentEquals(t.getArtista().getNome())&&tr.getArtista().getCognome().contentEquals(t.getArtista().getCognome())&&t.getAlbum().getNome().contentEquals(tr.getAlbum().getNome())) {
	              trovato=1;

		model.addRow(new Object[] {t.getArtista().getcodicefiscale(),t.getversione(),t.getTitolo(),t.getDurata(),t.getArtista().getNome(),t.getArtista().getCognome(),"Traccia Originale"});
				}
				if(trovato==0) {
					for(Cover cov:controller.getCoverdellaTracciaOriginale(tr)) {
						if(t.getversione().equals(cov.getversione())&&t.getTitolo().contentEquals(cov.getTitolo())&&t.getDurata().equals(cov.getDurata())&&
								t.getArtista().getNome().contentEquals(cov.getArtista().getNome())&&t.getArtista().getCognome().contentEquals(cov.getArtista().getCognome())
								&&t.getAlbum().getNome().contentEquals(cov.getAlbum().getNome())) {
			                trovato=1;
						model.addRow(new Object[] {cov.getArtista().getcodicefiscale(),cov.getversione(),cov.getTitolo(),cov.getDurata(),cov.getArtista().getNome(),cov.getArtista().getCognome(),"Cover"});
						}
						}
					}
				if(trovato==0) {
					for(Remastering rem:controller.getRemasteringdellaTracciaOriginale(tr)) {
						if(t.getversione().equals(rem.getversione())&&t.getTitolo().contentEquals(rem.getTitolo())&&t.getDurata().equals(rem.getDurata())&&
								t.getArtista().getNome().contentEquals(rem.getArtista().getNome())&&t.getArtista().getCognome().contentEquals(rem.getArtista().getCognome())
								&&t.getAlbum().getNome().contentEquals(rem.getAlbum().getNome())) {
			                trovato=1;
						model.addRow(new Object[] {rem.getArtista().getcodicefiscale(),rem.getversione(),rem.getTitolo(),rem.getDurata(),rem.getArtista().getNome(),rem.getArtista().getCognome(),"Remastering"});
				}
						}}
			if(trovato==1) break;
				}
		trovato=0;
		}
		contentPane.add(table, BorderLayout.NORTH);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 5, 651, 366);
		getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setToolTipText("Immetti il titolo");
		textArea.setBounds(266, 411, 89, 22);
		contentPane.add(textArea);
		btnNewButton_3 = new JButton("Cerca");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textArea.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame,"Immetti il titolo");}
				else {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				for(int i=model.getRowCount()-1;i>=0;i--) {
					String titolo=new String(table.getModel().getValueAt(i, 2).toString());
					if(!(titolo.contentEquals(textArea.getText())))
						model.removeRow(i);}}
			}
		});
		btnNewButton_3.setBackground(new Color(255, 165, 0));
		btnNewButton_3.setBounds(384, 412, 89, 23);
		contentPane.add(btnNewButton_3);
		
	}

}
