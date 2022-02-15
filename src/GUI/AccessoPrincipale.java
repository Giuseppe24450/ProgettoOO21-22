package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class AccessoPrincipale.
 */
public class AccessoPrincipale extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

/** The controller. */
Controller controller;

/** The frame. */
JFrame frame;
	
	/**
	 * Instantiates a new accesso principale.
	 *
	 * @param c the controller
	 */
	public AccessoPrincipale(Controller c) {
		controller=c;
		initialize();
		frame.setVisible(true);}
	
	/**
	 * Initialize.
	 */
	public void initialize() {
		
		frame=this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setForeground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Scegli l'accesso desiderato!");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(287, 11, 247, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Accesso Utente");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameAccesso=new AccessoUtente(controller);
				frame.setVisible(false);
				frameAccesso.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(65, 105, 225));
		btnNewButton.setIcon(null);
		btnNewButton.setBounds(37, 306, 479, 192);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Accesso Amministratore");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame frameAccessoAmministratore=new AccessoAmministratore(controller);
				frame.setVisible(false);
				frameAccessoAmministratore.setVisible(true);
			}
		});
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setIcon(null);
		btnNewButton_1.setBounds(28, 62, 488, 203);
		contentPane.add(btnNewButton_1);
		
		JLabel lbladmin = new JLabel("");
		lbladmin.setBounds(493, 62, 210, 203);
		contentPane.add(lbladmin);
		lbladmin.setIcon(new ImageIcon(this.getClass().getResource("/Image/admin.png")));
		
		JLabel lblutente = new JLabel("");
		lblutente.setBounds(506, 306, 253, 192);
		contentPane.add(lblutente);
		lblutente.setIcon(new ImageIcon(this.getClass().getResource("/Image/omino2.png")));
	}
}
