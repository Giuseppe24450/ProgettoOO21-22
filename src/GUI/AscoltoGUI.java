package GUI;

import java.awt.BorderLayout;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Ascolto;
import Model.Cover;
import Model.Remastering;
import Model.Traccia;
import Model.TracciaOriginale;
import Model.Utente;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class AscoltoGUI.
 */
public class AscoltoGUI extends JFrame {

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

/** The btn cerca. */
private JButton btnCerca;

/** The text fascia oraria. */
private JTextField textFasciaOraria;

/** The dtf. */
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

/** The panel 1. */
private JPanel panel_1;
	
	/**
	 * Instantiates a new ascolto GUI.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 * @param Ruolo the ruolo
	 */
	public AscoltoGUI(Controller c,JFrame frameChiamante,String Ruolo) {
		setResizable(false);
		frame=this;
		controller=c;
		ArrayList<Ascolto> ascolti=new ArrayList<Ascolto>();
		if(Ruolo.contentEquals("Utente")) {
		ascolti=controller.getascolto();}
		else {
		ascolti=controller.listascoltiDB();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Ritorna al menù");
		btnNewButton.setBounds(504, 441, 295, 23);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		
		if(Ruolo.contentEquals("Utente")) {
			setBounds(100, 100, 1200, 502);
		table = new JTable(){public boolean isCellEditable(int row, int column) {                
            return false; }};
            table.setShowVerticalLines(false);
    		table.setShowHorizontalLines(false);
    		table.setShowGrid(false);
    		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
		table.setBounds(1, 26, 1084, 0);
		table.setBackground(new Color(224, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DataAscolto", "Versione", "Durata", "Titolo", "NomeArtista", "CognomeArtista","Album"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class,String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(5).setPreferredWidth(119);
		contentPane.add(table, BorderLayout.NORTH);
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		scrollPane = new JScrollPane(table);
		
		scrollPane.setBounds(10, 0, 1166, 430);
		getContentPane().add(scrollPane);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 0, 1186, 464);
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/imgascolto.png"));
		lblNewLabel_2.setIcon(Immagineicona);
		contentPane.add(lblNewLabel_2);
		for(Ascolto ascolt:ascolti) {
			model.addRow(new Object[] {dtf.format(ascolt.getdata()),ascolt.getTraccia().getversione(),ascolt.getTraccia().getDurata(),ascolt.getTraccia().getTitolo(),ascolt.getTraccia().getArtista().getNome(),ascolt.getTraccia().getArtista().getCognome(),ascolt.getTraccia().getAlbum().getNome()});
		}}
		else {
			setBounds(100, 100, 1240, 604);
			
			panel_1 = new JPanel();
			panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 165, 0)));
			panel_1.setBounds(909, 441, 281, 108);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_1 = new JLabel("Utenti con maggiori ascolti della traccia");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
			lblNewLabel_1.setBounds(10, 0, 218, 43);
			panel_1.add(lblNewLabel_1);
			JButton btnNewButton_1 = new JButton("Cerca");
			btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnNewButton_1.setBackground(Color.ORANGE);
			btnNewButton_1.setBounds(162, 57, 89, 23);
			panel_1.add(btnNewButton_1);
			JTextArea textcerca2 = new JTextArea();
			textcerca2.setToolTipText("Inserisci il titolo");
			textcerca2.setBounds(43, 56, 96, 22);
			panel_1.add(textcerca2);
			btnNewButton_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(textcerca2.getText().contentEquals("")) {
						JOptionPane.showMessageDialog(frame,"Non è stato digitato il titolo");}
					else {
					
						JTable table2 = new JTable(){
							public boolean isCellEditable(int row, int column) {                
				                return false; } 
						};
					
						table2.setBounds(1, 26, 450, 0);
						table2.setBackground(new Color(224, 255, 255));
						
						table2.setModel(new DefaultTableModel(
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
						table2.getColumnModel().getColumn(6).setPreferredWidth(109);
						table2.getColumnModel().getColumn(0).setPreferredWidth(135);
						table2.getColumnModel().getColumn(2).setPreferredWidth(135);
						table2.getColumnModel().getColumn(7).setPreferredWidth(135);
						table2.getColumnModel().getColumn(4).setPreferredWidth(135);
						table2.getColumnModel().getColumn(5).setPreferredWidth(135);
						table2.setToolTipText("Seleziona la traccia per la verifica");
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

						
						
						DefaultTableModel model= (DefaultTableModel) table2.getModel();
						for(Traccia t:cov) {

							if(t.getTitolo().contentEquals(textcerca2.getText())) {
						model.addRow(new Object[] {t.getArtista().getcodicefiscale(),(t.getversione()),t.getTitolo(),t.getDurata(),t.getArtista().getNome(),t.getArtista().getCognome(),"Cover",t.getAlbum().getNome()});
					
							}
						}
						
						for(Traccia t:r) {
							if(t.getTitolo().contentEquals(textcerca2.getText())) {
							model.addRow(new Object[] {t.getArtista().getcodicefiscale(),(t.getversione()),t.getTitolo(),t.getDurata(),t.getArtista().getNome(),t.getArtista().getCognome(),"Remastering",t.getAlbum().getNome()});
							
							}
						}
						
						for(Traccia t:tr) {
							if(t.getTitolo().contentEquals(textcerca2.getText())) {
							model.addRow(new Object[] {t.getArtista().getcodicefiscale(),(t.getversione()),t.getTitolo(),t.getDurata(),t.getArtista().getNome(),t.getArtista().getCognome(),"Traccia Originale",t.getAlbum().getNome()});
							}
						}
						
						contentPane.setLayout(null);
					
					
						scrollPane = new JScrollPane(table2);
						scrollPane.setBounds(42, 10, 715, 258);
						if(table2.getRowCount()==0) {
							JOptionPane.showMessageDialog(frame,"La canzone non esiste.");
						}	
						else {
						JOptionPane.showMessageDialog(frame,table2);
				
						int riga=table2.getSelectedRow();
						if(riga==-1) {// nel caso in cui la tabella è vuota oppure non vuota ma non viene selezionata una canzone
							JOptionPane.showMessageDialog(frame,"Non hai selezionato una canzone");
						}else {
						String versione=new String(table2.getModel().getValueAt(riga, 1).toString());
						String durata=new String(table2.getModel().getValueAt(riga, 3).toString());
						String titolo=new String(table2.getModel().getValueAt(riga, 2).toString());
						String codf=new String(table2.getModel().getValueAt(riga, 0).toString());
						String nomeAlbum=new String(table2.getModel().getValueAt(riga, 7).toString());
						ArrayList<Utente> ut=controller.getListaUtentiDB();
						int conteggio=0;
						int conteggiocorrente=0;
						String Result=new String(" ");
					for(Utente u:ut) {
						for(int i=0;i<table.getModel().getRowCount();i++) {
						if(table.getModel().getValueAt(i, 2).toString().contentEquals(versione)&&table.getModel().getValueAt(i, 3).toString().contentEquals(nomeAlbum)
							&&table.getModel().getValueAt(i, 4).toString().contentEquals(durata)&&table.getModel().getValueAt(i, 5).toString().contentEquals(titolo)
							&&table.getModel().getValueAt(i, 6).toString().contentEquals(codf)&&
							table.getModel().getValueAt(i, 7).toString().contentEquals(u.getnickname())) {
							conteggiocorrente++;
						}
					}
						if(conteggio==conteggiocorrente) {
							Result=new String(Result+","+u.getnickname());
						}
						if(conteggio<conteggiocorrente) {
							conteggio=conteggiocorrente;
							Result=new String("L utente che ha effettuato più ascolti è :"+u.getnickname());
						}
						conteggiocorrente=0;
					}
					if(conteggio==0) {
						JOptionPane.showMessageDialog(frame,"la traccia non ha registrato ascolti");
					}
					else {
						JOptionPane.showMessageDialog(frame,Result);}
						
					}
				}
				
					
			}}
		});
			table = new JTable(){public boolean isCellEditable(int row, int column) {                
	            return false; }};
	            table.setShowVerticalLines(false);
	    		table.setShowHorizontalLines(false);
	    		table.setShowGrid(false);
	    		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
			table.setBounds(1, 26, 1084, 0);
			table.setBackground(new Color(224, 255, 255));
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"DataAscolto","Tipologia", "Versione", "Album","Durata", "Titolo","CodiceFiscaleArtista", "NickName","NomeArtista","CognomeArtista"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class,String.class,String.class, String.class, String.class, String.class, String.class,String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			table.getColumnModel().getColumn(5).setPreferredWidth(119);
			contentPane.add(table, BorderLayout.NORTH);
			DefaultTableModel model= (DefaultTableModel) table.getModel();
			scrollPane = new JScrollPane(table);
			
