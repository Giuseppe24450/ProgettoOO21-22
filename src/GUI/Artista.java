package GUI;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Controller.Controller;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class Artista.
 */
public class Artista extends JFrame {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The table. */
	private JTable table;
	
	/** The text nome. */
	private JTextField textNome;

/** The controller. */
Controller controller;

/** The frame. */
JFrame frame;

/** The text cognome. */
private JTextField textCognome;

/** The selezionanomecognome. */
boolean selezionanomecognome=false;

/** The scroll pane. */
private JScrollPane scrollPane;

/**
 * Instantiates a new artista.
 *
 * @param c the controller
 * @param frameChiamante the frame chiamante
 * @param ruolo the ruolo
 */
public Artista(Controller c,JFrame frameChiamante,String Ruolo) {
	setResizable(false);
		controller=c;
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 833, 661);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
                return false; } 
		};
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
		table.setToolTipText("Seleziona per visualizzare le tracce");
		table.setBounds(1, 26, 450, 0);
		table.setBackground(new Color(255, 99, 71));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int riga=table.getSelectedRow();
				Model.Artista ar=controller.getArtista(table.getModel().getValueAt(riga,2).toString());
				controller.setlistaTracceArtista(ar);
				JFrame frameTracceDiArtista=new TracceDiArtista(controller,frame,ar,Ruolo);
				frame.setVisible(false);
				frameTracceDiArtista.setVisible(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Cognome", "CodiceFiscale"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		if(Ruolo.contentEquals("Utente")) {
		TableColumn col=table.getColumnModel().getColumn(2);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);}
		ArrayList<Model.Artista>listart=new ArrayList<Model.Artista>();
		listart=controller.listartista();
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		for(Model.Artista a:listart) {
			model.addRow(new Object[] {a.getNome(),a.getCognome(),a.getcodicefiscale()});
		}
		
		contentPane.setLayout(null);
		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 36, 452, 265);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(25, 419, 646, 222);
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JRadioButton ButtonNome = new JRadioButton("Ricerca nome");
		ButtonNome.setBounds(256, 65, 103, 23);
		ButtonNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(ButtonNome);
		ButtonNome.setBackground(new Color(255, 255, 255));
		ButtonNome.setHorizontalAlignment(SwingConstants.CENTER);
		
		textNome = new JTextField();
		textNome.setBounds(226, 96, 96, 20);
		panel.add(textNome);
		textNome.setEnabled(false);
		textNome.setColumns(10);
		
		JButton CercaNome = new JButton("Cerca");
		CercaNome.setFont(new Font("Times New Roman", Font.BOLD, 11));
		CercaNome.setBounds(348, 95, 88, 23);
		panel.add(CercaNome);
		CercaNome.setBackground(new Color(255, 99, 71));
		CercaNome.setEnabled(false);
		
		JRadioButton ButtonCognome = new JRadioButton("Ricerca cognome");
		ButtonCognome.setBounds(256, 123, 123, 23);
		ButtonCognome.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(ButtonCognome);
		ButtonCognome.setBackground(new Color(255, 255, 255));
		ButtonCognome.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton CercaCogn = new JButton("Cerca");
		CercaCogn.setFont(new Font("Times New Roman", Font.BOLD, 11));
		CercaCogn.setBounds(348, 153, 88, 23);
		panel.add(CercaCogn);
		CercaCogn.setBackground(new Color(255, 99, 71));
		CercaCogn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				int trovato=0;
				if(textCognome.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame,"Non è stato digitato il cognome");
				}
				else{
					if(selezionanomecognome==true&&!(textNome.getText().contentEquals(""))){
				for(int i=0;i<model.getRowCount();i++) {
					
					if(((String) table.getModel().getValueAt(i,1)).contentEquals(textCognome.getText())) {
						trovato=1;
					}
					}
					}else {
						if(selezionanomecognome==false) {
						for(int i=0;i<model.getRowCount();i++) {
							
							if(((String) table.getModel().getValueAt(i,1)).contentEquals(textCognome.getText())) {
								trovato=1;
							}
						}
					}
						else {
						JOptionPane.showMessageDialog(frame,"Digitare anche il nome");	}
					}
				}
					
				if(trovato==1) {
					for(int i=(model.getRowCount()-1);i>=0;i--) {
						if(((String) model.getValueAt(i,1)).contentEquals(textCognome.getText())) {}
						else {model.removeRow(i);}
				}
			}
				
				
			}
		});
		CercaCogn.setEnabled(false);
		
		textCognome = new JTextField();
		textCognome.setBounds(226, 154, 96, 20);
		panel.add(textCognome);
		textCognome.setEnabled(false);
		textCognome.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(111, 81, 367, 32);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(447, 46, 1, 1);
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 140, 0)));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		panel_3.setBounds(111, 137, 371, 42);
		panel.add(panel_3);
		
		JRadioButton ButtonNomeCogn = new JRadioButton("Ricerca  nome-cognome");
		ButtonNomeCogn.setBounds(215, 7, 163, 23);
		panel.add(ButtonNomeCogn);
		ButtonNomeCogn.setFont(new Font("Tahoma", Font.BOLD, 11));
		ButtonNomeCogn.setBackground(new Color(255, 255, 255));
		ButtonNomeCogn.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 0, 0)));
		panel_4.setBounds(83, 24, 454, 172);
		panel.add(panel_4);
		ButtonNomeCogn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonNomeCogn.setEnabled(false);
				
				if((ButtonNome.isSelected()||ButtonCognome.isSelected())) {
					JOptionPane.showMessageDialog(frame,"Ricerca non selezionata");
				}
				else {
				selezionanomecognome=true;
				textNome.setEnabled(true);
				textCognome.setEnabled(true);
				ButtonNome.setEnabled(false);
				ButtonCognome.setEnabled(false);
				CercaNome.setEnabled(true);
				CercaCogn.setEnabled(true);}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Ritorna al men\u00F9 ");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(643, 39, 148, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setBackground(new Color(255, 99, 71));
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/ImmagineArt.png"));
		lblNewLabel.setIcon(Immagineicona);
		lblNewLabel.setBounds(296, -40, 708, 653);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Artisti");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(354, -40, 96, 102);
		contentPane.add(lblNewLabel_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		ButtonCognome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonCognome.setEnabled(false);
				if((ButtonNome.isSelected()||ButtonNomeCogn.isSelected())) {
					JOptionPane.showMessageDialog(frame,"Ricerca non selezionata");}
				else {
				textCognome.setEnabled(true);
				CercaCogn.setEnabled(true);
				ButtonNome.setEnabled(false);
				ButtonNomeCogn.setEnabled(false);
			}}
		});
		CercaNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				int trovato=0;
				if(textNome.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame,"Non è stato digitato il nome");
				}
				else{
					if(selezionanomecognome==true&&!(textCognome.getText().contentEquals(""))) {
				for(int i=0;i<model.getRowCount();i++) {
					
					if(((String) table.getModel().getValueAt(i,0)).contentEquals(textNome.getText())) {
						trovato=1;
					}}
					}
					else {
						if(selezionanomecognome==false) {
							for(int i=0;i<model.getRowCount();i++) {
								
								if(((String) table.getModel().getValueAt(i,0)).contentEquals(textNome.getText())) {
									trovato=1;
								}}
						}
						else {
						JOptionPane.showMessageDialog(frame,"Digitare anche il cognome");}
					}
					}
					
				if(trovato==1) {
					for(int i=(model.getRowCount()-1);i>=0;i--) {
						if(((String) model.getValueAt(i,0)).contentEquals(textNome.getText())) {}
						else {model.removeRow(i);}
				}
			}
				
				
					
			}
		});
		ButtonNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ButtonNome.setEnabled(false);
				if((ButtonCognome.isSelected()||ButtonNomeCogn.isSelected())) {
					JOptionPane.showMessageDialog(frame,"Ricerca non selezionata");}
				else {
				textNome.setEnabled(true);
				CercaNome.setEnabled(true);
				ButtonCognome.setEnabled(false);
				ButtonNomeCogn.setEnabled(false);}
			}
		});
	}
}
