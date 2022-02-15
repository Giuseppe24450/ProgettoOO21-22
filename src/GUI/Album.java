package GUI;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controller;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

// TODO: Auto-generated Javadoc
/**
 * The Class Album.
 */
public class Album extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

/** The controller. */
Controller controller;

/** The frame. */
JFrame frame;

/** The table. */
private JTable table;

/** The Testo album. */
private JTextField TestoAlbum;

/** The scroll pane. */
private JScrollPane scrollPane;
/** The panel. */
private JPanel panel;
/** The label. */
private JLabel lblNewLabel;
/** The label. */
private JLabel lblNewLabel_1;
/** The label. */
private JLabel lblNewLabel_2;
	
	/**
	 * Instantiates a new album.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 * @param frameChiamante the ruolo
	 */
	public Album(Controller c,JFrame frameChiamante,String Ruolo) {
		setResizable(false);
		frame=this;
		controller=c;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {                
                return false; } 
		};
		table.setForeground(new Color(0, 0, 0));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
		table.setToolTipText("Seleziona per visualizzare le tracce al suo interno");
		table.setBounds(1, 26, 450, 0);
		table.setBackground(new Color(255, 215, 0));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int riga=table.getSelectedRow();
				Model.Album al=controller.getAlbum(((String) table.getModel().getValueAt(riga,0)));
				controller.setlistatracceAlbum(al,new String(table.getModel().getValueAt(riga,0).toString()));
				
				JFrame frameTracceDiAlbum=new TracceDiAlbum(controller,frame,al,Ruolo);
			frame.setVisible(false);
			frameTracceDiAlbum.setVisible(true);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codice", "Album"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		ArrayList<Model.Album>listalb=new ArrayList<Model.Album>();
		ArrayList<String>listaiden=controller.listaidentificativialbum();
		listalb=controller.listalbum();
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		
		for(int i=0;i<listalb.size();i++) {
		
			model.addRow(new Object[] {listaiden.get(i),listalb.get(i).getNome()});
		}
		if(Ruolo.contentEquals("Utente")) {
		TableColumn col=table.getColumnModel().getColumn(0);
		col.setMinWidth(0);
		col.setMaxWidth(0);
		col.setPreferredWidth(0);}
		contentPane.setLayout(null);
		contentPane.add(table);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 159, 412, 197);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton_1 = new JButton("Ritorna al men\u00F9");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(483, 386, 155, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setBackground(new Color(255, 165, 0));
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/Albummusicale.jpg"));
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		panel.setBounds(26, 58, 332, 90);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Cerca Album");
		lblNewLabel.setBounds(125, 5, 89, 13);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 11));
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(221, 0, 109, 83);
		panel.add(lblNewLabel_1);
		
		JLabel lblimmagine = new JLabel("");
		ImageIcon Immagineicona2=new ImageIcon(this.getClass().getResource("/Image/album.png"));
		lblNewLabel_1.setIcon(Immagineicona2);
		TestoAlbum = new JTextField();
		TestoAlbum.setBounds(10, 40, 96, 20);
		panel.add(TestoAlbum);
		TestoAlbum.setColumns(10);
		
		JButton btnNewButton = new JButton("Cerca");
		btnNewButton.setBounds(135, 39, 120, 23);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(255, 140, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				int trovato=0;
				if(TestoAlbum.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame,"Non è stato digitato il nome");
				}
				else{
				for(int i=0;i<model.getRowCount();i++) {
					if(((String) table.getModel().getValueAt(i,1)).contentEquals(TestoAlbum.getText())) {
						trovato=1;
					}
					}
				if(trovato==1) {
					for(int i=(model.getRowCount()-1);i>=0;i--) {
						if(((String) model.getValueAt(i,1)).contentEquals(TestoAlbum.getText())) {}
						else {model.removeRow(i);}
				}
					}else {
						for(int i=(model.getRowCount()-1);i>=0;i--)
							model.removeRow(i);
					}
				}
		}
	});		
		lblimmagine.setBounds(-52, 33, 700, 400);
		contentPane.add(lblimmagine);
		lblimmagine.setIcon(Immagineicona);
		
		lblNewLabel_2 = new JLabel("ALBUM");
		lblNewLabel_2.setForeground(new Color(255, 215, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		lblNewLabel_2.setBounds(257, 0, 144, 32);
		contentPane.add(lblNewLabel_2);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
	}
}