			scrollPane.setBounds(0, 0, 1216, 430);
			getContentPane().add(scrollPane);
			
			textFasciaOraria = new JTextField();
			textFasciaOraria.setToolTipText("Inserisci nickname utente");
			
			textFasciaOraria.setBounds(30, 470, 96, 20);
			contentPane.add(textFasciaOraria);
			textFasciaOraria.setColumns(10);
			
			JPanel panel = new JPanel();
			panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.ORANGE));
			panel.setBounds(20, 437, 232, 80);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Attivit\u00E0 Fascia Oraria");
			lblNewLabel.setBounds(28, 12, 150, 13);
			panel.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 11));
			
			btnCerca = new JButton("Cerca");
			btnCerca.setFont(new Font("Times New Roman", Font.BOLD, 11));
			btnCerca.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//Vengono definite quattro fasce orarie:(00:06)-(06-12)-(12-18)-(18-24)
					if(textFasciaOraria.getText().contentEquals("")) {
						JOptionPane.showMessageDialog(frame,"Non è stato digitato il nickname");
					}else {
						if(textFasciaOraria.getText().contains(" ")) {
							JOptionPane.showMessageDialog(frame," Il NickName non può contenere spazi");}
						else {
						if(((textFasciaOraria.getText().matches("(([a-zA-Z].*([0-9]|[a-zA-Z]))|([0-9].*([a-zA-Z]).*([0-9]|[a-zA-Z])))"))&&textFasciaOraria.getText().matches("[0-9]+"))||textFasciaOraria.getText().contains(" ")) {
							JOptionPane.showMessageDialog(frame,"Nickname non valido");}
						else {
						int trovato=0;
						String utente= new String("");
						String FasciaOraria=new String("");
						String FasciaOrariaAttiva=new String("");
						for(int k=0;k<table.getModel().getRowCount();k++) {
							utente=new String(table.getModel().getValueAt(k, 7).toString());
							if(utente.contentEquals(textFasciaOraria.getText())) {
								trovato=1;
							break;}
						}
						if(trovato==0) {
							int presente=0;
							ArrayList<Utente> ut=controller.getListaUtentiDB();
							for(Utente u:ut) {
								if(u.getnickname().contentEquals(textFasciaOraria.getText()))
							presente=1;
							}
							if(presente==0) {
								JOptionPane.showMessageDialog(frame,"L utente non è registrato");
							}else {
							JOptionPane.showMessageDialog(frame,"L utente non ha effettuato ascolti!");}}
						else {
					
					LocalTime orario = null;
					int conteggio=0;
					int conteggiocorrente=0;
					for(int j=0;j<4;j++) {
						
						for(int i=0;i<table.getModel().getRowCount();i++) {
							utente=new String(table.getModel().getValueAt(i, 7).toString());
							FasciaOraria=new String(table.getModel().getValueAt(i, 0).toString());
							
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
							LocalDateTime dateTime = LocalDateTime.parse(FasciaOraria, formatter);
							int h=dateTime.getHour();
							int m=dateTime.getMinute();
							int s=dateTime.getSecond();
							orario=LocalTime.of(h,m,s);
							if(j==0&&utente.contentEquals(textFasciaOraria.getText())) {
								if((orario.isBefore(LocalTime.parse("06:00:00"))&&orario.isAfter(LocalTime.parse("00:00:00"))||orario.equals("00:00:00"))) {
									conteggiocorrente++;
								}
							}
							if(j==1&&utente.contentEquals(textFasciaOraria.getText())) {
								if((orario.isBefore(LocalTime.parse("12:00:00"))&&orario.isAfter(LocalTime.parse("06:00:00"))||orario.equals("06:00:00"))) {
									conteggiocorrente++;
								}
							}
							if(j==2&&utente.contentEquals(textFasciaOraria.getText())) {
								if((orario.isBefore(LocalTime.parse("18:00:00"))&&orario.isAfter(LocalTime.parse("12:00:00"))||orario.equals("12:00:00"))) {
									conteggiocorrente++;
								}
							}
							if(j==3&&utente.contentEquals(textFasciaOraria.getText())) {
								if((orario.isBefore(LocalTime.parse("23:59:59"))&&orario.isAfter(LocalTime.parse("18:00:00"))||orario.equals("18:00:00"))) {
									conteggiocorrente++;
								}
							}
							
						}
						if(conteggio==conteggiocorrente) {
							if(j==0)
							FasciaOrariaAttiva=new String(FasciaOrariaAttiva+"e 00:00:00-06:00:00 ");
							if(j==1)
								FasciaOrariaAttiva=new String(FasciaOrariaAttiva+"e 06:00:00-12:00:00 ");
							if(j==2)
								FasciaOrariaAttiva=new String(FasciaOrariaAttiva+"e 12:00:00-18:00:00 ");
							if(j==3)
								FasciaOrariaAttiva=new String(FasciaOrariaAttiva+"e 18:00:00-24:00:00 ");
						}
						if(conteggio<conteggiocorrente) {
							conteggio=conteggiocorrente;
							if(j==0)
							FasciaOrariaAttiva=new String("L utente "+textFasciaOraria.getText()+" ha registarto più ascolti nella fascia Oraria:00:00:00-06:00:00 ");
							if(j==1)
								FasciaOrariaAttiva=new String("L utente "+textFasciaOraria.getText()+" ha registarto più ascolti nella fascia Oraria:06:00:00-12:00:00 ");
							if(j==2)
								FasciaOrariaAttiva=new String("L utente "+textFasciaOraria.getText()+" ha registarto più ascolti nella fascia Oraria:12:00:00-18:00:00 ");
							if(j==3)
								FasciaOrariaAttiva=new String("L utente "+textFasciaOraria.getText()+" ha registarto più ascolti nella fascia Oraria:18:00:00-24:00:00 ");
						}

						conteggiocorrente=0;
						
					}
					JOptionPane.showMessageDialog(frame,FasciaOrariaAttiva);	
				}
			}}}
		}
	});
			btnCerca.setBounds(141, 32, 81, 23);
			panel.add(btnCerca);
			btnCerca.setBackground(Color.ORANGE);
			
			
			ArrayList<TracciaOriginale> tr=new ArrayList<TracciaOriginale>();
			tr=controller.listaTracciaOriginale();
