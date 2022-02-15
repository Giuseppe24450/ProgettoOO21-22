package GUI;

import java.awt.BorderLayout;

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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

// TODO: Auto-generated Javadoc
/**
 * The Class TracceDiArtista.
 */
public class TracceDiArtista extends JFrame {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The frame. */
	JFrame frame;
	
	/** The trovato. */
	int trovato=0;
	
	/** The controller. */
	Controller controller;
	
	/** The table. */
	private JTable table;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	/** The table. */
	private JTable table_3;
	/** The panel. */
	private JPanel panel_1;

	
	/**
	 * Instantiates a new tracce di artista.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 * @param ar the artista
	 * @param ruolo the ruolo
	 */
	public TracceDiArtista(Controller c,JFrame frameChiamante,Artista ar,String Ruolo) {
		setResizable(false);
		controller=c;
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664,392);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(10, 322, 634, 33);
		contentPane.add(panel);
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
                return false; } 
		};
		table.setBounds(1, 26, 632, 0);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
		if(Ruolo.contentEquals("Utente")) {
		JButton btnRiproduci = new JButton("Riproduci");
		
		btnRiproduci.setBackground(new Color(255, 165, 0));
		btnRiproduci.setBounds(302, 0, 105, 23);
		btnRiproduci.setEnabled(false);
		btnRiproduci.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!btnRiproduci.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Ricordati di selezionare prima");
				}
				else {
				int riga=table.getSelectedRow();
				String versione=new String(table.getModel().getValueAt(riga, 0).toString());
				Date v=Date.valueOf(versione);
				String titolo=new String(table.getModel().getValueAt(riga, 1).toString());
				String durata=new String(table.getModel().getValueAt(riga, 2).toString());
				String nomeAlbum=new String(table.getModel().getValueAt(riga, 4).toString());
				Time t=Time.valueOf(durata);
				Model.Album alb=new Album(nomeAlbum,v, titolo, t, ar);
				Traccia trac=new Traccia(v,titolo,t,ar,alb);
				int ora=t.getHours();
				int minuti=t.getMinutes();
				int secondi=t.getSeconds();
				JFrame frameRiproduzione;
				frameRiproduzione = new Riproduzione(controller,frame,ora,minuti,secondi,trac);
				frame.setVisible(false);
				frameRiproduzione.setVisible(true);
			}}
		});
		panel.add(btnRiproduci);
		
		JButton btnAggiungiPreferiti = new JButton("Aggiungi Preferiti");
		btnAggiungiPreferiti.setBackground(new Color(255, 165, 0));
		btnAggiungiPreferiti.setEnabled(false);
		btnAggiungiPreferiti.setBounds(122, 0, 170, 23);
		btnAggiungiPreferiti.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!btnAggiungiPreferiti.isEnabled()) {
					JOptionPane.showMessageDialog(frame,"Ricordati di selezionare prima");
				}else {
				int riga=table.getSelectedRow();
				String versione=new String(table.getModel().getValueAt(riga, 0).toString());
				String titolo=new String(table.getModel().getValueAt(riga, 1).toString());
				String durata=new String(table.getModel().getValueAt(riga, 2).toString());
				String nomeAlbum=new String(table.getModel().getValueAt(riga, 4).toString());
				Time d=Time.valueOf(durata);
				Date v=Date.valueOf(versione);
				Model.Album alb=new Album(nomeAlbum,v, titolo, d, ar);
				Traccia trac=new Traccia(v,titolo,d,ar,alb);
					controller.aggiungipreferiti(trac);
			}}
		});
		panel.add(btnAggiungiPreferiti);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAggiungiPreferiti.setEnabled(true);
				btnRiproduci.setEnabled(true);
			}
		});
		}
		
		JButton btnNewButton_2 = new JButton("OK");
		btnNewButton_2.setBounds(519, 0, 105, 23);
		btnNewButton_2.setBackground(new Color(255, 165, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Tracce");
		lblNewLabel_1.setForeground(new Color(255, 0, 51));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(10, 4, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		table.setBackground(new Color(224, 255, 255));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Versione", "Titolo", "Durata", "Tipologia","Album"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class,String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		DefaultTableModel model= (DefaultTableModel) table.getModel();
	
		ArrayList<TracciaOriginale>listatracciaoriginale=new ArrayList<TracciaOriginale>();
		listatracciaoriginale=controller.listaTracciaOriginale();
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		for(Traccia tr:ar.getTracce()) {
			
			for(TracciaOriginale t:listatracciaoriginale) {
			if(tr.getversione().equals(t.getversione())&&tr.getTitolo().contentEquals(t.getTitolo())&&tr.getDurata().equals(t.getDurata())&&
					tr.getArtista().getNome().contentEquals(t.getArtista().getNome())&&tr.getArtista().getCognome().contentEquals(t.getArtista().getCognome())
					&&t.getAlbum().getNome().contentEquals(tr.getAlbum().getNome())) {
              trovato=1;
				model.addRow(new Object[] {t.getversione(),t.getTitolo(),t.getDurata(),"Traccia Originale",t.getAlbum().getNome()});
			}
			
			if(trovato==0) {
				for(Cover cov:t.getCover()) {
					if(tr.getversione().equals(cov.getversione())&&tr.getTitolo().contentEquals(cov.getTitolo())&&tr.getDurata().equals(cov.getDurata())&&
							tr.getArtista().getNome().contentEquals(cov.getArtista().getNome())&&tr.getArtista().getCognome().contentEquals(cov.getArtista().getCognome())
							&&tr.getAlbum().getNome().contentEquals(cov.getAlbum().getNome())) {
		                trovato=1;
					model.addRow(new Object[] {cov.getversione(),cov.getTitolo(),cov.getDurata(),"Cover",cov.getAlbum().getNome()});
					}
					}
				}
			if(trovato==0) {
				for(Remastering rem:t.getRemastering()) {
					if(tr.getversione().equals(rem.getversione())&&tr.getTitolo().contentEquals(rem.getTitolo())&&tr.getDurata().equals(rem.getDurata())&&
							tr.getArtista().getNome().contentEquals(rem.getArtista().getNome())&&tr.getArtista().getCognome().contentEquals(rem.getArtista().getCognome())
							&&tr.getAlbum().getNome().contentEquals(rem.getAlbum().getNome())) {
		                trovato=1;
					model.addRow(new Object[] {rem.getversione(),rem.getTitolo(),rem.getDurata(),"Remastering",rem.getAlbum().getNome()});
			}
					}}
		if(trovato==1) break;
		}
			trovato=0;}
		
		contentPane.add(table, BorderLayout.CENTER);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 31, 634, 220);
		getContentPane().add(scrollPane);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 240));
		panel_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 0, 0)));
		panel_1.setBounds(-9, 345, 653, 21);
		contentPane.add(panel_1);
		JTextArea textArea = new JTextArea();
		textArea.setToolTipText("Immetti Titolo");
		textArea.setBounds(283, 272, 79, 22);
		contentPane.add(textArea);
		JButton btnNewButton = new JButton("Cerca");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textArea.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame,"Immetti il titolo");}
				else {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				for(int i=model.getRowCount()-1;i>=0;i--) {
					String titolo=new String(table.getModel().getValueAt(i, 1).toString());
					if(!(titolo.contentEquals(textArea.getText())))
						model.removeRow(i);}}
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(383, 273, 89, 23);
		contentPane.add(btnNewButton);
	
		
		ArrayList<String> codici=new ArrayList<String>();
		ArrayList<Album>a=controller.albumArtisti(codici, ar.getcodicefiscale());
		if(a.size()!=0) {
			setBounds(100, 100, 664, 645);
		table_3 = new JTable();
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int riga=table_3.getSelectedRow();
				Model.Album al=controller.getAlbum(((String) table_3.getModel().getValueAt(riga,1)));
				controller.setlistatracceAlbum(al,new String(table_3.getModel().getValueAt(riga,1).toString()));	
				JFrame frameTracceDiAlbum=new TracceDiAlbum(controller,frame,al,Ruolo);
			frame.setVisible(false);
			frameTracceDiAlbum.setVisible(true);
			}
		});
		table_3.setToolTipText("Seleziona per visualizzare le tracce");
		table_3.setBackground(new Color(224, 255, 255));
		table_3.setFont(new Font("Times New Roman", Font.BOLD, 11));
		table_3.setShowVerticalLines(false);
		table_3.setShowHorizontalLines(false);
		table_3.setShowGrid(false);
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Codice"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_3.getColumnModel().getColumn(0).setPreferredWidth(87);
		table_3.setBounds(796, 136, -39, -54);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane(table_3);
		scrollPane_1.setBounds(10, 383, 265, 214);
		if(Ruolo.contentEquals("Utente")) {
			TableColumn col=table_3.getColumnModel().getColumn(1);
			col.setMinWidth(0);
			col.setMaxWidth(0);
			col.setPreferredWidth(0);}
		getContentPane().add(scrollPane_1);
		
		JLabel lblNewLabel = new JLabel("Album");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 351, 89, 14);
		contentPane.add(lblNewLabel);
		JTextPane textPane = new JTextPane();
		textPane.setBounds(352, 498, 79, 20);
		contentPane.add(textPane);
		textPane.setToolTipText("Immetti il nome");
		
		JButton btnNewButton_1 = new JButton("Cerca");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textPane.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame,"Immetti il nome");}
				else {
				DefaultTableModel model=(DefaultTableModel) table_3.getModel();
				for(int i=model.getRowCount()-1;i>=0;i--) {
					String titolo=new String(table_3.getModel().getValueAt(i, 0).toString());
					if(!(titolo.contentEquals(textPane.getText())))
						model.removeRow(i);}}
			}
		});
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setBounds(441, 495, 89, 23);
		contentPane.add(btnNewButton_1);
		
		DefaultTableModel model2= (DefaultTableModel) table_3.getModel();
		for(int i=0;i<a.size();i++) {
			model2.addRow(new Object[] {a.get(i).getNome(),codici.get(i)});
		}
		}
		}
	}
