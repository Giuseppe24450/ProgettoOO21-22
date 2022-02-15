package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.Utente;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class VisualizzaUtenti.
 */
public class VisualizzaUtenti extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

/** The frame. */
JFrame frame;

/** The controller. */
Controller controller;

/** The table. */
private JTable table;

/** The scroll pane. */
private JScrollPane scrollPane;
/** The button. */
private JButton btnNewButton_1;
/** The text. */
private JTextField textCerca;
/** The panel. */
private JPanel panel;
/** The label. */
private JLabel lblNewLabel;
/** The label. */
private JLabel lblNewLabel_1;
	
	/**
	 * Instantiates a new visualizza utenti.
	 *
	 * @param c the controller
	 * @param frameChiamante the frame chiamante
	 */
	public VisualizzaUtenti(Controller c,JFrame frameChiamante) {
		setResizable(false);
		controller=c;
		frame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable() {public boolean isCellEditable(int row, int column) {                
            return false; }};
		table.setBackground(new Color(224, 255, 255));
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setFont(new Font("Times New Roman", Font.BOLD, 11));
		table.setBounds(1, 26, 503, 0);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NickName", "Nome", "Cognome"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		contentPane.add(table, BorderLayout.CENTER);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 27, 600, 186);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Ritorna al menù");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setBounds(135, 529, 282, 23);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameChiamante.setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		
		
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 255)));
		panel.setBounds(121, 454, 325, 49);
		contentPane.add(panel);
		textCerca = new JTextField();
		panel.add(textCerca);
		textCerca.setToolTipText("Inserire il nickname");
		textCerca.setColumns(10);
		btnNewButton_1 = new JButton("Cerca");
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		//verifica la presenza o assenza di un utente
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textCerca.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(frame,"Immetti il nickname");}
				else {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				for(int i=model.getRowCount()-1;i>=0;i--) {
					String nickname=new String(table.getModel().getValueAt(i, 0).toString());
					if(!(nickname.contentEquals(textCerca.getText())))
						model.removeRow(i);
				}}}
			
		});
		btnNewButton_1.setBackground(Color.CYAN);
		
		lblNewLabel = new JLabel("");
		ImageIcon Immagineicona=new ImageIcon(this.getClass().getResource("/Image/utenti.png"));
		lblNewLabel.setIcon(Immagineicona);
		lblNewLabel.setBounds(0, 87, 600, 563);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Utenti");
		lblNewLabel_1.setForeground(new Color(0, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(258, 2, 159, 23);
		contentPane.add(lblNewLabel_1);
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		ArrayList<Utente> listautente=controller.getListaUtentiDB();
		for(Utente u:listautente) {
			model.addRow(new Object[] {u.getnickname(),u.getNome(),u.getCognome()});
			
		}
	}

}