int trovato=0;
table.getColumnModel().getColumn(6).setPreferredWidth(109);
			for(Ascolto ascolt:ascolti) {
				for(TracciaOriginale trac:tr) {
					if(trac.getversione().equals(ascolt.getTraccia().getversione())&&trac.getTitolo().contentEquals(ascolt.getTraccia().getTitolo())&&trac.getDurata().equals(ascolt.getTraccia().getDurata())&&
							trac.getArtista().getcodicefiscale().contentEquals(ascolt.getTraccia().getArtista().getcodicefiscale())&&ascolt.getTraccia().getAlbum().getNome().contentEquals(trac.getAlbum().getNome())) {
		              trovato=1;

		              model.addRow(new Object[] {dtf.format(ascolt.getdata()),"TracciaOriginale",ascolt.getTraccia().getversione(),ascolt.getTraccia().getAlbum().getNome(),ascolt.getTraccia().getDurata(),ascolt.getTraccia().getTitolo(),ascolt.getTraccia().getArtista().getcodicefiscale(),ascolt.getUtente().getnickname(),ascolt.getTraccia().getArtista().getNome(),ascolt.getTraccia().getArtista().getCognome()});
					}
				if(trovato==0) {
					for(Cover cov:trac.getCover()) {
						if(cov.getversione().equals(ascolt.getTraccia().getversione())&&cov.getTitolo().contentEquals(ascolt.getTraccia().getTitolo())&&cov.getDurata().equals(ascolt.getTraccia().getDurata())&&
								cov.getArtista().getcodicefiscale().contentEquals(ascolt.getTraccia().getArtista().getcodicefiscale())&&ascolt.getTraccia().getAlbum().getNome().contentEquals(cov.getAlbum().getNome())) {
			              trovato=1;

			              model.addRow(new Object[] {dtf.format(ascolt.getdata()),"Cover",ascolt.getTraccia().getversione(),ascolt.getTraccia().getAlbum().getNome(),ascolt.getTraccia().getDurata(),ascolt.getTraccia().getTitolo(),ascolt.getTraccia().getArtista().getcodicefiscale(),ascolt.getUtente().getnickname(),ascolt.getTraccia().getArtista().getNome(),ascolt.getTraccia().getArtista().getCognome()});
						}
						}
					}
				if(trovato==0) {
					for(Remastering rem:trac.getRemastering()) {
						if(rem.getversione().equals(ascolt.getTraccia().getversione())&&rem.getTitolo().contentEquals(ascolt.getTraccia().getTitolo())&&rem.getDurata().equals(ascolt.getTraccia().getDurata())&&
								rem.getArtista().getcodicefiscale().contentEquals(ascolt.getTraccia().getArtista().getcodicefiscale())&&ascolt.getTraccia().getAlbum().getNome().contentEquals(rem.getAlbum().getNome())) {
			              trovato=1;

			              model.addRow(new Object[] {dtf.format(ascolt.getdata()),"Remastering",ascolt.getTraccia().getversione(),ascolt.getTraccia().getAlbum().getNome(),ascolt.getTraccia().getDurata(),ascolt.getTraccia().getTitolo(),ascolt.getTraccia().getArtista().getcodicefiscale(),ascolt.getUtente().getnickname(),ascolt.getTraccia().getArtista().getNome(),ascolt.getTraccia().getArtista().getCognome()});
						}
						}}
				if(trovato==1) break;
				}
			trovato=0;}	
			JLabel lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setBounds(-2, 30, 1216, 571);
			ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/imgascolto.png"));
			lblNewLabel_2.setIcon(Immagineicona);
			contentPane.add(lblNewLabel_2);
		}
		}
	
		
}
